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
}
