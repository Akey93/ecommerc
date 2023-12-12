import { Component, OnInit } from '@angular/core';
import { Product } from '../../dTypes';
import { ProductService } from '../../productGroup/productService/product.service';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private productService:ProductService ){

  }
  prodotti:Product[]=[]
  ngOnInit(): void {
    this.productService.getAllProduct().subscribe((data)=>{
      this.prodotti=data;
    })

  }
  
  
}
