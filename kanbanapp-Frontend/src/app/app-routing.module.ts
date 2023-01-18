import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';




import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { ReferfriendComponent } from './components/referfriend/referfriend.component';
import { RegisterComponent } from './components/register/register.component';
import { ResetpasswordComponent } from './components/resetpassword/resetpassword.component';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { AuthGuard } from './guards/auth.guard';


const routes: Routes = [
  {path:"home", component:HomeComponent},
  {path:"", redirectTo:"home", pathMatch:'full'},
  {path:"login", component:LoginComponent},
  {path:"register", component:RegisterComponent},
  {path:"dashboard", component:ToolbarComponent,canActivate: [AuthGuard]},
  {path:'resetpassword',component:ResetpasswordComponent},
  {path:"invitefriend", component:ReferfriendComponent},
  { path: '**', pathMatch: 'full', component: PagenotfoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
