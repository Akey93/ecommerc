import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './page/home/home.component';
import { SignupComponent } from './userGroup/signup/signup.component';
import { LoginComponent } from './userGroup/login/login.component';
import { CartComponent } from './page/cart/cart.component';
import { ProfileComponent } from './page/profile/profile.component';
import { authGuard, authGuard2 } from './authGuard/auth.guard';
import { AddProductComponent } from './page/add-product/add-product.component';
import { ProductComponent } from './productGroup/product/product.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'signup', component:SignupComponent},
  {path:'login', component:LoginComponent},
  {path:'cart', component:CartComponent},
  {path:'add-product',component:AddProductComponent, canActivate:[authGuard2]},
  {path:'profile',component:ProfileComponent, canActivate:[authGuard]},
  {path:'product',component:ProductComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
