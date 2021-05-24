import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  loggedIn: Boolean = false;

  constructor() {
    console.log("nav-bar");
    if (localStorage.getItem("token") != undefined) {
      this.loggedIn = true
    }
  }

  ngOnInit(): void {
  }

}
