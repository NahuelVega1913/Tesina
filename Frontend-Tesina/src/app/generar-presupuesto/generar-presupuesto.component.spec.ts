import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerarPresupuestoComponent } from './generar-presupuesto.component';

describe('GenerarPresupuestoComponent', () => {
  let component: GenerarPresupuestoComponent;
  let fixture: ComponentFixture<GenerarPresupuestoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenerarPresupuestoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenerarPresupuestoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
