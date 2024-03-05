import { Component } from '@angular/core';
import { AnnonceService } from '../../service/annonce.service';

@Component({
  selector: 'app-voiture',
  templateUrl: './voiture.component.html',
  styleUrl: './voiture.component.css'
})
export class VoitureComponent {
voitures:  any[] = []; 

constructor(private annonceService: AnnonceService) { }

  ngOnInit(): void {
    this.getVoitures();
  }

  getVoitures(): void {
    this.annonceService.affichervoiture().subscribe(
      voitures => {
        this.voitures = voitures;
      },
      error => {
        console.error('Error fetching announcements:', error);
      }
    );
  }

}
