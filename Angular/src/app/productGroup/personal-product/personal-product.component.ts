import { Component, Input } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Product } from 'src/app/dTypes';
import { ProductService } from '../productService/product.service';

@Component({
  selector: 'app-personal-product',
  templateUrl: './personal-product.component.html',
  styleUrls: ['./personal-product.component.css']
})
export class PersonalProductComponent {

  @Input({ required: true }) product!: Product;

  quantity = new FormControl(1, { validators: [Validators.required] })
  constructor(private productService: ProductService) {

  }
  delete():void {
    let codeProduct = this.product.codeProduct;
    this.productService.removeProduct(codeProduct).subscribe(()=>{
      location.reload();
    })
    
  }

}
