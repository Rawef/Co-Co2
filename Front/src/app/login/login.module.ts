import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './component/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';  
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { PreferancesComponent } from './component/preferances/preferances.component';
import { ProfileComponent } from './component/profile/profile.component';
import { UpdateComponent } from './component/update/update.component';
import { ChatComponent } from './component/chat/chat.component';
import { CovoiturageModule } from '../covoiturage/covoiturage.module';




@NgModule({
  declarations: [
    LoginComponent,
    PreferancesComponent,
    ProfileComponent,
    UpdateComponent,
    ChatComponent,
    
    
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    CovoiturageModule
    
  ],
  exports:[
    LoginComponent,
    PreferancesComponent,
    ProfileComponent,
    UpdateComponent,
    CovoiturageModule,
  ]
})
export class LoginModule { }
