import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-preguntas-frecuentes',
  imports: [NgClass, FormsModule],
  templateUrl: './preguntas-frecuentes.component.html',
  styleUrl: './preguntas-frecuentes.component.css',
})
export class PreguntasFrecuentesComponent {
  rol: any = '';
  // Estados de los acordeones para USER y para otros roles
  userOpen: boolean[] = [
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
  ];
  otherOpen: boolean[] = [
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
  ];

  ngOnInit(): void {
    this.rol = localStorage.getItem('role');
    console.log(this.rol);
  }

  toggleAccordion(index: number, isUser: boolean) {
    if (isUser) {
      this.userOpen[index] = !this.userOpen[index];
    } else {
      this.otherOpen[index] = !this.otherOpen[index];
    }
  }
}
