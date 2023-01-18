import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/model/User.model';
import { AuthService } from 'src/app/services/auth.service';
import { UserauthenticationService } from 'src/app/services/userauthentication.service';
import * as alertify from 'alertifyjs'
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  resetPasswordForm!:FormGroup<any>
  constructor(private auth:AuthService, private service:UserauthenticationService, private dialog:MatDialog,private formbuilder: FormBuilder) { 
    this.resetPasswordForm= this.formbuilder.group({
      emailid: new FormControl(this.auth.getEmailForResetPassword()),
      otp: new FormControl("",[Validators.required, Validators.pattern("^[0-9]{6,6}$")]),
      newpassword: new FormControl("",[Validators.required]),
      confirmnewpassword: new FormControl("",[Validators.required])
    },{
      validators: this.MustMatch('newpassword','confirmnewpassword')
    })
  }

  ngOnInit(): void {
  }


 


  get f(){
    return this.resetPasswordForm.controls
  }

  MustMatch(password:any,confirmpassword:any){
    return (formGroup: FormGroup) =>{
      const passwordcontrol= formGroup.controls[password]
      const confirmpasswordcontrol= formGroup.controls[confirmpassword]
      if(confirmpasswordcontrol.errors && !confirmpasswordcontrol.errors['MustMatch']){
        return
      }
      if(passwordcontrol.value!==confirmpasswordcontrol.value){
        confirmpasswordcontrol.setErrors({MustMatch:true})
      }
      else{
        confirmpasswordcontrol.setErrors(null)
      }
    }
  }

  resetPassword(){
   this.service.resetPassword(this.resetPasswordForm.value.otp,this.resetPasswordForm.value.emailid,this.resetPasswordForm.value.newpassword).subscribe(response => {
    alertify.success("Password reset successfully")
    this.openLoginDialog('300ms','500ms')
   },error => {
    alertify.error('Something went wrong, please try again !!!')
    console.log(error)
   })
  }


  openLoginDialog(enteranimation:any,exitanimation:any){
    this.dialog.open(LoginComponent,{
      enterAnimationDuration:enteranimation,
      exitAnimationDuration:exitanimation,
      width:"40%",
      
    })
  }


}
