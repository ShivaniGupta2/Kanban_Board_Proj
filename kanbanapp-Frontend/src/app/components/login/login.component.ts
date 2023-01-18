import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserauthenticationService } from 'src/app/services/userauthentication.service';
import { ForgotpasswordComponent } from '../forgotpassword/forgotpassword.component';
import * as alertify from 'alertifyjs'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup<any>
  constructor(private formBuilder:FormBuilder,private route: Router,private service:UserauthenticationService,private auth:AuthService,private dialog:MatDialog) {
    this.loginForm=this.formBuilder.group({
      emailid: new FormControl('', [Validators.required,Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")] ),
      password: new FormControl('', [Validators.required])
    })
   
   }

   get f(){
    return this.loginForm.controls
  }

  ngOnInit(): void {
   
  }

  loginMethod(){
  this.service.loginUser(this.loginForm.value).subscribe(response => {
      console.log(response)
      alertify.success("Login Successfully!!!")
      this.auth.setToken(response.token)
      this.auth.setEmail(this.loginForm.value.emailid)
      this.route.navigate([""])
      window.location.reload()
      
  },error => {
    console.log(error)
    alertify.error('failed to login, please try again')
  })
    
  }

  openForgotPasswordDialog(enteranimation:any,exitanimation:any){
    this.dialog.open(ForgotpasswordComponent,{
      enterAnimationDuration:enteranimation,
      exitAnimationDuration:exitanimation,
      width:"30%"
    })
  }

  
}
