import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, Subject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Message } from '../Model/Message';
import { Md5 } from 'ts-md5';



@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private messages: Array<Message> = [];
    private msgs = new Subject<Array<Message>>();
    private channel = new Subject<string>();


  private baseUrl = 'http://localhost:8089';

  private loggedInUserSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(private http:HttpClient) { }

  loginUser(loginData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, loginData)
      .pipe(
        tap(user => this.loggedInUserSubject.next(user))
      );
  }

  setLoggedInUser(user: any): void {
    this.loggedInUserSubject.next(user);
  }

  getLoggedInUser(): Observable<any> {
    return this.loggedInUserSubject.asObservable();
  }

  

  registerUser(userData: any): Observable<any> {
    // Assurez-vous de passer les données utilisateur (userData) à la méthode post
    return this.http.post(`${this.baseUrl}/save`, userData);
  }

  getLoggedInUserId(): number {
    const loggedInUser = this.loggedInUserSubject.value;
    return loggedInUser ? loggedInUser.id : null;
  }

  updatePreferences(selectedPreferences: any[]): Observable<any> {
    const userId = this.getLoggedInUserId();

    if (userId) {
      // Assuming your API endpoint for updating preferences is /add-preferences/{userId}
      return this.http.post(`${this.baseUrl}/add-preferences/${userId}`, selectedPreferences);
    } else {
      // Handle case where user is not logged in
      return throwError('User not logged in');
    }
  }

  getUserPreferences(): Observable<any> {
    const userId = this.getLoggedInUserId();

    if (userId) {
      // Assuming your API endpoint for getting preferences is /getPreferences/{userId}
      return this.http.get(`${this.baseUrl}/getPreferences/${userId}`);
    } else {
      // Handle case where user is not logged in
      return throwError('User not logged in');
    }
  }
  updateUser(userData: any): Observable<any> {
    const userId = this.getLoggedInUserId();
    if (userId) {
      return this.http.put(`${this.baseUrl}/update/${userId}`, userData);
    } else {
      return throwError('Update failed: User not logged in');
    }
  }

getUserProfile(userId: number): Observable<any> {
  return this.http.get(`${this.baseUrl}/${userId}`).pipe(
    catchError((error) => {
      console.error('Error fetching user profile:', error);
      return throwError('Failed to fetch user profile');
    })
  );
}

getConnectedUsers(): Observable<any> {
  // Assuming the endpoint for connected users is /listUsers
  const url = `${this.baseUrl}/listUsers`;
  return this.http.get<any>(url);
}



// Send a message
sendMessage(message: any): Observable<any> {
  return this.http.post(`${this.baseUrl}/messages`, message);
}


pushMessage(message: Message) {
  this.messages.push(message);
  this.msgs.next(this.messages);
}



sendReadReceipt(channelId: string, username: string) {
  this.http.post(this.baseUrl + '/messages/', {
      channel: channelId,
      username: username
  });
}

getMessages(): Observable<any> {
  return this.msgs.asObservable();
}

public static createChannel(user1: string, user2: string): string {
  let combined: string = '';

  if (user1 < user2) {
    combined = user1 + user2;
  } else {
    combined = user2 + user1;
  }

  return Md5.hashStr(combined).toString();
}

refreshChannel(channel: string) {
  this.channel.next(channel);
}

removeChannel() {
  this.channel.next('');
}

getChannel(): Observable<any> {
  return this.channel.asObservable();
}


}
