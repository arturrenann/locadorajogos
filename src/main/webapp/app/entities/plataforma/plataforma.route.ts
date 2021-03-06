import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Plataforma } from 'app/shared/model/plataforma.model';
import { PlataformaService } from './plataforma.service';
import { PlataformaComponent } from './plataforma.component';
import { PlataformaDetailComponent } from './plataforma-detail.component';
import { PlataformaUpdateComponent } from './plataforma-update.component';
import { PlataformaDeletePopupComponent } from './plataforma-delete-dialog.component';
import { IPlataforma } from 'app/shared/model/plataforma.model';

@Injectable({ providedIn: 'root' })
export class PlataformaResolve implements Resolve<IPlataforma> {
    constructor(private service: PlataformaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((plataforma: HttpResponse<Plataforma>) => plataforma.body));
        }
        return of(new Plataforma());
    }
}

export const plataformaRoute: Routes = [
    {
        path: 'plataforma',
        component: PlataformaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Plataformas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plataforma/:id/view',
        component: PlataformaDetailComponent,
        resolve: {
            plataforma: PlataformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Plataformas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plataforma/new',
        component: PlataformaUpdateComponent,
        resolve: {
            plataforma: PlataformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Plataformas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plataforma/:id/edit',
        component: PlataformaUpdateComponent,
        resolve: {
            plataforma: PlataformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Plataformas'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plataformaPopupRoute: Routes = [
    {
        path: 'plataforma/:id/delete',
        component: PlataformaDeletePopupComponent,
        resolve: {
            plataforma: PlataformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Plataformas'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
