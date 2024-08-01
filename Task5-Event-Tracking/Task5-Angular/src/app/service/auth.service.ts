import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {  map, Observable } from 'rxjs';
import { UserDto } from '../model/user-dto';



@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  register(user: UserDto): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user, { responseType: 'text' as 'json' });
  }

  login(userDto: { username: string; password: string }): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/login`, userDto, { responseType: 'text' as 'json' });
  }

  

}
