public class Acceso
{
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;

    public Acceso(String accesoADatos)
    {
        String[] datos = accesoADatos.split(" ");
        ano = Integer.parseInt(datos[0]);
        mes = Integer.parseInt(datos[1]);
        dia = Integer.parseInt(datos[2]);
        hora = Integer.parseInt(datos[3]);
        minutos = Integer.parseInt(datos[4]);
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
}