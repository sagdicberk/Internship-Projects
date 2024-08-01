import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventUpdateComponentComponent } from './event-update-component.component';

describe('EventUpdateComponentComponent', () => {
  let component: EventUpdateComponentComponent;
  let fixture: ComponentFixture<EventUpdateComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventUpdateComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventUpdateComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
