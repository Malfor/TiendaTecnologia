package testdatabuilder;

import java.util.Date;

import dominio.GarantiaExtendida;
import dominio.Producto;

public class GarantiaExtendidaTestDataBuilder {
	
	private static final String NOMBRE_CLIENTE = "RODOLFO BARRIOS";
	
	private Producto producto;
    private Date fechaSolicitudGarantia;
    private Date fechaFinGarantia;
    private double precioGarantia;
    private String nombreCliente;
    
    
    public GarantiaExtendidaTestDataBuilder(Producto producto) {
		this.producto = producto;
		this.fechaSolicitudGarantia = new Date();
		this.fechaFinGarantia = new Date();
		this.precioGarantia = calcularPrecioGarantia(producto);
		this.nombreCliente = NOMBRE_CLIENTE;
	}
    
    public GarantiaExtendidaTestDataBuilder conFechaSolicitudGarantia(Date fechaSolicitudGarantia) {
    	this.fechaSolicitudGarantia = fechaSolicitudGarantia;
    	return this;
    }
    
    public GarantiaExtendidaTestDataBuilder conFechaFinGarantia(Date fechaFinGarantia) {
    	this.fechaFinGarantia = fechaFinGarantia;
    	return this;
    }
    
    public GarantiaExtendidaTestDataBuilder conNombreCliente(String nombreCliente) {
    	this.nombreCliente = nombreCliente;
    	return this;
    }
    
    public GarantiaExtendida build() {
    	return new GarantiaExtendida(this.producto, this.fechaSolicitudGarantia,
    			this.fechaFinGarantia, this.precioGarantia, this.nombreCliente);
    }
    
	private double calcularPrecioGarantia(Producto producto) {
		
		if (producto.getPrecio() > 500000)
			return producto.getPrecio() * 0.2;
		else
			return producto.getPrecio() * 0.1;
		
	} 
	
}
