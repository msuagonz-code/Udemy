import { Component, Input } from '@angular/core';
import { Product } from '../models/product';

@Component({
  selector: 'table-product',
  imports: [],
  templateUrl: './products.component.html',
})
export class ProductsComponent {

  @Input() products : Product [] = [];

  title =  'Lista de productos';
}
