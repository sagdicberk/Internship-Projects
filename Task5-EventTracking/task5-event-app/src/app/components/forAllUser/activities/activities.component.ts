import { Component, OnInit } from '@angular/core';
import { Category } from '../../../model/category.model';
import { Event } from '../../../model/event.model';
import { AllAuthenticateUsersServiceService } from '../../../services/all-authenticate-users-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-activities',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {
  categories: Category[] = [];
  events: Event[] = [];
  selectedCategoryId: number | null = null;

  constructor(private service: AllAuthenticateUsersServiceService) {}

  ngOnInit(): void {
    this.loadCategories();
    this.loadEvents(); // Kategori seçimine göre etkinlikleri yüklüyoruz
  }

  loadCategories(): void {
    this.service.getAllCategories().subscribe(
      categories => this.categories = categories,
      error => console.error('Error loading categories', error)
    );
  }

  loadEvents(): void {
    if (this.selectedCategoryId !== null) {
      this.loadUpcomingEventsByCategoryId(this.selectedCategoryId);
    } else {
      this.loadUpcomingEvents();
    }
  }

  loadUpcomingEvents(): void {
    this.service.getUpcomingEvents().subscribe(
      events => this.events = events,
      error => console.error('Error loading upcoming events', error)
    );
  }

  loadUpcomingEventsByCategoryId(categoryId: number): void {
    this.service.getUpcomingEventsByCategoryId(categoryId).subscribe(
      events => this.events = events,
      error => console.error('Error loading events by category', error)
    );
  }

  onCategorySelect(categoryId: number): void {
    this.selectedCategoryId = categoryId;
    this.loadEvents(); // Kategoriye göre etkinlikleri yeniden yükle
  }

  loadEventsInNextWeek(): void {
    this.service.getEventsInNextWeek().subscribe(
      events => this.events = events,
      error => console.error('Error loading events in next week', error)
    );
  }

  joinEvent(eventId: number): void {
    this.service.joinEvent(eventId).subscribe(
      response => {
        console.log('Event joined successfully:', response);
        this.loadEvents();
        // Etkinliklere katıldıktan sonra gerekli işlemler burada yapılabilir, örn. bir mesaj gösterme
      },
      error => console.error('Error joining event', error)
    );
  }
}
