import { Component, OnInit } from '@angular/core';
import {PersonaService} from 'src/app/core/service/persona.service';
import {ResponseDto} from 'src/app/core/model/ResponseDto';
import {MovimientoDto} from 'src/app/core/model/MovimientoDto';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {

  response: ResponseDto = new ResponseDto;
  inputValue: string = '';

  movimientos: MovimientoDto[] = [];

  name: string = '';
  yearOld: string = '';
  account: string = '';
  rol: string = '';
  balance: number = 0;

  

  constructor(private personaService: PersonaService) { }

  ngOnInit(): void {
  }

  public getInformation(){

    this.personaService.getPerson(this.inputValue).subscribe(response=>{
      this.response = response;
      this.movimientos = response.data.movimientos;

      this.name = response.data.nombre +' '+ response.data.apellido;
      this.yearOld = response.data.edad;
      this.account = response.data.cuenta;
      this.rol = response.data.cargo;
      this.balance = response.data.saldo;
    }, ex=>{
      console.log(ex.error);
    }
    );
  }

}
