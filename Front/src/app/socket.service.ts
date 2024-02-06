import { Injectable } from '@angular/core';
import { Socket } from 'ngx-socket-io';


@Injectable({
  providedIn: 'root'
})
export class SocketService {
  constructor(private socket: Socket) { }

  connect(): void {
    this.socket.connect();
  }

  disconnect(): void {
    this.socket.disconnect();
  }

  sendMessage(message: any): void {
    this.socket.emit('message', message);
  }

  receiveMessages(): any {
    return this.socket.fromEvent('message');
  }
}
