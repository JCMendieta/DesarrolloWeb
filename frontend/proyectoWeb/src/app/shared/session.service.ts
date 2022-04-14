import { HttpClient, HttpHeaderResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Player } from '../model/player';

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
    return this.http.get<Player>("http://localhost:8080/player_api/spawn" + player.id);
  }
}
