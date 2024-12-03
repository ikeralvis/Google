package es.deusto.sd.google.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import es.deusto.sd.google.service.LoginService;
import es.deusto.sd.google.dto.CredencialesDTO;
import es.deusto.sd.google.entity.Credenciales;

@RestController
@RequestMapping("/auth")
@Tag(name = "Control de los usuarios", description = "Funciones relacionadas con los usuarios: registro, login y logout")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}


    //FUNCION PARA HACER LOGIN
    @Operation(
        summary = "Logearse en el sistema",
        description = "Permite a un usuario iniciar sesión proporcionando correo electrónico y contraseña.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: Inicio de sesión exitoso, devuelve un token"),
            @ApiResponse(responseCode = "401", description = "No autorizado: Credenciales inválidas, inicio de sesión fallido"),
            @ApiResponse(responseCode = "500", description = "Error interno en el servidor"),
            @ApiResponse(responseCode = "409", description = "El usuario no está registrado en el sistema") 
        }
    )

    @PostMapping("/login")
    public ResponseEntity<String> login(
        @Parameter(name = "credentials", description = "Credenciales del usuario", required = true)    	
    		@RequestBody CredencialesDTO credencialesDTO) {

        Credenciales credenciales = convertirDTOaCredenciales(credencialesDTO);

        boolean validarEmail = loginService.validarEmail(credenciales.getEmail());

        if(validarEmail){
            boolean validarLogin = loginService.validarLogin(credencialesDTO.getEmail(), credencialesDTO.getPassword());
            if(validarLogin){
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Contraseña no valida", HttpStatus.UNAUTHORIZED);
            }

        } else{
            return new ResponseEntity<>("Email no valido", HttpStatus.CONFLICT);
        }
    }

    @Operation(
        summary = "Verificar el email del usuario",
        description = "Permite verificar si un email está registrado en el sistema.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: El email está registrado"),
            @ApiResponse(responseCode = "404", description = "No encontrado: El email no está registrado"),
            @ApiResponse(responseCode = "500", description = "Error interno en el servidor")
        }
    )
    
    @PostMapping("/verificar-email")
    public ResponseEntity<String> verificarEmail(
        @Parameter(name = "email", description = "Email del usuario", required = true)
        @RequestBody String email) {

        boolean emailRegistrado = loginService.validarEmail(email);

        if (emailRegistrado) {
            return new ResponseEntity<>("El email está registrado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("El email no está registrado", HttpStatus.NOT_FOUND);
        }
    }

    public Credenciales convertirDTOaCredenciales(CredencialesDTO credentialsDTO){
        return new Credenciales(credentialsDTO.getEmail(), credentialsDTO.getPassword());
    }
}

 