import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { GameComponent } from './game/game/game.component';
import { HomeComponent } from './home/home/home.component';
import { LoginComponent } from './login/login/login.component';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomViewComponent } from './room/room-view/room-view.component';

const routes: Routes = [
  { path: 'home', component : HomeComponent },
  { path: 'login', component : LoginComponent },
  { path: 'game', component : GameComponent },
  { path: 'room/list', component : RoomListComponent },
  { path: 'room/view/:id', component : RoomViewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
