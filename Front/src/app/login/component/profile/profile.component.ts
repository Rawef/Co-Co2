import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../services/service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  loggedInUser: any;
  

  constructor(private router: Router,private service: ServiceService )
  {
  }

  ngOnInit() {
    this.service.getLoggedInUser().subscribe(user => {
      this.loggedInUser = user;
    });

  }

  updateProfile() {
    this.service.updateUser(this.loggedInUser).subscribe(
      updatedUser => {
        console.log('User updated successfully:', updatedUser);
        // You can perform additional actions if needed
      },
      error => {
        console.error('Error updating user:', error);
        // Handle the error as needed
      }
    );
  }
  navigateToUpdate() {
    this.router.navigate(['/update']);
  }
  

}
