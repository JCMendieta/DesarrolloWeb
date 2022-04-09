import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { concat, map, merge, mergeMap, pipe, switchMap } from 'rxjs';
import { DecorativeItem } from 'src/app/model/decorative-item';
import { DecorativeItemService } from 'src/app/shared/decorative-item.service';

@Component({
  selector: 'app-decorative-item-list',
  templateUrl: './decorative-item-list.component.html',
  styleUrls: ['./decorative-item-list.component.css']
})
export class DecorativeItemListComponent implements OnInit {

  decorativeItems : DecorativeItem[] = [];

  constructor(
    private decorativeItemService : DecorativeItemService, 
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void 
  {
    this.decorativeItemService.list().subscribe((dIs) => this.decorativeItems = dIs);
    /*this.route.paramMap.pipe(mergeMap(params =>
      this.decorativeItemService.list() // +(params.get('id') || 1)
    )).subscribe(dIs => this.decorativeItems = dIs);*/
  }
}