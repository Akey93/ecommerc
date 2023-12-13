import { Component, Input } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/dTypes';
import { ProductService } from '../productService/product.service';

@Component({
  selector: 'app-personal-product',
  templateUrl: './personal-product.component.html',
  styleUrls: ['./personal-product.component.css']
})
export class PersonalProductComponent {

 /*  productForm:FormGroup ; */

  @Input({ required: true }) product!: Product;

 /*  quantity = new FormControl(1, { validators: [Validators.required] }) */
  /* constructor(private productService: ProductService, private formBuilder:FormBuilder) {
    this.productForm=this.formBuilder.group({
      nameProduct:[this.product.nameProduct,[Validators.required, Validators.pattern("^[a-zA-Z-' ]{4,}$")]],
      codeProduct:[this.product.codeProduct,[Validators.required, Validators.pattern("^[a-zA-Z0-9]{4,}$")]],
      price:[this.product.price,[Validators.required, Validators.min(0)]],
      quantity:[this.product.quantity,[Validators.required, Validators.min(0)]],
      type:[this.product.type,[Validators.required]]
    })

  }
  quantityP(data:number):void{
    this.productService.modify
  }
  delete():void {
    let codeProduct = this.product.codeProduct;
    
    this.productService.removeProduct(codeProduct).subscribe(()=>{
      location.reload();
    })
    
  } */

}
