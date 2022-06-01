import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../shared/authentication.service';
import { SessionService } from '../shared/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''
  invalidLogin = false
  logBook : String[] = [];
  
  @Input() error: string | null | undefined;

  constructor(private router: Router,
    private loginservice: AuthenticationService, private sessionService : SessionService) { }

  ngOnInit() {
  }

  checkLogin() {
    this.sessionService.logIn(this.username, this.password).subscribe(
      (player) => {
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
        sessionStorage.setItem("loogBook", JSON.stringify(this.logBook));
      });

    (this.loginservice.authenticate(this.username, this.password).subscribe(
      data => {
        this.router.navigate(['home'])
        this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true
        this.error = error.message;

      }
    )
    );

  }

}