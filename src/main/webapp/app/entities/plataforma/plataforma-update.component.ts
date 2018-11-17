import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPlataforma } from 'app/shared/model/plataforma.model';
import { PlataformaService } from './plataforma.service';
import { IReservar } from 'app/shared/model/reservar.model';
import { ReservarService } from 'app/entities/reservar';

@Component({
    selector: 'jhi-plataforma-update',
    templateUrl: './plataforma-update.component.html'
})
export class PlataformaUpdateComponent implements OnInit {
    plataforma: IPlataforma;
    isSaving: boolean;

    reservars: IReservar[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private plataformaService: PlataformaService,
        private reservarService: ReservarService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plataforma }) => {
            this.plataforma = plataforma;
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
        if (this.plataforma.id !== undefined) {
            this.subscribeToSaveResponse(this.plataformaService.update(this.plataforma));
        } else {
            this.subscribeToSaveResponse(this.plataformaService.create(this.plataforma));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlataforma>>) {
        result.subscribe((res: HttpResponse<IPlataforma>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
