import { HomeModule } from './home/home.module';
import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { profile } from 'console';
import { PanierModule } from './panier/panier.module';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/component/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginModule } from './login/login.module';
import { SocketIoModule, SocketIoConfig } from 'ngx-socket-io';
import { SocketService } from './socket.service';
import { CovoiturageModule } from './covoiturage/covoiturage.module';
import { DetailedAnnonceComponent } from './covoiturage/components/detailed-annonce/detailed-annonce.component';


const config: SocketIoConfig = { url: 'http://localhost:8089', options: {} };



@NgModule({
  declarations: [
    AppComponent,
   
    
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    SharedModule,
    PanierModule,
    FormsModule,
    HomeModule,
    LoginModule,
    CovoiturageModule,
    SocketIoModule.forRoot(config),
 
    
    
    

  ],
  providers: [
    provideClientHydration(),
    SocketService
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
