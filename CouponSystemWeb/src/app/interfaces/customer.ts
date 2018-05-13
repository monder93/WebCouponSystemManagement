import { Coupon } from "./coupon";

export interface Customer 
{
    id : number;
    custName : string;
    password : string;
    coupons : Coupon[];
}
