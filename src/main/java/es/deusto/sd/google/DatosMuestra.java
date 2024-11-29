
package es.deusto.sd.google;

import org.springframework.context.annotation.Configuration;

import es.deusto.sd.google.entity.Credenciales;

@Configuration
public class DatosMuestra {
    // Usuarios de muestra
    Credenciales usainBolt = new Credenciales("UsainBolt", "usain.bolt@athletics.com");
    Credenciales serenaWilliams = new Credenciales("SerenaWilliams", "serena.williams@tennis.com");
    Credenciales lebronJames = new Credenciales("LeBronJames", "lebron.james@basketball.com");


    
}


