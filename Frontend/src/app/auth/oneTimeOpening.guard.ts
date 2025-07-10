// time-auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtDecode } from '../pages/login/JwtDecode';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class OneTimeAuthGuard implements CanActivate {

  constructor(
    private jwtDecode: JwtDecode,
    private router: Router,
    private authService: AuthService // Assuming you have an AuthService for token management
  ) {}

  canActivate(): boolean {
    const roles = this.jwtDecode.roleCheaking();

    // Allow if admin
    if (roles.includes('ROLE_ADMIN')) {
      return true;
    }

    // Time-based access for others (e.g., 9 AM to 5 PM)
    

    if (this.authService.isHaveAdmin("ADMIN")) { // Assuming isTokenValid checks if the token is valid
      return true;
    }

    // Deny and redirect
    alert('This page is accessible only between 9AM and 5PM');
    this.router.navigate(['/not-allowed']); // or wherever you want
    return false;
  }
}
