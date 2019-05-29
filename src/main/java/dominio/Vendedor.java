package dominio;

import dominio.repositorio.RepositorioProducto;

import java.util.Calendar;
import java.util.Date;

import dominio.excepcion.GarantiaExtendidaException;
import dominio.repositorio.RepositorioGarantiaExtendida;

public class Vendedor {

    public static final String EL_PRODUCTO_TIENE_GARANTIA = "El producto ya cuenta con una garantia extendida";
    public static final String EL_PRODUCTO_NO_TIENE_GARANTIA = "Este producto no cuenta con garantía extendida";
	public static final String EL_PRODUCTO_NO_EXISTE = "Producto no existe";

    private RepositorioProducto repositorioProducto;
    private RepositorioGarantiaExtendida repositorioGarantia;

    public Vendedor(RepositorioProducto repositorioProducto, RepositorioGarantiaExtendida repositorioGarantia) {
        this.repositorioProducto = repositorioProducto;
        this.repositorioGarantia = repositorioGarantia;

    }

    public void generarGarantia(String codigo, String nombreCliente) {

    	Producto producto = repositorioProducto.obtenerPorCodigo(codigo);
    	
    	if(codigoProductoTiene3Vocales(codigo)) 
    		throw new GarantiaExtendidaException(EL_PRODUCTO_NO_TIENE_GARANTIA);
    	if(tieneGarantia(codigo))
    		throw new GarantiaExtendidaException(EL_PRODUCTO_TIENE_GARANTIA);
    	if(producto.getCodigo().isEmpty())
    		throw new GarantiaExtendidaException(EL_PRODUCTO_NO_EXISTE);
    	
    	repositorioGarantia.agregar(llenarGarantia(producto, nombreCliente));

    }
    
	public GarantiaExtendida llenarGarantia(Producto producto, String nombreCliente) {
		
		if (producto.getPrecio() > 500000) {
			
			 return new GarantiaExtendida(producto, new Date(),
					sumarDias(new Date(), 200), producto.getPrecio()*0.2, nombreCliente);
			
		} else {
				
			return new GarantiaExtendida(producto, new Date(),
					sumarDias(new Date(), 100), producto.getPrecio()*0.1, nombreCliente);
		}
		
	}

	public Date sumarDias(Date date, int dias) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		if(dias == 100) {
							
			calendar.add(Calendar.DATE, dias);
			return calendar.getTime();
			
		}else {
			
			while(dias != 0) {
				if ((Calendar.MONDAY == calendar.get(Calendar.DAY_OF_WEEK)))
					calendar.add(Calendar.DATE, 1);
				calendar.add(Calendar.DATE, 1);
				dias--;
			}
			return calendar.getTime();
		}
		
	}
	
	public boolean codigoProductoTiene3Vocales(String codigo) {
		
		int count = 0;
		
		for (int i = 0; i < codigo.length(); i++) {
			if (detectarVocal(String.valueOf(codigo.charAt(i)))) {
				count++;
				if(count >= 3)
					return true;
			}
		}
		
		return false;
	}

	
	private boolean detectarVocal(String letra) {
		
		if (letra.equalsIgnoreCase("A") ||
			letra.equalsIgnoreCase("E") ||
			letra.equalsIgnoreCase("I") ||
			letra.equalsIgnoreCase("O") ||
			letra.equalsIgnoreCase("U"))
			return true;
		
		return false;
	}
	
	public boolean tieneGarantia(String codigo) {
    	
    	Producto producto = repositorioGarantia.obtenerProductoConGarantiaPorCodigo(codigo);
    	
    	if(producto != null)
    		return true;
    	
    	return false;
    }

}
