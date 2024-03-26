import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AnnonceService } from '../../service/annonce.service';
import { HttpClient } from '@angular/common/http';
import { ServiceService } from '../../../login/services/service.service'; 
@Component({
  selector: 'app-ajoutervoiture',
  templateUrl: './ajoutervoiture.component.html',
  styleUrl: './ajoutervoiture.component.css'
})

export class AjoutervoitureComponent implements OnInit {


  
  
  matricule: string = '';
  nombrePlaces: number = 0;
  img: string = '';
  marque: string='';
  modele: string='';

  constructor(private http: HttpClient, 
    private router: Router,
    private service: AnnonceService, 
    private userService: ServiceService ) { }
  ngOnInit(): void {
  }

    onFileSelected(event: any) {
      const file: File = event.target.files[0];
      if (file) {
        // You can access the selected file properties here
        console.log('Selected file:', file);
        // You can further process the file, e.g., upload it to the server
      }
    }
  addVoiture() {
    // Prepare the voiture data object
    let voitureData = {
      matricule: this.matricule,
      nombrePlaces: this.nombrePlaces,
      img: this.img,
      marque: this.marque,
      modele: this.modele


    };

    this.userService.getLoggedInUser().subscribe(
      (user: any) => {
          const userId = user.id;

          // Make the HTTP request to add the voiture
          this.service.registerVoiture({
                  userId: userId,
                  ...voitureData // Spread the voitureData object
            }).subscribe(
                (result: any) => {
                    console.log(result);
                    alert("Voiture added successfully");
                    // Redirect to home page or wherever you need
                    this.router.navigateByUrl('/home');
                },
                (error) => {
                    console.error("Error adding voiture:", error);
                    alert("Failed to add voiture. Please try again.");
                }
            );
        },
        (error) => {
            console.error("Error retrieving user information:", error);
            alert("Failed to retrieve user information. Please try again.");
        }
    );
      }}