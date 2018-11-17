import { NgModule } from '@angular/core';

import { LocadoraSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [LocadoraSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [LocadoraSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class LocadoraSharedCommonModule {}
