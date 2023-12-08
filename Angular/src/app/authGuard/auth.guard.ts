import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from './auth.service';


export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);

  return authService.getAuthToken();
};
export const authGuard2: CanActivateFn =(route, state) =>{
  const role = localStorage.getItem('userRole')
  const authService = inject(AuthService);
  if (role=='ADMIN'){
  return authService.getAuthToken();
  
  }else {
    return authService.getAuthToken2();
  }
}
