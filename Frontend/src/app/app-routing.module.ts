import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { AdminSignUpComponent } from './pages/admin-sign-up/admin-sign-up.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthGuard } from './auth/auth.guard';
import { EmployeeDashboardComponent } from './pages/employee/employee-dashboard/employee-dashboard.component';
import { EmployeeLayoutComponent } from './layouts/employee-layout/employee-layout.component';
import { OneTimeAuthGuard } from './auth/oneTimeOpening.guard';
import { AlreadyAuthGuard } from './auth/already-auth.guard';
import { EmployeeManegingComponent } from './pages/employee/employee-maneging/employee-maneging.component';

const routes: Routes = [
  // {path:"", redirectTo: 'Sign-in', pathMatch: 'full' },
  {path: 'Sign-in',component: LoginComponent,canActivate: [AlreadyAuthGuard]},
  {path:"Sign-up",component:SignUpComponent},

   {
    path: "Admin",
    component: AdminLayoutComponent, canActivate: [AuthGuard] , data: { role: 'ROLE_ADMIN' } ,
    children: [
      {path:"", redirectTo: 'AdminDashboard', pathMatch: 'full' },
      {path:"AdminDashboard",component:AdminDashboardComponent},


    ]
  },



{
    path: "Employee",
    component: EmployeeLayoutComponent, canActivate: [AuthGuard] , data: { role: 'ROLE_EMPLOYEE' } ,
    children: [
      {path:"", redirectTo: 'EmployeeDashboard', pathMatch: 'full' },
      {path:"EmployeeDashboard",component:EmployeeDashboardComponent},
      {path:"EmployeeManege",component:EmployeeManegingComponent},

    
    ]
  },

{
  path: 'Admin-sign-up',
  component: AdminSignUpComponent, canActivate: [OneTimeAuthGuard]
}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
