import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../services/admin.service';
import { Event } from '../../../model/event.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from '../../../model/user.model';
import { Router, RouterModule } from '@angular/router';
import { EventUpdateComponent } from "../event-update/event-update.component";
import { EventDto } from '../../../model/event.dto';
import { EventCreateComponent } from "../event-create/event-create.component";
import { AllAuthenticateUsersServiceService } from '../../../services/all-authenticate-users-service.service';
import { Category } from '../../../model/category.model';
import { STRING_TYPE } from '@angular/compiler';

@Component({
  selector: 'app-event-management',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, EventUpdateComponent, EventCreateComponent],
  templateUrl: './event-management.component.html',
  styleUrl: './event-management.component.css'
})
export class EventManagementComponent implements OnInit{
  categories: Category[] = [];
  events: Event[] = [];
  users: User[] = [];
  selectedEventId: number | null = null;
  usernameForJoinRequest: string = '';
  selectedEvent: Event | null = null;

  constructor(private adminService: AdminService, private allService:AllAuthenticateUsersServiceService , private router: Router) {}

  ngOnInit(): void {
    this.loadEvents();
    this.loadUsers();
    this.loadCategories();
  }

  loadCategories(): void {
    this.allService.getAllCategories().subscribe(
      (categories: Category[]) => {
        this.categories = categories; 
      },
      error => console.error('Error loading categories', error)
    );
  }



  loadEvents(): void {
    this.adminService.getAllEvents().subscribe(
      (events: any[]) => this.events = events.map(event => this.convertToEvent(event)),
      error => console.error('Error loading events', error)
    );
  }

  loadUsers(): void {
    this.adminService.getAllUsers().subscribe(
      (users: User[]) => this.users = users,
      error => console.error('Error loading users', error)
    );
  }

  private convertToEvent(event: any): Event {
    return {
      ...event,
      date: new Date(event.date).toISOString(),
      completionDate: event.completionDate ? new Date(event.completionDate).toISOString() : null
    };
  }


  deleteEvent(id: number): void {
    this.adminService.deleteEvent(id).subscribe(
      response => {
        console.log('Event deleted successfully', response);
        this.loadEvents(); 
      },
      error => console.error('Error deleting event', error)
    );
  }

  sendJoinRequest(eventId: number): void {
    if (this.usernameForJoinRequest) {
      this.adminService.sendJoinRequest(eventId, this.usernameForJoinRequest).subscribe(
        response => {
          console.log('Join request sent successfully', response);
          this.usernameForJoinRequest = ''; 
        },
        error => console.error('Error sending join request', error)
      );
    } else {
      console.error('Username is required');
    }
  }
}
