import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { NotificacionesService } from '../services/notificaciones.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-notificaciones',
  imports: [DatePipe],
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
    this.marksAsRead();
  }

  getNotificaciones() {
    const getSubscription = this.service.getAllNotifications().subscribe({
      next: (res) => {
        this.notifications = res;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  marksAsRead() {
    const markSubscription = this.service.markAllAsRead().subscribe({
      next: (res) => {
        console.log('Todas las notificaciones marcadas como leídas');
        this.getNotificaciones(); // Actualiza la lista de notificaciones
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
