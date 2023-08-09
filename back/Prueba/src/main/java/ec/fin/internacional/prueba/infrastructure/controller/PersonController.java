/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.internacional.prueba.infrastructure.controller;

import ec.fin.internacional.prueba.application.dto.DtoOutPerson;
import ec.fin.internacional.prueba.application.dto.ResponseGeneral;
import ec.fin.internacional.prueba.application.service.PersonService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author PC
 */
@Path("persona")
public class PersonController {
    
    private PersonService personService;
    
    public PersonController(){
        personService = new PersonService();
    }
    
    @GET
    @Path("v1/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInformation(@QueryParam("identification") String identification) {
        ResponseGeneral response = new ResponseGeneral();
        DtoOutPerson result = personService.getPersonByIdentification(identification);
        
        if(result != null){
            response.setCode("0");
            response.setDescription("Busqueda realizada correctamente");
            response.setData(result);
        }else{
            response.setCode("1");
            response.setDescription("Error al buscar la persona");
            response.setData(null);
        }
        return Response.ok(response).build();

    }
    
}
