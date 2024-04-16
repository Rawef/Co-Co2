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
import { EventComponent } from './eventt/event.component'; // Import the EventComponent
import { EventtModule } from './eventt/eventt.module';
import { from } from 'rxjs';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { QRCodeModule } from 'angularx-qrcode';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { QrDialogComponent } from './qr-dialog/qr-dialog.component';





const config: SocketIoConfig = { url: 'http://localhost:8089', options: {} };



@NgModule({
  declarations: [
    AppComponent,
    EventComponent,
    QrDialogComponent,
    
  
  
    
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
    SocketIoModule.forRoot(config),
    EventtModule,
    MatSnackBarModule,
    QRCodeModule,
    MatIconModule,
    MatDialogModule,
    
    

  ],
  providers: [
    provideClientHydration(),
    SocketService,
    provideAnimationsAsync()
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
