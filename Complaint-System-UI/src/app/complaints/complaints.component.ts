import { computeMsgId } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ComplaintService } from '../complaint.service';
import { Complaint } from '../model/complaint.module';
import { ComplaintUpdate } from '../model/complaintupdate.module';
import { User } from '../model/user.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-complaints',
  templateUrl: './complaints.component.html',
  styleUrls: ['./complaints.component.css']
})
export class ComplaintsComponent implements OnInit {

  complaints:any[];
  complaintUpdate:any[];
  allengineers:any[];

  usertype:string|null = "";
  user:string|null = "";
  userid:number = Number(sessionStorage.getItem("userid"));
  
  comSubject:string="";
  comDetail:string="";

  assignuser:string;

  updateRemark:string = "";
  isresolved:string = "";

  constructor(private router:Router,private service:ComplaintService, private userservice:UserService) { }

  ngOnInit(): void {
    if(this.userid != null && this.userid > 0){
      this.user = sessionStorage.getItem("username");
      this.usertype = sessionStorage.getItem("usertype");
      this.service.getAllComplaints(this.userid).subscribe({
        next:(coms:any)=>{
        this.complaints = coms
        }
      });
      this.service.getAllComplaintUpdate().subscribe({
        next:(cu:any)=>{
        this.complaintUpdate = cu
        }
      });
      this.userservice.getAllEngineers().subscribe({
        next:(engs:any)=>{
          this.allengineers = engs;
        }
      });
    }
  }

  addNewComplaint(){
    let newcomp:Complaint = new Complaint();
    newcomp.subject = this.comSubject;
    newcomp.detail = this.comDetail;
    newcomp.resolved = false;
    newcomp.user = this.getCurrentUserObject();
    
    this.service.addNewComplain(newcomp).subscribe({
      complete:()=>{
        // this.router.navigate(['complaints']);
        window.location.reload();
      }
    });
  }

  assignComplaint(complaintid:number){
    console.log("Assign Complaint : " + complaintid);
    console.log("User ID : " + this.assignuser);
    
    let compUpdate:ComplaintUpdate = this.getComplaintUpdateObject(complaintid);
    compUpdate.isResolved = false;
    compUpdate.isWorkingOn = true;
    compUpdate.statusRemark = this.updateRemark;
  }

  updateComplaint(complaintid:number){
    let resolved = false;
    if(this.isresolved === "true"){
      resolved = true;
    }
    let compUpdate:ComplaintUpdate = this.getComplaintUpdateObject(complaintid);
    compUpdate.isResolved = resolved;
    compUpdate.isWorkingOn = !resolved;
    compUpdate.statusRemark = this.updateRemark;
  }

  submitComplaintUpdate(comUpdate:ComplaintUpdate){
    this.service.addComplaintUpdate(comUpdate).subscribe({
      complete:() => this.router.navigate(['complaints'])
    });
  }

  getCurrentUserObject():User{
    let us:User = new User();
    us.id = this.userid;
    us.name = this.user;
    return us;
  }

  getComplaintObject(complaintid:number):Complaint{
    let c:Complaint = new Complaint();
    c.id = complaintid;
    return c;
  }

  getComplaintUpdateObject(complaintid:number):ComplaintUpdate{
    let compUpdate:ComplaintUpdate = new ComplaintUpdate();
    compUpdate.complaint = this.getComplaintObject(complaintid);
    compUpdate.user = this.getCurrentUserObject();
    return compUpdate;
  }
}
