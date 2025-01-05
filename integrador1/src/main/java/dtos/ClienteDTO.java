package main.java.dtos;

public class ClienteDTO {
    private int idCliente;
    private String nombre;
    private int totalFacturado;

    public ClienteDTO(int idcliente, String nombre, int totalFacturado) {
        this.idCliente = idcliente;
        this.nombre = nombre;
        this.totalFacturado = totalFacturado;
    }

    public String toString() {
        return  "idCliente= " + idCliente + " , Nombre= " + nombre + " , Total Facturado= $" + totalFacturado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTotalFacturado() {
        return totalFacturado;
    }
}