import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DecorativeItem } from '../model/decorative-item';
import { Exit } from '../model/exit';
import { Item } from '../model/item';
import { Monster } from '../model/monster';
import { Player } from '../model/player';
import { Room } from '../model/room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http : HttpClient) { }

  list() : Observable<Room[]>
  {
    return this.http.get<Room[]>("http://localhost:8080/room_api/list");
  }

  view(id : number) : Observable<Room>
  {
    return this.http.get<Room>("http://localhost:8080/room_api/view/" + id);
  }

  viewCurrentItems(id : number) : Observable<Item[]>
  {
    return this.http.get<Item[]>("http://localhost:8080/room_api/view_item_list/" + id);
  }

  viewCurrentDecorativeItems(id : number) : Observable<DecorativeItem[]>
  {
    return this.http.get<DecorativeItem[]>("http://localhost:8080/room_api/view_decorativeItem_list/" + id);
  }

  viewMonster(id : number) : Observable<Monster>
  {
    return this.http.get<Monster>("http://localhost:8080/room_api/view_monster/" + id);
  }

  viewCurrentExits(id : number) : Observable<Exit[]>
  {
    return this.http.get<Exit[]>("http://localhost:8080/room_api/view_exit_list/" + id);
  }

  viewCurrentPlayers(id : number) : Observable<Player[]>
  {
    return this.http.get<Player[]>("http://localhost:8080/room_api/view_player_list/" + id);
  }
}
