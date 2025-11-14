import java.util.ArrayList;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
//Esta clase es la que va a gestionar todos los metodos relacionados a las operaciones principales
//Como podría ser añadir, listar, eliminar cuentas, etc...

public class Gestor {
    //Lista de todas las cuentas almacenadas/existentes en la memoria
    private List<Cuenta> cuentas = new ArrayList<>();

    //Nombre del archivo donde se van a guardar los datos
    private final String FILE_NAME = "contraseñas.txt";

    //Constructor que se ejecuta al crear el gestor e intenta cargar los datos previos
    public Gestor() {
        loadFromFile();
    }

    //Añadir una cuenta a la lista y guarda los cambios en el archivo creado antes
    public void addCuenta(String service, String user, String pass){
        //Codificamos la contraseña con un encode (no estará cifrada pero si algo mas oculta)
        cuentas.add(new Cuenta(service, user, encode(pass)));

        //Guardamos la info en el txt
        saveToFile();
    }

    //Mostramos todas las cuentas almacenadas en el txt
    public void listCuentas(){
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas guardadas");
        } else {
            cuentas.forEach(System.out::println);
        }
    }

    //Eliminar una cuenta segun el nombre del servicio
    public void borrarCuenta(String service){
        //recorremos la lista en busca del servicio y eliminamos
        cuentas.removeIf(a -> a.getService().equalsIgnoreCase(service));
        //guardamos en el txt tras eliminar
        saveToFile();
    }

    //Metodo para Codificar el texto y ocultarlo (lo usamos anteriormente para la pass pero aun no estaba creado como tal)
    //(esta práctica no es segura, pero es mejor que no tener nada)
    private String encode(String text){
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    //Ahora un metodo similar pero esta vez para descifrar
    private String decode(String text){
        return new String(Base64.getDecoder().decode(text));
    }

    //Ahora el metodo saveToFile para guardar todas las cuentas en el archivo (esto sobrescribe el txt)
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Para cada cuenta escribimos una línea con sus tres campos separados por ;
            for (Cuenta a : cuentas)
                pw.println(a.getService() + ";" + a.getUser() + ";" + a.getPass());
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    //Por último hacemos un metodo para cargar dicho archivo (solo si existe)
    private void loadFromFile() {
        File archivo = new File(FILE_NAME);
        if (!archivo.exists()) return;

        try (Scanner sc = new Scanner(archivo)) {
            // Leemos el archivo línea por línea
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                if (parts.length == 3)
                    cuentas.add(new Cuenta(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}
