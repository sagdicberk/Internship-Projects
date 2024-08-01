import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventCreateComponentComponent } from './event-create-component.component';

describe('EventCreateComponentComponent', () => {
  let component: EventCreateComponentComponent;
  let fixture: ComponentFixture<EventCreateComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventCreateComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventCreateComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
