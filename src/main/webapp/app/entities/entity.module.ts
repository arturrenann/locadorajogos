import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { LocadoraClienteModule } from './cliente/cliente.module';
import { LocadoraJogoModule } from './jogo/jogo.module';
import { LocadoraPlataformaModule } from './plataforma/plataforma.module';
import { LocadoraReservarModule } from './reservar/reservar.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        LocadoraClienteModule,
        LocadoraJogoModule,
        LocadoraPlataformaModule,
        LocadoraReservarModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LocadoraEntityModule {}
