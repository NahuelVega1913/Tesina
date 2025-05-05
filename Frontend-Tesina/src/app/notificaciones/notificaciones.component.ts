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
  calculateTimeDiff(date: string) {
    const currentDate = new Date();
    const notificationDate = new Date(date);
    const timeDiff = currentDate.getTime() - notificationDate.getTime();

    if (timeDiff < 60000) {
      return `${Math.floor(timeDiff / 1000)} segundos`;
    } else if (timeDiff < 3600000) {
      return `${Math.floor(timeDiff / 60000)} minutos`;
    } else if (timeDiff < 86400000) {
      return `${Math.floor(timeDiff / 3600000)} horas`;
    } else {
      return `${Math.floor(timeDiff / 86400000)} días`;
    }
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
}
