import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user:string|null = "";
  userid:number = Number(sessionStorage.getItem("userid"));

  constructor() {}

  ngOnInit(): void {
    if(this.userid != null && this.userid > 0){
      this.user = sessionStorage.getItem("username");
    }
  }

}
