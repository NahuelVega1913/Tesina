import { Component, inject } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { ChatbotService } from '../services/chatbot.service';

@Component({
  selector: 'app-chatbot',
  imports: [FormsModule],
  templateUrl: './chatbot.component.html',
  styleUrl: './chatbot.component.css',
})
export class ChatbotComponent {
  private service: ChatbotService = inject(ChatbotService);

  mensajesBot: any[] = [];
  mensajesUsuario: any[] = [];
  mensajeUsuario: string = '';

  agregarMensajeUsuario() {
    this.mensajesUsuario.push(this.mensajeUsuario);
    this.getResponse();
    this.mensajeUsuario = '';
  }
  agregarMensajeBot() {
    this.mensajesUsuario.push('Mensaje de Bot');
  }

  getResponse() {
    const message = {
      message: this.mensajeUsuario,
    };
    this.service.registerProveedor(this.mensajeUsuario).subscribe({
      next: (res) => {
        console.log(res);
        this.mensajesBot.push(res.toString());
        this.mensajeUsuario = '';
      },
      error: (err) => {
        console.error(err);
      },
    });
  }
}
