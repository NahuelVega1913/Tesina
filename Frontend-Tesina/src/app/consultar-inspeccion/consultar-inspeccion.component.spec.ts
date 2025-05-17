import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarInspeccionComponent } from './consultar-inspeccion.component';

describe('ConsultarInspeccionComponent', () => {
  let component: ConsultarInspeccionComponent;
  let fixture: ComponentFixture<ConsultarInspeccionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultarInspeccionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultarInspeccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
