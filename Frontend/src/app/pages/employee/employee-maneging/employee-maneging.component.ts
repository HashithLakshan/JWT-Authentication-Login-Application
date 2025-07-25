import { Component } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/shared/user';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-employee-maneging',
  templateUrl: './employee-maneging.component.html',
  styleUrls: ['./employee-maneging.component.css']
})
export class EmployeeManegingComponent {

  user : User = {
     commonStatus: "",
    userId: "",
    userName: "",
    email: "",
    roleDto:{
        roleId: "",
        roleName: ""
    }
  }

  collectionSize: number = 0;
  page: number = 1;
  pageSize: number = 5;

  userDetails: User[] = [];

  constructor(private userService : UserService){
this.getAllUsers( "ACTIVE");
  }

   getAllUsers(commonStatus: "ACTIVE") {
    this.userService.getAllUsers(commonStatus, this.page - 1, this.pageSize).subscribe(
      (response) => {
        this.userDetails = response.payload[0];

        if (response.pages && response.pages.length > 0) {
          const pagination = response.pages[0];

          this.collectionSize = pagination.totalItems; // Total number of items
          this.pageSize = 10; // Number of items per page (should match backend size)

          console.log("Total Items:", this.collectionSize, "Page Size:", this.pageSize);
        } else {
          console.warn("Pagination details not found in response");
        }
      },
      (error) => {
        console.error("Error fetching users:", error);
      }
    );
  }

  updateStatus(commonStatus: "ACTIVE", userId: string) {
  this.userService.updateStatus(commonStatus, userId).subscribe(
(response)=>{
  if(response.status === true){
    Swal .fire ("",response.commonMessage,"success")
  }else{
        Swal .fire ("",response.commonMessage,"success")
  }
} ,(error) => {
          Swal .fire ("","Error fetching users:", error)      }
  )
}
  get totalPages(): number {
  return Math.ceil(this.collectionSize / this.pageSize);
}

onPageChange(newPage: number) {
  this.page = newPage;
  // Fetch your data for the new page here, using this.page and this.pageSize
this.getAllUsers( "ACTIVE");
}
}

