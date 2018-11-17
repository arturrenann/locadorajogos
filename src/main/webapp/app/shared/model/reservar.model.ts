import { Moment } from 'moment';
import { IJogo } from 'app/shared/model//jogo.model';
import { IPlataforma } from 'app/shared/model//plataforma.model';
import { ICliente } from 'app/shared/model//cliente.model';

export interface IReservar {
    id?: number;
    dias?: number;
    dataInicio?: Moment;
    dataEntrega?: Moment;
    jogos?: IJogo[];
    plataformas?: IPlataforma[];
    clientes?: ICliente[];
    total?: number;
}

export class Reservar implements IReservar {
    constructor(
        public id?: number,
        public dias?: number,
        public dataInicio?: Moment,
        public dataEntrega?: Moment,
        public jogos?: IJogo[],
        public plataformas?: IPlataforma[],
        public clientes?: ICliente[],
        public total?: number;
    ) {}
}
