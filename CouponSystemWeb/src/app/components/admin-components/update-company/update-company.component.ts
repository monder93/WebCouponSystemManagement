import { inject } from '@angular/core/testing';
import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css']
})
export class UpdateCompanyComponent implements OnInit {

  form: FormGroup;


  constructor(private formBuilder: FormBuilder,
    private matDialogRef: MatDialogRef<UpdateCompanyComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      id: this.data ? this.data.id : '',
      compName: this.data ? this.data.compName : '',
      password: this.data ? this.data.password : '',
      email: this.data ? this.data.email : ''
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
