import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/dTypes';
import { ProductService } from 'src/app/productGroup/productService/product.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  searchName: string = '';
  constructor(private productService:ProductService){

  }

  prodotti:Product[]=[]
  ngOnInit():void{
    this.productService.getProductByEmail().subscribe((data)=>{
      this.prodotti=data;
      console.log(this.prodotti);
    })

  }

  isAdmin():boolean{
    let role = localStorage.getItem('userRole');
    if(role=='ADMIN'){
      return true;
    }return false;
  }

}
