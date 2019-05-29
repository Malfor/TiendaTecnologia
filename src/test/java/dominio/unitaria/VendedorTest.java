package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import dominio.Vendedor;
import dominio.GarantiaExtendida;
import dominio.Producto;
import dominio.repositorio.RepositorioProducto;
import dominio.repositorio.RepositorioGarantiaExtendida;
import testdatabuilder.ProductoTestDataBuilder;

public class VendedorTest {
	
	private static final String CODIGO = "U01IOA015E";
	private static final String NOMBRE_CLIENTE = "RODOLFO BARRIOS";
	private static final double PRECIO_PRODUCTO = 450000;
	
	private static final double PRECIO_200 = 156000; //precio producto test DataBuilder dafault 780.000
	private static final double PRECIO_100 = 45000 ; //precio producto 450.000
	
		
	@Test
	public void productoYaTieneGarantiaTest() {
		
		// arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		
		Producto producto = productoTestDataBuilder.build(); 
		
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(producto);
		
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		// act 
		boolean existeProducto = vendedor.tieneGarantia(producto.getCodigo());
		
		//assert
		assertTrue(existeProducto);
	}
	
	@Test
	public void productoNoTieneGarantiaTest() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();
		
		Producto producto = productoestDataBuilder.build(); 
		
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
		
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		// act 
		boolean existeProducto =  vendedor.tieneGarantia(producto.getCodigo());
		
		//assert
		assertFalse(existeProducto);
	}
	
	@Test
	public void productoTiene3VocalesTest() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder().conCodigo(CODIGO);
				
		Producto producto = productoestDataBuilder.build(); 
				
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
				
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
			
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
				
		// act 
		boolean existeProducto =  vendedor.codigoProductoTiene3Vocales(producto.getCodigo());
				
		//assert
		assertTrue(existeProducto);
		
	}
	
	@Test
	public void productoNoTiene3VocalesTest() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();
				
		Producto producto = productoestDataBuilder.build(); 
				
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
				
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
			
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
				
		// act 
		boolean existeProducto =  vendedor.codigoProductoTiene3Vocales(producto.getCodigo());
				
		//assert
		assertFalse(existeProducto);
		
	}
	
	@Test
	public void garantiaSuma200DiasTest() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();
				
		Producto producto = productoestDataBuilder.build(); 
				
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
				
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
			
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018,7,16);
		
		// act 
		Date date =  vendedor.sumarDias(calendar.getTime(), 200);
		
		calendar.set(2019, 3, 6);
		
		//assert
		assertTrue(0 == calendar.getTime().compareTo(date));
		
	}
	
	@Test
	public void garantiaSuma100DiasTest() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();
				
		Producto producto = productoestDataBuilder.build(); 
				
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
				
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
			
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018,7,16);
		
		// act 
		Date date =  vendedor.sumarDias(calendar.getTime(), 100);

		calendar.set(2018,10,24);
		
		//assert
		assertTrue(0 == calendar.getTime().compareTo(date));
	}
	
	@Test
	public void llenarGarantia200Test() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();
				
		Producto producto = productoestDataBuilder.build(); 
				
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
				
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
			
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		// act 
		GarantiaExtendida garantiaExtendida = vendedor.llenarGarantia(producto, NOMBRE_CLIENTE);
		
		//assert
		assertEquals(producto, garantiaExtendida.getProducto());
		assertTrue(garantiaExtendida.getPrecioGarantia() == PRECIO_200);
		assertEquals(NOMBRE_CLIENTE, garantiaExtendida.getNombreCliente());
	}
	
	@Test
	public void llenarGarantia100Test() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder()
				.conPrecio(PRECIO_PRODUCTO);
				
		Producto producto = productoestDataBuilder.build(); 
				
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
				
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
			
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		// act 
		GarantiaExtendida garantiaExtendida = vendedor.llenarGarantia(producto, NOMBRE_CLIENTE);
		
		//assert
		assertEquals(producto, garantiaExtendida.getProducto());
		assertTrue(garantiaExtendida.getPrecioGarantia() == PRECIO_100);
		assertEquals(NOMBRE_CLIENTE, garantiaExtendida.getNombreCliente());
	}
	
}
