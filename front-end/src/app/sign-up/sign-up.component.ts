import { Component, OnInit } from '@angular/core';
import { User } from "./user";
import { HttpServiceService } from "../http-service.service"

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  role: String = "employer";
  resume: Number;
  file: any;

  constructor(private httpService: HttpServiceService) { }

  ngOnInit(): void {
  }

  onClickSubmit(value: User) {
    console.log(value.name)
    console.log(value.company)


    value.resume = this.resume
    this.httpService.signUp(value, value.role);
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
