import { Component, OnInit } from '@angular/core';
import { UserService } from '../userService/user.service';
import { LoginRequest } from '../../dTypes';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});


  constructor(private formBuilder:FormBuilder, private userService:UserService){


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
      this.userService.logIn(data);
    }
  }
}
