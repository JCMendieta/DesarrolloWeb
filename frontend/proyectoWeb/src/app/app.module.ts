import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DecorativeItemListComponent } from './decorative-item/decorative-item-list/decorative-item-list.component';
import { HttpClientModule } from '@angular/common/http';
import { ExitListComponent } from './exit/exit-list/exit-list.component';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomViewComponent } from './room/room-view/room-view.component';
import { LoginComponent } from './login/login/login.component';
import { HomeComponent } from './home/home/home.component';
import { GameComponent } from './game/game/game.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent, 
    DecorativeItemListComponent, 
    ExitListComponent, 
    RoomListComponent, 
    RoomViewComponent, LoginComponent, HomeComponent, GameComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
