import { Routes } from '@angular/router';
import { RegistrarseComponent } from './registrarse/registrarse.component';
import { LoginComponent } from './login/login.component';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { RepuestosComponent } from './repuestos/repuestos.component';
import { RepuestoComponent } from './repuesto/repuesto.component';
import { DashboardsComponent } from './dashboards/dashboards.component';
import { CasillaComponent } from './notificaciones/notificaciones.component';
import { LandingComponent } from './landing/landing.component';
import { InicioComponent } from './inicio/inicio.component';
import { ActualizarPerfilComponent } from './actualizar-perfil/actualizar-perfil.component';
import { ProveedoresComponent } from './proveedores/proveedores.component';
import { RegistrarProveedorComponent } from './registrar-proveedor/registrar-proveedor.component';
import { ModificarProoveedorComponent } from './modificar-prooveedor/modificar-prooveedor.component';
import { RegistrarRepuestoComponent } from './registrar-repuesto/registrar-repuesto.component';
import { ModificarRepuestoComponent } from './modificar-repuesto/modificar-repuesto.component';
import { CarritoComponent } from './carrito/carrito.component';
import { VentasComponent } from './ventas/ventas.component';
import { DetallesVentaComponent } from './detalles-venta/detalles-venta.component';
import { EmpleadosComponent } from './empleados/empleados.component';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';
import { ModificarEmpleadoComponent } from './modificar-empleado/modificar-empleado.component';
import { ServiciosComponent } from './servicios/servicios.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  { path: 'registrarse', component: RegistrarseComponent },
  {
    path: '',
    component: InicioComponent,
    children: [
      { path: 'chatinteligente', component: ChatbotComponent },
      { path: 'repuestos', component: RepuestosComponent },
      { path: 'repuesto', component: RepuestoComponent },
      { path: 'dashboards', component: DashboardsComponent },
      { path: 'actualizarPerfil', component: ActualizarPerfilComponent },
      { path: 'notificaciones', component: CasillaComponent },
      { path: 'landing', component: LandingComponent },
      { path: 'proveedores', component: ProveedoresComponent },
      { path: 'registrarProveedor', component: RegistrarProveedorComponent },
      { path: 'modificarProveedor', component: ModificarProoveedorComponent },
      { path: 'registrarRepuesto', component: RegistrarRepuestoComponent },
      { path: 'modificarRepuesto', component: ModificarRepuestoComponent },
      { path: 'carrito', component: CarritoComponent },
      { path: 'ventas', component: VentasComponent },
      { path: 'detalles-venta', component: DetallesVentaComponent },
      { path: 'empleados', component: EmpleadosComponent },
      { path: 'registrar-empleado', component: RegistrarEmpleadoComponent },
      { path: 'modificar-empleado', component: ModificarEmpleadoComponent },
      { path: 'servicios', component: ServiciosComponent },
    ],
  },
];
