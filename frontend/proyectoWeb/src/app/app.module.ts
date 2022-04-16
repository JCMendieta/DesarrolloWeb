import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login/login.component';
import { HomeComponent } from './home/home/home.component';
import { GameComponent } from './game/game/game.component';
import { FormsModule } from '@angular/forms';
import { FinishComponent } from './finish/finish.component';

@NgModule({
  declarations: [
    AppComponent, 
    LoginComponent, 
    HomeComponent, 
    GameComponent,
    FinishComponent,
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
