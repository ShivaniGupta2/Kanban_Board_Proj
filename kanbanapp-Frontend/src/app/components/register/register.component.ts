import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserauthenticationService } from 'src/app/services/userauthentication.service';
import * as alertify from 'alertifyjs'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  responsedata:any;
  submitted = false;
  accountForm!: FormGroup<{empname: FormControl<string | null>; designation: FormControl<string | null>; emailid: FormControl<string | null>; password: FormControl<string | null>; confirmpassword: FormControl<string | null>; }>;
  save: boolean | undefined;
  
  constructor( private router: Router, private formbuilder: FormBuilder,  private route: Router, private service:UserauthenticationService, private dialogref: MatDialogRef<RegisterComponent> ) { 
    this.accountForm = this.formbuilder.group({
      
      empname:new FormControl('',[Validators.required,Validators.pattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")]),
      designation:new FormControl('',[Validators.required]),
      emailid: new FormControl('', [Validators.required,Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")] ),
      password: new FormControl('', [Validators.required]),
      confirmpassword: new FormControl('', [Validators.required])
  },{
    validators: this.MustMatch('password','confirmpassword')
  });
  }

  get f(){
    return this.accountForm.controls
  }

  ngOnInit(): void {
    
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

  

  onSubmit(){
      if (this.accountForm.valid)
      {
        this.service.registerUser(this.accountForm.value).subscribe(response=>{
         alertify.success("registered successfully")
        },error=>{
          console.log(error)
          alertify.error("Something went wrong, please try again !!!")
        })
      }
    }
}
