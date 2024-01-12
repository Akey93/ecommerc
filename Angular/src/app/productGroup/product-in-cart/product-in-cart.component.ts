import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DtoProduct, ProductInCart } from '../../dTypes';
import { ProductService } from '../productService/product.service';
import { FormControl, Validators } from '@angular/forms';
import { Observable, catchError } from 'rxjs';

@Component({
  selector: 'app-product-in-cart',
  templateUrl: './product-in-cart.component.html',
  styleUrls: ['./product-in-cart.component.css']
})
export class ProductInCartComponent implements OnInit {
  
  soldiInsufficienti:boolean=true;
  @Output() calcolo = new EventEmitter<Number>
  @Output()SoldiInsufficienti= new EventEmitter<boolean>
  @Input({ required: true }) product!: ProductInCart;

  quantity = new FormControl(1, { validators: [Validators.required] })

  constructor(private productService: ProductService) {
  }
  ngOnInit(): void {
    this.quantity.setValue(this.product.quantity);
    this.cartProduct.quantity=this.product.quantity;
    
  }



  compra(dato: FormControl): void {
    let cartProduct: DtoProduct = {
      quantity: 0,
      codeProduct: ''
    }
    cartProduct.codeProduct = this.product.product.codeProduct;
    cartProduct.quantity = dato.value;

    this.productService.buyProduct(cartProduct.codeProduct).pipe(catchError((error: any) => {
      this.SoldiInsufficienti.emit(this.soldiInsufficienti)
      console.log(this.soldiInsufficienti);

      return new Observable<boolean>((observer) => {
        observer.next(false);
        observer.complete();
      });
    })
    ).subscribe((data) => {
     if(!data){
     
     }
     else{location.reload()}
      

    })
  }
  delete(): void {
    let cartProduct: DtoProduct = {
      quantity: 0,
      codeProduct: ''
    }
    cartProduct.codeProduct = this.product.product.codeProduct;
    this.productService.deleteProductCart(cartProduct.codeProduct).subscribe(() => {

      location.reload();
    })
  }
  cartProduct:DtoProduct={
    quantity:0,
    codeProduct:''
  }
  quantitaP(data: FormControl): void {
    
    this.cartProduct.codeProduct = this.product.product.codeProduct;
    this.cartProduct.quantity = data.value;
    console.log()
    this.productService.quantityPIC(this.cartProduct).subscribe((dato) => {
      this.productService.calcolo().subscribe((data) => {
        this.calcolo.emit(data)
        console.log(data)
      });
    })
  }
}
