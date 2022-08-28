import { Complaint } from './complaint.module';
import { User } from './user.model';

export class ComplaintUpdate{
    id:number;
    complaint:Complaint;
    user:User;
    isWorkingOn:boolean;
    isResolved:boolean;
    statusRemark:string;
    last_update:Date;
}