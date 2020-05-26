package ec.edu.ups.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.DetalleFacturaDAO;
import ec.edu.ups.dao.FacturaDAO;
import ec.edu.ups.model.Carrito;
import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.DetalleFactura;
import ec.edu.ups.model.Factura;
import ec.edu.ups.model.Producto;
import ec.edu.ups.model.Tarjeta;
import ec.edu.ups.model.objetos.CarritoTmp;
import ec.edu.ups.model.objetos.ComprasTmp;
import ec.edu.ups.model.objetos.FacturaTmp;
import ec.edu.ups.model.objetos.VendidosTmp;

@Stateless
public class FacturaON {

	@Inject
	private FacturaDAO facturaDAO;
	@Inject
	private DetalleFacturaDAO detalleFacturaDAO;
	@Inject
	private TarjetaON tarjetaON;
	@Inject
	private CarritoON carritoON;

	public void facturar(int codigo) {
		Tarjeta tarjeta = tarjetaON.read(codigo);
		Cliente cliente = tarjeta.getCliente();
		Factura factura = new Factura();
		factura.setCliente(cliente);
		factura.setTarjeta(tarjeta);
		factura.setFecha(new Date());
		List<CarritoTmp> carritos = carritoON.listarCarritosCliente(cliente.getCodigo(), false);
		List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
		for (CarritoTmp ct : carritos) {
			DetalleFactura detalleFactura = new DetalleFactura();
			Producto producto = ct.getProducto();
			int cantidad = ct.getCantidad();
			double precioUnitario = producto.getPrecio();
			double precioTotal = precioUnitario * cantidad;
			detalleFactura.setCantidad(cantidad);
			detalleFactura.setDescripcion("Compra en linea");
			detalleFactura.setPrecioUnitario(precioUnitario);
			detalleFactura.setPrecioTotal(precioTotal);
			detalleFactura.setProducto(producto);
			detalleFactura.setFactura(factura);
			detalles.add(detalleFactura);
			actualizarCarrito(ct);
		}
		double subtotal = 0.0;
		double iva = 0.0;
		double total = 0.0;
		for (DetalleFactura df : detalles)
			subtotal += df.getPrecioTotal();

		iva = subtotal * 0.12;
		total = subtotal + iva;
		factura.setDetalles(detalles);
		factura.setSubtotal(subtotal);
		factura.setIva(iva);
		factura.setTotal(total);

		generarFactura(factura);
	}

	public void generarFactura(Factura factura) {
		crearFactura(factura);
	}

	public void actualizarCarrito(CarritoTmp carritoTmp) {
		Carrito carrito = new Carrito();
		carrito.setCodigo(carritoTmp.getCodigo());
		carrito.setCliente(new Cliente(carritoTmp.getCliente().getCodigo()));
		carrito.setProducto(carritoTmp.getProducto());
		carrito.setCantidad(carritoTmp.getCantidad());
		carrito.setPagado(true);
		carritoON.update(carrito);
	}

	public void crearFactura(Factura factura) {
		facturaDAO.create(factura);
	}

	public void crearDetalleFactura(DetalleFactura detalleFactura) {
		detalleFacturaDAO.create(detalleFactura);
	}

	public Factura leerFactura(int codigo) {
		return facturaDAO.read(codigo);
	}

	public DetalleFactura leerDetalleFactura(int codigo) {
		return detalleFacturaDAO.read(codigo);
	}

	public void actualizarFactura(Factura fact) {
		facturaDAO.update(fact);
	}

	public void actualizarDetalleFactura(DetalleFactura detalleFactura) {
		detalleFacturaDAO.update(detalleFactura);
	}

	public void eliminarFactura(int codigo) {
		facturaDAO.delete(codigo);
	}

	public void eliminarDetalleFactura(int codigo) {
		detalleFacturaDAO.delete(codigo);
	}

	public List<Factura> listarFactura() {
		return facturaDAO.listar();
	}

	public List<DetalleFactura> listarDetalleFactura() {
		return detalleFacturaDAO.listar();
	}

	public List<FacturaTmp> listarPorCedula(String cedula) {
		return facturaDAO.listarPorCedula(cedula);
	}

	public Factura leerPorCodigo(int codigo) {
		return facturaDAO.leerPorCodigo(codigo);
	}

	public List<VendidosTmp> listarMasVendidos() {
		return detalleFacturaDAO.listarMasVendidos();
	}

	public List<ComprasTmp> listarClientesQueCompraronMas() {
		return facturaDAO.listarClientesQueCompraronMas();
	}

}
