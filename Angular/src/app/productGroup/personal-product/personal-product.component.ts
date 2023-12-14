import { Component, Input, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/dTypes';
import { ProductService } from '../productService/product.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-personal-product',
  templateUrl: './personal-product.component.html',
  styleUrls: ['./personal-product.component.css']
})
export class PersonalProductComponent implements OnInit{


  /*  quantity = new FormControl(1, { validators: [Validators.required] }) */
  productForm: FormGroup;

  @Input({ required: true }) product!: Product;




  constructor(private formBuilder: FormBuilder, private productService: ProductService, private cdRef: ChangeDetectorRef) {
    this.productForm = this.formBuilder.group({
      nameProduct: ['', [Validators.required, Validators.pattern("^[a-zA-Z-' ]{4,}$")]],
      price: [0.01, [Validators.required, Validators.min(0.01)]],
      quantity: [1, [Validators.required, Validators.min(1)]],
      type:['',[Validators.required]]
    });

  }
  ngOnInit(): void {
    /* this.productForm.value({'nameProduct':this.product.nameProduct}) */
  }
  cNome = false;
  cPrice = false;
  cQuantity = false;
  cType = false;

  cambioNome(): void {
    this.cNome = true;
  }
  cambioNomeN(): void {
    this.cNome = false;
  }
  cambioPrice(): void {
    this.cPrice = true;
    this.cdRef.detectChanges();
  }
  cambioPriceN(): void {
    this.cPrice = false;
  }
  cambioQuantity(): void {
    this.cQuantity = true;
    this.cdRef.detectChanges();
  }
  cambioQuantityN(): void {
    this.cQuantity = false;
  }
  cambioType(): void {
    this.cType = true;
    this.cdRef.detectChanges();
  }
  cambioTypeN(): void {
    this.cType = false;
  }


  quantityP():void{

    /* this.productService.modify */
  }
  delete(): void {
    let codeProduct = this.product.codeProduct;

    this.productService.removeProduct(codeProduct).subscribe(() => {
      location.reload();
    })

  }

}
