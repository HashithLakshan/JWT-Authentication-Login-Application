import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { JwtDecode } from './JwtDecode';
import Swal from 'sweetalert2';
import { FormGroup,FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
 myForm : FormGroup;

  constructor(private authService: AuthService, private router: Router,private jwtDecode : JwtDecode) { 
     this.myForm = new FormGroup({
      userName: new FormControl(''),
      password: new FormControl(''),
    });
  }

  onClick(): void {
  const loginData = {
    userName: this.myForm.value.userName,
    password: this.myForm.value.password,
  };
  this.authService.login(loginData).subscribe({
    next: (response) => {

      this.saveToSession(response);
      const role = this.jwtDecode.roleCheaking();

      if (role === 'ROLE_ADMIN') {

        console.log("Admin role detected");
        this.router.navigateByUrl('/Admin');
        Swal.fire('Success', 'Login Successful!', 'success');

      } else if (role === 'ROLE_EMPLOYEE') {

        console.log("Employee role detected");
        this.router.navigateByUrl('/Employee');
        Swal.fire('Success', 'Login Successful!', 'success');

      } else {

        Swal.fire('Oops', 'Unauthorized Role!', 'error');

      }
    },
    error: (error) => {

      Swal.fire('Oops', 'Username or Password Incorrect!', 'error');

    }
  });

}
  saveToSession(data: any){
    localStorage.setItem('role', data.roles);
    localStorage.setItem('jwtToken', data.token);
    localStorage.setItem('userName', data.username);
    localStorage.setItem('email', data.email);
    localStorage.setItem('userID', data.id);
  }

}
