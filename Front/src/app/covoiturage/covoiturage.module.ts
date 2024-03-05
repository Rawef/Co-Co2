import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnnonceComponent } from './components/annonce/annonce.component';
import { AjouterannonceComponent } from './components/ajouterannonce/ajouterannonce.component';
import { AjoutervoitureComponent } from './components/ajoutervoiture/ajoutervoiture.component';
import { VoitureComponent } from './components/voiture/voiture.component';



@NgModule({
  declarations: [
    AnnonceComponent,
    AjouterannonceComponent,
    AjoutervoitureComponent,
    VoitureComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    AnnonceComponent,
    AjouterannonceComponent,
    AjoutervoitureComponent,
    VoitureComponent
  ]
})
export class CovoiturageModule { }
