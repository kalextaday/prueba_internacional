import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http'
import { Observable } from 'rxjs';
import {ResponseDto} from 'src/app/core/model/ResponseDto';


@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  private baseUrl = 'http://localhost:8080/prueba/api';
  
  private endpointSearch = '/persona/v1/buscar';

  constructor(private http: HttpClient) { }

  public getPerson(identification: string): Observable <ResponseDto>{
    const params = new HttpParams()
    .set('identification', identification);

    return this.http.get<ResponseDto>(`${this.baseUrl}${this.endpointSearch}`,{params});
  }

}
