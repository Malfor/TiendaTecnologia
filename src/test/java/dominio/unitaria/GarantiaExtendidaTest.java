package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import dominio.GarantiaExtendida;
import dominio.Producto;
import testdatabuilder.GarantiaExtendidaTestDataBuilder;
import testdatabuilder.ProductoTestDataBuilder;

public class GarantiaExtendidaTest {

	private static final String CODIGO = "F01TSA015E";
	private static final String NOMBRE_PRODUCTO = "Mouse USB";
	private static final int PRECIO_1 = 650000;
	
	private static final int PRECIO_2 = 450000;
	
	private Calendar ini, fin1, fin2;
	private static final String NOMBRE_CLIENTE = "RODOLFO BARRIOS";
	private static final double PRECIO_GARANTIA_ESPERADO_1 = 130000;
	private static final double PRECIO_GARANTIA_ESPERADO_2 = 45000;
	
	
	@Before
	public void ini() {
		ini = Calendar.getInstance();
		fin1 = Calendar.getInstance();
		fin2 = Calendar.getInstance();
		
		ini.set(2018, 7, 16);
		fin1.set(2019, 3, 6);
		fin2.set(2018, 10, 24);
	}
	
	
	@Test
	public void crearGarantiaExtendida200Test() {
		
		// arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder().
				conNombre(NOMBRE_PRODUCTO).
				conCodigo(CODIGO).
				conPrecio(PRECIO_1);
		Producto producto = productoTestDataBuilder.build();
		
		GarantiaExtendidaTestDataBuilder garantiaExtendidaTestDataBuilder = new GarantiaExtendidaTestDataBuilder(producto).
				conFechaSolicitudGarantia(ini.getTime()).
				conFechaFinGarantia(fin1.getTime()).
				conNombreCliente(NOMBRE_CLIENTE);

		// act
		GarantiaExtendida garantiaExtendida = garantiaExtendidaTestDataBuilder.build();

		// assert
		assertEquals(producto, garantiaExtendida.getProducto());
		assertEquals(ini.getTime(), garantiaExtendida.getFechaSolicitudGarantia());
		assertEquals(fin1.getTime(), garantiaExtendida.getFechaFinGarantia());
		assertTrue(PRECIO_GARANTIA_ESPERADO_1 == garantiaExtendida.getPrecioGarantia());
		assertEquals(NOMBRE_CLIENTE, garantiaExtendida.getNombreCliente());
		
	}
	
	@Test
	public void crearGarantiaExtendida100Test() {
		
		// arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder().
				conNombre(NOMBRE_PRODUCTO).
				conCodigo(CODIGO).
				conPrecio(PRECIO_2);
		Producto producto = productoTestDataBuilder.build();
		
		GarantiaExtendidaTestDataBuilder garantiaExtendidaTestDataBuilder = new GarantiaExtendidaTestDataBuilder(producto).
				conFechaSolicitudGarantia(ini.getTime()).
				conFechaFinGarantia(fin2.getTime()).
				conNombreCliente(NOMBRE_CLIENTE);

		// act
		GarantiaExtendida garantiaExtendida = garantiaExtendidaTestDataBuilder.build();

		// assert
		assertEquals(producto, garantiaExtendida.getProducto());
		assertEquals(ini.getTime(), garantiaExtendida.getFechaSolicitudGarantia());
		assertEquals(fin2.getTime(), garantiaExtendida.getFechaFinGarantia());
		assertTrue(PRECIO_GARANTIA_ESPERADO_2 == garantiaExtendida.getPrecioGarantia());
		assertEquals(NOMBRE_CLIENTE, garantiaExtendida.getNombreCliente());
		
	}
	
	
}
