import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-alertbox',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './alertbox.component.html',
  styleUrls: ['./alertbox.component.css']
})
export class AlertboxComponent {
  @Input() message: string | null = null;
  @Input() alertType: 'info' = 'info'; 

  closeMessageBox() {
    this.message = null;
    this.alertType = 'info'; 
  }
}
