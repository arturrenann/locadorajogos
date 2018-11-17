import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJogo } from 'app/shared/model/jogo.model';

@Component({
    selector: 'jhi-jogo-detail',
    templateUrl: './jogo-detail.component.html'
})
export class JogoDetailComponent implements OnInit {
    jogo: IJogo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jogo }) => {
            this.jogo = jogo;
        });
    }

    previousState() {
        window.history.back();
    }
}
