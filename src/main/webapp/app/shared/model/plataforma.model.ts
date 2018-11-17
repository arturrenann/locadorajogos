import { IReservar } from 'app/shared/model//reservar.model';

export interface IPlataforma {
    id?: number;
    plataforma?: string;
    valor?: number;
    reservars?: IReservar[];
}

export class Plataforma implements IPlataforma {
    constructor(public id?: number, public plataforma?: string, public valor?: number, public reservars?: IReservar[]) {}
}
