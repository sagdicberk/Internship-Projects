import { Component } from '@angular/core';
import { AuthService, UserDto } from '../../../services/auth.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  userDto: UserDto = {username:'', email: '', password: '', roles: '' }; // Kullanıcı bilgilerini içeren nesne

  constructor(private authService: AuthService, private router: Router) { }

  onRegister() {
    this.authService.registerUser(this.userDto).subscribe(
      (response: string) => {
        // Başarılı kayıt işlemi
        console.log(response);
        // Kayıt başarılı ise giriş sayfasına yönlendirme
        this.router.navigate(['/login']);
      },
      (error) => {
        // Hata durumunda hata mesajını göster
        console.error('Kayıt hatası:', error.message);
        alert('Kayıt işlemi başarısız oldu. Lütfen bilgilerinizi kontrol edin.');
      }
    );
  }
}
