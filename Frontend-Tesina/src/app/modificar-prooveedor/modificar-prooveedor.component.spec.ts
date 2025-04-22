import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarProoveedorComponent } from './modificar-prooveedor.component';

describe('ModificarProoveedorComponent', () => {
  let component: ModificarProoveedorComponent;
  let fixture: ComponentFixture<ModificarProoveedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificarProoveedorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModificarProoveedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
