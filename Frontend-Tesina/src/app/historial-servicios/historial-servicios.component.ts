import { Component, inject } from '@angular/core';
import { MercadoPagoService } from '../services/mercado-pago.service';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ServiciosService } from '../services/servicios.service';

@Component({
  selector: 'app-historial-servicios',
  imports: [DatePipe, FormsModule],
  templateUrl: './historial-servicios.component.html',
  styleUrl: './historial-servicios.component.css',
})
export class HistorialServiciosComponent {
  category: string = '';
  search: string = '';
  lst: any[] = [];
  dateFrom: any;
  dateTo: any;
  lstFiltered: any[] = [];
  private service: ServiciosService = inject(ServiciosService);

  constructor(private router: Router) {}

  redirectTo(url: string, id: number) {
    localStorage.setItem('idServicio', id.toString());
    this.router.navigate([`${url}`]);
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getVentas();
  }
  filter() {
    // Filtra por fecha, categoría y búsqueda de cliente
    this.lstFiltered = this.lst.filter((item) => {
      // Usar dateExit para el filtro de fecha
      const itemDate = item.dateExit
        ? new Date(item.dateExit).setHours(0, 0, 0, 0)
        : null;
      const fromDate = this.dateFrom
        ? new Date(this.dateFrom).setHours(0, 0, 0, 0)
        : null;
      const toDate = this.dateTo
        ? new Date(this.dateTo).setHours(0, 0, 0, 0)
        : null;

      // Filtro por tipo de servicio
      const matchesCategory =
        this.category === '' || item.type === this.category;

      // Filtro por rango de fechas
      const matchesDate =
        (!fromDate || (itemDate && itemDate >= fromDate)) &&
        (!toDate || (itemDate && itemDate <= toDate));

      // Filtro por búsqueda de nombre de cliente
      const matchesSearch =
        this.search === '' ||
        (item.nombreCompleto &&
          item.nombreCompleto
            .toLowerCase()
            .includes(this.search.toLowerCase()));

      return matchesCategory && matchesDate && matchesSearch;
    });
  }
  tipo(tipo: string) {
    if (tipo == 'REPAIR') {
      return 'Reparacion';
    }
    if (tipo == 'CUSTOMIZATION') {
      return 'Customizacion';
    }
    if (tipo == 'INSPECTION') {
      return 'Inspeccion';
    }
    return 'Error';
  }

  getVentas() {
    const getSubscription = this.service.getServices().subscribe({
      next: (res) => {
        this.lst = res;
        this.lstFiltered = res;
        console.log(this.lst);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
