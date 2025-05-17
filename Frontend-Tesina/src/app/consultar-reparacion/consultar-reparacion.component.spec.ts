import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarReparacionComponent } from './consultar-reparacion.component';

describe('ConsultarReparacionComponent', () => {
  let component: ConsultarReparacionComponent;
  let fixture: ComponentFixture<ConsultarReparacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultarReparacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultarReparacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
