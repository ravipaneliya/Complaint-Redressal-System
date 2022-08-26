import { Component, OnInit } from '@angular/core';
import { ComplaintService } from '../complaint.service';
import { Complaint } from '../model/complaint.module';

@Component({
  selector: 'app-complaints',
  templateUrl: './complaints.component.html',
  styleUrls: ['./complaints.component.css']
})
export class ComplaintsComponent implements OnInit {

  complaints:Complaint[];
  constructor(private service:ComplaintService) { }

  ngOnInit(): void {
    this.service.getAllComplaints().subscribe(coms=>this.complaints = coms);
  }

}
