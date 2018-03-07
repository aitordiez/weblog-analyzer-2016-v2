public class Acceso
{
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    private String ip;
    private String paginaWebSolicitada;
    private String codigoDeRespuesta;
    public Acceso(String accesoADatos)
    {
        String[] datosAccesos=accesoADatos.split(" ");
        ip=datosAccesos[0];
        this.ano = Integer.parseInt(datosAccesos[1].substring(1,5));
        this.mes = Integer.parseInt(datosAccesos[2]);
        this.dia = Integer.parseInt(datosAccesos[3]);
        this.hora = Integer.parseInt(datosAccesos[4]);
        this.minutos = Integer.parseInt(datosAccesos[5].substring(0,2));
        paginaWebSolicitada = datosAccesos[6];
        this.codigoDeRespuesta = datosAccesos[7];
    }

    public int getAno() 
    {
        return ano;
    }

    public int getMes()
    {
        return mes;
    }

    public int getDia()
    {
        return dia;
    }

    public int getHora()
    {
        return hora;
    }

    public int getMinutos()
    {
        return minutos;
    }

    public String getPaginaWebSolicitada(){
        return paginaWebSolicitada;
    }

    public String getDireccionIp(){
        return ip;
    }

    public String getCodigoDeRespuesta(){
        return codigoDeRespuesta;
    }
}