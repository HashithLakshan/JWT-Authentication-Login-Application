import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data['role']; // ✅ Fix: use 'role' not 'ROLE_ADMIN'
    const token = localStorage.getItem('jwtToken');

    if (!token) {
      this.router.navigate(['/sign-in']);
      return false;
    }

    try {
      const decoded: any = jwtDecode(token);
      if (decoded.roles?.includes(expectedRole)) {
        return true;
      } else {
        this.router.navigate(['/software']);
        return false;
      }
    } catch (error) {
      this.router.navigate(['/sign-in']);
      return false;
    }
  }
}
