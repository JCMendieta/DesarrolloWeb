import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, switchMap } from 'rxjs';
import { Item } from 'src/app/model/item';
import { Room } from 'src/app/model/room';
import { RoomService } from 'src/app/shared/room.service';

@Component({
  selector: 'app-room-view',
  templateUrl: './room-view.component.html',
  styleUrls: ['./room-view.component.css']
})

export class RoomViewComponent implements OnInit 
{

  room : Room | undefined;
  items : Item[] = [];

  constructor(
    private roomService: RoomService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void 
  {
    this.route.paramMap.pipe(switchMap(params => this.roomService.view(+ params.get('id')!))).subscribe(r => this.room = r);
    this.route.paramMap.pipe(switchMap(params => this.roomService.viewCurrentItems(+ params.get('id')!))).subscribe(is => this.items = is);
  }
}
