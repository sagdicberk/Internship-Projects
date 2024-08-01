import { Component, OnInit } from '@angular/core';
import { EventService } from '../../service/event.service';
import { Event } from '../../model/event';
import { CommonModule, DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EventCreateComponentComponent } from '../event-create-component/event-create-component.component';
import { AlertboxComponent } from '../alertbox/alertbox.component';
import { EventDto } from '../../model/event-dto';
import { EventUpdateComponent } from "../event-update-component/event-update-component.component";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, EventCreateComponentComponent, AlertboxComponent, EventUpdateComponent],
  providers:[DatePipe]
})
export class EventListComponent implements OnInit {
  events: Event[] = [];
  allEvents: Event[] = [];
  search: string = '';
  showModal: boolean = false;
  message: string | null = null;
  alertType: 'success' | 'error' | 'warning' | 'info' = 'info';
  selectedEventId: number | null = null; 
  selectedEvent: EventDto | null =null;
  showUpdateForm: boolean = false; 

  constructor(private eventService: EventService, private router: Router, private datePipe: DatePipe ) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.eventService.getAllEvents().subscribe(data => {
      this.events = data;
      this.allEvents = data;
    }, error => {
      console.error('Error fetching events', error);
      this.displayMessage('Error fetching events', 'error');
    });
  }

  onEventCreated(): void {
    this.loadEvents(); 
    this.displayMessage('Event created successfully', 'success');
  }

  formatDate(date: Date): string {
    return this.datePipe.transform(date, 'yyyy-MM-dd') || ''; 
  }

  editEvent(id: number): void {
    this.selectedEventId = id;
    this.showUpdateForm = true;

    this.eventService.getEventById(id).subscribe(event => {
      this.selectedEvent = {
        name: event.name,
        location: event.location,
        date: event.date
      }; 
    }, error => {
      console.error('Error fetching event', error);
      this.displayMessage('Error fetching event', 'error');
    });
  }

  updateEvent(eventDto: EventDto): void {
    if (this.selectedEventId != null) {
      this.eventService.updateEvent(this.selectedEventId, eventDto).subscribe(response => {
        console.log('Event updated', response);
        this.displayMessage('Event updated successfully', 'success');
        this.loadEvents();
        this.selectedEventId = null;
        this.showUpdateForm = false;
      }, error => {
        console.error('Error updating event', error);
        this.displayMessage('Error updating event', 'error');
      });
    } else {
      console.error('No selected event ID or event data provided');
      this.displayMessage('Error updating event', 'error');
    }
  }

  closeUpdateForm(): void {
    this.showUpdateForm = false;
    this.selectedEvent = null;
  }
  

  deleteEvent(id: number): void {
    this.eventService.deleteEvent(id).subscribe(response => {
      console.log('Event deleted', response);
      this.displayMessage('Event deleted successfully','success');
      this.loadEvents(); 
    }, error => {
      console.error('Error deleting event', error);
      this.displayMessage('Error deleting event','error');
    });
  }

  joinEvent(id: number): void {
    this.eventService.joinEvent(id).subscribe(response => {
      console.log('Joined event', response);
      this.displayMessage('Successfully joined event','success');
      this.loadEvents(); 
    }, error => {
      console.error('Error joining event', error);
      this.displayMessage('Error joining event','error');
    });
  }

  onSearch(searchTerm: string): void {
    this.events = this.allEvents.filter(event =>
      event.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }

  onSearchButtonClick(): void {
    this.onSearch(this.search); 
  }

  openModal(): void {
    this.showModal = true;
    const modal = document.querySelector('.modal') as HTMLElement;
    if (modal) {
      modal.style.display = 'block'; 
    }
  }

  closeModal(): void {
    this.showModal = false;
    const modal = document.querySelector('.modal') as HTMLElement;
    if (modal) {
      modal.style.display = 'none'; 
    }
  }

  displayMessage(message: string, p0?: string) {
    this.message = message;

    setTimeout(() => {
      this.message = null;
    }, 5000);
  }
}
