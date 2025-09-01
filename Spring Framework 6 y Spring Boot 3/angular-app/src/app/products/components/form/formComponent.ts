import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../models/product';
import { FormsModule, NgForm } from '@angular/forms';
import { ProductComponent } from '../product/product.component';

@Component({
  selector: 'app-form',
  imports: [FormsModule],
  templateUrl: './form.html',
  styleUrl: './form.css'
})
export class FormComponent {
  
  @Input() product: Product = { 
    id:0,
    name: '', 
    description: '', 
    price:0
  };

  @Output() newProductEvent = new EventEmitter();

  onSubmit(productForm: NgForm): void {
    if(productForm.valid){
      this.newProductEvent.emit(this.product);
      console.log(this.product);
    }
    productForm.reset();
    productForm.resetForm();
  }

  clean(): void{
    this.product = { 
      id:0,
      name: '', 
      description: '', 
      price:0
    };
  }

}
