package com.tp1.tp1.Entidades;

import com.tp1.tp1.Enumeraciones.EstadoPedido;
import com.tp1.tp1.Enumeraciones.TipoDeEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntidad{
    private Date fecha;
    private double total;
    private EstadoPedido estado;

    private TipoDeEnvio tipo_envio;

    @OneToOne(cascade =CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="pedido_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallespedido=new ArrayList<>();
    public void AddDetallePedido(DetallePedido dp) {
        getDetallespedido().add(dp);
    }
    public void MostrardetallePedidos() {
        for (DetallePedido dpedido : getDetallespedido()) {
            System.out.println("cantidad: " + dpedido.getCant() + ", subtotal: " + dpedido.getSubtotal());
        }
    }
    public void asociarFactura(Factura dp) {
        setFactura(dp);
    }

    public void MostrarFactura() {
        System.out.println("numero factura:"+ getFactura().getNumero());
        System.out.println("fecha factura :"+ getFactura().getFecha());
        System.out.println(" descuento:"+ getFactura().getDto());
        System.out.println("total:"+ getFactura().getTotal());
        System.out.println("fecha :"+ getFactura().getFecha());
        System.out.println("forma de pago:"+ getFactura().getForma_pago());
    }

    public double obtenerTotal() {
        for (DetallePedido detped:getDetallespedido()){
            total+= detped.getSubtotal();
        }
        return total;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public TipoDeEnvio getTipo_envio() {
        return tipo_envio;
    }

    public void setTipo_envio(TipoDeEnvio tipo_envio) {
        this.tipo_envio = tipo_envio;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<DetallePedido> getDetallespedido() {
        return detallespedido;
    }

    public void setDatallespedido(List<DetallePedido> datallespedido) {
        this.detallespedido = datallespedido;
    }
}
