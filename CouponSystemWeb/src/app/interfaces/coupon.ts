import { Customer } from "./customer";

export interface Coupon 
{
    id : number;
    title : string;
    startDate : Date;
    endDate : Date;
    amount : number;
    type : string;
    message : string;
    price : number;
    image : string;
    customers : Customer[];
}
