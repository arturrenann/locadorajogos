import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IReservar } from 'app/shared/model/reservar.model';
import { ReservarService } from './reservar.service';
import { IJogo } from 'app/shared/model/jogo.model';
import { JogoService } from 'app/entities/jogo';
import { IPlataforma } from 'app/shared/model/plataforma.model';
import { PlataformaService } from 'app/entities/plataforma';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente';

@Component({
    selector: 'jhi-reservar-update',
    templateUrl: './reservar-update.component.html'
})
export class ReservarUpdateComponent implements OnInit {
    reservar: IReservar;
    isSaving: boolean;

    jogos: IJogo[];

    plataformas: IPlataforma[];

    clientes: ICliente[];
    dataInicioDp: any;
    dataEntregaDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private reservarService: ReservarService,
        private jogoService: JogoService,
        private plataformaService: PlataformaService,
        private clienteService: ClienteService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ reservar }) => {
            this.reservar = reservar;
        });
        this.jogoService.query().subscribe(
            (res: HttpResponse<IJogo[]>) => {
                this.jogos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.plataformaService.query().subscribe(
            (res: HttpResponse<IPlataforma[]>) => {
                this.plataformas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.clienteService.query().subscribe(
            (res: HttpResponse<ICliente[]>) => {
                this.clientes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.reservar.id !== undefined) {
            this.subscribeToSaveResponse(this.reservarService.update(this.reservar));
        } else {
            this.subscribeToSaveResponse(this.reservarService.create(this.reservar));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReservar>>) {
        result.subscribe((res: HttpResponse<IReservar>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackJogoById(index: number, item: IJogo) {
        return item.id;
    }

    trackPlataformaById(index: number, item: IPlataforma) {
        return item.id;
    }

    trackClienteById(index: number, item: ICliente) {
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
