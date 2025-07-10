export class SignUpRequest {
     commonStatus?: String;
    userId?: String;
    userName?: String;
    email?: String;
    roleDto?:{
        roleId?: String;
        roleName?: String;
    }
    password?:String;
    }