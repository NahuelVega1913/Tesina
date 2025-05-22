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
import { RegistrarInspeccionComponent } from './registrar-inspeccion/registrar-inspeccion.component';
import { RegistrarPersonalizacionComponent } from './registrar-personalizacion/registrar-personalizacion.component';
import { RegistrarReparacionComponent } from './registrar-reparacion/registrar-reparacion.component';
import { EsperaComponent } from './espera/espera.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { roleGuard } from './guards/role.guard';
import { ConsultarServiciosComponent } from './consultar-servicios/consultar-servicios.component';
import { ModificarCustomizacionComponent } from './modificar-customizacion/modificar-customizacion.component';
import { ModificarReparacionComponent } from './modificar-reparacion/modificar-reparacion.component';
import { ModificarInspeccionComponent } from './modificar-inspeccion/modificar-inspeccion.component';
import { ConsultarInspeccionComponent } from './consultar-inspeccion/consultar-inspeccion.component';
import { ConsultarCustomizacionComponent } from './consultar-customizacion/consultar-customizacion.component';
import { ConsultarReparacionComponent } from './consultar-reparacion/consultar-reparacion.component';
import { TerminosCondicionesComponent } from './terminos-condiciones/terminos-condiciones.component';
import { PreguntasFrecuentesComponent } from './preguntas-frecuentes/preguntas-frecuentes.component';
import { HistorialServiciosComponent } from './historial-servicios/historial-servicios.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  { path: 'registrarse', component: RegistrarseComponent },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: 'terminos-condiciones', component: TerminosCondicionesComponent },

  {
    path: '',
    component: InicioComponent,
    children: [
      { path: 'chatinteligente', component: ChatbotComponent },
      { path: 'repuestos', component: RepuestosComponent },
      { path: 'repuesto', component: RepuestoComponent },
      {
        path: 'dashboards',
        component: DashboardsComponent,
        data: { role: ['ADMIN', 'SUPERADMIN'] },
        canActivate: [roleGuard],
      },
      { path: 'actualizarPerfil', component: ActualizarPerfilComponent },
      { path: 'notificaciones', component: CasillaComponent },
      { path: 'landing', component: LandingComponent },
      {
        path: 'proveedores',
        component: ProveedoresComponent,
        data: { role: ['ADMIN', 'SUPERADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'registrarProveedor',
        component: RegistrarProveedorComponent,
        data: { role: ['ADMIN', 'SUPERADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'modificarProveedor',
        component: ModificarProoveedorComponent,
        data: { role: ['ADMIN', 'SUPERADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'registrarRepuesto',
        component: RegistrarRepuestoComponent,
        data: { role: ['ADMIN', 'SUPERADMIN'] },
        canActivate: [roleGuard],
      },
      {
        path: 'modificarRepuesto',
        component: ModificarRepuestoComponent,
        canActivate: [roleGuard],

        data: { role: ['ADMIN', 'SUPERADMIN'] },
      },
      { path: 'carrito', component: CarritoComponent },
      {
        path: 'ventas',
        component: VentasComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN', 'SUPERADMIN'] },
      },
      {
        path: 'detalles-venta',
        component: DetallesVentaComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN', 'SUPERADMIN'] },
      },
      {
        path: 'empleados',
        component: EmpleadosComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN', 'SUPERADMIN'] },
      },
      {
        path: 'registrar-empleado',
        component: RegistrarEmpleadoComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN', 'SUPERADMIN'] },
      },
      {
        path: 'modificar-empleado',
        component: ModificarEmpleadoComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN', 'SUPERADMIN'] },
      },
      { path: 'servicios', component: ServiciosComponent },
      { path: 'registrar-inspeccion', component: RegistrarInspeccionComponent },

      {
        path: 'registrar-personalizacion',
        component: RegistrarPersonalizacionComponent,
      },
      { path: 'registrar-reparacion', component: RegistrarReparacionComponent },
      {
        path: 'espera',
        component: EsperaComponent,
      },
      {
        path: 'consultarServicios',
        component: ConsultarServiciosComponent,
        data: { role: ['ADMIN', 'SUPERADMIN'] },
      },
      {
        path: 'modificar-customizacion',
        component: ModificarCustomizacionComponent,
      },
      { path: 'modificar-reparacion', component: ModificarReparacionComponent },
      { path: 'modificar-inspeccion', component: ModificarInspeccionComponent },
      { path: 'consultar-inspeccion', component: ConsultarInspeccionComponent },
      {
        path: 'consultar-customizacion',
        component: ConsultarCustomizacionComponent,
      },
      { path: 'consultar-reparacion', component: ConsultarReparacionComponent },
      { path: 'preguntas-frecuentes', component: PreguntasFrecuentesComponent },
      { path: 'chatbot', component: ChatbotComponent },
      { path: 'historial-servicios', component: HistorialServiciosComponent },
    ],
  },
];
