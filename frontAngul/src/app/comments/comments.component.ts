import {Component, Input, OnInit} from '@angular/core';
import {Comment, User} from '../shared/interfaces';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {CommentService} from '../services/comment.service';
import {AuthService} from '../services/auth.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../shared/services/alert.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  commentForm: FormGroup;

  modal = false;

  comments: Comment[];

  commentModify: Comment;

  page = 1;


  constructor(private dataEstablishmentService: DataEstablishmentService,
              private commentService: CommentService,
              public auth: AuthService,
              private alertService: AlertService,
              private modalService: NgbModal) {
    this.comments = this.dataEstablishmentService.establishment.comments;
  }

  ngOnInit(): void {
    this.commentForm = new FormGroup({
      text: new FormControl('', Validators.required),
    });
  }

  deleteComment(comment: Comment): void {
    this.commentService.delete(comment.id).subscribe(v => {
      this.comments = this.comments.filter(t => t.id !== comment.id);
      this.modalService.dismissAll();
    });
  }

  changeComment(comment: Comment): void {
    this.commentService.modify(comment).subscribe(v => {});
  }

  submit(): void {
    if (this.commentForm.valid) {
      const comment: Comment = this.commentForm.value;
      comment.userId = this.auth.user.id;
      comment.author = this.auth.user.firstName;
      comment.establishmentId = this.dataEstablishmentService.establishment.id;
      console.log(comment);
      this.commentService.save(comment)
        .subscribe(v => {
          this.commentForm.reset();
          this.dataEstablishmentService.establishment.comments.push(comment);
          this.alertService.success('Commentaire est crée');
        });
    }
  }

  modify(comment: Comment): void {
    this.commentModify = comment;
    this.modal = true;
  }

  open(content): void {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {
    });
  }
}
