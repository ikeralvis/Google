package es.deusto.sd.google.dto;

public class CredencialesDTO{
    private String email;
    private String password;

    public CredencialesDTO(String email, String password){
        this.email = email;
        this.password = password;
    }

    public CredencialesDTO(){
        this.email = "";
        this.password = "";
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
}