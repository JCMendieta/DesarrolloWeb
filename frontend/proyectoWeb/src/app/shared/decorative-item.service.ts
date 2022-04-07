import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DecorativeItem } from '../model/decorative-item';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DecorativeItemService {

  constructor(private http : HttpClient) { }

  decorativeItemList() : Observable<DecorativeItem>
  {
    return this.http.get<DecorativeItem>("http://localhost:8080/decorative_item_api/list");
  }
}
