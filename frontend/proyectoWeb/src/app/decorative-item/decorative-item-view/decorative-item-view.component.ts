import { Component, OnInit } from '@angular/core';
import { map, merge, mergeMap, pipe } from 'rxjs';
import { DecorativeItem } from 'src/app/model/decorative-item';
import { DecorativeItemService } from 'src/app/shared/decorative-item.service';

@Component({
  selector: 'app-decorative-item-view',
  templateUrl: './decorative-item-view.component.html',
  styleUrls: ['./decorative-item-view.component.css']
})
export class DecorativeItemViewComponent implements OnInit {

  decorativeItem: DecorativeItem = new DecorativeItem(0, "", null);

  constructor(private decorativeItemService : DecorativeItemService) { }

  ngOnInit(): void 
  {
    this.decorativeItemService.decorativeItemList().subscribe((dI => {
      //Hacer algo
    }));
  }
}