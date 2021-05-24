import { Component, OnInit } from '@angular/core';
import { User } from "./user";
import { HttpServiceService } from "../http-service.service"
import {HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  role: String = "employer";
  resume: Number;
  file: any;

  constructor(private httpService: HttpServiceService, private router: Router) {
    console.log("inside constructor")
    if (localStorage.getItem("token") != undefined) {
      router.navigate([""])
    }
  }

  ngOnInit(): void {
  }

  onClickSubmit(value: User) {
    console.log(value.name)
    console.log(value.company)

    value.resume = this.resume

    const params = new HttpParams();
    // @ts-ignore
    params.append("role", value.role)


    this.httpService.post(`/api/user/signUp?role=${value.role}`,value, params, null).then( res => {
      console.log(res);
      this.httpService.login({"username": value.email, "password": value.password}).then( res => {
        localStorage.setItem("token", res.access_token);
        this.router.navigate([""]);
      }).catch()
    }).catch( res => {
      alert(res.error.error_description);
    });
  }

  getFile($event: Event): void {

    // @ts-ignore
    if (event.target.files && event.target.files[0]) {
      // @ts-ignore
      this.file = event.target.files[0];
    }

  }

  upload() {
    this.httpService.upload(this.file).then(res => {
      this.resume = res
    });
  }
}
