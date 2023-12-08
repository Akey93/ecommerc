import { Component, Input,  OnInit } from '@angular/core';
import { DtoProduct, ProductInCart } from '../../dTypes';
import { ProductService } from '../productService/product.service';
import { FormControl,  Validators } from '@angular/forms';

@Component({
  selector: 'app-product-in-cart',
  templateUrl: './product-in-cart.component.html',
  styleUrls: ['./product-in-cart.component.css']
})
export class ProductInCartComponent implements  OnInit{

  
  @Input({ required: true }) product!: ProductInCart;
  quantity=new FormControl(1, {validators:[Validators.required]})

  constructor(private productService:ProductService){
  }
  ngOnInit(): void {
    this.quantity.setValue(this.product.quantity);
  }
 


  compra(dato:FormControl):void{
    let cartProduct:DtoProduct={
      email: '',
      quantity: 0,
        codeProduct:''
      }
      cartProduct.codeProduct=this.product.product.codeProduct;
    cartProduct.email=localStorage.getItem('userEmail') || '';
    cartProduct.quantity=dato.value;

    this.productService.buyProduct(cartProduct.codeProduct).subscribe((data)=>{
      location.reload();

    })
  }
  delete():void{
    let cartProduct:DtoProduct={
    email: '',
    quantity: 0,
      codeProduct:''
    }
    cartProduct.codeProduct=this.product.product.codeProduct;
    cartProduct.email=localStorage.getItem('userEmail') || '';
    this.productService.deleteProductCart(cartProduct.codeProduct).subscribe(()=>{
      
      location.reload();
    })
  }
  quantitaP():void{
    let cartProduct:DtoProduct={
      email: '',
      quantity: 0,
        codeProduct:''
      }
      cartProduct.codeProduct=this.product.product.codeProduct;
    cartProduct.email=localStorage.getItem('userEmail') || '';
    cartProduct.quantity=1 ;

    this.productService.quantity(cartProduct).subscribe((dato)=>{
    })
  }
}
