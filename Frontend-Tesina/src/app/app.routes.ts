import { Routes } from '@angular/router';
import { RegistrarseComponent } from './registrarse/registrarse.component';
import { LoginComponent } from './login/login.component';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { RepuestosComponent } from './repuestos/repuestos.component';
import { RepuestoComponent } from './repuesto/repuesto.component';
import { DashboardsComponent } from './dashboards/dashboards.component';
import { CasillaComponent } from './notificaciones/notificaciones.component';
import { LandingComponent } from './landing/landing.component';

export const routes: Routes = [
  { path: '', redirectTo: 'landing', pathMatch: 'full' },
  {
    path: 'login',
    component: LoginComponent,
  },
  { path: 'registrarse', component: RegistrarseComponent },
  { path: 'chatinteligente', component: ChatbotComponent },
  { path: 'repuestos', component: RepuestosComponent },
  { path: 'repuesto', component: RepuestoComponent },
  { path: 'dashboards', component: DashboardsComponent },
  { path: 'chatinteligente', component: ChatbotComponent },
  { path: 'notificaciones', component: CasillaComponent },
  { path: 'landing', component: LandingComponent },
];
