import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../productService/product.service';




@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit{

  /* colorControl = new FormControl('primary' as ThemePaletuute); */

  productForm:FormGroup;

  isFormValid: boolean = true;
  

  


  constructor(private formBuilder:FormBuilder, private productService:ProductService){
    this.productForm=this.formBuilder.group({
      nameProduct:['',[Validators.required, Validators.pattern("^[a-zA-Z-' ]{4,}$")]],
      codeProduct:['',[Validators.required, Validators.pattern("^[a-zA-Z0-9]{4,}$")]],
      price:[0.00,[Validators.required, Validators.min(0)]],
      quantity:['',[Validators.required,Validators.min(0)]],
      type:['',[Validators.required]]
    })
    this.productForm.statusChanges.subscribe(() => {
      this.isFormValid = this.productForm.valid ;
    });
    console.log(this.isFormValid)
    
   
    
  }
  
  
  ngOnInit(): void {
    
    
  }
  aggiungi():void{
    
    this.productService.addProduct(this.productForm.value).subscribe((data)=>{
      console.log(data)
    });
  }
 

}
