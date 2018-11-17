/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LocadoraTestModule } from '../../../test.module';
import { JogoComponent } from 'app/entities/jogo/jogo.component';
import { JogoService } from 'app/entities/jogo/jogo.service';
import { Jogo } from 'app/shared/model/jogo.model';

describe('Component Tests', () => {
    describe('Jogo Management Component', () => {
        let comp: JogoComponent;
        let fixture: ComponentFixture<JogoComponent>;
        let service: JogoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LocadoraTestModule],
                declarations: [JogoComponent],
                providers: []
            })
                .overrideTemplate(JogoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JogoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JogoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Jogo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.jogos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
