import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { InicioComponent } from './inicio/inicio.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoginComponent, InicioComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'Frontend-Tesina';

  ngOnInit(): void {
    initFlowbite();
  }
}
function initFlowbite() {
  throw new Error('Function not implemented.');
}
