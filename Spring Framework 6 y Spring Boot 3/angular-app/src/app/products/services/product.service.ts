import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { map, Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  
  private products: Product[] = [
    {
      id: 1,
      name: 'Mesa Comedor',
      description: 'Excelente mesa para el comedor',
      price: 700
    },
    {
      id: 2,
      name: 'Teclado Mecanico',
      description: 'Excelente teclado para typing',
      price: 500
    }
  ];

  private url: string = 'http://localhost:8080/products';

  constructor(private http: HttpClient){}

  findAll(): Observable<Product[]>{
    // return of(this.products);
    return this.http.get(this.url).pipe(
      map((response: any) => response._embedded.products as Product[]),
    );
  }

  create(product: Product): Observable<Product>{
    return this.http.post<Product>(this.url, product)
  }

  update(product: Product): Observable<Product>{
    return this.http.put<Product>(`${this.url}/${product.id}`, product)
  }

  remove(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

}
