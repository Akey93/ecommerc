import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { PageDTO, Product } from '../../dTypes';
import { ProductService } from '../../productGroup/productService/product.service';

import { MatPaginator, PageEvent } from '@angular/material/paginator';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  prodotti: Product[] = [];
  controlloProdotti:Product[]=[];
  controllo: boolean=false;
  pageIndex = 0;
  searchName: String = '';

  @Output() PicAOR= new EventEmitter<boolean>

  constructor(private productService: ProductService) {

  }
  ngOnInit(): void {


  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  ngAfterViewInit() {
    let pageRequest: PageDTO = {
      nPage: this.paginator.pageIndex,
      dPage: this.paginator.pageSize,
    }
    console.log(pageRequest)
    this.productService.getAllProductP(pageRequest).subscribe((data) => {
      this.prodotti = data.content;
      this.productService.getAllProductP(pageRequest).subscribe((dataN) => {
        this.controlloProdotti=dataN.content;
        if(this.controlloProdotti.length>0){
          this.controllo=true
        }else this.controllo=false;
      })
    })
  }
  paginaCambiata($event: PageEvent) {

    let pageRequest: PageDTO = {
      nPage: this.paginator.pageIndex,
      dPage: this.paginator.pageSize,
    }
    console.log(pageRequest)
    this.productService.getAllProductP(pageRequest).subscribe((data) => {
      this.prodotti = data.content;  
      this.productService.getAllProductP(pageRequest).subscribe((dataN) => {
        this.controlloProdotti=dataN.content;
        if(this.controlloProdotti.length>0){
          this.controllo=true
        }else this.controllo=false;
      })
    })
  }
  picA: boolean = false;
  PicA(value: boolean) {
    this.picA = value;
  }
  PicAO() {
    this.picA = false;
  }


}
