import { Component } from '@angular/core';
import { AuthService, UserDto } from '../../../services/auth.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userDto: UserDto = { email: '', password: '', username:'', roles:'' }; // Kullanıcı bilgilerini içeren nesne

  constructor(private authService: AuthService, private router: Router) { }

  onLogin() {
    this.authService.authenticateUser(this.userDto).subscribe(
      (token: string) => {
        // Başarılı girişte token'ı saklayabilirsiniz
        localStorage.setItem('authToken', token);
        // Giriş başarılı ise yönlendirme
        this.router.navigate(['/home']);
      },
      (error) => {
        console.error('Giriş hatası:', error);
        // Hata mesajını kullanıcıya gösterebilirsiniz
      }
    );
  }
}
