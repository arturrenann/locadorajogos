import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IJogo } from 'app/shared/model/jogo.model';
import { JogoService } from './jogo.service';
import { IReservar } from 'app/shared/model/reservar.model';
import { ReservarService } from 'app/entities/reservar';

@Component({
    selector: 'jhi-jogo-update',
    templateUrl: './jogo-update.component.html'
})
export class JogoUpdateComponent implements OnInit {
    jogo: IJogo;
    isSaving: boolean;

    reservars: IReservar[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private jogoService: JogoService,
        private reservarService: ReservarService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jogo }) => {
            this.jogo = jogo;
        });
        this.reservarService.query().subscribe(
            (res: HttpResponse<IReservar[]>) => {
                this.reservars = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.jogo.id !== undefined) {
            this.subscribeToSaveResponse(this.jogoService.update(this.jogo));
        } else {
            this.subscribeToSaveResponse(this.jogoService.create(this.jogo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IJogo>>) {
        result.subscribe((res: HttpResponse<IJogo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackReservarById(index: number, item: IReservar) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
