import { Component } from '@angular/core';
import { ProductComponent } from "./products/components/product/product.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [ProductComponent],
})
export class App {
  
  title: string = 'Hola Mundo Angular 20';
  enabled: boolean = false;

  courses: string[] = ['Angular', 'React', 'Spring Boot']

  setEnable(): void {
    this.enabled = !this.enabled;
    console.log('Hemos hecho click en setEnable');
  }

}
