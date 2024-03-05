import { Component } from '@angular/core';
import { AnnonceService } from '../../service/annonce.service';

@Component({
  selector: 'app-ajoutervoiture',
  templateUrl: './ajoutervoiture.component.html',
  styleUrl: './ajoutervoiture.component.css'
})
export class AjoutervoitureComponent {
  voiture: Voiture=new voiture();
  voitures: Voiture[] = []; 
  
  constructor(private annonceService: AnnonceService) { }

  onSubmit() {
    // Ajoutez ici le code pour ajouter l'annonce
    // Par exemple, vous pouvez appeler le service AnnonceColocService pour ajouter l'annonce à la liste des annonces
    this.annonceService.registerVoiture(this.voiture.subscribe(() => {
      // Réinitialisez l'objet annonceColoc pour effacer le formulaire après la soumission
      this.voiture = new Voiture();
      // Vous pouvez également ajouter un message de succès ou de redirection vers une autre page ici
    }));
  }
  
  }

  


 