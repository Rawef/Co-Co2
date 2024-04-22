import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketComponent } from './ticket/ticket.component';
import { QRCodeModule } from 'angularx-qrcode';
import { CalendrierComponent } from './calendrier/calendrier.component';



@NgModule({
  declarations: [
    TicketComponent,
    CalendrierComponent
  ],
  imports: [
    CommonModule,
    QRCodeModule,

    
  ],
  providers: [],
})
export class EventtModule { }
