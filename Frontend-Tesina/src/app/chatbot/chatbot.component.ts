import { Component } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-chatbot',
  imports: [FormsModule],
  templateUrl: './chatbot.component.html',
  styleUrl: './chatbot.component.css',
})
export class ChatbotComponent {
  mensajesBot: any[] = [
    'Hola, como estas?',
    'Excelente!',
    'En que puedo ayudarte?',
    'Que tal?',
  ];
  mensajesUsuario: any[] = [
    'hola',
    'bien y vos?',
    'Tengo una consulta',
    'que tal?',
  ];
  mensajeUsuario: string = '';

  agregarMensajeUsuario() {
    this.mensajesUsuario.push(this.mensajeUsuario);
  }
  agregarMensajeBot() {
    this.mensajesUsuario.push('Mensaje de Bot');
  }
}
