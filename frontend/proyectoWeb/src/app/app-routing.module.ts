import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FinishComponent } from './finish/finish.component';
import { GameComponent } from './game/game/game.component';
import { HomeComponent } from './home/home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
  { path: 'home', component : HomeComponent },
  { path: '', component : LoginComponent },
  { path: 'game', component : GameComponent },
  { path: 'finish', component : FinishComponent },
  { path: 'logout', component : LogoutComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
