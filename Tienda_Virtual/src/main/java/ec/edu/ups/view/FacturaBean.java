package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.business.FacturaON;
import ec.edu.ups.model.DetalleFactura;
import ec.edu.ups.model.Factura;
import ec.edu.ups.model.objetos.FacturaTmp;

@ManagedBean
@ViewScoped
public class FacturaBean {

	@Inject
	private FacturaON facturaON;
	@Inject
	private FacesContext facesContext;

	private int codigo;
	private String cedula;
	private Factura factura;
	private List<FacturaTmp> facturas;
	private List<DetalleFactura> detalles;

	@PostConstruct
	public void init() {
		factura = new Factura();
		facturas = new ArrayList<>();
		detalles = new ArrayList<>();
	}

	public void cargarDatosFactura() {
		if (codigo == 0)
			return;
		try {
			factura = facturaON.leerPorCodigo(codigo);
			detalles = factura.getDetalles();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public String listarFacturas() {
		if (!cedula.isEmpty())
			facturas = facturaON.listarPorCedula(cedula);
		return null;
	}

	public String detalle(FacturaTmp _factura) {
		return "factura?faces-redirect=true&codigo=" + _factura.getCodigo();
	}

	public String listar() {
		return "lista-factura";
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<FacturaTmp> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<FacturaTmp> facturas) {
		this.facturas = facturas;
	}

	public List<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}

}
