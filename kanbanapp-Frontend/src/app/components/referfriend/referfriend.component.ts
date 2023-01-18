import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { DashboardService } from 'src/app/services/dashboard.service';
import * as alertify from 'alertifyjs'
import { Router } from '@angular/router';

@Component({
  selector: 'referfriend',
  templateUrl: './referfriend.component.html',
  styleUrls: ['./referfriend.component.css']
})
export class ReferfriendComponent implements OnInit {

  inviteForm!:FormGroup<any>
  constructor(private auth: AuthService,private formbuilder: FormBuilder, private dashboardServices: DashboardService, private router: Router) {
    this.inviteForm = this.formbuilder.group({
      yourname: new FormControl('',[Validators.required,Validators.pattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")]),
      youremailid: new FormControl(this.auth.getEmail()),
      friendsEmailid: new FormControl('',[Validators.required,Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")])
    })
   }

  ngOnInit(): void {
  }

 

  get f(){
    return this.inviteForm.controls;
  }

  sendEmail(){
    console.log(this.inviteForm.value.friendsEmailid)
    this.dashboardServices.inviteUserEmail(this.inviteForm.value.friendsEmailid).subscribe(response => {
      alertify.success(response)
      this.router.navigate(["/dashboard"])
    }, error =>{
      console.log(error)
      alertify.error("Something went wrong, please try again !!!")
    })
  }

}
