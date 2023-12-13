import { Injectable } from '@angular/core';
import { ApiService } from '../../apiService/api.service';
import { Router } from '@angular/router';
import { API } from '../../constants';
import { DtoProduct, Product, ProductInCart } from '../../dTypes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private apiService: ApiService, private router: Router) {

  }
  addProduct(product: Product): Observable<Product> {
    return this.apiService.makeRequest('post', `${API.product}${API.addP}`, product)

  }
  getAllProduct(): Observable<Product[]> {
    return this.apiService.makeRequest('get', `${API.product}${API.getAllP}`)
  }
  getProductByEmail(): Observable<Product[]> {
    return this.apiService.makeRequest('get', `${API.product}${API.getUserProduct}`)
  }
  removeProduct(dato: String): any {
    let data = { codeProduct: dato }
    return this.apiService.makeRequest('delete', `${API.product}${API.rimuoviProdotto}`, null, data);
  }





  addToCart(data: DtoProduct): Observable<ProductInCart[]> {

    return this.apiService.makeRequest('post', `${API.cart}${API.addC}`, data)
  }
  buyProduct(dato: string): Observable<Product> {
    let data = { code: dato }
    return this.apiService.makeRequest('post', `${API.cart}${API.buy}`, null, data)
  }
  deleteProductCart(dato: String): any {
    let data = { codeProduct: dato }
    return this.apiService.makeRequest('delete', `${API.cart}${API.removePC}`, null, data)
  }
  buyAll(): any {
    return this.apiService.makeRequest('post', `${API.cart}${API.buyAll}`)
  }
  quantity(data: DtoProduct): Observable<Number> {
    return this.apiService.makeRequest('put', `${API.cart}${API.modifyQP}`, data);
  }
  getCart(): Observable<ProductInCart[]> {
    return this.apiService.makeRequest('get', `${API.cart}${API.getAllC}`)
  }
}
