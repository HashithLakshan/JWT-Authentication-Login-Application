import { jwtDecode } from 'jwt-decode';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtDecode {
  roleCheaking(): String {
    const token = localStorage.getItem('jwtToken');
    if (!token) 
        return "Login in the account";

    try {
      const decoded: any = jwtDecode(token);
      console.log(decoded.roles?.[0] + ' is the role');
      return decoded.roles?.[0];
    } catch (error) {
      console.error('Invalid token', error);
      return "Login inthe account";
    }
  }
}
