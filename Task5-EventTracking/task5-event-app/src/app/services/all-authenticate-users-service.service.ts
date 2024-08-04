import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../model/category.model';
import { Notification } from '../model/notification.model';
import { Event } from '../model/event.model';

@Injectable({
  providedIn: 'root'
})
export class AllAuthenticateUsersServiceService {
  private baseUrl = 'http://localhost:8080/api/AllAuthenticateUsers';

  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken'); // JWT token'ı yerel depodan alın
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  // Get notifications
  getNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.baseUrl}/get/notifications`, {
      headers: this.getAuthHeaders()
    });
  }

  // Join an event
  joinEvent(eventId: number): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/${eventId}/join`, {}, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json'
    });
  }

  // Respond to invitation
  respondToInvitation(eventId: number, accept: boolean, notificationId: number): Observable<string> {
    // Güncellenmiş URL ile POST isteği gönderme
    return this.http.post<string>(`${this.baseUrl}/${eventId}/respond/${accept}/${notificationId}`, {}, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json'
    });
  }

  // Get all categories
  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUrl}`);
  }

  // Get upcoming events
  getUpcomingEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/upcoming`);
  }

  // Angular service method for getting events by category ID
  getUpcomingEventsByCategoryId(categoryId: number): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/category/${categoryId}/upcoming`, {
      headers: this.getAuthHeaders()
    });
  }



  // Get events in next week
  getEventsInNextWeek(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/next-week`);
  }
}
