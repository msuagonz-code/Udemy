import { Component, OnInit, signal, Signal } from '@angular/core';
import { ProductsComponent } from './components/products.component';
import { Product } from './models/product';
import { FormComponent } from './components/form.component';
import Swal from 'sweetalert2';
import { ProductService } from './services/product.service';

@Component({
  selector: 'app-root',
  imports: [ProductsComponent, FormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  products : Product[] = [];
  countId = signal(4);
  productSelected : Product = new Product();

  constructor(private service: ProductService){

  }

  ngOnInit(): void {

    this.service.findAll().subscribe(products => this.products = products);

    /*
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
    */
  }
  
  addProduct(product: Product): void{
    //let maxID = (Math.max(...this.products.map(product => product.id))) + 1;
    //this.products = [...this.products, { ...product, id: maxID }];

    // Otra forma de hacerlo

    if(product.id > 0){

      this.service.update(product).subscribe(productUpdate => {

        this.products = this.products.map(p => {
          if(p.id == product.id){
            return {... productUpdate}
          }
          return p;
        })
  
        Swal.fire({
          title: "Producto actualizado!",
          text: `Producto ${productUpdate.name} Actualizado correctamente!`,
          icon: "success"
        });

      });

    }else{

      this.service.create(product).subscribe(productNew => {
        this.products = [...this.products, { ...productNew }];
  
        Swal.fire({
          title: "Producto creado!",
          text: `Producto ${productNew.name} Creado correctamente!`,
          icon: "success"
        });
      });

    }

  }


  onUpdateProduct(product: Product):void{
    this.productSelected = {... product}
  }

  onRemoveProduct(id: number): void{

    Swal.fire({
        title: "¿Está seguro que desea eliminar?",
        text: "Cuidado!, va a eliminar un producto del sistema",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, Eliminar!",
        cancelButtonText: "No, cancelar"
      }).then((result) => {
        if (result.isConfirmed) {

          this.service.remove(id).subscribe(productDeleted => {
            this.products = this.products.filter(product => product.id != id);
            Swal.fire({
              title: "Producto eliminado!",
              text: `Producto ${productDeleted.name} eliminado correctamente!`,
              icon: "success"
            });
          });

        }
    });

  }

}