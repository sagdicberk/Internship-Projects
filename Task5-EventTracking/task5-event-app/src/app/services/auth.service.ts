import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UserDto {
  email:string;
  username: string;
  password: string;
  roles:string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth'; 

  constructor(private http: HttpClient) { }

  // Kullanıcı kaydı
  registerUser(userDto: UserDto): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/register`, userDto, { responseType: 'text' as 'json' });
  }

  // Kullanıcı girişi
  authenticateUser(userDto: UserDto): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/login`, userDto, { responseType: 'text' as 'json' });
  }
}
