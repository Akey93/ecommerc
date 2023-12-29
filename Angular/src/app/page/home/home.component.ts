import { Component, OnInit, ViewChild } from '@angular/core';
import { PageDTO, Product } from '../../dTypes';
import { ProductService } from '../../productGroup/productService/product.service';

import { MatPaginator, PageEvent } from '@angular/material/paginator';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  pageIndex= 0;
paginaCambiata($event: PageEvent) {
 
   let pageRequest: PageDTO={
     nPage: this.paginator.pageIndex,
     dPage: this.paginator.pageSize,
   }
   console.log(pageRequest)
  this.productService.getAllProductP(pageRequest).subscribe((data)=>{
    this.prodotti=data.content;
  })
}

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  ngAfterViewInit() {
    let pageRequest: PageDTO={
      nPage: this.paginator.pageIndex,
      dPage: this.paginator.pageSize,
    }
    console.log(pageRequest)
    this.productService.getAllProductP(pageRequest).subscribe((data)=>{
      this.prodotti=data.content;
    })
  }
  searchName:String='';
  constructor(private productService:ProductService ){

  }
  prodotti:Product[]=[]
  ngOnInit(): void {
    

  }
  picA:boolean= false;
  PicA(value:boolean){
    this.picA=value;
  }
  PicAO(){
    this.picA=false;
  }
  
  
}
