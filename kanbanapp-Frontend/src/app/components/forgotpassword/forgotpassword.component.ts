import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth.service';
import { UserauthenticationService } from 'src/app/services/userauthentication.service';
import * as alertify from 'alertifyjs'
import { ResetpasswordComponent } from '../resetpassword/resetpassword.component';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {
  
  forgotPasswordForm!: FormGroup<any>

  constructor(private dialog:MatDialog, private auth:AuthService, private service: UserauthenticationService, private formBuilder: FormBuilder) {
    this.forgotPasswordForm=this.formBuilder.group({
      email: new FormControl('', [Validators.required,Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")] ),
    })
   }

  ngOnInit(): void {
  }


  

  get f(){
    return this.forgotPasswordForm.controls;
  }


  sendOTP(){
    this.service.generateOTP(this.forgotPasswordForm.value.email).subscribe(response => {
      this.auth.setEmailForResetPassword(this.forgotPasswordForm.value.email);
      alertify.success("Sent OTP Successfully")
      this.openResetPasswordDialog('300ms','500ms')
    }, error => {
      alertify.error("Email is not registered, please registered yourself !!!")
    })
  
  }


  openResetPasswordDialog(enteranimation:any,exitanimation:any){
    this.dialog.open(ResetpasswordComponent,{
      enterAnimationDuration:enteranimation,
      exitAnimationDuration:exitanimation,
      width:"50%"
    })
  }
  

}
