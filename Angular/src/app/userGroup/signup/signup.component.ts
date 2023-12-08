
import { Component, OnInit } from '@angular/core';
import { UserService } from '../userService/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {

  sellerSignUp: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder, private userService: UserService) {
  }
  ngOnInit(): void {
    this.sellerSignUp = this.formBuilder.group({
      firstName: ['', Validators.required],
      surname: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }
  signup(): void {
    if (this.sellerSignUp.valid) {
      let data: any = this.sellerSignUp.value;

      console.warn(data);
      this.userService.signUp(data);
    }
  }
}
