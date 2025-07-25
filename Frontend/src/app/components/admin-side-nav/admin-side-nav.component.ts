import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-admin-side-nav',
  templateUrl: './admin-side-nav.component.html',
  styleUrls: ['./admin-side-nav.component.css']
})
export class AdminSideNavComponent {

  constructor(private authService : AuthService, private router : Router) { }

  logout() {
    this.authService.logout();
    this.router.navigate(['/Sign-in']);
  }

}
