import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
private baseUrl = 'http://localhost:8081/api/auth'; // Adjust to your backend URL

  constructor(private http: HttpClient) {}

  login(payload : any):Observable<any> {
    return this.http.post(`${this.baseUrl}/signIn`, payload);
  }

  register(payload : any) :Observable<any>{
    return this.http.post(`${this.baseUrl}/signUp`, payload);
  }

  isHaveAdmin(eRole:any):Observable<any>{
              return this.http.get<any>(`${this.baseUrl}/isHaveAdmin`, { params: { eRole } });
            }

  logout() {
    localStorage.clear(); 
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
