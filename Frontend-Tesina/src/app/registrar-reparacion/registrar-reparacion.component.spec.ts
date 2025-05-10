import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarReparacionComponent } from './registrar-reparacion.component';

describe('RegistrarReparacionComponent', () => {
  let component: RegistrarReparacionComponent;
  let fixture: ComponentFixture<RegistrarReparacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrarReparacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrarReparacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
