/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author k.skowronski
 */
@javax.ws.rs.ApplicationPath("rs")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(pl.CrossOriginResourceSharingFilter.class);
        resources.add(pl.login.rest.LoginService.class);
        resources.add(service.AdresyVOFacadeREST.class);
        resources.add(service.NapUzytkownikVOFacadeREST.class);
        resources.add(service.NuprUprawnienieSkFacadeREST.class);
        resources.add(service.PracownicyVOFacadeREST.class);
        resources.add(service.StanowiskoKosztowFacadeREST.class);
        resources.add(service.ZatrudnienieVOFacadeREST.class);
    }
    
}
