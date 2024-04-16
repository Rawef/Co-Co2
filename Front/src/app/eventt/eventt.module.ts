import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketComponent } from './ticket/ticket.component';
import { QRCodeModule } from 'angularx-qrcode';


@NgModule({
  declarations: [
    TicketComponent
  ],
  imports: [
    CommonModule,
    QRCodeModule
  ]
})
export class EventtModule { }
