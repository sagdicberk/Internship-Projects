import { Component, OnInit } from '@angular/core';
import { AllAuthenticateUsersServiceService } from '../../../services/all-authenticate-users-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Category } from '../../../model/category.model';
import { AdminService } from '../../../services/admin.service';
import { CategoryDto } from '../../../model/category.dto';

@Component({
  selector: 'app-category-management',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './category-management.component.html',
  styleUrl: './category-management.component.css'
})
export class CategoryManagementComponent implements OnInit{
  categories : Category[] = [];
  newCategoryName: string = '';
  categoryDto: CategoryDto = { name: '' }; 

  constructor(private allService:AllAuthenticateUsersServiceService, private adminService: AdminService){}

  ngOnInit(): void {
    this.loadCategories();
  }

  addCategory(): void {
    if (this.newCategoryName.trim()) {
      this.categoryDto.name = this.newCategoryName;

      this.adminService.createCategory(this.categoryDto).subscribe(
        response => {
          console.log('Category added successfully', response);
          this.loadCategories(); 
          this.newCategoryName = ''; 
        },
        error => console.error('Error adding category', error)
      );
    }
  }

  loadCategories(): void {
    this.allService.getAllCategories().subscribe(
      (categories: Category[]) => this.categories = categories,
      error => console.error('Error loading categories', error)
    );
  }

}
