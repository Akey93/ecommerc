import { ChangeDetectorRef, Component, Input, OnChanges, OnInit} from '@angular/core';
import { ProductInCart } from '../../dTypes';
import { ProductService } from '../../productGroup/productService/product.service';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {



  productInCart: ProductInCart[] = []
  calcolo!: Number;
  interval = 100;

  constructor(private productService: ProductService, private cdRef: ChangeDetectorRef) {
    this.productService.getCart().subscribe((data) => {
      this.productInCart = data;
    })
  }
  isLog(): boolean {
    return localStorage.getItem('userRole') != null;
  }



  ngOnInit(): void {
   
    /* this.pollData() */
    this.productService.calcolo().subscribe((data) => {
      this.calcolo = data;

    });

  }
  ricalcolo(value: Number){
    console.log(value)
    this.productService.calcolo().subscribe((data:Number) => {
      this.calcolo = data;
      this.cdRef.markForCheck();
    });
  }
  /* pollData() {
    interval(this.interval).subscribe(() => {
      this.productService.calcolo().subscribe((data) => {
        this.calcolo = data;
      });
    });
  } */
  buyAllCart(): void {
    this.productService.buyAll().subscribe(() => {
      location.reload();
    })
  }


}
