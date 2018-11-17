import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Jogo } from 'app/shared/model/jogo.model';
import { JogoService } from './jogo.service';
import { JogoComponent } from './jogo.component';
import { JogoDetailComponent } from './jogo-detail.component';
import { JogoUpdateComponent } from './jogo-update.component';
import { JogoDeletePopupComponent } from './jogo-delete-dialog.component';
import { IJogo } from 'app/shared/model/jogo.model';

@Injectable({ providedIn: 'root' })
export class JogoResolve implements Resolve<IJogo> {
    constructor(private service: JogoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((jogo: HttpResponse<Jogo>) => jogo.body));
        }
        return of(new Jogo());
    }
}

export const jogoRoute: Routes = [
    {
        path: 'jogo',
        component: JogoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Jogos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jogo/:id/view',
        component: JogoDetailComponent,
        resolve: {
            jogo: JogoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Jogos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jogo/new',
        component: JogoUpdateComponent,
        resolve: {
            jogo: JogoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Jogos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jogo/:id/edit',
        component: JogoUpdateComponent,
        resolve: {
            jogo: JogoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Jogos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jogoPopupRoute: Routes = [
    {
        path: 'jogo/:id/delete',
        component: JogoDeletePopupComponent,
        resolve: {
            jogo: JogoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Jogos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
