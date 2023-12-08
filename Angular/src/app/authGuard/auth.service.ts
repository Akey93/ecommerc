import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  router = new Router();
  constructor() { }
  getAuthToken(): Observable<boolean> {
    const token = localStorage.getItem('userToken');
    
    if (token) {
      return of(true);

    }
    return of(false);
  }
  getAuthToken2():Observable<boolean>{
    return of (false);
  }

}
