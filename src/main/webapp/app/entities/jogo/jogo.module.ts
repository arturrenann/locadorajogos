import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LocadoraSharedModule } from 'app/shared';
import {
    JogoComponent,
    JogoDetailComponent,
    JogoUpdateComponent,
    JogoDeletePopupComponent,
    JogoDeleteDialogComponent,
    jogoRoute,
    jogoPopupRoute
} from './';

const ENTITY_STATES = [...jogoRoute, ...jogoPopupRoute];

@NgModule({
    imports: [LocadoraSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [JogoComponent, JogoDetailComponent, JogoUpdateComponent, JogoDeleteDialogComponent, JogoDeletePopupComponent],
    entryComponents: [JogoComponent, JogoUpdateComponent, JogoDeleteDialogComponent, JogoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LocadoraJogoModule {}
