import { AfterViewInit, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MercadoPagoService } from '../services/mercado-pago.service';
import { DatePipe } from '@angular/common';
import Chart, { registerables } from 'chart.js/auto';
import * as XLSX from 'xlsx';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

@Component({
  selector: 'app-ventas',
  imports: [FormsModule, DatePipe],
  templateUrl: './ventas.component.html',
  styleUrl: './ventas.component.css',
})
export class VentasComponent implements AfterViewInit {
  category: string = '';
  search: string = '';
  lst: any[] = [];
  dateFrom: any;
  dateTo: any;
  lstFiltered: any[] = [];
  private service: MercadoPagoService = inject(MercadoPagoService);
  private chart: Chart | null = null;
  private pieChart: Chart | null = null;
  private countChart: Chart | null = null;

  constructor(private router: Router) {
    // Setea las fechas por defecto: de hoy a un año atrás
    const today = new Date();
    const lastYear = new Date();
    lastYear.setFullYear(today.getFullYear() - 1);

    // Formatea a 'YYYY-MM-DD'
    const format = (d: Date) =>
      d.getFullYear() +
      '-' +
      ('0' + (d.getMonth() + 1)).slice(-2) +
      '-' +
      ('0' + d.getDate()).slice(-2);

    this.dateTo = format(today);
    this.dateFrom = format(lastYear);
  }
  tipoDePago(tipo: string): string {
    switch (tipo) {
      case 'CASH':
        return 'Efectivo';
      case 'MERCADO_PAGO':
        return 'Mercado Pago';
      default:
        return 'Desconocido';
    }
  }

  ngAfterViewInit(): void {
    Chart.register(...registerables);
    this.initCountChart();
    this.initChart();
    this.initPieChart();
  }

  redirectTo(url: string, id: number) {
    localStorage.setItem('idVenta', id.toString());
    this.router.navigate([`${url}`]);
  }

  ngOnInit(): void {
    this.getVentas();
  }
  getFechaActual(): string {
    const ahora = new Date();
    const yyyy = ahora.getFullYear();
    const mm = String(ahora.getMonth() + 1).padStart(2, '0'); // meses van de 0-11
    const dd = String(ahora.getDate()).padStart(2, '0');
    return `${yyyy}-${mm}-${dd}`;
  }

  exportarExcel() {
    const tabla = document.getElementById('tabla');
    const wb = XLSX.utils.table_to_book(tabla, { sheet: 'Datos' });
    const fecha = this.getFechaActual();
    XLSX.writeFile(wb, `ventas_${fecha}.xlsx`);
  }

  exportarPDF() {
    const doc = new jsPDF();
    const fecha = this.getFechaActual();

    autoTable(doc, { html: '#tabla' }); // usa el id de tu tabla
    doc.save(`ventas_${fecha}.pdf`);
    return;
  }

  filter() {
    this.lstFiltered = this.lst.filter((item) => {
      const itemDate = item.date ? new Date(item.date) : null;
      const fromDate = this.dateFrom
        ? new Date(this.dateFrom + 'T00:00:00')
        : null;
      const toDate = this.dateTo ? new Date(this.dateTo + 'T23:59:59') : null;

      const matchesDate =
        (!fromDate || (itemDate && itemDate >= fromDate)) &&
        (!toDate || (itemDate && itemDate <= toDate));

      const matchesCategory =
        !this.category || item.typePayment === this.category;
      const matchesSearch =
        !this.search ||
        (item.user &&
          item.user.toLowerCase().includes(this.search.toLowerCase()));

      return matchesCategory && matchesDate && matchesSearch;
    });
    this.updateCountChart();
    this.updateChart();
    this.updatePieChart();
  }

