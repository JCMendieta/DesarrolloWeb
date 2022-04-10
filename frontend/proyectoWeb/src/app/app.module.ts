import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DecorativeItemListComponent } from './decorative-item/decorative-item-list/decorative-item-list.component';
import { HttpClientModule } from '@angular/common/http';
import { ExitListComponent } from './exit/exit-list/exit-list.component';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomViewComponent } from './room/room-view/room-view.component';

@NgModule({
  declarations: [
    AppComponent, 
    DecorativeItemListComponent, 
    ExitListComponent, 
    RoomListComponent, 
    RoomViewComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
