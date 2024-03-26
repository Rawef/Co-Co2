import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnnonceService } from '../../service/annonce.service';

@Component({
  selector: 'app-annonce',
  templateUrl: './annonce.component.html',
  styleUrls: ['./annonce.component.css']
})
export class AnnonceComponent implements OnInit {
  annonces: any[] = []; 

  constructor(private annonceService: AnnonceService, private router: Router) { }

  ngOnInit(): void {
    this.getAnnonces();
  }

  viewAnnouncementDetails(ida: number): void {
    this.router.navigate(['/annonces', ida]); // Assuming the route is '/annonce/:ida'
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
