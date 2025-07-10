import { Component } from '@angular/core';
import { AuthService } from './service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';

  constructor(private authService: AuthService,private router: Router) {
   this.isAdminAvailable("ADMIN");
}

isAdminAvailable(eRole: any): void {
 this.authService.isHaveAdmin(eRole).subscribe(
      (response) => {
        console.log(response);
        if (response === true) {
          this.router.navigate(['Sign-in']);

        } else {
          this.router.navigate(['Admin-sign-up']);
        }
      },
      (error) => {
        console.error('Error checking admin availability:', error);
      }
    );
  }
  }
