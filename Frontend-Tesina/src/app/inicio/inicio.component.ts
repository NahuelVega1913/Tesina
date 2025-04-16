import { Component } from '@angular/core';
import { RepuestosComponent } from '../repuestos/repuestos.component';
import { ChatbotComponent } from '../chatbot/chatbot.component';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-inicio',
  imports: [RepuestosComponent, ChatbotComponent, RouterOutlet, RouterLink],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class InicioComponent {
  constructor(private router: Router) {}

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
}
