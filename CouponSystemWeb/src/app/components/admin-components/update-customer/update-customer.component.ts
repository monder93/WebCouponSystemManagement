import { Customer } from './../../../interfaces/customer';
import { inject } from '@angular/core/testing';
import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private matDialogRef: MatDialogRef<UpdateCustomerComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public snackBar: MatSnackBar) { }


  ngOnInit() {
    this.form = this.formBuilder.group({
      id: this.data ? this.data.id : '',
      custName: this.data ? this.data.custName : '',
      password: this.data ? this.data.password : '',
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
