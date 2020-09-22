import { Component, OnInit } from '@angular/core';
import {DataEstablishmentService} from '../../shared/services/data-establishment.service';
import {ReservationService} from '../../services/reservation.service';
import {EstablishmentService} from '../../services/establishment.service';
import {ActivatedRoute, Params} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {Comment, Establishment} from '../../shared/interfaces';
import {CommentService} from '../../services/comment.service';
import {AlertService} from '../../shared/services/alert.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  establishment: Establishment;

  page = 1;


  constructor(private dataEstablishmentService: DataEstablishmentService,
              private reservationService: ReservationService,
              private establishmentService: EstablishmentService,
              private route: ActivatedRoute,
              private commentService: CommentService,
              public auth: AuthService,
              private alertService: AlertService,
              private modalService: NgbModal) {
    this.establishment = dataEstablishmentService.establishment;
  }

  ngOnInit(): void {
    if (!this.establishment){
      this.route.params.subscribe((params: Params) => {
        this.establishmentService.getById(params.id)
          .subscribe(establishment => {
            this.establishment = establishment;
          });
        this.dataEstablishmentService.establishment = this.establishment;
      });
    }
  }

  deleteComment(comment: Comment): void {
    this.commentService.delete(comment.id).subscribe(v => {
      this.establishment.comments = this.establishment.comments.filter(t => t.id !== comment.id);
      this.alertService.warning('Le commentaire a été supprimé');
      this.modalService.dismissAll();
    });
  }

  open(content): void {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {
    });
  }
}
