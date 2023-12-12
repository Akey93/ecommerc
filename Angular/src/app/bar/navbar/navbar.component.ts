import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  



  isAdmin(): boolean {
    return  localStorage.getItem('userRole') =='ADMIN';
  
  }
  isLog():boolean{
    return localStorage.getItem('userRole') != null;
  }
  ngOnInit(): void {

  }
  logout():void{
    localStorage.clear();

  }
}
