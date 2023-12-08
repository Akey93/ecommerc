import { Injectable } from '@angular/core';
import { BehaviorSubject, switchMap } from 'rxjs';
import { ApiService } from '../../apiService/api.service';
import { Router } from '@angular/router';
import { API } from '../../constants';
import { LoginRequest } from '../../dTypes';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  

  isUserLoggedIn = new BehaviorSubject<boolean>(false);
  constructor(private apiService: ApiService, private router: Router) {

  }

  signUp(data: object): void {
    this.apiService.makeRequest('post', `${API.user}${API.add}`, data).subscribe((response) => {
      

      
      let email = response.email;
      let token = response.token;
      let ruolo = response.ruolo;

      localStorage.setItem('userEmail', email);
      localStorage.setItem('userToken', token);
      localStorage.setItem('userRole', ruolo);

      this.isUserLoggedIn.next(true);
      this.router.navigate(['profile']);

    })



  }
  reload(): void {
    if (localStorage.getItem('userToken')) {
      this.isUserLoggedIn.next(true);
      this.router.navigate(['profile']);

    }
  }
  logIn(data:LoginRequest):void{
    this.apiService.makeRequest('post',`${API.user}${API.login}`,data).subscribe((response)=>{

      let email = response.email; 
      let token = response.token;
      let ruolo = response.ruolo;

      localStorage.setItem('userEmail', email);
      localStorage.setItem('userToken', token);
      localStorage.setItem('userRole', ruolo);

      this.isUserLoggedIn.next(true);
      this.router.navigate(['profile']);
    })
  
   }

}

