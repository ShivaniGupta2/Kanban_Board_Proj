import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {MatIconModule} from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { HttpClientModule } from '@angular/common/http';
import { ForgotpasswordComponent } from './components/forgotpassword/forgotpassword.component';


import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {DragDropModule} from '@angular/cdk/drag-drop';


import { MatTooltipModule } from '@angular/material/tooltip';

import { ResetpasswordComponent } from './components/resetpassword/resetpassword.component';
import { ReferfriendComponent } from './components/referfriend/referfriend.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';







@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
  
    ForgotpasswordComponent,
  
    ToolbarComponent,
      
       ResetpasswordComponent,
              ReferfriendComponent,
              PagenotfoundComponent,
       

   

 
   
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    HttpClientModule,
    MatDialogModule,
    MatMenuModule,
    CommonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSelectModule,
    DragDropModule,
    MatProgressBarModule,
    MatSlideToggleModule,
    MatTooltipModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule
  ],
  exports:[  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
