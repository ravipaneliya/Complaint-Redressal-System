import { User } from './user.model';

export class Complaint{
    id:number;
    subject:string;
    detail:string;
    user:User;
    resolved:boolean;
    last_update:Date;
}