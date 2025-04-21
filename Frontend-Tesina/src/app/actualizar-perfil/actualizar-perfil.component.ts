import { Component, inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { NgClass } from '@angular/common';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-actualizar-perfil',
  imports: [NgClass, ReactiveFormsModule],
  templateUrl: './actualizar-perfil.component.html',
  styleUrl: './actualizar-perfil.component.css',
})
export class ActualizarPerfilComponent {
  private service: UsuarioService = inject(UsuarioService);

  constructor(private router: Router) {}

  form = new UntypedFormGroup({
    name: new UntypedFormControl('', []),
    lastname: new UntypedFormControl('', []),
    phone: new UntypedFormControl('', []),
    address: new UntypedFormControl('', []),
    email: new UntypedFormControl('', []),
    password: new UntypedFormControl('', []),
  });

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.service.getUsuario().subscribe((data) => {
      this.form.patchValue({
        name: data.name,
        lastname: data.lastname,
        phone: data.phone,
        address: data.address,
        email: data.email,
        password: data.password,
      });
    });
  }

  save() {
    if (this.form.valid) {
      Swal.fire({
        title: 'Estas seguro?',
        showDenyButton: true,
        confirmButtonText: 'Actualizar',
        denyButtonText: `Salir`,
      }).then((result) => {
        if (result.isConfirmed) {
          const entity: any = this.form.value;
          const addSubscription = this.service.putUsuario(entity).subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Usuario creado!',
                text: 'El usuario fue registrado exitosamente',
                confirmButtonColor: '#3085d6',
              });

              this.router.navigate(['/login']);
            },
            error: (err) => {
              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocurrio un error al registrar el usuario',
              });
            },
          });
        } else if (result.isDenied) {
          Swal.fire('Los cambios no se guardaran', '', 'info');
          window.location.href = '/inicio';
        }
      });
    }
  }
}
