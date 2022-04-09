import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DecorativeItem } from '../model/decorative-item';

@Injectable({
  providedIn: 'root'
})
export class DecorativeItemService {

  constructor(private http : HttpClient) { }

  list() : Observable<DecorativeItem[]>
  {
    return this.http.get<DecorativeItem[]>("http://localhost:8080/decorative_item_api/list");
  }
}
