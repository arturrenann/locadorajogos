/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { LocadoraTestModule } from '../../../test.module';
import { JogoUpdateComponent } from 'app/entities/jogo/jogo-update.component';
import { JogoService } from 'app/entities/jogo/jogo.service';
import { Jogo } from 'app/shared/model/jogo.model';

describe('Component Tests', () => {
    describe('Jogo Management Update Component', () => {
        let comp: JogoUpdateComponent;
        let fixture: ComponentFixture<JogoUpdateComponent>;
        let service: JogoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LocadoraTestModule],
                declarations: [JogoUpdateComponent]
            })
                .overrideTemplate(JogoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JogoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JogoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Jogo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jogo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Jogo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jogo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
