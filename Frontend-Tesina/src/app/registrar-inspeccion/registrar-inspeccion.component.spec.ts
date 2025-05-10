import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarInspeccionComponent } from './registrar-inspeccion.component';

describe('RegistrarInspeccionComponent', () => {
  let component: RegistrarInspeccionComponent;
  let fixture: ComponentFixture<RegistrarInspeccionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrarInspeccionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrarInspeccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
