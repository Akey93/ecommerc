import { Component, OnInit } from '@angular/core';
import { UserService } from '../userService/user.service';
import { LoginRequest } from '../../dTypes';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isUserLoggedIn = new BehaviorSubject<boolean>(false);
  loginForm: FormGroup = new FormGroup({});
  dataNotCorrect: boolean = false;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {


  }



  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }
  login(): void {
    if (this.loginForm.valid) {
      const data: LoginRequest = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      };
      this.userService.logIn(data).pipe(
        catchError((error: any) => {
          this.dataNotCorrect = true;
          console.log(this.dataNotCorrect);

          return new Observable<any>((observer) => {
            observer.next(false);
            observer.complete();
          });
        })
      ).subscribe((response) => {
        if (response) {
          let email = response.email;
          let token = response.token;
          let ruolo = response.ruolo;

          localStorage.setItem('userEmail', email);
          localStorage.setItem('userToken', token);
          localStorage.setItem('userRole', ruolo);

          this.isUserLoggedIn.next(true);
          this.router.navigate(['']);
        } else { console.error('Autenticazione fallita.'); }
      });
    }
  }
}
