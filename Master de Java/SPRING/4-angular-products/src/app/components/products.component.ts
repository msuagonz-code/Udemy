import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../models/product';

@Component({
  selector: 'table-product',
  imports: [],
  templateUrl: './products.component.html',
})
export class ProductsComponent {

  @Input() products : Product [] = [];

  title =  'Lista de productos';
  
  @Output() updateProductEvent = new EventEmitter();
  @Output() removeProductEvent = new EventEmitter();

  onUpdateProduct(product: Product) : void {
    this.updateProductEvent.emit(product);
  }

  onRemoveProduct(id: number): void {
    this.removeProductEvent.emit(id);
  }

}
