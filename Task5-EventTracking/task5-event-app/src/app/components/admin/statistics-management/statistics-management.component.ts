import { Component, OnInit } from '@angular/core';
import { Category } from '../../../model/category.model';
import { Event } from '../../../model/event.model';
import { AllAuthenticateUsersServiceService } from '../../../services/all-authenticate-users-service.service';
import { Notification } from '../../../model/notification.model';
import { AdminService } from '../../../services/admin.service';
import { User } from '../../../model/user.model';



@Component({
  selector: 'app-statistics-management',
  standalone: true,
  imports: [],
  templateUrl: './statistics-management.component.html',
  styleUrls: ['./statistics-management.component.css']
})
export class StatisticsManagementComponent implements OnInit {
  categories: Category[] = [];
  upcomingEvents: Event[] = [];
  eventsNextWeek: Event[] = [];
  notifications: Notification[] = [];
  totalUsers: number = 0;
  totalEvents: number = 0;
  totalCategories: number = 0;
  totalUpcomingEvents: number = 0;
  totalEventsNextWeek: number = 0;
  totalNotifications: number = 0;
  allEvents: any[] = [];


  constructor(private allService: AllAuthenticateUsersServiceService, private adminService: AdminService) { }

  ngOnInit(): void {
    this.loadCategories();
    this.loadUpcomingEvents();
    this.loadEventsNextWeek();
    this.loadNotifications();
    this.loadUserCount();
    this.loadEventCount();
  }

  // Load total number of users
  loadUserCount(): void {
    this.adminService.getAllUsers().subscribe(
      (users: User[]) => this.totalUsers = users.length,
      error => console.error('Error loading total users', error)
    );
  }

  // Load total number of events
  loadEventCount(): void {
    this.adminService.getAllEvents().subscribe(
      (events: any[]) => {
        this.allEvents = events; // Tüm etkinlikleri sakla
        this.totalEvents = events.length; // Etkinliklerin toplam sayısını al
      },
      error => console.error('Error loading total events', error) // Hata mesajını göster
    );
  }

  // Load total number of categories
  loadCategories(): void {
    this.allService.getAllCategories().subscribe(
      (categories: Category[]) => {
        this.categories = categories;
        this.totalCategories = categories.length;
      },
      error => console.error('Error loading categories', error)
    );
  }

  // Load upcoming events
  loadUpcomingEvents(): void {
    this.allService.getUpcomingEvents().subscribe(
      (events: Event[]) => {
        this.upcomingEvents = events;
        this.totalUpcomingEvents = events.length;
      },
      error => console.error('Error loading upcoming events', error)
    );
  }

  // Load events happening in the next week
  loadEventsNextWeek(): void {
    this.allService.getEventsInNextWeek().subscribe(
      (events: Event[]) => {
        this.eventsNextWeek = events;
        this.totalEventsNextWeek = events.length;
      },
      error => console.error('Error loading events next week', error)
    );
  }

  // Load total number of notifications
  loadNotifications(): void {
    this.allService.getNotifications().subscribe(
      (notifications: Notification[]) => {
        this.notifications = notifications;
        this.totalNotifications = notifications.length;
      },
      error => console.error('Error loading notifications', error)
    );
  }

  
}
