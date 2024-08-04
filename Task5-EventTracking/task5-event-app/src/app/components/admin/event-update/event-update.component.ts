import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

import { AdminService } from '../../../services/admin.service';
import { AllAuthenticateUsersServiceService } from '../../../services/all-authenticate-users-service.service';
import { Category } from '../../../model/category.model';
import { Event } from '../../../model/event.model';
import { EventDto } from '../../../model/event.dto';

@Component({
  selector: 'app-event-update',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './event-update.component.html',
  styleUrls: ['./event-update.component.css']
})
export class EventUpdateComponent implements OnInit {
  eventId: number | null = null;
  event: EventDto = {
    header: '',
    description: '',
    date: new Date(),
    completionDate: new Date(),
    categoryId: 0
  };
  categories: Category[] = [];

  constructor(
    private adminService: AdminService,
    private allService: AllAuthenticateUsersServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.eventId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCategories();
    if (this.eventId) {
      this.loadEvent(this.eventId);
    }
  }

  loadCategories(): void {
    this.allService.getAllCategories().subscribe(
      (categories: Category[]) => this.categories = categories,
      error => console.error('Error loading categories', error)
    );
  }

  loadEvent(eventId: number): void {
    this.adminService.getEventById(eventId).subscribe(
      (event: any) => this.event = event,
      error => console.error('Error loading event', error)
    );
  }

  onSubmit(): void {
    if (this.eventId !== null) {
      this.adminService.updateEvent(this.eventId, this.event).subscribe(
        response => {
          console.log('Event updated successfully', response);
          this.router.navigate(['/admin/event-management']); // Güncellemeden sonra event management sayfasına yönlendirme
        },
        error => console.error('Error updating event', error)
      );
    }
  }
}
