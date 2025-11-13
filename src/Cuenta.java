//Esta clase se va a usar para almacenar la información de una cuenta utilizada en un servicio
//El usuario, la contraseña.. (Por ejemplo, los datos de la cuenta de Google)
public class Cuenta {
    //Definimos los atributos que se van a guardar posteriormente
    private String service;
    private String user;
    private String pass;

    //Ahora creamos un constructor para poder crear objetos de la clase
    public Cuenta(String service, String user, String pass) {
        this.service = service;
        this.user = user;
        this.pass = pass;
    }

    //Hacemos lo mismo, ahora con getter para acceder a los datos de los atributos

    public String getService() {
        return service;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    //Por último creamos un metodo toString
    //Este metodo es para definir como se muestra la cuenta en el terminal al hacer el print
    @Override
    public String toString() {
        return "Cuenta{" +
                "Servicio='" + service + '\'' +
                ", Usuario='" + user + '\'' +
                ", Contraseña='" + pass + '\'' +
                '}';
    }
}
