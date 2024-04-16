import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';

import { AppModule } from './app.module';
import { AppComponent } from './app.component';
import { EventtModule } from './eventt/eventt.module';



@NgModule({
  imports: [
    
    ServerModule,
    EventtModule,
   
  ],
  bootstrap: [AppComponent],
})
export class AppServerModule {}
