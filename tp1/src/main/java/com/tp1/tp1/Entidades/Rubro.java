package com.tp1.tp1.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro extends BaseEntidad{
    private String denominacion;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos=new ArrayList<>();
    public void AddProducto(Producto produ) {
        getProductos().add(produ);
    }
    public void MostrarProductos() {
        for (Producto produ : getProductos()) {
            System.out.println("tipo: " + produ.getTipo() + ", tiempo estimado: " + produ.getTpo_estimado()+ ", denominacion: " + produ.getDenominacion()+ ", precio venta : " + produ.getPrecio_venta()+ ", precio compra : " + produ.getPrecio_compra()+ ", stock actual: " + produ.getStock()+ ", stock minimo: " + produ.getStock_min()+ ",  unidad de medida: " + produ.getUni_medida()+ ", receta : " + produ.getReceta());
        }
    }
    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
