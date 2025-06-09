import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EmpleadosService } from '../services/empleados.service';
import { ServiciosService } from '../services/servicios.service';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-empleados',
  imports: [FormsModule],
  templateUrl: './empleados.component.html',
  styleUrl: './empleados.component.css',
})
export class EmpleadosComponent {
  category: string = '';
  search: string = '';
  lst: any[] = [];
  lstServices: any[] = [];
  lstFiltered: any[] = [];

  page: number = 1;
  pageSize: number = 6;

  private service: EmpleadosService = inject(EmpleadosService);
  private serviceService: ServiciosService = inject(ServiciosService);

  private timeChart: any;
  private salaryChart: any;
  private jornadaChart: any;

  constructor(private router: Router) {}

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

  filter() {
    if (!this.search) {
      this.lstFiltered = this.lst;
      this.page = 1;
      return;
    }
    const searchLower = this.search.toLowerCase();
    this.lstFiltered = this.lst.filter((item) =>
      (item.fullName || '').toLowerCase().includes(searchLower)
    );
    this.page = 1;
  }
  ngOnInit(): void {
    this.getEmployees();
    this.getVentas();
    console.log(this.lstServices);
  }

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
  moveTo(url: string, id: number) {
    localStorage.setItem('idEmpleado', id.toString());
    this.router.navigate([`${url}`]);
  }

  getEmployees() {
    this.service.getEmployees().subscribe({
      next: (res) => {
        this.lst = res;
        this.lstFiltered = res;
        // No inicialices los gráficos aquí
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  getVentas() {
    this.serviceService.getServices().subscribe({
      next: (res) => {
        this.lstServices = res;
        this.filter();
        setTimeout(() => {
          this.initCharts();
        }, 0);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  // --- CHARTS LOGIC ---
  initCharts() {
    this.initTimeChart();
    this.initSalaryChart();
    this.initJornadaChart();
  }

  updateCharts() {
    this.updateTimeChart();
    this.updateSalaryChart();
    this.updateJornadaChart();
  }
  sendMensaje(numero: string) {
    const url = `https://wa.me/${numero}?text=hola`;
    window.open(url, '_blank');
  }
  // 1. Tiempo promedio de finalización de trabajos por empleado
  initTimeChart() {
    const ctx = document.getElementById('myTimeChart') as HTMLCanvasElement;
    if (!ctx) return;
    if (this.timeChart) {
      this.timeChart.destroy();
    }
    this.timeChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: [],
        datasets: [
          {
            label: 'Horas promedio por trabajo',
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
    const labels: string[] = [];
    const data: number[] = [];

    // Solo considera servicios terminados (con dateExit) y con fechas válidas y coherentes
    this.lst.forEach((emp) => {
      const serviciosEmpleado = this.lstServices.filter(
        (serv) =>
          Array.isArray(serv.empleados) &&
          serv.empleados.some((e: any) => e.id === emp.id) &&
          serv.dateEntry &&
          serv.dateExit &&
          serv.dateExit !== null &&
          serv.dateExit !== undefined &&
          serv.dateExit !== ''
      );

      let totalHoras = 0;
      let count = 0;
      serviciosEmpleado.forEach((serv) => {
        const entry =
          typeof serv.dateEntry === 'string'
            ? new Date(serv.dateEntry)
            : serv.dateEntry;
        const exit =
          typeof serv.dateExit === 'string'
            ? new Date(serv.dateExit)
            : serv.dateExit;
        // Solo sumar si exit es posterior a entry
        if (
          entry instanceof Date &&
          exit instanceof Date &&
          !isNaN(entry.getTime()) &&
          !isNaN(exit.getTime()) &&
          exit.getTime() > entry.getTime()
        ) {
          totalHoras += (exit.getTime() - entry.getTime()) / (1000 * 60 * 60);
          count++;
        }
      });

      if (count > 0) {
        const promedio = totalHoras / count;
        labels.push(emp.fullName);
        data.push(Number(promedio.toFixed(2)));
      }
    });

    this.timeChart.data.labels = labels;
    this.timeChart.data.datasets[0].data = data;
    this.timeChart.update();
  }

  // 2. Gráfico de barras de salarios
  initSalaryChart() {
    const ctx = document.getElementById('mySalaryChart') as HTMLCanvasElement;
    if (!ctx) return;
    this.salaryChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: [],
        datasets: [
          {
            label: 'Salario',
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
            title: { display: true, text: 'Salario ($)' },
          },
        },
      },
    });
    this.updateSalaryChart();
  }

  updateSalaryChart() {
    if (!this.salaryChart) return;
    const labels: string[] = [];
    const data: number[] = [];
    this.lst.forEach((emp) => {
      labels.push(emp.fullName);
      data.push(emp.salary || 0);
    });
    this.salaryChart.data.labels = labels;
    this.salaryChart.data.datasets[0].data = data;
    this.salaryChart.update();
  }

  // 3. Gráfico de torta de jornada laboral
  initJornadaChart() {
    const ctx = document.getElementById('myJornadaChart') as HTMLCanvasElement;
    if (!ctx) return;
    if (this.jornadaChart) {
      this.jornadaChart.destroy();
    }
    this.jornadaChart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: [],
        datasets: [
          {
            label: 'Jornada',
            data: [],
            backgroundColor: [
              'rgba(59, 130, 246, 0.7)',
              'rgba(16, 185, 129, 0.7)',
              'rgba(251, 191, 36, 0.7)',
              'rgba(239, 68, 68, 0.7)',
            ],
          },
        ],
      },
      options: {
        responsive: true,
        plugins: { legend: { position: 'bottom' } },
      },
    });
    this.updateJornadaChart();
  }

  updateJornadaChart() {
    if (!this.jornadaChart) return;
    // Traducción de labels para todos los posibles valores
    const traduccion: { [key: string]: string } = {
      FULLTIME: 'Tiempo completo',
      PART_TIME: 'Medio tiempo',
      NIGHT: 'Nocturno',
      PERMANENT: 'Permanente',
      TEMPORARY: 'Temporal',
      INTERNSHIP: 'Pasantía',
      'Sin especificar': 'Sin especificar',
    };
    const counts: { [key: string]: number } = {};
    this.lst.forEach((emp) => {
      const jornada = emp.workingDay || emp.jornada || 'Sin especificar';
      counts[jornada] = (counts[jornada] || 0) + 1;
    });
    const labels = Object.keys(counts).map((j) => traduccion[j] || j);
    const data = Object.keys(counts).map((j) => counts[j]);
    this.jornadaChart.data.labels = labels;
    this.jornadaChart.data.datasets[0].data = data;
    this.jornadaChart.update();
  }
}
