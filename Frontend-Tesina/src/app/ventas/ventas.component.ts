import { AfterViewInit, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MercadoPagoService } from '../services/mercado-pago.service';
import { DatePipe } from '@angular/common';
import Chart, { registerables } from 'chart.js/auto';

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

  constructor(private router: Router) {}

  redirectTo(url: string, id: number) {
    localStorage.setItem('idVenta', id.toString());
    this.router.navigate([`${url}`]);
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getVentas();
  }
  filter() {
    console.log(this.dateFrom, this.dateTo);
    this.lstFiltered = this.lst.filter((item) => {
      const itemDate = new Date(item.date.split('-').join('/')).setHours(
        0,
        0,
        0,
        0
      );
      const fromDate = this.dateFrom
        ? new Date(this.dateFrom.split('-').join('/')).setHours(0, 0, 0, 0)
        : null;
      const toDate = this.dateTo
        ? new Date(this.dateTo.split('-').join('/')).setHours(0, 0, 0, 0)
        : null;

      const matchesCategory =
        this.category === '' || item.typePayment === this.category;
      const matchesDate =
        (!fromDate || itemDate >= fromDate) && (!toDate || itemDate <= toDate);
      const matchesSearch =
        this.search === '' ||
        item.user.toLowerCase().includes(this.search.toLowerCase());

      return matchesCategory && matchesDate && matchesSearch;
    });
  }

  getVentas() {
    const getSubscription = this.service.getVentas().subscribe({
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
  ngAfterViewInit() {
    Chart.register(...registerables);

    const ctx = document.getElementById('myChart') as HTMLCanvasElement;

    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Enero', 'Febrero', 'Marzo'],
        datasets: [
          {
            label: 'Ventas',
            data: [12, 19, 3],
            backgroundColor: '#3b82f6',
          },
        ],
      },
      options: {
        responsive: true,
      },
    });
  }
}
