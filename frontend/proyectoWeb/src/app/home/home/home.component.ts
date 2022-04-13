import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from 'src/app/login/login/login.component';
import { Player } from 'src/app/model/player';
import { SessionService } from 'src/app/shared/session.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit 
{

  currentPlayer : Player | undefined;
  player : boolean = true;
  designer : boolean = true;
  admin : boolean = true;

  constructor(
    private router : Router, 
    private sessionService : SessionService
    ) { }

  ngOnInit(): void 
  {
    this.currentPlayer = JSON.parse(sessionStorage.getItem("currentPlayer")!);

    if (this.currentPlayer?.role == 'PLAYER')
    {
      this.player = false;
    }
    else if (this.currentPlayer?.role == 'DESIGNER')
    {
      this.designer = false;
    }
    else
    {
      this.admin = false;
    }
  }

  startGame() : void
  {
    this.sessionService.spawn(this.currentPlayer as Player)
    .subscribe(player => {
      this.currentPlayer = player;
      sessionStorage.setItem("currentPlayer", JSON.stringify(player));
      this.router.navigate(['game']);
    });


  }
}
