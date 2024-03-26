import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnnonceService } from '../../service/annonce.service';
import { ServiceService } from '../../../login/services/service.service';

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
 

  constructor(
    private route: ActivatedRoute,
    private annonceService: AnnonceService,
    private userService: ServiceService
  ) {}

  ngOnInit(): void {
    this.getAnnonceDetails();

  }

  

  getAnnonceDetails(): void {
    const idaString = this.route.snapshot.paramMap.get('ida');
    if (idaString) {
      const ida = +idaString;
      this.annonceService.getAnnonceById(ida).subscribe(
        (annonce: any) => {
          this.annonce = annonce;
          // Fetch comments for the current announcement
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
        // Add comment
        this.annonceService.addComment(annonceCovId, userId, this.commentText).subscribe(() => {
          // After adding comment, refresh comments
          this.fetchComments();
          // Reset comment text
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
  likeComment(commentId: number): void {
    this.userService.getLoggedInUser().subscribe((user: any) => {
      const userId = user.id;
      this.annonceService.likeComment(commentId, userId).subscribe(() => {
        // Update total likes after successful like
        this.updateLikesDislikes(commentId);
      }, (error) => {
        console.error('Error liking comment:', error);
        // Handle error (e.g., display an error message)
      });
    }, (error) => {
      console.error('Error retrieving user information:', error);
      // Handle error (e.g., display an error message)
    });
  }
  
  dislikeComment(commentId: number): void {
    this.userService.getLoggedInUser().subscribe((user: any) => {
      const userId = user.id;
      this.annonceService.dislikeComment(commentId, userId).subscribe(() => {
        // Update total dislikes after successful dislike
        this.updateLikesDislikes(commentId);
      }, (error) => {
        console.error('Error disliking comment:', error);
        // Handle error (e.g., display an error message)
      });
    }, (error) => {
      console.error('Error retrieving user information:', error);
      // Handle error (e.g., display an error message)
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
