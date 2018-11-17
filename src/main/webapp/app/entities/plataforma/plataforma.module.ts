import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LocadoraSharedModule } from 'app/shared';
import {
    PlataformaComponent,
    PlataformaDetailComponent,
    PlataformaUpdateComponent,
    PlataformaDeletePopupComponent,
    PlataformaDeleteDialogComponent,
    plataformaRoute,
    plataformaPopupRoute
} from './';

const ENTITY_STATES = [...plataformaRoute, ...plataformaPopupRoute];

@NgModule({
    imports: [LocadoraSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlataformaComponent,
        PlataformaDetailComponent,
        PlataformaUpdateComponent,
        PlataformaDeleteDialogComponent,
        PlataformaDeletePopupComponent
    ],
    entryComponents: [PlataformaComponent, PlataformaUpdateComponent, PlataformaDeleteDialogComponent, PlataformaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LocadoraPlataformaModule {}
