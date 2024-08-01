import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EventDto } from '../../model/event-dto';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-event-update-component',
  imports: [CommonModule, FormsModule],
  standalone: true,
  templateUrl: './event-update-component.component.html',
  styleUrl: './event-update-component.component.css'
})
export class EventUpdateComponent implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  @Input() event: EventDto | null = null;
  @Output() update = new EventEmitter<EventDto>();
  @Output() close = new EventEmitter<void>();

  saveChanges(): void {
    if (this.event) {
      this.update.emit(this.event);
    }
  }

  cancel(): void {
    this.close.emit();
  }
}