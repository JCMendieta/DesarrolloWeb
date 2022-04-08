import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Room } from '../model/room';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DecorativeItemService {

  constructor(private http : HttpClient) { }

  decorativeItemList() : Observable<Room>
  {
    return this.http.get<Room>("http://localhost:8080/decorative_item_api/list");
  }
}
