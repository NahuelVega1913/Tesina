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
    this.getVentas();
  }
  filter() {
    this.lstFiltered = this.lst.filter((item) => {
      // Solo muestra los servicios retirados
      if (item.status !== 'WITHDRAW') return false;

      // Filtro por categoría
      if (this.category && item.type !== this.category) return false;

      // Filtro por búsqueda de cliente
      if (
        this.search &&
        (!item.nombreCompleto ||
          !item.nombreCompleto
            .toLowerCase()
            .includes(this.search.toLowerCase()))
      )
        return false;

      // Filtro por fechas
      // dateExit viene como "2025-04-15T09:00:00"
      const itemDate = item.dateExit ? new Date(item.dateExit) : null;
      if (this.dateFrom) {
        // dateFrom viene como "YYYY-MM-DD"
        const fromDate = new Date(this.dateFrom + 'T00:00:00');
        if (!itemDate || itemDate < fromDate) return false;
      }
      if (this.dateTo) {
        // dateTo viene como "YYYY-MM-DD"
        const toDate = new Date(this.dateTo + 'T23:59:59');
        if (!itemDate || itemDate > toDate) return false;
      }

      return true;
    });
    console.log('La lista filtrada es:');
    console.log(this.lstFiltered);
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
    this.service.getServices().subscribe({
      next: (res) => {
        this.lst = res;
        this.filter(); // Aplica los filtros después de cargar los datos
        console.log(this.lst);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
