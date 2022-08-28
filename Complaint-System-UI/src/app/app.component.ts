import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Complaint-System-UI';

  user:string|null = "";
  userid:number = Number(sessionStorage.getItem("userid"));

  constructor() { }

  ngOnInit(): void {
    if(this.userid != null && this.userid > 0){
      this.user = sessionStorage.getItem("username");
    }
  }
}
