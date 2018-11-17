/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LocadoraTestModule } from '../../../test.module';
import { JogoDetailComponent } from 'app/entities/jogo/jogo-detail.component';
import { Jogo } from 'app/shared/model/jogo.model';

describe('Component Tests', () => {
    describe('Jogo Management Detail Component', () => {
        let comp: JogoDetailComponent;
        let fixture: ComponentFixture<JogoDetailComponent>;
        const route = ({ data: of({ jogo: new Jogo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LocadoraTestModule],
                declarations: [JogoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(JogoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(JogoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.jogo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
