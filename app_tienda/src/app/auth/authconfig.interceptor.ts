import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler } from "@angular/common/http";
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        const authToken = this.authService.getToken();
        req = req.clone({
            setHeaders: {
                Authorization: "Bearer " + `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUaWVuZGFfVmlydHVhbCIsImlhdCI6MTU4MTMyMzgyMCwiZXhwIjoxNTgxMzIzODIwNTgsImVtYWlsIjoiYWRtaW5AdGVzdC5jb20ifQ.xNhtbeq4Y8dmqcj1O_LgneWbbs4TcPlP5CxsZZPB4WU`
            }
        });
        return next.handle(req);
    }
}

// https://www.positronx.io/angular-jwt-user-authentication-tutorial/