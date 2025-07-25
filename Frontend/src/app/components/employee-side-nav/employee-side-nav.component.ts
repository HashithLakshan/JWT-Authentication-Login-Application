import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-employee-side-nav',
  templateUrl: './employee-side-nav.component.html',
  styleUrls: ['./employee-side-nav.component.css']
})
export class EmployeeSideNavComponent {

  
  constructor(private authService : AuthService, private router : Router) { }

  logout() {
    this.authService.logout();
    this.router.navigate(['/Sign-in']);
  }
  
}
