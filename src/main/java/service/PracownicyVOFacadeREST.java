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
import pl.models.hr.PracownikVO;
import pl.models.users.NapUzytkownikVO;
import pl.nap.rest.EK.EkService;
import pl.rest.ErrorMessage;

/**
 *
 * @author k.skowronski
 */
@Stateless
@Path("pracownicy")
public class PracownicyVOFacadeREST extends AbstractFacade<PracownikVO> {
    
    @EJB
    EkService ekService;

    @PersistenceContext(unitName = "NapPU_Test")
    private EntityManager em;

    public PracownicyVOFacadeREST() {
        super(PracownikVO.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public void create(PracownikVO entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, PracownikVO entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public PracownikVO find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<PracownikVO> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<PracownikVO> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    //moje 
    
    @GET
    @Path("/listPracUzytkownika/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<PracownikVO> listPracUzytkownika( @PathParam("id") Long uzIdUser )
    {
        String sql = "";
        List<PracownikVO> ret = em.createQuery(sql).setParameter("uzId",uzIdUser).getResultList();

        return ret; 
    }  
    
    
    @Path("/listPracDlaSK")
    @GET
    @Produces("application/json;charset=UTF8")
    public List<PracownikVO> skUseraDlaUprawnienia( @QueryParam("skId") Long skId, @QueryParam("skKod") String skKod )
    {
       ErrorMessage er = new ErrorMessage();
       List<PracownikVO> sk = new ArrayList<PracownikVO>();
        
        try { 
    
            sk =  ekService.pobierzPracownikowDlaSk(skId,skKod);
   
        }
        catch( Exception e )
        {
             er.setMessage("Błąd - dfg345 + e.getMessage()");
           // return Response.serverError().status(Response.Status.BAD_REQUEST).entity(er).build();
        }
        
        return sk;
        
    }
    
}
