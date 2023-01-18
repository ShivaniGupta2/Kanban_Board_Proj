import { Injectable } from '@angular/core';
import { UserauthenticationService } from './userauthentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private service: UserauthenticationService) { }

  isLoggedIn(){
    return this.getToken()
  }

  setToken(token: string){
    localStorage.setItem('token', token);
  }

  getToken(){
    return  localStorage.getItem('token');
  }

  setEmail(emailid: string){
    localStorage.setItem('emailid', emailid);
  }

  getEmail(){
    return localStorage.getItem('emailid');
  }

  setEmailForResetPassword(tempEmailid: string){
    localStorage.setItem('tempEmailid', tempEmailid);
  }

  getEmailForResetPassword(){
    return localStorage.getItem('tempEmailid');
  }
  clear(){
    localStorage.clear();
  }
}
