import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarCustomizacionComponent } from './modificar-customizacion.component';

describe('ModificarCustomizacionComponent', () => {
  let component: ModificarCustomizacionComponent;
  let fixture: ComponentFixture<ModificarCustomizacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificarCustomizacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModificarCustomizacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
