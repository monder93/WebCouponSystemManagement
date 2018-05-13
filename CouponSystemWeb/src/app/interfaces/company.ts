import { Coupon } from "./coupon";

export interface Company 
{
    id : number;
    compName : string;
    password : string;
    email : string;
    coupons: Coupon[];
}
