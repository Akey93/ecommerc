import { Component, OnInit} from '@angular/core';
import { ProductInCart } from '../../dTypes';
import { ProductService } from '../../productGroup/productService/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{

  productInCart: ProductInCart[] = []
  calcolo!: Number;

  constructor(private productService: ProductService) { }
  isLog(): boolean {
    return localStorage.getItem('userRole') != null;
  }

  ngOnInit(): void {
    this.productService.getCart().subscribe((data) => {
      this.productInCart = data;
    })
    this.productService.calcolo().subscribe((data)=>{
      this.calcolo=data;
    })
  }
  buyAllCart(): void {
    this.productService.buyAll().subscribe(() => {
      location.reload();
    })
  }

}
