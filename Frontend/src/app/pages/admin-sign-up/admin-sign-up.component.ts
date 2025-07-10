import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { SignUpRequest } from 'src/app/shared/SiginUpRequest';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-sign-up',
  templateUrl: './admin-sign-up.component.html',
  styleUrls: ['./admin-sign-up.component.css']
})
export class AdminSignUpComponent {
   signUp: SignUpRequest = {
      commonStatus: 'ACTIVE',
      userId: '',
      userName: '',
      email: '',
      roleDto: {
        roleId: '',
        roleName: 'ADMIN'
      },
      password: ''
    };
    confirmPassword: any = '';
    email1: string = '';
  
      constructor(private router: Router, private authService: AuthService) { }
  
       Register(): void {
  
  
      function containsAtSymbol(input: string): boolean {
        return input.includes('@');
      }
  
      // Validate password confirmation
  
      const x = containsAtSymbol(this.email1);
      if (x === true) {

        this.signUp.email = this.email1;

        if (this.signUp.password == this.confirmPassword) {

          this.authService.register(this.signUp).subscribe(
            (response: any) => { // Ensure correct response type
              if (response.message_status === 'Success') {

                this.router.navigate(['Sign-in']);
                Swal.fire('', 'Successfully registered as Admin', 'success');

              } else {

                Swal.fire('', response.commonMessage, 'error');

              }
            },
            (error) => {

              Swal.fire('', 'A backend error occurred. Please try again later.', 'error');
              
            }
          );
        } else {
  
          Swal.fire('', 'Passwords do not match', 'error');
  
        }
  
      } else {
        Swal.fire('', 'Invalid Email', 'error');
  
      }
    }
  

}
