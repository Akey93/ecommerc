import { Component, EventEmitter, Input, Output} from '@angular/core';
import { DtoProduct, Product } from '../../dTypes';
import { FormControl, Validators } from '@angular/forms';
import { ProductService } from '../../productGroup/productService/product.service';
import { event } from 'jquery';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent   {

  @Output() PicA= new EventEmitter<boolean>;
  @Input({ required: true }) product!: Product;

  picA:boolean=true;
  

  quantity=new FormControl(1, {validators:[Validators.required]})

 
  
  constructor(private productService: ProductService) {
     
  }
  
  
  cartP(data:FormControl): void {
    
    console.log(data)
    if(data.valid){
      let dtoProduct: DtoProduct ={
        quantity: data.value,
        codeProduct: this.product.codeProduct
      }
  
      this.productService.addToCart(dtoProduct).subscribe((dato)=>{
        console.log(dato)
        this.PicA.emit(this.picA)

      })

    }
  }
  pic:boolean=true
  Pic(value:boolean){
    this.pic=value
  }
 

}