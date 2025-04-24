import { TestBed } from '@angular/core/testing';

import { RespuestosService } from './respuestos.service';

describe('RespuestosService', () => {
  let service: RespuestosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RespuestosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
