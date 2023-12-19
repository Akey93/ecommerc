import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './bar/navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { HomeComponent } from './page/home/home.component';
import { SignupComponent } from './userGroup/signup/signup.component';
import { LoginComponent } from './userGroup/login/login.component';
import { CartComponent } from './page/cart/cart.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ProfileComponent } from './page/profile/profile.component';
import { AddProductComponent } from './page/add-product/add-product.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductInCartComponent } from './productGroup/product-in-cart/product-in-cart.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { SidebarComponent } from './bar/sidebar/sidebar.component';
import { PersonalProductComponent } from './productGroup/personal-product/personal-product.component';
import { ProductComponent } from './productGroup/product/product.component';
import { SearchPipe } from './pipe/search.pipe';
import { LOCALE_ID} from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeIt from '@angular/common/locales/it';

registerLocaleData(localeIt);


@NgModule({
  
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    SignupComponent,
    LoginComponent,
    CartComponent,
    ProfileComponent,
    AddProductComponent,
    ProductComponent,
    ProductInCartComponent,
    SidebarComponent,
    PersonalProductComponent,
    SearchPipe,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatSelectModule,
    MatButtonModule,



  ],
  providers: [{ provide: LOCALE_ID, useValue: 'it-IT' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
