import { Component, OnInit } from '@angular/core';
import { Event } from '../../../model/event.model';
import { Category } from '../../../model/category.model';
import { AllAuthenticateUsersServiceService } from '../../../services/all-authenticate-users-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule,CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  
}
