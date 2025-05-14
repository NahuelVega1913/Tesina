import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarReparacionComponent } from './modificar-reparacion.component';

describe('ModificarReparacionComponent', () => {
  let component: ModificarReparacionComponent;
  let fixture: ComponentFixture<ModificarReparacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificarReparacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModificarReparacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
