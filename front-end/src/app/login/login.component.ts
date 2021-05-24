import {Component, OnInit} from '@angular/core';
import {HttpServiceService} from "../http-service.service";
import * as url from "url";
import {HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUrl: String = "http://prishita:prishita-secret@localhost:8080/oauth/token?"
  constructor(private httpService: HttpServiceService, private router: Router) {
    console.log("inside constructor")
    if (localStorage.getItem("token") != undefined) {
      router.navigate([""])
    }
  }

  ngOnInit(): void {
  }

  onClickLogin(value: any) {

    this.httpService.login(value).then( res => {
      if (res.access_token != null) {
        localStorage.setItem("token", res.access_token);
        console.log(localStorage.getItem("token"));
        this.router.navigate(["/"])
      }
    }).catch( res => {
      alert(res.error.error_description);
    })
  }

}
