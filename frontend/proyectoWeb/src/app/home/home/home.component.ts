import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MonsterType } from 'src/app/model/monster-type';
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
  logBook : String[] = [];
  monsterType : MonsterType | undefined;
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
    this.logBook = JSON.parse(sessionStorage.getItem("logBook")!);

    if (this.currentPlayer?.role == 'ROLE_PLAYER')
    {
      this.player = false;
    }
    else if (this.currentPlayer?.role == 'ROLE_DESIGNER')
    {
      this.designer = false;
    }
    else if (this.currentPlayer?.role == 'ROLE_ADMIN')
    {
      this.admin = false;
    }
    else
    {
      this.router.navigate(['login']);
    }
  }

  start() : void
  {
    this.sessionService.spawn(this.currentPlayer as Player)
    .subscribe(player => {
      this.currentPlayer = player;
      sessionStorage.setItem("currentPlayer", JSON.stringify(player));
      sessionStorage.setItem("logbook", JSON.stringify(this.logBook));
      this.router.navigate(['game']);
    });
  }
}
