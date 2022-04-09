import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
}
