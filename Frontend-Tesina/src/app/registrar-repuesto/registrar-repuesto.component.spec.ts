import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarRepuestoComponent } from './registrar-repuesto.component';

describe('RegistrarRepuestoComponent', () => {
  let component: RegistrarRepuestoComponent;
  let fixture: ComponentFixture<RegistrarRepuestoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrarRepuestoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrarRepuestoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
