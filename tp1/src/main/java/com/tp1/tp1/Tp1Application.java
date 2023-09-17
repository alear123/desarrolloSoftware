package com.tp1.tp1;

import com.tp1.tp1.Entidades.*;
import com.tp1.tp1.Enumeraciones.EstadoPedido;
import com.tp1.tp1.Enumeraciones.FormaDePago;
import com.tp1.tp1.Enumeraciones.TipoDeEnvio;
import com.tp1.tp1.Enumeraciones.TipoDeProducto;
import com.tp1.tp1.Repositorios.ClienteRepository;
import com.tp1.tp1.Repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Tp1Application {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
		System.out.println("ESTOY FUNCIONANDO");
	}
	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo){
		return args -> {
			System.out.println("----------------ESTOY----FUNCIONANDO---------------------");
		Domicilio domi1=new Domicilio();
		domi1.setCalle("suipacha");
		domi1.setNumero("123");
		domi1.setLocalidad("Las heras");


		Domicilio domi2=new Domicilio();
		domi2.setCalle("alvarez");
		domi2.setNumero("321");
		domi2.setLocalidad("Guaymallen");

		Cliente cliente1=new Cliente();
		cliente1.setNombre("Alejandro");
		cliente1.setApellido("Arce");
		cliente1.setTelefono("2613004548");
		cliente1.setEmail("alear12373@gmail.com");
		cliente1.AddDomicilio(domi1);
		cliente1.AddDomicilio(domi2);

		Producto p1=new Producto();
		p1.setTipo(TipoDeProducto.Insumo);
		p1.setTpo_estimado(2);
		p1.setDenominacion("lomo");
		p1.setPrecio_venta(200.00);
		p1.setPrecio_compra(150.00);
		p1.setStock_min(100);
		p1.setStock(150);
		p1.setUni_medida("cantidad de lomos");
		p1.setReceta("cortar el pan, colocar la ensalada y luego colocar el bife cocido");

		Producto p2=new Producto();
		p1.setTipo(TipoDeProducto.Insumo);
		p1.setTpo_estimado(1);
		p1.setDenominacion("pizza");
		p1.setPrecio_venta(250.00);
		p1.setPrecio_compra(150.00);
		p1.setStock_min(200);
		p1.setStock(250);
		p1.setUni_medida("cantidad de pizzas");
		p1.setReceta("colocar el queso arriba de la prepizza y llevar al horno por 30 minutos");

		Rubro r1=new Rubro();
		r1.setDenominacion("comida");
		r1.AddProducto(p1);
		r1.AddProducto(p2);

		SimpleDateFormat formatoFecha = new SimpleDateFormat ("yyyy-MM-dd");
		String fechaString = "2023-09-09";
		Date fecha1 = formatoFecha.parse(fechaString);

		SimpleDateFormat formatoFecha2 = new SimpleDateFormat ("yyyy-MM-dd");
		String fechaString2 = "2023-10-09";
		Date fecha2 = formatoFecha.parse(fechaString);

		DetallePedido detped1= new DetallePedido();
		detped1.setCant(2);
		detped1.setSubtotal(500);

		DetallePedido detped2= new DetallePedido();
		detped1.setCant(2);
		detped1.setSubtotal(400);

		DetallePedido detped3= new DetallePedido();
		detped1.setCant(3);
		detped1.setSubtotal(600);



		Pedido ped1=new Pedido();
		ped1.setEstado(EstadoPedido.Entregado);
		ped1.setFecha(fecha1);
		ped1.setTipo_envio(TipoDeEnvio.Delivery);
		ped1.AddDetallePedido(detped1);
		ped1.AddDetallePedido(detped2);
		ped1.obtenerTotal();

		Pedido ped2=new Pedido();
		ped2.setEstado(EstadoPedido.Entregado);
		ped2.setFecha(fecha2);
		ped2.setTipo_envio(TipoDeEnvio.TakeAway);
		ped2.AddDetallePedido(detped1);
		ped2.AddDetallePedido(detped2);
		ped2.AddDetallePedido(detped3);
		ped2.obtenerTotal();

		double t1= ped1.obtenerTotal();
			Factura f1=new Factura();
			//f1.setFecha(fecha);
			f1.setDto(15.00);
			f1.setNumero(111);
			f1.setForma_pago(FormaDePago.Efectivo);
			f1.setTotal(t1);

			double t2= ped2.obtenerTotal();
			Factura f2=new Factura();
			//f1.setFecha(fecha);
			f1.setDto(20.00);
			f1.setNumero(112);
			f1.setForma_pago(FormaDePago.MercadoPago);
			f1.setTotal(t2);
		cliente1.AddPedido(ped1);
		cliente1.AddPedido(ped2);
		rubroRepository.save(r1);
		clienteRepository.save(cliente1);
			Cliente clienteRecuperado= clienteRepository.findById(cliente1.getId()).orElse(null);
			if(clienteRecuperado!=null){
				System.out.println("nombre: "+ clienteRecuperado.getNombre());
				System.out.println("apellido: "+ clienteRecuperado.getApellido());
				System.out.println("nombre: "+ clienteRecuperado.getTelefono());
				System.out.println("nombre: "+ clienteRecuperado.getEmail());
				clienteRecuperado.MostrarDomicilios();
			}
		Rubro rubroRecuperado= rubroRepository.findById(r1.getId()).orElse(null);
		if(rubroRecuperado!=null){
			System.out.println("denominacion: "+rubroRecuperado.getDenominacion());
			rubroRecuperado.MostrarProductos();

			clienteRecuperado.MostrarDomicilios();
			}
		};

	}
}
