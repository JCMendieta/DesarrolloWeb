import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-finish',
  templateUrl: './finish.component.html',
  styleUrls: ['./finish.component.css']
})
export class FinishComponent implements OnInit 
{
  score : number | undefined;

  constructor() { }

  ngOnInit(): void 
  {
    this.score = JSON.parse(sessionStorage.getItem("score")!);
  }
}
