import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  isAdmin():boolean{
    let role = localStorage.getItem('userRole');
    if(role=='ADMIN'){
      return true;
    }return false;
  }

}