  getVentas() {
    this.service.getVentas().subscribe({
      next: (res) => {
        this.lst = res;
        this.filter(); // Aplica el filtro al cargar los datos y actualiza los gráficos
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  // Gráfico de cantidad de ventas por mes
  private initCountChart() {
    const ctx = document.getElementById('myCountChart') as HTMLCanvasElement;
    if (!ctx) return;

    const { labels, data } = this.getCountChartData();

    this.countChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Cantidad de Ventas',
            data: data,
            backgroundColor: '#f59e42',
            borderColor: '#f59e42',
            fill: false,
            tension: 0.3,
            pointRadius: 4,
            pointBackgroundColor: '#f59e42',
          },
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: false },
        },
        scales: {
          y: {
            beginAtZero: true,
            title: { display: true, text: 'Cantidad' },
            ticks: { stepSize: 1 },
          },
          x: {
            title: { display: true, text: 'Mes' },
          },
        },
      },
    });
  }

  private updateCountChart() {
    if (!this.countChart) return;
    const { labels, data } = this.getCountChartData();
    this.countChart.data.labels = labels;
    this.countChart.data.datasets[0].data = data;
    this.countChart.update();
  }

  // Devuelve la cantidad de ventas por mes
  private getCountChartData() {
    const monthMap: { [key: string]: number } = {};
    this.lstFiltered.forEach((item) => {
      if (!item.date) return;
      const date = new Date(item.date);
      const key = `${('0' + (date.getMonth() + 1)).slice(
        -2
      )}/${date.getFullYear()}`;
      monthMap[key] = (monthMap[key] || 0) + 1;
    });

    const sortedKeys = Object.keys(monthMap).sort((a, b) => {
      const [ma, ya] = a.split('/').map(Number);
      const [mb, yb] = b.split('/').map(Number);
      return ya !== yb ? ya - yb : ma - mb;
    });

    const labels = sortedKeys;
    const data = sortedKeys.map((key) => monthMap[key]);
    return { labels, data };
  }

  // Inicializa el gráfico
  private initChart() {
    const ctx = document.getElementById('myChart') as HTMLCanvasElement;
    if (!ctx) return;

    const { labels, data } = this.getChartData();

    this.chart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Recaudación por mes',
            data: data,
            backgroundColor: '#3b82f6',
          },
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: false },
        },
        scales: {
          y: {
            beginAtZero: true,
            title: { display: true, text: 'Recaudación ($)' },
          },
          x: {
            title: { display: true, text: 'Mes' },
          },
        },
      },
    });
  }

  // Actualiza el gráfico con los datos filtrados
  private updateChart() {
    if (!this.chart) return;
    const { labels, data } = this.getChartData();
    this.chart.data.labels = labels;
    this.chart.data.datasets[0].data = data;
    this.chart.update();
  }

  // Obtiene los datos de recaudación por mes a partir de lstFiltered
  private getChartData() {
    // Agrupa por mes y suma el total
    const monthMap: { [key: string]: number } = {};
    this.lstFiltered.forEach((item) => {
      if (!item.date) return;
      const date = new Date(item.date);
      // Formato: MM/YYYY
      const key = `${('0' + (date.getMonth() + 1)).slice(
        -2
      )}/${date.getFullYear()}`;
      monthMap[key] = (monthMap[key] || 0) + (item.total || 0);
    });

    // Ordena los meses cronológicamente
    const sortedKeys = Object.keys(monthMap).sort((a, b) => {
      const [ma, ya] = a.split('/').map(Number);
      const [mb, yb] = b.split('/').map(Number);
      return ya !== yb ? ya - yb : ma - mb;
    });

    const labels = sortedKeys;
    const data = sortedKeys.map((key) => monthMap[key]);

    return { labels, data };
  }

  // Inicializa el gráfico de torta
  private initPieChart() {
    const ctx = document.getElementById('myPieChart') as HTMLCanvasElement;
    if (!ctx) return;

    const { labels, data } = this.getPieChartData();

    this.pieChart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Ventas por tipo',
            data: data,
            backgroundColor: [
              '#3b82f6',
              '#f59e42',
              '#10b981',
              '#ef4444',
              '#a78bfa',
              '#fbbf24',
            ],
          },
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: true, position: 'bottom' },
        },
      },
    });
  }

  // Actualiza el gráfico de torta con los datos filtrados
  private updatePieChart() {
    if (!this.pieChart) return;
    const { labels, data } = this.getPieChartData();
    this.pieChart.data.labels = labels;
    this.pieChart.data.datasets[0].data = data;
    this.pieChart.update();
  }

  // Obtiene los datos para el gráfico de torta (ventas por tipo de pago)
  private getPieChartData() {
    // El tipo de pago está en la entidad general: item.typePayment
    const typeMap: { [key: string]: number } = {};
    this.lstFiltered.forEach((venta) => {
      const tipo = venta.typePayment || 'Desconocido';
      typeMap[tipo] = (typeMap[tipo] || 0) + 1;
    });

    // Mapea los nombres para mostrar en el gráfico
    const labelMap: { [key: string]: string } = {
      CASH: 'Efectivo',
      MERCADO_PAGO: 'Mercado Pago',
    };

    const labels = Object.keys(typeMap).map((key) => labelMap[key] || key);
    const data = Object.keys(typeMap).map((key) => typeMap[key]);
    return { labels, data };
  }
}
