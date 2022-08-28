import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './model/user.model';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  baseUrl:string="http://localhost:8082/user";
  engineersUrl:string="http://localhost:8082/user/engineers";

  loginUrl:string="http://localhost:8082/login";

  loginUser(username:string, password:string,usertype:string):Observable<Object>{
    let body = new HttpParams();
    body = body.set('username', username);
    body = body.set('password', password);
    body = body.set('usertype', usertype);

    return this.http.post<Object>(this.loginUrl,body);
  }

  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl);
  }

  getAllEngineers():Observable<User[]>{
    return this.http.get<User[]>(this.engineersUrl);
  }

  getUserById(id:number):Observable<User>{
    return this.http.get<User>(this.baseUrl+"/"+id)
  }
  addUser(user:User){
    return this.http.post(this.baseUrl,user);
  }
  deleteUserById(id:number){
      return this.http.delete(this.baseUrl+"/"+id);
  }
  editUser(user:User,id:number){
    return this.http.put(this.baseUrl+"/"+id,user);
  }
}
