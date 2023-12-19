import { Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/dTypes';
import { ProductService } from '../productService/product.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-personal-product',
  templateUrl: './personal-product.component.html',
  styleUrls: ['./personal-product.component.css']
})
export class PersonalProductComponent implements OnInit, OnChanges{


 
  productForm: FormGroup;

  @Input({ required: true }) product!: Product;




  constructor(private formBuilder: FormBuilder, private productService: ProductService, private cdRef: ChangeDetectorRef) {
    this.productForm = this.formBuilder.group({
      url:[],
      nameProduct: ['', [Validators.required, Validators.pattern("^[a-zA-Z-' ]{4,}$")]],
      codeProduct:['valorePredefinito'],
      price: ['', [Validators.required, Validators.min(0.01)]],
      quantity: ['', [Validators.required, Validators.min(1)]],
      type:['',[Validators.required]]
    });

  console.log(this.productForm.get('codeProduct')?.value)
  

  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['product'] && this.product) {
      this.productForm.patchValue({
        url:this.product.url,
        nameProduct: this.product.nameProduct,
        codeProduct: this.product.codeProduct,
        price: this.product.price,
        quantity: this.product.quantity,
        type: this.product.type
      });
      this.cdRef.detectChanges();
    }
  }
  ngOnInit(): void {
   
  }



  AggiornamentoProdotto():void{
    this.productService.modify(this.productForm.value).subscribe((data)=>{
      location.reload();
    })
  }
  delete(): void {
    let codeProduct = this.product.codeProduct;

    this.productService.removeProduct(codeProduct).subscribe(() => {
      location.reload();
    })

  }

}
