import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Exit } from 'src/app/model/exit';
import { Item } from 'src/app/model/item';
import { Player } from 'src/app/model/player';
import { Playerxroom } from 'src/app/model/playerxroom';
import { Room } from 'src/app/model/room';
import { SessionService } from 'src/app/shared/session.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit 
{
  currentPlayer : Player | undefined;
  currentPlayers : Player[] = [];

  constructor(
    private router : Router,
    private sessionService : SessionService) { }

  ngOnInit(): void 
  {
    this.currentPlayer = JSON.parse(sessionStorage.getItem("currentPlayer")!);
  }

  discard (item : Item) : void 
  {
    this.sessionService.discard(this.currentPlayer as Player, item)
    .subscribe(player => 
      {
        this.currentPlayer = player;
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
      });
  }

  collect (item : Item) : void
  {
    this.sessionService.collect(this.currentPlayer as Player, item)
    .subscribe(player =>
      {
        this.currentPlayer = player;
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
      });
  }

  move (exit : Exit) : void
  {
    this.sessionService.move(this.currentPlayer as Player, exit)
    .subscribe(player =>
      {
        this.currentPlayer = player;
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
      })
  }
}
