import { Component } from '@angular/core';

@Component({
  selector: 'app-preguntas-frecuentes',
  imports: [],
  templateUrl: './preguntas-frecuentes.component.html',
  styleUrl: './preguntas-frecuentes.component.css',
})
export class PreguntasFrecuentesComponent {
  rol: any = '';

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.rol = localStorage.getItem('role');
    console.log(this.rol);
  }
}
