import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from '../model/event';
import { EventDto } from '../model/event-dto';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private apiUrl = 'http://localhost:8080/api/events';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken');
    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  createEvent(eventDto: EventDto): Observable<any> {
    return this.http.post(`${this.apiUrl}`, eventDto, { headers: this.getAuthHeaders(), responseType: 'text' as 'json' });
  }

  updateEvent(id: number, eventDto: EventDto): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, eventDto, { headers: this.getAuthHeaders(), responseType: 'text' as 'json' });
  }

  deleteEvent(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, { headers: this.getAuthHeaders(), responseType: 'text' as 'json' });
  }

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.apiUrl, { headers: this.getAuthHeaders() });
  }

  getEventById(id: number): Observable<Event> {
    return this.http.get<Event>(`${this.apiUrl}/${id}`, { headers: this.getAuthHeaders() });
  }

  joinEvent(eventId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${eventId}/join`, {}, { headers: this.getAuthHeaders(), responseType: 'text' as 'json'  });
  }
}
