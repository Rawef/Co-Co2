import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../../service/annonce.service';
import { ServiceService } from '../../../login/services/service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-voiture',
  templateUrl: './voiture.component.html',
  styleUrl: './voiture.component.css'
})
export class VoitureComponent implements OnInit {
voitures: any[] = [];

constructor(private annonceService: AnnonceService, private userService: ServiceService) { }

ngOnInit(): void {
this.getVoitures();
}

getVoitures(): void {
this.userService.getLoggedInUser().subscribe(
  (user: any) => {
    const userId = user.id;
    this.annonceService.affichervoiture(userId).subscribe(
      voitures => { 
        this.voitures = voitures;
      },
      (error) => {
        console.error('Error fetching voitures:', error);
      }
    );
  },
  (error) => {
    console.error('Error retrieving user information:', error);
  }
);
}
}
