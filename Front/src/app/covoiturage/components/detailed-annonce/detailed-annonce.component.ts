import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnnonceService } from '../../service/annonce.service';
import { ServiceService } from '../../../login/services/service.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-detailed-annonce',
  templateUrl: './detailed-annonce.component.html',
  styleUrls: ['./detailed-annonce.component.css']
})
export class DetailedAnnonceComponent implements OnInit {
  annonce: any;
  comments: any[] = [];
  commentText: string = '';
  replyText: string = '';
  loggedInUserId: any;

  constructor(
    private route: ActivatedRoute,
    private annonceService: AnnonceService,
    private userService: ServiceService
  ) {}

  ngOnInit(): void {
    this.getAnnonceDetails();
    this.userService.getLoggedInUser().subscribe((user: any) => {
      if (user) {
        this.loggedInUserId = user.id; 
      } else {        // For example, redirect to the login page
        }      });
  }

  

  getAnnonceDetails(): void {
    const idaString = this.route.snapshot.paramMap.get('ida');
    if (idaString) {
      const ida = +idaString;
      this.annonceService.getAnnonceById(ida).subscribe(
        (annonce: any) => {
          this.annonce = annonce;
          this.fetchComments();
        },
        error => {
          console.error('Error fetching announcement details:', error);
        }
      );
    } else {
      console.error('IDA parameter is null or undefined');
    }
  }

  submitComment(): void {
    this.userService.getLoggedInUser().subscribe(
      (user: any) => {
        const userId = user.id;
        const annonceCovId = this.annonce.ida;
        this.annonceService.addComment(annonceCovId, userId, this.commentText).subscribe(() => {
          this.fetchComments();
          this.commentText = '';
        });
      }
    );

  }

  fetchComments() {
    if (this.annonce && this.annonce.ida) {
      this.annonceService.getCommentsByAnnonce(this.annonce.ida).subscribe((comments: any[]) => {
        this.comments = comments;
      });
    }
  }

  deleteComment(commentUserId: number, idco: number): void {
    // Get the logged-in user
    this.userService.getLoggedInUser().subscribe(
      (loggedInUser: any) => {
        if (loggedInUser) {
          const userId = loggedInUser.id;
          // Proceed with deletion
          this.annonceService.deleteCommentByUserIdAndIdco(userId, idco).subscribe(
            (response) => {
              console.log('Delete comment response:', response);
              // Remove the comment from the local array upon successful deletion
              this.comments = this.comments.filter(comment => comment.idco !== idco);
            },
            (error) => {
              console.error('Error deleting comment:', error);
              // Handle error, if necessary
            }
          );
        } else {
          console.error('User is not logged in.');
          // Handle the case where the user is not logged in, if necessary
        }
      },
      (error) => {
        console.error('Error retrieving logged-in user:', error);
        // Handle error, if necessary
      }
    );
  }
  
  isCommentOwner(commentUserId: number): boolean {
    return this.loggedInUserId === commentUserId;
  }

  likeComment(idco: number): void {
    this.userService.getLoggedInUser().subscribe((user: any) => {
      const userId = user.id;
      this.annonceService.likeComment(idco, userId).subscribe(() => {
        // Update likes for the specific comment
        this.updateLikesDislikes(idco);
      }, (error) => {
        console.error('Error liking comment:', error);
        // Handle error, if necessary
      });
    }, (error) => {
      console.error('Error retrieving user information:', error);
      // Handle error, if necessary
    });
  }
  
  dislikeComment(idco: number): void {
    this.userService.getLoggedInUser().subscribe((user: any) => {
      const userId = user.id;
      this.annonceService.dislikeComment(idco, userId).subscribe(() => {
        // Update dislikes for the specific comment
        this.updateLikesDislikes(idco);
      }, (error) => {
        console.error('Error disliking comment:', error);
        // Handle error, if necessary
      });
    }, (error) => {
      console.error('Error retrieving user information:', error);
      // Handle error, if necessary
    });
  }

  updateLikesDislikes(commentId: number): void {
    this.annonceService.getLikesForComment(commentId).subscribe((likes: number) => {
      const index = this.comments.findIndex(comment => comment.idco === commentId);
      if (index !== -1) {
        this.comments[index].likes = likes;
      }
    });

    this.annonceService.getDislikesForComment(commentId).subscribe((dislikes: number) => {
      const index = this.comments.findIndex(comment => comment.idco === commentId);
      if (index !== -1) {
        this.comments[index].dislikes = dislikes;
      }
    });
  }
  replyToComment(parentCommentId: number, replyText: string): void {
    this.userService.getLoggedInUser().subscribe((user: any) => {
      const replyComment: any = {
        comments: replyText,
        user: user
      };
      this.annonceService.replyToComment(parentCommentId, replyComment).subscribe((savedReply: any) => {
        // Handle success
        console.log('Reply added successfully:', savedReply);
        // Refresh comments
        this.fetchComments();
      }, error => {
        // Handle error
        console.error('Error adding reply:', error);
      });
    });
  }

}
