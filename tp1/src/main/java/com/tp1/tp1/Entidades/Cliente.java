package com.tp1.tp1.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends BaseEntidad {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos=new ArrayList<>();
    public void AddDomicilio(Domicilio domi) {
        getDomicilios().add(domi);
    }
    public void AddPedido(Pedido pedido) {
        getPedidos().add(pedido);
    }

    public void MostrarDomicilios() {
        for (Domicilio domi : getDomicilios()) {
            System.out.println("calle: " + domi.getCalle() + ", numero: " + domi.getNumero());
        }
    }

    public void MostrarPedidos() {
        for (Pedido pedido : getPedidos()) {
           System.out.println("fecha: " + pedido.getFecha() + ", estado: " + pedido.getEstado()+ ", total: " + pedido.getTotal()+ ", numero factura: " + pedido.getFactura().getNumero() + ", Tipo de envio: " + pedido.getTipo_envio());
        }
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Domicilio> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
