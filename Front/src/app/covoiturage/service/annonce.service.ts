import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  private baseUrl = 'http://localhost:8089';
    constructor(private http:HttpClient) { }


    afficherannonce(): Observable<any> {
      return this.http.get(`${this.baseUrl}/annonces/retrieve-all-annonces`);
    }
                  
    getAnnonceById(ida: number): Observable<any> {
      return this.http.get(`${this.baseUrl}/annonces/retrieve-annonce/${ida}`);
    }
   

    registerVoiture(voiture: any):Observable<any> {
      const{userId}=voiture;
      return this.http.post(`${this.baseUrl}/voitures/add-voiture/${userId}`, voiture);
    }
    registerAnnonce(annonce: any):Observable<any> {
      const{userId}=annonce;
      const{matricule}=annonce;
      return this.http.post(`${this.baseUrl}/voitures/add-voiture/${userId}/${matricule}`, annonce);
    }

    afficherannonces(userId: number): Observable<any> {
      return this.http.get(`${this.baseUrl}/annonces/user/${userId}`)
        .pipe(
          catchError(error => {
            console.error('Error fetching user annonces:', error);
            return throwError('An error occurred while fetching user annonces.');
          })
        );}

    

    getVoitureByMatricule(matricule: string): Observable<any> {
      return this.http.get(`${this.baseUrl}/voitures/matricule/${matricule}`);
    }
    
    affichervoiture(userId: number): Observable<any[]> {
      console.log('Requesting user voitures for user ID:', userId);
      return this.http.get<any[]>(`${this.baseUrl}/voitures/voiture/${userId}`).pipe(
        tap((response: any[]) => {
          console.log('Received user voitures:', response);
        }),
        catchError((error) => {
          console.error('Error fetching user voitures:', error);
          return throwError(error); // You can handle the error as per your application's requirements
        })
      );
    }
    
  
    registerAnnonceWithCircuitAndUserAndVoiture(formData: any): Observable<any> {
      return this.http.post<any>(`${this.baseUrl}/annonces/add/${formData.userId}/${formData.matricule}`, formData).pipe(
        catchError((error) => {
          console.error('Error adding annonce:', error);
          return throwError(error); // You can handle the error as per your application's requirements
        })
      );
    }
    getVoitures(userId: number): Observable<string[]> {
      const url = `${this.baseUrl}/voiture/${userId}`;
      return this.http.get<string[]>(url);    }

      addComment(annonceCovId: number, userId: number, commentText: string): Observable<any> {
        return this.http.post(`${this.baseUrl}/commentaires/add/${userId}/${annonceCovId}`, { comments: commentText })
          .pipe(
            catchError((error: HttpErrorResponse) => {
              console.error('An error occurred while adding comment:', error);
              return throwError('Failed to add comment.');
            })
          );
      }

      getCommentsByAnnonce(annonceCovId: number): Observable<any[]> {
        return this.http.get<any[]>(`${this.baseUrl}/commentaires/annonce/${annonceCovId}`);
      }

      likeComment(idco: number, userId: number): Observable<any> {
        return this.http.post(`${this.baseUrl}/commentaires/like/${idco}/${userId}`, {});
      }
    
      dislikeComment(idco: number, userId: number): Observable<any> {
        return this.http.post(`${this.baseUrl}/commentaires/dislike/${idco}/${userId}`, {});
      }
    
      getLikesForComment(idco: number): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}/commentaires/likes/${idco}`);
      }
    
      getDislikesForComment(idco: number): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}/commentaires/dislikes/${idco}`);
      }
    
      replyToComment(parentCommentId: number, replyComment: any): Observable<any> {
        return this.http.post(`${this.baseUrl}/commentaires/${parentCommentId}/reply`, replyComment);
      }

      getUserVoitureByMatricule(userId: number, matricule: string): Observable<any> {
        return this.http.get(`${this.baseUrl}/voitures/${userId}/${matricule}`);
      }
    
      addAnnonceCov(userId: number, matricule: string, annonceCov: any): Observable<any> {
        return this.http.post(`${this.baseUrl}/annonces/add/${userId}/${matricule}`, annonceCov);
      }
}
