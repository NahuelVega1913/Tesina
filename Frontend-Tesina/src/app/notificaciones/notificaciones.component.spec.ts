import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasillaComponent } from './notificaciones.component';

describe('CasillaComponent', () => {
  let component: CasillaComponent;
  let fixture: ComponentFixture<CasillaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CasillaComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CasillaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
