import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Player } from 'src/app/model/player';
import { SessionService } from 'src/app/shared/session.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit 
{
  currentPlayer : Player | undefined;

  constructor(
    private router : Router,
    private sessionService : SessionService) { }

  ngOnInit(): void 
  {
    this.currentPlayer = JSON.parse(sessionStorage.getItem("currentPlayer")!);
  }
}
