import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/dTypes';
import { ProductService } from 'src/app/productGroup/productService/product.service';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private productService:ProductService){

  }

  prodotti:Product[]=[]
  ngOnInit():void{
    this.productService.getProductByEmail().subscribe((data)=>{
      this.prodotti=data;
    })

  }

}
