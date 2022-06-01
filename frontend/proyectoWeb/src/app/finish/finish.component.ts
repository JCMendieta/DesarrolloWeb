import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LogoutComponent } from '../logout/logout.component';

@Component({
  selector: 'app-finish',
  templateUrl: './finish.component.html',
  styleUrls: ['./finish.component.css']
})
export class FinishComponent implements OnInit 
{
  score : number | undefined;

  constructor(private router : Router) { }

  ngOnInit(): void 
  {
    this.score = JSON.parse(sessionStorage.getItem("score")!);
  }

  accept() : void
  {
    this.router.navigate(['logout']);
  }
}
