import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RutaProhibidaComponent } from './ruta-prohibida.component';

describe('RutaProhibidaComponent', () => {
  let component: RutaProhibidaComponent;
  let fixture: ComponentFixture<RutaProhibidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RutaProhibidaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RutaProhibidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
