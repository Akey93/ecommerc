import {   ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Product, User } from 'src/app/dTypes';
import { ProductService } from 'src/app/productGroup/productService/product.service';
import { UserService } from 'src/app/userGroup/userService/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  searchName: string = '';
  prodotti: Product[] = []
  user: User ={
    firstName: '',
    surname: '',
    email: '',
    money: 0,
    indirizzo: ''
  }
  
  userForm: FormGroup;
  money:FormControl ;


  isFormValid:boolean=false;
  isMoneyValid:boolean=false;
 

  
  constructor(private productService: ProductService, private userService: UserService, private formBuilder: FormBuilder,private cdRef: ChangeDetectorRef) {

    this.userForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.pattern("^[a-zA-Z-' ]{2,}$")]],
      surname: ['', [Validators.required, Validators.pattern("^[a-zA-Z-' ]{2,}$")]],
      email: ['', [Validators.required, Validators.email]],
      
      
    })
    this.userForm.statusChanges.subscribe(()=>{
      this.isFormValid=this.userForm.valid;
    })
  
    const decimalPattern = /^(\d+(\.\d{1,2})?)?$/;

    this.money = new FormControl(1, [Validators.required, Validators.pattern(decimalPattern)]);
    this.userService.getUserM().subscribe((data) => {
      this.user = data;

      this.userForm.patchValue({
        firstName:this.user.firstName,
        surname:this.user.surname,
        email:this.user.email
      })
      this.cdRef.detectChanges();

    })
    this.money.statusChanges.subscribe(() => {
      const moneyControl = this.money;
      if (moneyControl) {
        this.isMoneyValid = moneyControl.valid;
      }
    });

  }

  ricarica() {
    console.log(this.money.value)
    let moneyU = this.money.value
    if (moneyU > 0) {
      this.userService.ricaricaSoldi(moneyU).subscribe((dato) => {
        location.reload();
      })
    }

  }
 
  preleva() {
    console.log(this.money.value)
    let moneyU = this.money.value
    if (moneyU > 0) {
      this.userService.prelevaSoldi(moneyU).subscribe((dato) => {
        location.reload();
      })
    }
  }




  ngOnInit(): void {
    this.productService.getProductByEmail().subscribe((data) => {
      this.prodotti = data;
    })
    
  }
  isAdmin(): boolean {
    let role = localStorage.getItem('userRole');
    if (role == 'ADMIN') {
      return true;
    } return false;
  }
  modificaP:boolean=false;
  modifica():void{
    this.userService.modifica(this.userForm.value)
      this.modificaP=true;

  }
  modificaPE(){
    this.modificaP=false;
    location.reload();
  }

}
