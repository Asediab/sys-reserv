import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {DataEstablishmentService} from '../../services/data-establishment.service';
import {Comment} from '../../interfaces';
import {CommentService} from '../../../services/comment.service';
import {AlertService} from '../../services/alert.service';

@Component({
  selector: 'app-modal-modif-comment',
  templateUrl: './modal-modif-comment.component.html',
  styleUrls: ['./modal-modif-comment.component.css']
})
export class ModalModifCommentComponent implements OnInit {

  @Input() comment: Comment;

  @Output() closeMod: EventEmitter<any> = new EventEmitter<void>();

  form: FormGroup;

  constructor(private dataEstablishmentService: DataEstablishmentService,
              private commentService: CommentService,
              private alertService: AlertService) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      text: new FormControl(this.comment.text),
    });
  }

  modifyComment(): void {
    if (this.form.valid) {
      const comment2 = this.comment;
      this.comment.text = this.form.value.text;
      console.log(this.comment);
      console.log(comment2);
      console.log(this.form.value.text);
      this.commentService.modify(this.comment)
        .subscribe(v => {
          this.closeMod.emit(null);
          this.form.reset();
          this.alertService.success('Commentaire est modifié');
        });
    }
  }
}
