import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPlataforma } from 'app/shared/model/plataforma.model';
import { Principal } from 'app/core';
import { PlataformaService } from './plataforma.service';

@Component({
    selector: 'jhi-plataforma',
    templateUrl: './plataforma.component.html'
})
export class PlataformaComponent implements OnInit, OnDestroy {
    plataformas: IPlataforma[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private plataformaService: PlataformaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.plataformaService.query().subscribe(
            (res: HttpResponse<IPlataforma[]>) => {
                this.plataformas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPlataformas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPlataforma) {
        return item.id;
    }

    registerChangeInPlataformas() {
        this.eventSubscriber = this.eventManager.subscribe('plataformaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
