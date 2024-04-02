import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AnnonceComponent } from './components/annonce/annonce.component';
import { AjouterannonceComponent } from './components/ajouterannonce/ajouterannonce.component';
import { VoitureComponent } from './components/voiture/voiture.component';
import { AjoutervoitureComponent } from './components/ajoutervoiture/ajoutervoiture.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DetailedAnnonceComponent } from './components/detailed-annonce/detailed-annonce.component';
import { MesannoncesComponent } from './components/mesannonces/mesannonces.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';

@NgModule({
  declarations: [
    AnnonceComponent,
    AjouterannonceComponent,
    VoitureComponent,
    AjoutervoitureComponent,
    MesannoncesComponent,
    DetailedAnnonceComponent,
    ReservationComponent,
    StatisticsComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxChartsModule

  ],
  exports:[
    AnnonceComponent,
    AjouterannonceComponent,
    VoitureComponent,
    AjoutervoitureComponent,
    MesannoncesComponent,
    DetailedAnnonceComponent,
    ReservationComponent,
    StatisticsComponent
  ],

})
export class CovoiturageModule { }
