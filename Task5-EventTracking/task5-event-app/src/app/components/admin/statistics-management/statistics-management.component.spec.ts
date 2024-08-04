import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatisticsManagementComponent } from './statistics-management.component';

describe('StatisticsManagementComponent', () => {
  let component: StatisticsManagementComponent;
  let fixture: ComponentFixture<StatisticsManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StatisticsManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StatisticsManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
