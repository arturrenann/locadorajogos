webpackHotUpdate("main",{

/***/ "./src/main/webapp/app/entities/reservar/reservar-detail.component.html":
/*!******************************************************************************!*\
  !*** ./src/main/webapp/app/entities/reservar/reservar-detail.component.html ***!
  \******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("module.exports = \"<div class=\\\"row justify-content-center\\\"> <div class=\\\"col-8\\\"> <div *ngIf=\\\"reservar\\\"> <h2><span>Reservar</span> {{reservar.id}}</h2> <hr> <jhi-alert-error></jhi-alert-error> <dl class=\\\"row-md jh-entity-details\\\"> <dt><span>Dias</span></dt> <dd> <span>{{reservar.dias}}</span> </dd> <dt><span>Data Inicio</span></dt> <dd> <span>{{reservar.dataInicio}}</span> </dd> <dt><span>Data Entrega</span></dt> <dd> <span>{{reservar.dataEntrega}}</span> </dd> <dt><span>Jogo</span></dt> <dd> <span *ngFor=\\\"let jogo of reservar.jogos; let last = last\\\"> <a [routerLink]=\\\"['/jogo', jogo?.id, 'view' ]\\\">{{jogo.nome}}</a>{{last ? '' : ', '}} </span> </dd> <dt><span>Plataformas</span></dt> <dd> <span *ngFor=\\\"let plataformas of reservar.plataformas; let last = last\\\"> <a [routerLink]=\\\"['/plataforma', plataformas?.id, 'view' ]\\\">{{plataformas.plataforma}}</a>{{last ? '' : ', '}} </span> </dd> <dt><span>Valor Total</span></dt> <dd> <span>{{reservar.total | currency: 'BRL':true }}</span> </dd> </dl> <button type=\\\"submit\\\" (click)=\\\"previousState()\\\" class=\\\"btn btn-info\\\"> <fa-icon [icon]=\\\"'arrow-left'\\\"></fa-icon>&nbsp;<span> Back</span> </button> <button type=\\\"button\\\" [routerLink]=\\\"['/reservar', reservar.id, 'edit']\\\" class=\\\"btn btn-primary\\\"> <fa-icon [icon]=\\\"'pencil-alt'\\\"></fa-icon>&nbsp;<span> Edit</span> </button> </div> </div> </div> \";//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9zcmMvbWFpbi93ZWJhcHAvYXBwL2VudGl0aWVzL3Jlc2VydmFyL3Jlc2VydmFyLWRldGFpbC5jb21wb25lbnQuaHRtbD9jNDBmIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLHdJQUF3SSxhQUFhLCtIQUErSCxlQUFlLDZEQUE2RCxxQkFBcUIsOERBQThELHNCQUFzQix3RkFBd0YsdUVBQXVFLFdBQVcsTUFBTSxrQkFBa0IsNkdBQTZHLG9GQUFvRix3QkFBd0IsTUFBTSxrQkFBa0IsOERBQThELHdDQUF3QyxpSkFBaUoscUxBQXFMIiwiZmlsZSI6Ii4vc3JjL21haW4vd2ViYXBwL2FwcC9lbnRpdGllcy9yZXNlcnZhci9yZXNlcnZhci1kZXRhaWwuY29tcG9uZW50Lmh0bWwuanMiLCJzb3VyY2VzQ29udGVudCI6WyJtb2R1bGUuZXhwb3J0cyA9IFwiPGRpdiBjbGFzcz1cXFwicm93IGp1c3RpZnktY29udGVudC1jZW50ZXJcXFwiPiA8ZGl2IGNsYXNzPVxcXCJjb2wtOFxcXCI+IDxkaXYgKm5nSWY9XFxcInJlc2VydmFyXFxcIj4gPGgyPjxzcGFuPlJlc2VydmFyPC9zcGFuPiB7e3Jlc2VydmFyLmlkfX08L2gyPiA8aHI+IDxqaGktYWxlcnQtZXJyb3I+PC9qaGktYWxlcnQtZXJyb3I+IDxkbCBjbGFzcz1cXFwicm93LW1kIGpoLWVudGl0eS1kZXRhaWxzXFxcIj4gPGR0PjxzcGFuPkRpYXM8L3NwYW4+PC9kdD4gPGRkPiA8c3Bhbj57e3Jlc2VydmFyLmRpYXN9fTwvc3Bhbj4gPC9kZD4gPGR0PjxzcGFuPkRhdGEgSW5pY2lvPC9zcGFuPjwvZHQ+IDxkZD4gPHNwYW4+e3tyZXNlcnZhci5kYXRhSW5pY2lvfX08L3NwYW4+IDwvZGQ+IDxkdD48c3Bhbj5EYXRhIEVudHJlZ2E8L3NwYW4+PC9kdD4gPGRkPiA8c3Bhbj57e3Jlc2VydmFyLmRhdGFFbnRyZWdhfX08L3NwYW4+IDwvZGQ+IDxkdD48c3Bhbj5Kb2dvPC9zcGFuPjwvZHQ+IDxkZD4gPHNwYW4gKm5nRm9yPVxcXCJsZXQgam9nbyBvZiByZXNlcnZhci5qb2dvczsgbGV0IGxhc3QgPSBsYXN0XFxcIj4gPGEgW3JvdXRlckxpbmtdPVxcXCJbJy9qb2dvJywgam9nbz8uaWQsICd2aWV3JyBdXFxcIj57e2pvZ28ubm9tZX19PC9hPnt7bGFzdCA/ICcnIDogJywgJ319IDwvc3Bhbj4gPC9kZD4gPGR0PjxzcGFuPlBsYXRhZm9ybWFzPC9zcGFuPjwvZHQ+IDxkZD4gPHNwYW4gKm5nRm9yPVxcXCJsZXQgcGxhdGFmb3JtYXMgb2YgcmVzZXJ2YXIucGxhdGFmb3JtYXM7IGxldCBsYXN0ID0gbGFzdFxcXCI+IDxhIFtyb3V0ZXJMaW5rXT1cXFwiWycvcGxhdGFmb3JtYScsIHBsYXRhZm9ybWFzPy5pZCwgJ3ZpZXcnIF1cXFwiPnt7cGxhdGFmb3JtYXMucGxhdGFmb3JtYX19PC9hPnt7bGFzdCA/ICcnIDogJywgJ319IDwvc3Bhbj4gPC9kZD4gPGR0PjxzcGFuPlZhbG9yIFRvdGFsPC9zcGFuPjwvZHQ+IDxkZD4gPHNwYW4+e3tyZXNlcnZhci50b3RhbCB8IGN1cnJlbmN5OiAnQlJMJzp0cnVlIH19PC9zcGFuPiA8L2RkPiA8L2RsPiA8YnV0dG9uIHR5cGU9XFxcInN1Ym1pdFxcXCIgKGNsaWNrKT1cXFwicHJldmlvdXNTdGF0ZSgpXFxcIiBjbGFzcz1cXFwiYnRuIGJ0bi1pbmZvXFxcIj4gPGZhLWljb24gW2ljb25dPVxcXCInYXJyb3ctbGVmdCdcXFwiPjwvZmEtaWNvbj4mbmJzcDs8c3Bhbj4gQmFjazwvc3Bhbj4gPC9idXR0b24+IDxidXR0b24gdHlwZT1cXFwiYnV0dG9uXFxcIiBbcm91dGVyTGlua109XFxcIlsnL3Jlc2VydmFyJywgcmVzZXJ2YXIuaWQsICdlZGl0J11cXFwiIGNsYXNzPVxcXCJidG4gYnRuLXByaW1hcnlcXFwiPiA8ZmEtaWNvbiBbaWNvbl09XFxcIidwZW5jaWwtYWx0J1xcXCI+PC9mYS1pY29uPiZuYnNwOzxzcGFuPiBFZGl0PC9zcGFuPiA8L2J1dHRvbj4gPC9kaXY+IDwvZGl2PiA8L2Rpdj4gXCI7Il0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///./src/main/webapp/app/entities/reservar/reservar-detail.component.html\n");

/***/ })

})