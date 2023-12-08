import { Component, OnInit } from '@angular/core';
import {  ProductInCart } from '../dTypes';
import { ProductService } from '../productGroup/productService/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  productInCart: ProductInCart[] = []
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    let email = localStorage.getItem('userEmail')
    this.productService.getCart(email!).subscribe((data) => {
      console.log(data);
      this.productInCart = data;
      console.log("questo è il productInCart: ", this.productInCart)
    })
  }
  buyAllCart(): void {
    let email = localStorage.getItem('userEmail')
    this.productService.buyAll(email!).subscribe(() => {
      location.reload();
    })
  }

}
