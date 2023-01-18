import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserauthenticationService {
  isLoggedIn: boolean = false;
  private registerURL = "http://localhost:9001/kanban/v2/";
  private loginURL = "http://localhost:9001/kanban/v3/loginapi";
  private getOTPURL ="http://localhost:9001/kanban/v2/otp/";
  private resetPasswordURL = "http://localhost:9001/kanban/v2/resetPassword/"

  requestHeader = new HttpHeaders(
    {'No-Auth': 'False'}
  )
  constructor(private http: HttpClient) { }

  registerUser(data: any): Observable<any> {
    console.log(data);
    return this.http.post(`${this.registerURL}register`, data)
  }

  loginUser(data: any): Observable<any> {
    console.log(data);
    return this.http.post(this.loginURL, data);
  }

  generateOTP(registeredEmail:any){ 
    return this.http.get<any>(this.getOTPURL+registeredEmail);
  }

  resetPassword(otp:any, registeredEmailid:any, changedPassword:any ){
    return this.http.get(this.resetPasswordURL+otp+'/'+registeredEmailid+'/'+changedPassword);
  }
}
