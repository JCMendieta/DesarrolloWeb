import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, switchMap } from 'rxjs';
import { DecorativeItem } from 'src/app/model/decorative-item';
import { Exit } from 'src/app/model/exit';
import { Item } from 'src/app/model/item';
import { Monster } from 'src/app/model/monster';
import { Player } from 'src/app/model/player';
import { Room } from 'src/app/model/room';
import { MonsterService } from 'src/app/shared/monster.service';
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
  decorativeItems : DecorativeItem [] = [];
  monster : Monster | undefined;
  exits : Exit[] = [];
  players : Player[] = [];

  constructor(
    private roomService: RoomService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void 
  {
    this.route.paramMap.pipe(switchMap(params => this.roomService.view(+ params.get('id')!))).subscribe(r => this.room = r);
    this.route.paramMap.pipe(switchMap(params => this.roomService.viewCurrentItems(+ params.get('id')!))).subscribe(is => this.items = is);
    this.route.paramMap.pipe(switchMap(params => this.roomService.viewCurrentDecorativeItems(+ params.get('id')!))).subscribe(dis => this.decorativeItems = dis);
    this.route.paramMap.pipe(switchMap(params => this.roomService.viewMonster(+ params.get('id')!))).subscribe(m => this.monster = m);
    this.route.paramMap.pipe(switchMap(params => this.roomService.viewCurrentExits(+ params.get('id')!))).subscribe(es => this.exits = es);
    this.route.paramMap.pipe(switchMap(params => this.roomService.viewCurrentPlayers(+ params.get('id')!))).subscribe(ps => this.players = ps);
  }
}
