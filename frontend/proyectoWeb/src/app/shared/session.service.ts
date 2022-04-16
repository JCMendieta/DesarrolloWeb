import { HttpClient, HttpHeaderResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exit } from '../model/exit';
import { Item } from '../model/item';
import { Player } from '../model/player';
import { Room } from '../model/room';
import { Monster} from '../model/monster';

@Injectable({
  providedIn: 'root'
})

export class SessionService 
{
  httpOptions = {
    headers : new HttpHeaders(
      {"Content-Type" : "application/json"}
      )
  };

  constructor(private http : HttpClient) { }

  logIn (username: string, password: string) : Observable<Player>
  {
    return this.http.get<Player>("http://localhost:8080/player_api/" + username + "/" + password);
  }

  spawn (player : Player) : Observable<Player>
  {
    return this.http.get<Player>("http://localhost:8080/player_api/spawn/" + player.id);
  }

  discard (player : Player, item : Item) : Observable<Player>
  {
    return this.http.get<Player>("http://localhost:8080/player_api/discard/" + player.id + "/" + item.id);
  }

  collect (player : Player, item : Item) : Observable<Player>
  {
    return this.http.get<Player>("http://localhost:8080/player_api/collect/" + player.id + "/" + item.id);
  }

  move (player : Player, exit : Exit) : Observable<Player>
  {
    return this.http.get<Player>("http://localhost:8080/player_api/move/" + player.id + "/" + exit.id);
  }

  attack (player : Player, rMonster : Monster) : Observable<Player>
  {
    return this.http.get<Player>("http://localhost:8080/player_api/attack/" + player.id + "/" + rMonster.id);
  }

  players (room : Room) : Observable<Player[]>
  {
    return this.http.get<Player[]>("http://localhost:8080/player_api/currentPlayers/" + room.id);
  }

  finish (player : Player) : Observable<number>
  {
    return this.http.get<number>("http://localhost:8080/player_api/finish/" + player.id);
  }
}
