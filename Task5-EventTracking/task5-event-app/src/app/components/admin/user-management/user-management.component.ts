import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/user.model';
import { AdminService } from '../../../services/admin.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Event } from '../../../model/event.model';
import { EventDto } from '../../../model/event.dto';

@Component({
  selector: 'app-user-management',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './user-management.component.html',
  styleUrl: './user-management.component.css'
})
export class UserManagementComponent implements OnInit {
  users: User[] = [];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.adminService.getAllUsers().subscribe(
      users => this.users = users,
      error => console.error('Error loading users', error)
    );
  }
}
