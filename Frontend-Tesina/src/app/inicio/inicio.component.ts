import { Component } from '@angular/core';
import { RepuestosComponent } from '../repuestos/repuestos.component';
import { ChatbotComponent } from '../chatbot/chatbot.component';

@Component({
  selector: 'app-inicio',
  imports: [RepuestosComponent, ChatbotComponent],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class InicioComponent {}
