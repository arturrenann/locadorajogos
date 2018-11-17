import { IReservar } from 'app/shared/model//reservar.model';

export interface IJogo {
    id?: number;
    nome?: string;
    reservars?: IReservar[];
}

export class Jogo implements IJogo {
    constructor(public id?: number, public nome?: string, public reservars?: IReservar[]) {}
}
