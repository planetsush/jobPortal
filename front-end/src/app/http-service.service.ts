import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

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

  post(url: String, body: any, params: HttpParams, headers: HttpHeaders): Promise<any> {

      return new Promise<any>((resolve, reject) => {
        this.http.post(`${this.url}${url}`, body, {params: params, headers: headers}).toPromise().then( x => {
          resolve(x);
        }).catch( x => {
          reject(x);
        })
      });
  }

  // @ts-ignore
  upload(file: any): Promise<any> {

    this.header.append("Content-Type", "multipart/form-data")
    this.header.append("Accept", "application/json")


    const data = new FormData();
    data.append("resume", file, file.name)

    return new Promise<any>((resolve, reject) => {
      this.http.post(`${this.url}/api/user/resume`, data, {headers: this.header}).toPromise().then(x => {
        resolve(x);
      }).catch(x => {
        reject(x);
      });
    });
  }

  login(value: any): Promise<any> {

    const params: HttpParams = new HttpParams();
    params.append("grant_type", "password");
    params.append("username", value.username);
    params.append("password", value.password);

    return new Promise<any>( (resolve, reject) => {
      this.http.post(`http://prishita:prishita-secret@localhost:8080/oauth/token?grant_type=password&username=${value.username}&password=${value.password}`,
        null, {headers: this.header})
        .toPromise().then( res => {
          resolve(res);
      }).catch( res => {
        reject(res);
      });
    });
  }
}
