
import { LoginComponent } from './login/component/login/login.component';
import { PanierComponent } from './panier/component/panier/panier.component';
import { HomeComponent } from './home/component/home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './shared/components/header/header.component';
import { PreferancesComponent } from './login/component/preferances/preferances.component';
import { ProfileComponent } from './login/component/profile/profile.component';
import { UpdateComponent } from './login/component/update/update.component';
import { ChatComponent } from './login/component/chat/chat.component';
import { AnnonceComponent } from './covoiturage/components/annonce/annonce.component';
import { VoitureComponent } from './covoiturage/components/voiture/voiture.component';
import { AjoutervoitureComponent } from './covoiturage/components/ajoutervoiture/ajoutervoiture.component';
import { AjouterannonceComponent } from './covoiturage/components/ajouterannonce/ajouterannonce.component';
import { DetailedAnnonceComponent } from './covoiturage/components/detailed-annonce/detailed-annonce.component';
import { MesannoncesComponent } from './covoiturage/components/mesannonces/mesannonces.component';
import { ReservationComponent } from './covoiturage/components/reservation/reservation.component';
import { StatisticsComponent } from './covoiturage/components/statistics/statistics.component';


const routes: Routes = [

  {path:"home" , component:HomeComponent},
  {path:"panier" , component:PanierComponent},
  {path:"profile" , component:ProfileComponent},
  {path:"update" , component:UpdateComponent},
  {path:"chat" , component:ChatComponent},
  {path:"annonceCOV" , component:AnnonceComponent},
  {path:"voiture" , component:VoitureComponent},
  {path:"ajoutervoiture" , component:AjoutervoitureComponent},
  {path:"ajouterannonce" , component:AjouterannonceComponent},
  { path: 'annonces/:ida', component: DetailedAnnonceComponent },
  { path: "annonces", component: MesannoncesComponent },
  { path: "mesReservations", component: ReservationComponent },
  { path: "stat", component: StatisticsComponent },





  {path:"" , component:LoginComponent},
  {path:"preferances" , component:PreferancesComponent},
  {path:"**",redirectTo:"home" ,pathMatch:"full"} 

  
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
