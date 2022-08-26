import { Injectable } from '@angular/core';
import { Complaint } from './model/complaint.module';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  baseUrl:string="http://localhost:8082/user";
  
  constructor(private http:HttpClient) { }

  getAllComplaints():Observable<Complaint[]>{
    return this.http.get<Complaint[]>(this.baseUrl);
  }
}
