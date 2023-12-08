import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../productService/product.service';



@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit{

  productForm:FormGroup;

  


  constructor(private formBuilder:FormBuilder, private productService:ProductService){
    this.productForm=this.formBuilder.group({
      nameProduct:['',[Validators.required, Validators.pattern("^[a-zA-Z-' ]{4,}$")]],
      codeProduct:['',[Validators.required, Validators.pattern("^[a-zA-Z0-9]{4,}$")]],
      price:['',[Validators.required, Validators.min(0)]],
      quantity:['',[Validators.required,Validators.min(0)]],
      type:['',[Validators.required]]
    })
    const nameValue = this.productForm.get('nameProduct')?.value; 
  }
  
  ngOnInit(): void {
    
  }
  aggiungi():void{
    
    this.productService.addProduct(this.productForm.value).subscribe((data)=>{
      
    });
  }
 

}
