import { ChangeDetectorRef, Component, Input, OnChanges, OnInit } from '@angular/core';
import { ProductInCart, User } from '../../dTypes';
import { ProductService } from '../../productGroup/productService/product.service';
import { UserService } from 'src/app/userGroup/userService/user.service';
import { Observable, catchError } from 'rxjs';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {


  user: User | undefined;
  productInCart: ProductInCart[] = []
  calcolo!: Number;

  soldiInsufficienti: boolean = false;

  constructor(private productService: ProductService, private cdRef: ChangeDetectorRef, private userService: UserService) {
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
    this.userService.getUserM().subscribe((data) => {
      this.user = data;
    })

  }
  ricalcolo(value: Number) {
    console.log(value)
    this.productService.calcolo().subscribe((data: Number) => {
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
    this.productService.buyAll().pipe(catchError((error: any) => {
      this.soldiInsufficienti = true;
      console.log(this.soldiInsufficienti);

      return new Observable<boolean>((observer) => {
        observer.next(false);
        observer.complete();
      });
    })
    ).subscribe(() => {

      this.productService.getCart().subscribe((data) => {
        this.productInCart = data;
      })
      this.productService.calcolo().subscribe((data: Number) => {
        this.calcolo = data;})
      
    })
  }
  capito():void{
    this.soldiInsufficienti=false;
  }
  soldiIns(data:boolean){
    this.soldiInsufficienti=data;
  }


}
