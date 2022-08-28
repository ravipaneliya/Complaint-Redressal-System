import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private router:Router) { }

  userid:number = Number(sessionStorage.getItem("userid"));

  ngOnInit(): void {
    sessionStorage.clear()
    if(this.userid != null && this.userid > 0){
      window.location.reload()
    }
    setTimeout(()=>{ this.router.navigate(['home']) }, 2000);
  }

}
