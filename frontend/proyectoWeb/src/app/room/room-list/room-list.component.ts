import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/model/room';
import { RoomService } from 'src/app/shared/room.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  rooms : Room[] = [];

  constructor(private roomService : RoomService) { }

  ngOnInit(): void 
  {
    this.roomService.list().subscribe((rs) => this.rooms = rs);
  }
}
