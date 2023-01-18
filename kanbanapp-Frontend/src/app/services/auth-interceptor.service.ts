import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  constructor(private authService: AuthService, private router: Router) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if(req.headers.get("No-Auth") == 'True'){
      console.log("control comes to httpInterceptor's if condition")
      return next.handle(req.clone());
    }
    console.log("control comes to httpInterceptor's else condition")
    const token = this.authService.getToken()
    req = this.addToken(req, token)

    return next.handle(req).pipe(
      catchError(
        (error:HttpErrorResponse) => {
          console.log(error.status)
          if(error.status == 401){
            this.router.navigate(['/toolbar/login'])
          }
          return throwError("Something went wrong");
        }
      )
    )

  }

  private addToken(request:HttpRequest<any>, token:any){
    return request.clone({
      setHeaders: {
        Authorization : `Bearer ${token}`
      }
    })
  }
}

