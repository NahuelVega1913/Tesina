import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarProveedorComponent } from './registrar-proveedor.component';

describe('RegistrarProveedorComponent', () => {
  let component: RegistrarProveedorComponent;
  let fixture: ComponentFixture<RegistrarProveedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrarProveedorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrarProveedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
