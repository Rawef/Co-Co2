import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  private baseUrl = 'http://localhost:8089';
    constructor(private http:HttpClient) { }


    afficherannonce(): Observable<any> {
      return this.http.get(`${this.baseUrl}/annonces/retrieve-all-annonces`);
    }
                  
    registerAnnonceWithCircuitAndUserAndVoiture(annonceData: any): Observable<any> {
      const { userId, voitureId, circuitId } = annonceData;
      return this.http.post(`${this.baseUrl}/annonce/${userId}/${voitureId}/${circuitId}`, annonceData);
    }

    registerVoiture(voiture: any):Observable<any> {
      const{userId}=voiture;
      return this.http.post(`${this.baseUrl}/voitures/add-voiture/${userId}`, voiture);
    }

    affichervoiture(): Observable<any> {
      return this.http.get(`${this.baseUrl}/voitures`);
    }


}
