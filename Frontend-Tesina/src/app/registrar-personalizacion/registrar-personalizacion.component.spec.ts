import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarPersonalizacionComponent } from './registrar-personalizacion.component';

describe('RegistrarPersonalizacionComponent', () => {
  let component: RegistrarPersonalizacionComponent;
  let fixture: ComponentFixture<RegistrarPersonalizacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrarPersonalizacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrarPersonalizacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
