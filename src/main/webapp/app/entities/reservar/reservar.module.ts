import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LocadoraSharedModule } from 'app/shared';
import {
    ReservarComponent,
    ReservarDetailComponent,
    ReservarUpdateComponent,
    ReservarDeletePopupComponent,
    ReservarDeleteDialogComponent,
    reservarRoute,
    reservarPopupRoute
} from './';

const ENTITY_STATES = [...reservarRoute, ...reservarPopupRoute];

@NgModule({
    imports: [LocadoraSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReservarComponent,
        ReservarDetailComponent,
        ReservarUpdateComponent,
        ReservarDeleteDialogComponent,
        ReservarDeletePopupComponent
    ],
    entryComponents: [ReservarComponent, ReservarUpdateComponent, ReservarDeleteDialogComponent, ReservarDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LocadoraReservarModule {}
