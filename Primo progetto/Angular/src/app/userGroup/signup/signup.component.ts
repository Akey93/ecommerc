
import { Component, OnInit } from '@angular/core';
import { UserService } from '../userService/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {
  constructor(private userService:UserService){
  }
  ngOnInit(): void {
    this.userService.reload();
  }
  signup(data: object): void {
    console.warn(data);
    this.userService.signUp(data);
  }
}
