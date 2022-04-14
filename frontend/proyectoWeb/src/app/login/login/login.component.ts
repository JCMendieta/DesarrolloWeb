import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/shared/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit 
{
  username : string = "";
  password : string = "";

  constructor(
    private router : Router,
    private sessionService : SessionService) { }

  ngOnInit(): void 
  {
  }

  logIn() : void 
  {
    console.log(this.username + ' - ' + this.password);
    this.sessionService.logIn(this.username, this.password).subscribe(
      (player) => {
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
        this.router.navigate(['home']);
      });
  }
}
