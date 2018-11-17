import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IJogo } from 'app/shared/model/jogo.model';
import { Principal } from 'app/core';
import { JogoService } from './jogo.service';

@Component({
    selector: 'jhi-jogo',
    templateUrl: './jogo.component.html'
})
export class JogoComponent implements OnInit, OnDestroy {
    jogos: IJogo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jogoService: JogoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.jogoService.query().subscribe(
            (res: HttpResponse<IJogo[]>) => {
                this.jogos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInJogos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IJogo) {
        return item.id;
    }

    registerChangeInJogos() {
        this.eventSubscriber = this.eventManager.subscribe('jogoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
