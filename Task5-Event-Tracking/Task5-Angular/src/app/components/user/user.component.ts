import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { UserDto } from '../../model/user-dto';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import { AlertboxComponent } from '../alertbox/alertbox.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  standalone : true,
  imports: [CommonModule, FormsModule, RouterOutlet,AlertboxComponent]
})
export class UserComponent implements OnInit {
  users: any[] = [];
  user: UserDto = { username: '', email: '', password: '', roles: '' };
  isRegisterForm = false; 
  message: string | null = null;
  alertType: 'success' | 'error' | 'warning' | 'info' = 'info';
  
  constructor(private AuthService: AuthService, private router: Router) { }

  ngOnInit(): void {
    
  }

  toggleForm() {
    this.isRegisterForm = !this.isRegisterForm;
  }

  register() {
    this.AuthService.register(this.user).subscribe(
      response => {
        console.log('User registered successfully', response);
        this.displayMessage('User registered successfully', 'success');
      },
      error => {
        console.error('Error registering user', error);
        this.displayMessage('Error registering user','error');
      }
    );
  }

  login() {
    this.AuthService.login(this.user).subscribe(
      (token: string) => {
        console.log('Token received:', token);
        localStorage.setItem('authToken', token); 
        this.router.navigate(['/events']);
      },
      (error) => {
        console.error('Error logging in user:', error);
        this.displayMessage('Error login','error');
      }
    );
  }

  displayMessage(message: string, p0?: string) {
    this.message = message;
    setTimeout(() => {
      this.message = null;
    }, 5000);
  }
}
