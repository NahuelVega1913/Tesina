import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ProveedoresService } from '../services/proveedores.service';
import { FormsModule } from '@angular/forms';

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
