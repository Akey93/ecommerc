import { Component, Input} from '@angular/core';
import { DtoProduct, Product } from '../../dTypes';
import { FormControl, Validators } from '@angular/forms';
import { ProductService } from '../productService/product.service';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent   {

   
  
  @Input({ required: true }) product!: Product;
  

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
      })

    }
  }
 

}