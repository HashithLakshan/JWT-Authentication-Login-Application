import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8081/api/user'; // Adjust to your backend URL

  constructor(private http: HttpClient) {}

  getAllUsers(
    commonStatus: string,
    page: number,
    size: number
  ): Observable<any> {
    return this.http.get<any>(
      `${this.apiUrl}/getAllUsers?commonStatus=${commonStatus}&page=${page}&size=${size}`
    );
  }
  updateStatus(commonStatus: string, userId: string): Observable<any> {
    return this.http.put<any>(
      `${this.apiUrl}/updateStatus?commonStatus=${commonStatus}&userId=${userId}`,
      null
    );
  }

}
