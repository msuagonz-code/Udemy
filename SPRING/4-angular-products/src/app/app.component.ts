import { Component, OnInit, signal, Signal } from '@angular/core';
import { ProductsComponent } from './components/products.component';
import { Product } from './models/product';
import { FormComponent } from './components/form.component';

@Component({
  selector: 'app-root',
  imports: [ProductsComponent, FormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  products : Product[] = [];
  countId = signal(4);

  ngOnInit(): void {
    this.products = [
      {
        id: 1,
        name: 'Monitor Asus 35 pulgadas',
        price: 1000,
        description: 'El monitor es perfecto para juegos y peliculas!'
      },
      {
        id: 2,
        name: 'Lampara de oficina',
        price: 800,
        description: 'Lampara verde led para oficina'
      },
      {
        id: 3,
        name: 'mesa comerdor',
        price: 1000,
        description: 'mesa comedor de madera'
      }
    ]
  }
  
  addProduct(product: Product): void{
    //let maxID = (Math.max(...this.products.map(product => product.id))) + 1;
    //this.products = [...this.products, { ...product, id: maxID }];

    // Otra forma de hacerlo
    this.products = [...this.products, { ...product, id: this.countId() }];
    this.countId.update(id => id + 1);
  }

}