import { Component, OnInit } from '@angular/core';
import { Exit } from 'src/app/model/exit';
import { ExitService } from 'src/app/shared/exit.service';

@Component({
  selector: 'app-exit-list',
  templateUrl: './exit-list.component.html',
  styleUrls: ['./exit-list.component.css']
})
export class ExitListComponent implements OnInit {

  exits : Exit[] = [];

  constructor(private exitService : ExitService) { }

  ngOnInit(): void 
  {
    //this.decorativeItemService.list().subscribe((dIs) => 
    //this.decorativeItems = dIs);
    this.exitService.list().subscribe((es) => this.exits = es);
  }
}
