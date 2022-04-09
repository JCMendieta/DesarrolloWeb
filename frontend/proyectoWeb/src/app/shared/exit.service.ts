import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exit } from '../model/exit';

@Injectable({
  providedIn: 'root'
})
export class ExitService {

  constructor(private http : HttpClient) { }

  list() : Observable<Exit[]>
  {
    return this.http.get<Exit[]>("http://localhost:8080/exit_api/list");
  }
}
