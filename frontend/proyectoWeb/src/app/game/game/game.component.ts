import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Exit } from 'src/app/model/exit';
import { Item } from 'src/app/model/item';
import { Player } from 'src/app/model/player';
import { SessionService } from 'src/app/shared/session.service';
import { Monster } from 'src/app/model/monster';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})

export class GameComponent implements OnInit 
{
  currentPlayer : Player | undefined;
  currentPlayers : Player[] = [];
  logBook : String[] = [];
  monsterHp : any;
  monsterHpafterHit : any; 

  constructor(
    private router : Router,
    private sessionService : SessionService) { }

  ngOnInit(): void 
  {
    this.currentPlayer = JSON.parse(sessionStorage.getItem("currentPlayer")!);
    this.logBook = JSON.parse(sessionStorage.getItem("loogBook")!);
    sessionStorage.setItem("logBook",JSON.stringify(this.logBook));

    this.players();
  }

  discard (item : Item) : void 
  {
    this.sessionService.discard(this.currentPlayer as Player, item)
    .subscribe(player => 
      {
        this.currentPlayer = player;
        this.logBook.push(this.currentPlayer.name +" drop "+ item.name +" to the floor of the room.");
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
        //if(this.currentPlayer.maxWeight < this.currentPlayer.)
      });
  }

  collect (item : Item) : void
  {
    this.sessionService.collect(this.currentPlayer as Player, item)
    .subscribe(player =>
      {
        this.currentPlayer = player;
        
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
        if(this.currentPlayer.weight + item.weight > this.currentPlayer.maxWeight){
          this.logBook.push(this.currentPlayer.name +" tried to pick "+ item.name +" from the room, but is carrying too much weight!!.");
        }
        else if(this.currentPlayer.idRoom.rMonster != null){
          this.logBook.push(this.currentPlayer.name + "tried to pick "+item.name + " from the room, but " + this.currentPlayer.idRoom.rMonster.idMonsterType.name + " is watching...")
        }
        else{
          this.logBook.push(this.currentPlayer.name +" took "+ item.name +" from the room.");
        }
      });
  }

  move (exit : Exit) : void
  {
    this.sessionService.move(this.currentPlayer as Player, exit)
    .subscribe(player =>
      {
        this.currentPlayer = player;
        this.logBook.push(this.currentPlayer.name +" moved through the exit "+ exit.id +" to the next room.");
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
      })
  }

  attack (rMonster : Monster) : void
  {
    this.monsterHp = rMonster.hitpoints;

    this.sessionService.attack(this.currentPlayer as Player, rMonster)
    .subscribe(player =>
      {
        this.currentPlayer = player;
        sessionStorage.setItem("currentPlayer", JSON.stringify(player));
        this.logBook.push(this.currentPlayer.name +" attacked "+this.currentPlayer.idRoom.rMonster.idMonsterType.name+" and inflicted "+(this.monsterHp-this.currentPlayer.idRoom.rMonster.hitpoints)+ " of damage.");
        sessionStorage.setItem("logBook",JSON.stringify(this.logBook));
      })
  }

  players () : void
  {
    this.sessionService.players(this.currentPlayer?.idRoom!)
    .subscribe(ps => 
      this.currentPlayers = ps);
  }

  finish () : void
  {
    this.sessionService.finish(this.currentPlayer as Player)
    .subscribe(score => {
      sessionStorage.setItem("score", JSON.stringify(score));
      this.router.navigate(['finish']);
    });
  }
}
