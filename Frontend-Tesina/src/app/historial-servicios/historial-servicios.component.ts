import { Component, inject } from '@angular/core';
import { MercadoPagoService } from '../services/mercado-pago.service';
import { Router } from '@angular/router';
import { DatePipe, NgClass } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ServiciosService } from '../services/servicios.service';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-historial-servicios',
  imports: [DatePipe, FormsModule, NgClass],
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

  private timeChart: any;
  private revenueChart: any;
  private typeChart: any;

  page: number = 1;
  pageSize: number = 8;

  constructor(private router: Router) {}

  redirectTo(url: string, id: number) {
    localStorage.setItem('idServicio', id.toString());
    this.router.navigate([`${url}`]);
  }

  ngOnInit(): void {
    this.getVentas();
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.initCharts();
    }, 0);
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
    this.page = 1; // Reinicia a la primera página al filtrar
    this.updateCharts();
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
        this.filter();
        this.updateCharts();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  // --- CHARTS LOGIC ---
  initCharts() {
    this.initTimeChart();
    this.initRevenueChart();
    this.initTypeChart();
  }

  updateCharts() {
    this.updateTimeChart();
    this.updateRevenueChart();
    this.updateTypeChart();
  }

  // 1. Tiempo promedio de permanencia (horas) por servicio
  initTimeChart() {
    const ctx = document.getElementById('myTimeChart') as HTMLCanvasElement;
    if (!ctx) return;
    this.timeChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: [],
        datasets: [
          {
            label: 'Horas en taller',
            data: [],
            backgroundColor: 'rgba(59, 130, 246, 0.5)',
            borderColor: 'rgba(59, 130, 246, 1)',
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        plugins: { legend: { display: false } },
        scales: {
          y: {
            beginAtZero: true,
            title: { display: true, text: 'Horas' },
          },
        },
      },
    });
    this.updateTimeChart();
  }

  updateTimeChart() {
    if (!this.timeChart) return;
    // Agrupa por tipo y calcula el promedio de horas
    const tipos = ['INSPECTION', 'REPAIR', 'CUSTOMIZATION'];
    const labels = ['Inspección', 'Reparación', 'Customización'];
    const sum: { [key: string]: number } = {
      INSPECTION: 0,
      REPAIR: 0,
      CUSTOMIZATION: 0,
    };
    const count: { [key: string]: number } = {
      INSPECTION: 0,
      REPAIR: 0,
      CUSTOMIZATION: 0,
    };

    this.lstFiltered.forEach((item) => {
      if (item.dateEntry && item.dateExit && tipos.includes(item.type)) {
        const entry = new Date(item.dateEntry);
        const exit = new Date(item.dateExit);
        const diff = (exit.getTime() - entry.getTime()) / (1000 * 60 * 60); // horas
        sum[item.type] += diff;
        count[item.type]++;
      }
    });

    const data = tipos.map((tipo) =>
      count[tipo] > 0 ? Number((sum[tipo] / count[tipo]).toFixed(2)) : 0
    );

    this.timeChart.data.labels = labels;
    this.timeChart.data.datasets[0].data = data;
    this.timeChart.update();
  }

  // 2. Recaudación mensual
  initRevenueChart() {
    const ctx = document.getElementById('myChart') as HTMLCanvasElement;
    if (!ctx) return;
    this.revenueChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: [],
        datasets: [
          {
            label: 'Recaudación ($)',
            data: [],
            backgroundColor: 'rgba(16, 185, 129, 0.5)',
            borderColor: 'rgba(16, 185, 129, 1)',
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        plugins: { legend: { display: false } },
        scales: {
          y: {
            beginAtZero: true,
            title: { display: true, text: 'Pesos' },
          },
        },
      },
    });
    this.updateRevenueChart();
  }

  updateRevenueChart() {
    if (!this.revenueChart) return;
    const monthly: { [key: string]: number } = {};
    this.lstFiltered.forEach((item) => {
      if (item.dateExit && item.cost) {
        const date = new Date(item.dateExit);
        const key = `${date.getFullYear()}-${(date.getMonth() + 1)
          .toString()
          .padStart(2, '0')}`;
        monthly[key] = (monthly[key] || 0) + Number(item.cost);
      }
    });
    const labels = Object.keys(monthly).sort();
    const data = labels.map((l) => monthly[l]);
    this.revenueChart.data.labels = labels;
    this.revenueChart.data.datasets[0].data = data;
    this.revenueChart.update();
  }

  // 3. Pie chart tipo de servicio
  initTypeChart() {
    const ctx = document.getElementById('myPieChart') as HTMLCanvasElement;
    if (!ctx) return;
    this.typeChart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: ['Inspección', 'Reparación', 'Customización'],
        datasets: [
          {
            label: 'Tipo de Servicio',
            data: [0, 0, 0],
            backgroundColor: [
              'rgba(59, 130, 246, 0.7)',
              'rgba(16, 185, 129, 0.7)',
              'rgba(251, 191, 36, 0.7)',
            ],
          },
        ],
      },
      options: {
        responsive: true,
        plugins: { legend: { position: 'bottom' } },
      },
    });
    this.updateTypeChart();
  }

  updateTypeChart() {
    if (!this.typeChart) return;
    const counts = { INSPECTION: 0, REPAIR: 0, CUSTOMIZATION: 0 };
    type ServiceType = 'INSPECTION' | 'REPAIR' | 'CUSTOMIZATION';
    this.lstFiltered.forEach((item) => {
      const type = item.type as ServiceType;
      if (counts[type] !== undefined) counts[type]++;
    });
    this.typeChart.data.datasets[0].data = [
      counts.INSPECTION,
      counts.REPAIR,
      counts.CUSTOMIZATION,
    ];
    this.typeChart.update();
  }

  get pagedList() {
    const start = (this.page - 1) * this.pageSize;
    return this.lstFiltered.slice(start, start + this.pageSize);
  }

  get totalPages() {
    return Math.ceil(this.lstFiltered.length / this.pageSize);
  }

  goToPage(page: number) {
    if (page < 1 || page > this.totalPages) return;
    this.page = page;
  }

  nextPage() {
    if (this.page < this.totalPages) this.page++;
  }

  prevPage() {
    if (this.page > 1) this.page--;
  }
}
