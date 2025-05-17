import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarCustomizacionComponent } from './consultar-customizacion.component';

describe('ConsultarCustomizacionComponent', () => {
  let component: ConsultarCustomizacionComponent;
  let fixture: ComponentFixture<ConsultarCustomizacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultarCustomizacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultarCustomizacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
