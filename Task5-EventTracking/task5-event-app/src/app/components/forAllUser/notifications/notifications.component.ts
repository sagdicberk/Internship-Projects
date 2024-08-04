import { Component, OnInit } from '@angular/core';
import { AllAuthenticateUsersServiceService } from '../../../services/all-authenticate-users-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Notification } from '../../../model/notification.model';

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.css'
})
export class NotificationsComponent implements OnInit {
  notifications: Notification[] = [];

  constructor(private userService: AllAuthenticateUsersServiceService) {}

  ngOnInit(): void {
    this.loadNotifications();
  }

  loadNotifications(): void {
    this.userService.getNotifications().subscribe(
      (data: Notification[]) => {
        // Debugging: Log data type and contents
        console.log('Received data:', data);
        console.log('Is array:', Array.isArray(data));
        this.notifications = data;
      },
      (error) => {
        console.error('Error fetching notifications', error);
      }
    );
  }

  respondToInvitation(eventId: number, accept: boolean, notificationId: number): void {
    this.userService.respondToInvitation(eventId, accept, notificationId).subscribe(
      (response) => {
        console.log('Response sent:', response);
        this.loadNotifications(); // Refresh notifications
      },
      (error) => {
        console.error('Error responding to invitation', error);
      }
    );
  }
}