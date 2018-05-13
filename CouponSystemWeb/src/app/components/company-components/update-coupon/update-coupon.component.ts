import { inject } from '@angular/core/testing';
import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-update-coupon',
  templateUrl: './update-coupon.component.html',
  styleUrls: ['./update-coupon.component.css']
})
export class UpdateCouponComponent implements OnInit {
  form: FormGroup;


  constructor(private formBuilder: FormBuilder,
    private matDialogRef: MatDialogRef<UpdateCouponComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      id: this.data ? this.data.id : '',
      title: this.data ? this.data.title : '',
      endDate: this.data ? this.data.endDate : '',
      price: this.data ? this.data.price : ''
    })
  }


  // choosing cancel 
  onCloseNo(): void {
    this.matDialogRef.close();
  }

  openSnackBar() {
    this.snackBar.open("field is not editable", "close", {
      duration: 2000,
    });
  }

}
