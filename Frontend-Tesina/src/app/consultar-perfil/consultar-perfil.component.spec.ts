import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarPerfilComponent } from './consultar-perfil.component';

describe('ConsultarPerfilComponent', () => {
  let component: ConsultarPerfilComponent;
  let fixture: ComponentFixture<ConsultarPerfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultarPerfilComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultarPerfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
