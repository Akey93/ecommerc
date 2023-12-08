import { Component, OnInit } from '@angular/core';
import { UserService } from '../userService/user.service';
import { LoginRequest } from '../../dTypes';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {



  constructor( private userService:UserService){


  }


  ngOnInit(): void {
    this.userService.reload();

  }
  login(data:LoginRequest):void{
    this.userService.logIn(data);
  }
}
