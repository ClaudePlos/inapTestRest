/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pl.models.css.StanowiskoKosztow;
import pl.models.nupr.NuprUprawnienieDTO;
import pl.nap.rest.css.CssService;
import pl.nap.rest.nupr.NuprService;
import pl.rest.ErrorMessage;
import pl.w5.client.model.PozycjaListyZadanDTO;

/**
 *
 * @author k.skowronski
 */
@Stateless
@Path("nupruprawnieniesk")
public class NuprUprawnienieSkFacadeREST extends AbstractFacade<NuprUprawnienieDTO> {
    
    @EJB
    NuprService nuprService;
    
    @EJB
    CssService cssService;

    @PersistenceContext(unitName = "NapPU_Test")
    private EntityManager em;

    public NuprUprawnienieSkFacadeREST() {
        super(NuprUprawnienieDTO.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public void create(NuprUprawnienieDTO entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, NuprUprawnienieDTO entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public NuprUprawnienieDTO find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<NuprUprawnienieDTO> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<NuprUprawnienieDTO> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    // moje
    @Path("/uprawnieniaUsera")
    @GET
    @Produces("application/json;charset=UTF8")
    public List<NuprUprawnienieDTO> uprawnieniaUsera( @QueryParam("userId") Long userId )
    {
       ErrorMessage er = new ErrorMessage();
       List<NuprUprawnienieDTO> upr = new ArrayList<NuprUprawnienieDTO>();
        
        try { 
    
            upr =  nuprService.uprawnieniaZalogowanego(userId);
   
        }
        catch( Exception e )
        {
             er.setMessage("Błąd - dfg345 + e.getMessage()");
           // return Response.serverError().status(Response.Status.BAD_REQUEST).entity(er).build();
        }
        
        return upr;
        
    } 
    
    @Path("/skUseraDlaUprawnienia")
    @GET
    @Produces("application/json;charset=UTF8")
    public List<StanowiskoKosztow> skUseraDlaUprawnienia( @QueryParam("userId") Long userId, @QueryParam("uprawnienie") String uprawnienie )
    {
       ErrorMessage er = new ErrorMessage();
       List<StanowiskoKosztow> sk = new ArrayList<StanowiskoKosztow>();
        
        try { 
    
            sk =  cssService.pobierzStanowiskaUseraDlaUprawnienia(userId,uprawnienie);
   
        }
        catch( Exception e )
        {
             er.setMessage("Błąd - dfg345 + e.getMessage()");
           // return Response.serverError().status(Response.Status.BAD_REQUEST).entity(er).build();
        }
        
        return sk;
        
    } 
    
    
    @Path("/listZadaniaUzytkownika")
    @GET
    @Produces("application/json;charset=UTF8")
    public List<PozycjaListyZadanDTO> listZadaniaUzytkownika( @QueryParam("uzId") Long uzId )
    {
       ErrorMessage er = new ErrorMessage();
       List<PozycjaListyZadanDTO> zadania = new ArrayList<PozycjaListyZadanDTO>();
        
        try { 
    
            zadania =  cssService.listZadaniaUzytkownika(uzId);
   
        }
        catch( Exception e )
        {
             er.setMessage("Błąd - dfg345 + e.getMessage()");
           // return Response.serverError().status(Response.Status.BAD_REQUEST).entity(er).build();
        }
        
        return zadania;
        
    } 
    
}
