import { Component, OnInit } from '@angular/core';
import { EventDto } from '../../../model/event.dto';
import { AdminService } from '../../../services/admin.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AllAuthenticateUsersServiceService } from '../../../services/all-authenticate-users-service.service';
import { Category } from '../../../model/category.model';

@Component({
  selector: 'app-event-create',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent implements OnInit {
  event: EventDto = {
    header: '',
    description: '',
    date: new Date(),
    completionDate: new Date(),
    categoryId: 0 // Initialize with a default value
  };
  categories: Category[] = [];
  loading: boolean = false;
  errorMessage: string = '';

  constructor(private adminService: AdminService, private allService: AllAuthenticateUsersServiceService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.allService.getAllCategories().subscribe(
      (categories: Category[]) => this.categories = categories,
      error => console.error('Error loading categories', error)
    );
  }

  onSubmit(): void {
    this.loading = true;
    this.adminService.createEvent(this.event).subscribe(
      response => {
        console.log('Event created successfully', response);
        this.resetForm();
        this.loading = false;
        // Optionally, navigate to another page or display a success message
      },
      error => {
        console.error('Error creating event', error);
        this.errorMessage = 'An error occurred while creating the event. Please try again.';
        this.loading = false;
      }
    );
  }

  resetForm(): void {
    this.event = {
      header: '',
      description: '',
      date: new Date(),
      completionDate: new Date(),
      categoryId: 0
    };
  }
}
