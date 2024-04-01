import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../../service/annonce.service';
import { ServiceService } from '../../../login/services/service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mesannonces',
  templateUrl: './mesannonces.component.html',
  styleUrl: './mesannonces.component.css'
})
export class MesannoncesComponent implements OnInit {
  annonces: any[] = [];

  constructor(private annonceService: AnnonceService, private userService: ServiceService) { }

  ngOnInit(): void {
    this.getAnnonces();
  }

  getAnnonces(): void {
    this.userService.getLoggedInUser().subscribe(
      (user: any) => {
        const userId = user.id;
        this.annonceService.afficherannonces(userId).subscribe(
          annonces => { 
            this.annonces = annonces;
          },
          (error) => {
            console.error('Error fetching annonces:', error);
          }
        );
      },
      (error) => {
        console.error('Error retrieving user information:', error);
      }
    );
  }

 /* deleteAnnonce(userId: number, ida: number): void {
    // Call the service to delete the announcement directly
    this.annonceService.deleteAnnonce(userId, ida).subscribe(
      () => {
        console.log('Announcement deleted successfully.');
        // Optionally, refresh the list of announcements after deletion
        this.getAnnonces();
      },
      (error) => {
        console.error('Error deleting annonce:', error);
      }
    );
  }*/
  deleteAnnonce(userId: number, ida: number): void {
    console.log('Deleting announcement with userId:', userId, ' and ida:', ida);
    
    // First, check if there are old reservations
    this.annonceService.deleteReservation(ida).subscribe(
        (response: any) => {
            // Check the response to determine the action
            if (typeof response === 'string' && response.includes('less than 48 hours ago')) {
                // There are reservations made less than 48 hours ago
                alert("Cannot delete AnnonceCov because there are reservations made less than 48 hours ago.");
            } else {
                // Old reservations deleted successfully or no old reservations found
                console.log('Old reservations deleted successfully for announcement with ID', ida);
                
                // Now, delete the announcement
                this.annonceService.deleteAnnonce(userId, ida).subscribe(
                    () => {
                        // Announcement deleted successfully
                        console.log('Announcement deleted successfully.');
                        this.getAnnonces();
                    },
                    (error) => {
                        console.error('Error deleting announcement:', error);
                        alert("Deletion failed because of an error in deleting the announcement.");
                    }
                );
            }
        },
        (error) => {
            console.error('Error deleting old reservations:', error);
            alert("Deletion failed due to an error in deleting old reservations.");
        }
    );
  }

  
  
  



}  