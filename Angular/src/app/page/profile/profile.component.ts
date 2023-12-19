import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product, User } from 'src/app/dTypes';
import { ProductService } from 'src/app/productGroup/productService/product.service';
import { UserService } from 'src/app/userGroup/userService/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  searchName: string = '';
  userForm: FormGroup;
  constructor(private productService: ProductService, private userService: UserService, private formBuilder: FormBuilder) {
    this.userForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.pattern("^[a-zA-Z-' ]{2,}$")]],
      surname: ['', [Validators.required, Validators.pattern("^[a-zA-Z-' ]{2,}$")]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    })
    
  }

  prodotti: Product[] = []
  user: User | undefined;
  ngOnInit(): void {
    this.productService.getProductByEmail().subscribe((data) => {
      this.prodotti = data;
    })
    this.userService.getUserM().subscribe((data) => {
      this.user = data;
    })
   

  }
  isAdmin(): boolean {
    let role = localStorage.getItem('userRole');
    if (role == 'ADMIN') {
      return true;
    } return false;
  }

}
