import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../services/service.service';
import { Message } from '../../Model/Message';
import { SocketService } from '../../../socket.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {

  messages: Array<Message> = [];
  newMessage: string = '';
  private channel: string = '';
  connectedUsers: any[] = []; // Provide an initial value


  constructor(private socketService: SocketService , private service: ServiceService) { }

  ngOnInit(): void {
    // Subscribe to messages from the service
   

    // Get connected users
    this.service.getConnectedUsers().subscribe((users) => {
      this.connectedUsers = users;
    });
  }

  ngOnDestroy(): void {
    this.socketService.disconnect();
  }

  sendMessage(): void {
    const message = 'Your message content';
    this.socketService.sendMessage(message);
  }


  
}
