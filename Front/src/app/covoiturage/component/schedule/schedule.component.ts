import { Component, OnInit } from '@angular/core';
import { ScheduleService } from '../../service/schedule.service';
import { ServiceService } from '../../../login/services/service.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrl: './schedule.component.css'
})
export class ScheduleComponent  implements OnInit{

  scheduleData: any[] = [];

  constructor(private service:ScheduleService,private servicee:ServiceService) { }

  ngOnInit(): void {
    this.servicee.getLoggedInUser().subscribe(user => {
      if (user) {
        this.getSchedule(user.id); // Assuming 'id' is the property that holds the user's ID
      }
    });
  }

  getSchedule(userId: number): void {
    this.service.getschedule(userId).subscribe(data => {
      this.scheduleData = data;
    });
  }



}
