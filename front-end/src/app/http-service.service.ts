import {Injectable, Output, EventEmitter} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {User} from "./sign-up/user";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {
  response: Number;

  url: String = "http://localhost:8080";
  header: HttpHeaders = new HttpHeaders()
    .append("Access-Control-Allow-Origin", "*")
    .append("Access-Control-Allow-Credentials", "true")
    .append("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
    .append("Access-Control-Max-Age", "600")
    .append("Access-Control-Allow-Headers", "Accept, Content-Type, Content-Length, Accept-Encoding, X-CSRF-Token, Authorization")

  constructor(private http: HttpClient) {
  }

  signUp(value: User, role: String) {
    this.http.post(`${this.url}/api/user/signUp?role=${role.toUpperCase()}`, value, {headers: this.header}).toPromise().then((res: any) => {
      console.log(res);
    }).catch((err: any) => {
      console.log(err)
    })
  }

  // @ts-ignore
  upload(file: any): Promise<any> {

    this.header.append("Content-Type", "multipart/form-data")
    this.header.append("Accept","application/json")


    const data = new FormData();
    data.append("resume", file, file.name)

    return new Promise<any>((resolve, reject) => {
      this.http.post(`${this.url}/api/user/resume`, data ,{headers: this.header}).toPromise().then(x => {
        resolve(x);
      }).catch(x => {
        reject(x);
      });
    });
  }
}
