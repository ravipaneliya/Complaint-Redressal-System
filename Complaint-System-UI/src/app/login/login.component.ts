import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted: boolean = false;
  user:User;
  response:string = "";
  
  constructor(private builder: FormBuilder, private router:Router,private service:UserService) { }

  ngOnInit(): void {
    this.loginForm = this.builder.group({
      usertype: ['', Validators.required],
      username: ['', Validators.required],
      password: ['',[Validators.required]]
    });
    this.loginForm.controls['usertype'].setValue("USER");
  }
  get form() {
    return this.loginForm.controls;
  }

  onSubmit(){
    this.submitted = true;
    
    if (this.loginForm.invalid)
      return;
    else {
      // 
      console.log(this.loginForm.controls['usertype'].value);
      console.log(this.loginForm.controls['username'].value);
      console.log(this.loginForm.controls['password'].value);

      this.service.loginUser(this.loginForm.controls['username'].value, this.loginForm.controls['password'].value, 
      this.loginForm.controls['usertype'].value).subscribe((u:User)=> this.user = u);

      console.log("Response : "+ this.user);
      // if(this.user !== undefined && this.user.id > 0){
      //   this.response = "Successfully Logged In..!";
      //   sessionStorage.setItem("userid",this.user.id.toString());
      //   sessionStorage.setItem("username",this.user.name);
      //   sessionStorage.setItem("usertype",this.user.usertype.toString());
      //   setTimeout(()=>{ this.router.navigate(['home']) }, 3000);
      // } else {
      //   this.response = "Invalid Username Password or Usertype..!";
      // }
    }
  }

}