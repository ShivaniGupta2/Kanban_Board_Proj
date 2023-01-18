import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User.model';
import { AuthService } from 'src/app/services/auth.service';
import { DashboardService } from 'src/app/services/dashboard.service';
import { UserauthenticationService } from 'src/app/services/userauthentication.service';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dialog:MatDialog, private auth: AuthService, private service: UserauthenticationService, private router: Router, private dashboardservices: DashboardService) { }
  
  userEmpname = new User;
  ngOnInit(): void {
    this.dashboardservices.getUserdata(this.auth.getEmail()).subscribe((response: any) => {
      this.userEmpname=response;
    })
  }

  openRegisterDialog(enteranimation:any,exitanimation:any){
    this.dialog.open(RegisterComponent,{
      enterAnimationDuration:enteranimation,
      exitAnimationDuration:exitanimation,
      width:"50%"
    })
  }

  openLoginDialog(enteranimation:any,exitanimation:any){
    this.dialog.open(LoginComponent,{
      enterAnimationDuration:enteranimation,
      exitAnimationDuration:exitanimation,
      width:"40%",
      
    })
  }

  public isLoggedIn(){
    return this.auth.isLoggedIn();
  }

  logout(){
    this.auth.clear();
    window.location.reload()
  }

}
