import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../shared/interfaces';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {CommentService} from '../services/comment.service';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  comments: Comment[];

  comment: Comment;

  page = 1;


  constructor(private dataEstablishmentService: DataEstablishmentService,
              private commentService: CommentService,
              public auth: AuthService) {
    this.comments = this.dataEstablishmentService.establishment.comments;
  }

  ngOnInit(): void {
  }

  saveComment(comment: Comment): void {
    this.commentService.save(comment).subscribe(v => {
    });
  }

  deleteComment(comment: Comment): void {
    this.commentService.delete(comment.id).subscribe(v => {
      this.comments = this.comments.filter(t => t.id !== comment.id);
    });
  }

  changeComment(comment: Comment): void {
    this.commentService.modify(comment).subscribe(v => {});
  }

  submit(): void {
    return null;
  }
}
