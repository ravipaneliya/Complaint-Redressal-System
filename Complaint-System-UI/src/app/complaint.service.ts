import { Injectable } from '@angular/core';
import { Complaint } from './model/complaint.module';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ComplaintUpdate } from './model/complaintupdate.module';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  addCampignUrl:string = "http://localhost:8082/complaint";
  complaintUrl:string="http://localhost:8082/complaints";
  complaintUpdateUrl:string="http://localhost:8082/complaint/update";
  
  constructor(private http:HttpClient) { }

  getAllComplaints(userid:number):Observable<Complaint[]>{
    return this.http.get<Complaint[]>(this.complaintUrl+"/"+userid);
  }

  getAllComplaintUpdate():Observable<ComplaintUpdate[]>{
    return this.http.get<ComplaintUpdate[]>(this.complaintUpdateUrl);
  }

  addNewComplain(newComplaint:Complaint){
    return this.http.post(this.addCampignUrl, newComplaint);
  }

  addComplaintUpdate(comUpdate:ComplaintUpdate){
    return this.http.post(this.complaintUpdateUrl, comUpdate);
  }
}
