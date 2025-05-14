import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarInspeccionComponent } from './modificar-inspeccion.component';

describe('ModificarInspeccionComponent', () => {
  let component: ModificarInspeccionComponent;
  let fixture: ComponentFixture<ModificarInspeccionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificarInspeccionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModificarInspeccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
