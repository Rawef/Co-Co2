import { Component } from '@angular/core';
import { AnnonceService } from '../../service/annonce.service';

@Component({
  selector: 'app-annonce',
  templateUrl: './annonce.component.html',
  styleUrl: './annonce.component.css'
})
export class AnnonceComponent {

  annonces: any[] = []; 

  constructor(private annonceService: AnnonceService) { }

  ngOnInit(): void {
    this.getAnnonces();
  }

  getAnnonces(): void {
    this.annonceService.afficherannonce().subscribe(
      annonces => {
        this.annonces = annonces;
      },
      error => {
        console.error('Error fetching announcements:', error);
      }
    );
  }
  }



