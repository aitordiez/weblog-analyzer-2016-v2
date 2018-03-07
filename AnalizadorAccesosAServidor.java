import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class AnalizadorAccesosAServidor
{
    private ArrayList<Acceso> accesos;
    
    
    public AnalizadorAccesosAServidor() 
    {
        accesos = new ArrayList<>();
    }
    
    
    public void analizarArchivoDeLog(String archivo)
    {
        accesos.clear();
        File archivoALeer = new File(archivo);
        try {
            Scanner sc = new Scanner(archivoALeer);
            while (sc.hasNextLine()) {
                String lineaLeida = sc.nextLine();               
                String[] elementosLinea = lineaLeida.split(" ");
                Acceso accesoActual = new Acceso(lineaLeida);
                
                accesos.add(accesoActual);
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
    }
    
    
    public int obtenerHoraMasAccesos() 
    {
        int valorADevolver = -1;
        
        if (!accesos.isEmpty()) {
            int[] accesosPorHora = new int[24];
    
            for (Acceso accesoActual : accesos) {
                int horaAccesoActual = accesoActual.getHora();
                accesosPorHora[horaAccesoActual] = accesosPorHora[horaAccesoActual] + 1;
            }
            
            int numeroDeAccesosMasAlto = accesosPorHora[0];
            int horaDeAccesosMasAlto = 0;
            for (int i = 0; i < accesosPorHora.length; i++) {
                if (accesosPorHora[i] >= numeroDeAccesosMasAlto) {
                    numeroDeAccesosMasAlto = accesosPorHora[i];
                    horaDeAccesosMasAlto = i;
                }
            }
            
            valorADevolver = horaDeAccesosMasAlto;                      
        }
        
        return valorADevolver;
    }

    /**
     * Devuelve el nombre de la pagina Web mas solicitada por los clientes.
     * @return Devuelve un String que contiene el nombre de la pagina web mas 
     * solicitada por el cliente, en caso de empate devuelve cualquiera de las
     * paginas y si no hay datos devuelve null.
     */
    
    public String paginaWebMasSolicitada() 
    {
        String nombreMasAccesos=null;
        ArrayList<String> paginasWeb=new ArrayList<String>();
        for(Acceso acceso : accesos){
            paginasWeb.add(acceso.getPaginaWebSolicitada());
        }
        int numeroDeVecesQueSeHaRepetidoUnaPaginaWeb=0;
        for(String accesoAPaginasWeb: paginasWeb){
            int contadorAccesos=0;
            for(Acceso acceso : accesos){
                if(acceso.getPaginaWebSolicitada().equals(accesoAPaginasWeb)){
                    contadorAccesos++;
                }
            }

            if(contadorAccesos > numeroDeVecesQueSeHaRepetidoUnaPaginaWeb){
                numeroDeVecesQueSeHaRepetidoUnaPaginaWeb=contadorAccesos;
                nombreMasAccesos=accesoAPaginasWeb;
            }
        }

        if(nombreMasAccesos==null){
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
        return nombreMasAccesos;   
    }
    
    /**
     * Devuelve la direccion IP del cliente que ha realizado el mayor acceso 
     * exitoso al servidor
     * @return Devuelve un String que contiene la direccion Ip del cliente que 
     * mas accesos ha tenido al servido, en caso de empate devuelve cualquiera 
     * de las paginas y si no hay datos devuelve null.
     */
    public String clienteConMasAccesosExitosos()
    {
        String direccionDelClienteConMasAccesosExitosos = null;
        int numeroDeVecesQueSeHaRepetidoLaDireccionIp=0;
        int direccionIpMasAlta=0;
        for(Acceso direccionesIp1 : accesos){
            int vecesQueSeHaRepetidoLaDireccionIp=0;
            for(Acceso direccionesIp2 : accesos){
                if(direccionesIp1.getDireccionIp().equals(direccionesIp2.getDireccionIp()) && Integer.parseInt(direccionesIp2.getCodigoDeRespuesta().substring(0,1)) != 4){
                    vecesQueSeHaRepetidoLaDireccionIp++;
                }
            }
            String[] cuartoOcteto= direccionesIp1.getDireccionIp().split("\\.");
            if(vecesQueSeHaRepetidoLaDireccionIp > numeroDeVecesQueSeHaRepetidoLaDireccionIp){
                numeroDeVecesQueSeHaRepetidoLaDireccionIp=vecesQueSeHaRepetidoLaDireccionIp;
                direccionDelClienteConMasAccesosExitosos=direccionesIp1.getDireccionIp();
                direccionIpMasAlta=Integer.parseInt(cuartoOcteto[3]);
            }else if(vecesQueSeHaRepetidoLaDireccionIp == numeroDeVecesQueSeHaRepetidoLaDireccionIp && direccionIpMasAlta < Integer.parseInt(cuartoOcteto[3])){
                direccionDelClienteConMasAccesosExitosos = direccionesIp1.getDireccionIp();
            }
        } 

        if(direccionDelClienteConMasAccesosExitosos==null){
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
        return direccionDelClienteConMasAccesosExitosos;
    }


}
