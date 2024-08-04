import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventDto } from '../model/event.dto';
import { User } from '../model/user.model';
import { CategoryDto } from '../model/category.dto';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = 'http://localhost:8080/api/admin';  // API temel URL'si

  constructor(private http: HttpClient) {}

  // User Management

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/users/${id}`);
  }

  // Event Management

  createEvent(eventDto: EventDto): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/events`, eventDto, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json' 
    });
  }

  updateEvent(id: number, eventDto: EventDto): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/events/${id}`, eventDto, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json' 
    });
  }

  deleteEvent(id: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/events/${id}`, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json'
    });
  }

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/events`);
  }

  getEventById(id:number): Observable<Event> {
    return this.http.get<Event>(`${this.baseUrl}/events/${id}`);
  }

  sendJoinRequest(eventId: number, username: string): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/events/${eventId}/request/${username}`, {}, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json'
    });
  }

  // Category Management

  createCategory(categoryDto: CategoryDto): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/categories`, categoryDto, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json'
    });
  }

  deleteCategory(id: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/categories/${id}`, {
      headers: this.getAuthHeaders(),
      responseType: 'text' as 'json'
    });
  }

  // Helper method to get authentication headers
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken'); // veya token'ı uygun bir yerden alın
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
}
