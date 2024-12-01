
package es.deusto.sd.google;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deusto.sd.google.dao.CredencialesRepository;
import es.deusto.sd.google.entity.Credenciales;

@Configuration
public class DatosMuestra {

    @Bean
    CommandLineRunner initData(CredencialesRepository credencialesRepository) {
		return args -> {			
            Credenciales usainBolt = new Credenciales("UsainBolt", "usain.bolt@athletics.com");
            Credenciales serenaWilliams = new Credenciales("SerenaWilliams", "serena.williams@tennis.com");
            Credenciales lebronJames = new Credenciales("LeBronJames", "lebron.james@basketball.com");
            Credenciales prueba = new Credenciales("string", "string");

            
            credencialesRepository.save(usainBolt);
            credencialesRepository.save(serenaWilliams);
            credencialesRepository.save(lebronJames);
            credencialesRepository.save(prueba);
        };
    }

}



