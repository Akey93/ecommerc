import { Component, OnInit } from '@angular/core';

function searchToggle(obj: any, evt: { preventDefault: () => void; }){
  var container = (obj).closest('.search-wrapper');
      if(!container.hasClass('active')){
          container.addClass('active');
          evt.preventDefault();
      }
      else if(container.hasClass('active') && $(obj).closest('.input-holder').length == 0){
          container.removeClass('active');
          // clear input
          container.find('.search-input').val('');
      }
}

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
}
