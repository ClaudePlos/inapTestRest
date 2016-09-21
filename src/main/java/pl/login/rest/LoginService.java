/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.login.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pl.rest.ErrorMessage;
import pl.nap.rest.utils.NapUsersApiLight;
import pl.models.users.NapUzytkownikVO;

/**
 *
 * @author k.skowronski
 */
@Stateless
@Path("/login")
@LocalBean
public class LoginService {
    @EJB
    NapUsersApiLight usersLight; 
    
    
   
    
    @POST
    @Path("")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public Response login( NapUzytkownikVO userLogin )
    {
        ErrorMessage er = new ErrorMessage();
        if ( userLogin == null ) { 
            er.setMessage("Błąd - brak użytkownika");
            return Response.serverError().status(Response.Status.BAD_REQUEST).entity(er).build();
        }
        if ( userLogin.getUzNazwa() == null ) { 
            er.setMessage("Błąd - brak nazwy użytkownika");
            return Response.serverError().status(Response.Status.BAD_REQUEST).entity(er).build();
        }
        if ( userLogin.getUzHaslo() == null ) { 
            er.setMessage("Błąd - brak hasła użytkownika");
            return Response.serverError().status(Response.Status.BAD_REQUEST).entity(er).build();
        }
        //
        
        try { 
            
            NapUzytkownikVO user = usersLight.login(userLogin.getUzNazwa(), userLogin.getUzHaslo() );
            
            userLogin.setUzId( user.getUzId() );
            userLogin.setPrcId( user.getPrcId() );
            userLogin.setToken( user.getToken());
            userLogin.setUzHaslo( null );
            
            return Response.ok().entity(userLogin).build();
            
        }
        catch( Exception e )
        {
             er.setMessage("Błąd - nie udało się zalogować" + e.getMessage());
            return Response.serverError().status(Response.Status.BAD_REQUEST).entity(er).build();
        }
        
        
        
    }  
    
    
   
}
