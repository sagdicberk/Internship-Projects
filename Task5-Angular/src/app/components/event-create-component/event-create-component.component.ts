import { Component, Output, EventEmitter } from '@angular/core';
import { EventService } from '../../service/event.service';
import { EventDto } from '../../model/event-dto';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-event-create-component',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './event-create-component.component.html',
  styleUrl: './event-create-component.component.css'
})
export class EventCreateComponentComponent {
  newEvent: EventDto = {
    name: '',
    location: '',
    date: new Date()
  };

  @Output() eventCreated = new EventEmitter<void>(); 

  constructor(private eventService: EventService) { }

  createEvent(): void {
    this.eventService.createEvent(this.newEvent).subscribe(response => {
      console.log('Event created', response);
      this.eventCreated.emit(); 
      this.closeModal();
    }, error => {
      console.error('Error creating event', error);
    });
  }

  closeModal() {
    const modal = document.querySelector('.modal') as HTMLElement;
    if (modal) {
      modal.style.display = 'none'; 
    }
  }
}
