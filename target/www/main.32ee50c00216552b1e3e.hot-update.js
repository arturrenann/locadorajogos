webpackHotUpdate("main",{

/***/ "./src/main/webapp/app/entities/reservar/reservar-detail.component.html":
/*!******************************************************************************!*\
  !*** ./src/main/webapp/app/entities/reservar/reservar-detail.component.html ***!
  \******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("module.exports = \"<div class=\\\"row justify-content-center\\\"> <div class=\\\"col-8\\\"> <div *ngIf=\\\"reservar\\\"> <h2><span>Reservar</span> {{reservar.id}}</h2> <hr> <jhi-alert-error></jhi-alert-error> <dl class=\\\"row-md jh-entity-details\\\"> <dt><span>Dias</span></dt> <dd> <span>{{reservar.dias}}</span> </dd> <dt><span>Data Inicio</span></dt> <dd> <span>{{reservar.dataInicio}}</span> </dd> <dt><span>Data Entrega</span></dt> <dd> <span>{{reservar.dataEntrega}}</span> </dd> <dt><span>Jogo</span></dt> <dd> <span *ngFor=\\\"let jogo of reservar.jogos; let last = last\\\"> <a [routerLink]=\\\"['/jogo', jogo?.id, 'view' ]\\\">{{jogo.nome}}</a>{{last ? '' : ', '}} </span> </dd> <dt><span>Plataformas</span></dt> <dd> <span *ngFor=\\\"let plataformas of reservar.plataformas; let last = last\\\"> <a [routerLink]=\\\"['/plataforma', plataformas?.id, 'view' ]\\\">{{plataformas.plataforma}}</a>{{last ? '' : ', '}} </span> </dd> <dt><span>Valor Total</span></dt> <dd> <span>{{reservar.total |currency:BRL}}</span> </dd> </dl> <button type=\\\"submit\\\" (click)=\\\"previousState()\\\" class=\\\"btn btn-info\\\"> <fa-icon [icon]=\\\"'arrow-left'\\\"></fa-icon>&nbsp;<span> Back</span> </button> <button type=\\\"button\\\" [routerLink]=\\\"['/reservar', reservar.id, 'edit']\\\" class=\\\"btn btn-primary\\\"> <fa-icon [icon]=\\\"'pencil-alt'\\\"></fa-icon>&nbsp;<span> Edit</span> </button> </div> </div> </div> \";//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9zcmMvbWFpbi93ZWJhcHAvYXBwL2VudGl0aWVzL3Jlc2VydmFyL3Jlc2VydmFyLWRldGFpbC5jb21wb25lbnQuaHRtbD9jNDBmIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLHdJQUF3SSxhQUFhLCtIQUErSCxlQUFlLDZEQUE2RCxxQkFBcUIsOERBQThELHNCQUFzQix3RkFBd0YsdUVBQXVFLFdBQVcsTUFBTSxrQkFBa0IsNkdBQTZHLG9GQUFvRix3QkFBd0IsTUFBTSxrQkFBa0IsOERBQThELDhCQUE4QixpSkFBaUoscUxBQXFMIiwiZmlsZSI6Ii4vc3JjL21haW4vd2ViYXBwL2FwcC9lbnRpdGllcy9yZXNlcnZhci9yZXNlcnZhci1kZXRhaWwuY29tcG9uZW50Lmh0bWwuanMiLCJzb3VyY2VzQ29udGVudCI6WyJtb2R1bGUuZXhwb3J0cyA9IFwiPGRpdiBjbGFzcz1cXFwicm93IGp1c3RpZnktY29udGVudC1jZW50ZXJcXFwiPiA8ZGl2IGNsYXNzPVxcXCJjb2wtOFxcXCI+IDxkaXYgKm5nSWY9XFxcInJlc2VydmFyXFxcIj4gPGgyPjxzcGFuPlJlc2VydmFyPC9zcGFuPiB7e3Jlc2VydmFyLmlkfX08L2gyPiA8aHI+IDxqaGktYWxlcnQtZXJyb3I+PC9qaGktYWxlcnQtZXJyb3I+IDxkbCBjbGFzcz1cXFwicm93LW1kIGpoLWVudGl0eS1kZXRhaWxzXFxcIj4gPGR0PjxzcGFuPkRpYXM8L3NwYW4+PC9kdD4gPGRkPiA8c3Bhbj57e3Jlc2VydmFyLmRpYXN9fTwvc3Bhbj4gPC9kZD4gPGR0PjxzcGFuPkRhdGEgSW5pY2lvPC9zcGFuPjwvZHQ+IDxkZD4gPHNwYW4+e3tyZXNlcnZhci5kYXRhSW5pY2lvfX08L3NwYW4+IDwvZGQ+IDxkdD48c3Bhbj5EYXRhIEVudHJlZ2E8L3NwYW4+PC9kdD4gPGRkPiA8c3Bhbj57e3Jlc2VydmFyLmRhdGFFbnRyZWdhfX08L3NwYW4+IDwvZGQ+IDxkdD48c3Bhbj5Kb2dvPC9zcGFuPjwvZHQ+IDxkZD4gPHNwYW4gKm5nRm9yPVxcXCJsZXQgam9nbyBvZiByZXNlcnZhci5qb2dvczsgbGV0IGxhc3QgPSBsYXN0XFxcIj4gPGEgW3JvdXRlckxpbmtdPVxcXCJbJy9qb2dvJywgam9nbz8uaWQsICd2aWV3JyBdXFxcIj57e2pvZ28ubm9tZX19PC9hPnt7bGFzdCA/ICcnIDogJywgJ319IDwvc3Bhbj4gPC9kZD4gPGR0PjxzcGFuPlBsYXRhZm9ybWFzPC9zcGFuPjwvZHQ+IDxkZD4gPHNwYW4gKm5nRm9yPVxcXCJsZXQgcGxhdGFmb3JtYXMgb2YgcmVzZXJ2YXIucGxhdGFmb3JtYXM7IGxldCBsYXN0ID0gbGFzdFxcXCI+IDxhIFtyb3V0ZXJMaW5rXT1cXFwiWycvcGxhdGFmb3JtYScsIHBsYXRhZm9ybWFzPy5pZCwgJ3ZpZXcnIF1cXFwiPnt7cGxhdGFmb3JtYXMucGxhdGFmb3JtYX19PC9hPnt7bGFzdCA/ICcnIDogJywgJ319IDwvc3Bhbj4gPC9kZD4gPGR0PjxzcGFuPlZhbG9yIFRvdGFsPC9zcGFuPjwvZHQ+IDxkZD4gPHNwYW4+e3tyZXNlcnZhci50b3RhbCB8Y3VycmVuY3k6QlJMfX08L3NwYW4+IDwvZGQ+IDwvZGw+IDxidXR0b24gdHlwZT1cXFwic3VibWl0XFxcIiAoY2xpY2spPVxcXCJwcmV2aW91c1N0YXRlKClcXFwiIGNsYXNzPVxcXCJidG4gYnRuLWluZm9cXFwiPiA8ZmEtaWNvbiBbaWNvbl09XFxcIidhcnJvdy1sZWZ0J1xcXCI+PC9mYS1pY29uPiZuYnNwOzxzcGFuPiBCYWNrPC9zcGFuPiA8L2J1dHRvbj4gPGJ1dHRvbiB0eXBlPVxcXCJidXR0b25cXFwiIFtyb3V0ZXJMaW5rXT1cXFwiWycvcmVzZXJ2YXInLCByZXNlcnZhci5pZCwgJ2VkaXQnXVxcXCIgY2xhc3M9XFxcImJ0biBidG4tcHJpbWFyeVxcXCI+IDxmYS1pY29uIFtpY29uXT1cXFwiJ3BlbmNpbC1hbHQnXFxcIj48L2ZhLWljb24+Jm5ic3A7PHNwYW4+IEVkaXQ8L3NwYW4+IDwvYnV0dG9uPiA8L2Rpdj4gPC9kaXY+IDwvZGl2PiBcIjsiXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///./src/main/webapp/app/entities/reservar/reservar-detail.component.html\n");

/***/ })

})