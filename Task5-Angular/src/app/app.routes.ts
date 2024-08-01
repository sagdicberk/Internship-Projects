import { RouterModule, Routes } from '@angular/router';

import { EventListComponent } from './components/event-list/event-list.component';
import { UserComponent } from './components/user/user.component';
import { NgModule } from '@angular/core';


export const routes: Routes = [
  { path: 'login', component: UserComponent },
  { path: 'register', component: UserComponent },
  { path: 'events', component: EventListComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }