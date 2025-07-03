import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ProveedoresService } from '../services/proveedores.service';
import { FormsModule } from '@angular/forms';
import * as XLSX from 'xlsx';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

@Component({
  selector: 'app-proveedores',
  imports: [FormsModule],
  templateUrl: './proveedores.component.html',
  styleUrl: './proveedores.component.css',
})
export class ProveedoresComponent {
  private service: ProveedoresService = inject(ProveedoresService);

  category: string = '';
  search: string = '';
  lst: any[] = [];
  lstFiltered: any[] = [];

  constructor(private router: Router) {}
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getProveedores();
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
    XLSX.writeFile(wb, `proovedores_${fecha}.xlsx`);
  }

  exportarPDF() {
    const doc = new jsPDF();
    const fecha = this.getFechaActual();

    autoTable(doc, { html: '#tabla' }); // usa el id de tu tabla
    doc.save(`proovedores_${fecha}.pdf`);
    return;
  }

  filter() {
    this.lstFiltered = this.lst.filter((item) => {
      return (
        item.name.toLowerCase().includes(this.search.toLowerCase()) &&
        (this.category === '' || item.category === this.category)
      );
    });
  }

  setProveedor(id: number) {
    localStorage.setItem('idProveedor', id.toString());
    this.router.navigate(['/modificarProveedor']);
  }
  toLowerCase(str: string): string {
    str = str.toLowerCase();
    return str ? str.charAt(0).toUpperCase() + str.slice(1).toLowerCase() : '';
  }

  getProveedores() {
    const getSubscription = this.service.getProveedores().subscribe({
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

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
}
