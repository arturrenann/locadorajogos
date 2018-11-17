import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJogo } from 'app/shared/model/jogo.model';

type EntityResponseType = HttpResponse<IJogo>;
type EntityArrayResponseType = HttpResponse<IJogo[]>;

@Injectable({ providedIn: 'root' })
export class JogoService {
    public resourceUrl = SERVER_API_URL + 'api/jogos';

    constructor(private http: HttpClient) {}

    create(jogo: IJogo): Observable<EntityResponseType> {
        return this.http.post<IJogo>(this.resourceUrl, jogo, { observe: 'response' });
    }

    update(jogo: IJogo): Observable<EntityResponseType> {
        return this.http.put<IJogo>(this.resourceUrl, jogo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IJogo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IJogo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
