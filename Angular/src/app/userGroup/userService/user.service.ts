import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, switchMap } from 'rxjs';
import { ApiService } from '../../apiService/api.service';
import { Router } from '@angular/router';
import { API } from '../../constants';
import { LoginRequest, User } from '../../dTypes';
import { data } from 'jquery';

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
      this.router.navigate(['']);

    })



  }
  reload(): void {
    if (localStorage.getItem('userToken')) {
      this.isUserLoggedIn.next(true);
      this.router.navigate(['']);

    }
  }
  logIn(data: LoginRequest): void {
    this.apiService.makeRequest('post', `${API.user}${API.login}`, data).subscribe((response) => {

      let email = response.email;
      let token = response.token;
      let ruolo = response.ruolo;

      localStorage.setItem('userEmail', email);
      localStorage.setItem('userToken', token);
      localStorage.setItem('userRole', ruolo);

      this.isUserLoggedIn.next(true);
      this.router.navigate(['']);
    })

  }
  getUserM(): Observable<User> {
    return this.apiService.makeRequest('get', `${API.user}${API.getUserM}`)
  }
  prelevaSoldi(dato: number): Observable<Number> {
    console.log("si ci siamo ", dato)
    let data = { money: dato }
    return this.apiService.makeRequest('put', `${API.user}${API.preleva}`, null, data)
  }
  ricaricaSoldi(dato: number): Observable<Number> {
    console.log("si ci siamo ", dato)
    let data = { money: dato }
    console.log(data)
    return this.apiService.makeRequest('put', `${API.user}${API.ricarica}`, null, data)
  }
  modifica(data: User): Observable<User> {
    return this.apiService.makeRequest('put', `${API.user}${API.modify}`,data)
  }

}



