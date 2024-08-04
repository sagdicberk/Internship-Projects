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
    const token = localStorage.getItem('authToken'); 
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  
  getNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.baseUrl}/get/notifications`, {
      headers: this.getAuthHeaders()
    });
  }

  
  joinEvent(eventId: number): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/${eventId}/join`, {}, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json'
    });
  }

  
  respondToInvitation(eventId: number, accept: boolean, notificationId: number): Observable<string> {
    
    return this.http.post<string>(`${this.baseUrl}/${eventId}/respond/${accept}/${notificationId}`, {}, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json'
    });
  }

  
  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUrl}`);
  }

  
  getUpcomingEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/upcoming`);
  }

  
  getUpcomingEventsByCategoryId(categoryId: number): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/category/${categoryId}/upcoming`, {
      headers: this.getAuthHeaders()
    });
  }



  
  getEventsInNextWeek(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/next-week`);
  }
}
