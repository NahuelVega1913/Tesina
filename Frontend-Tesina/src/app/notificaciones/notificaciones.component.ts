import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { NotificacionesService } from '../services/notificaciones.service';

@Component({
  selector: 'app-notificaciones',
  imports: [],
  templateUrl: './notificaciones.component.html',
  styleUrl: './notificaciones.component.css',
})
export class CasillaComponent {
  private service: NotificacionesService = inject(NotificacionesService);
  // Aquí puedes inyectar tu servicio de notificaciones si lo tienes

  constructor(private router: Router) {}
  notifications: any[] = [];

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getNotificaciones();
  }
  getNotificaciones() {
    // Aquí puedes implementar la lógica para obtener las notificaciones
    // Por ejemplo, podrías hacer una llamada a un servicio para obtener las notificaciones desde un backend
    console.log('Obteniendo notificaciones...');
  }
}
