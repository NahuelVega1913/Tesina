import { Component } from '@angular/core';
import { RepuestosComponent } from '../repuestos/repuestos.component';
import { ChatbotComponent } from '../chatbot/chatbot.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-inicio',
  imports: [RepuestosComponent, ChatbotComponent, RouterOutlet],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class InicioComponent {}
