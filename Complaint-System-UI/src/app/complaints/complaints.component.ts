import { Component, OnInit } from '@angular/core';
import { ComplaintService } from '../complaint.service';
import { Complaint } from '../model/complaint.module';

@Component({
  selector: 'app-complaints',
  templateUrl: './complaints.component.html',
  styleUrls: ['./complaints.component.css']
})
export class ComplaintsComponent implements OnInit {

  complaints:any[];
  constructor(private service:ComplaintService) { }

  ngOnInit(): void {
    this.service.getAllComplaints().subscribe({
      next:(coms:any)=>{
      console.log("Complaints : " + coms)
      this.complaints = coms
      console.log("Now Complaints : " + this.complaints)
    }});
  }

}
