import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { HomeComponent } from './components/forAllUser/home/home.component';
import { NotificationsComponent } from './components/forAllUser/notifications/notifications.component';
import { ActivitiesComponent } from './components/forAllUser/activities/activities.component';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { UserManagementComponent } from './components/admin/user-management/user-management.component';
import { EventManagementComponent } from './components/admin/event-management/event-management.component';
import { CategoryManagementComponent } from './components/admin/category-management/category-management.component';
import { StatisticsManagementComponent } from './components/admin/statistics-management/statistics-management.component';
import { EventUpdateComponent } from './components/admin/event-update/event-update.component';
import { EventCreateComponent } from './components/admin/event-create/event-create.component';




export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: 'admin',
        component: DashboardComponent,
        children: [
          { path: 'user-management', component: UserManagementComponent },
          { path: 'event-management', component: EventManagementComponent },
          { path: 'event-create', component: EventCreateComponent },
          { path: 'event-update/:id', component: EventUpdateComponent },
          { path: 'category-management', component: CategoryManagementComponent },
          { path: 'statistics-management', component: StatisticsManagementComponent }
        ]
      },
      { path: 'notifications', component: NotificationsComponent },
      { path: 'activities', component: ActivitiesComponent }
    ]
  }
 // { path: '**', redirectTo: '/login' } // Tanımlanmamış rotalar için
];
