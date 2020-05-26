package ec.edu.ups.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.CategoriaDAO;
import ec.edu.ups.model.Categoria;

@Stateless
public class CategoriaON {

	@Inject
	private CategoriaDAO cDAO;

	public void crearCategoria(Categoria categoria) {
		cDAO.create(categoria);
	}

	public void actualizarCategoria(Categoria categoria) {
		cDAO.update(categoria);
	}

	public Categoria read(int codigo) {
		return cDAO.read(codigo);

	}

	public void eliminarCategoria(int codigo) {
		cDAO.delete(codigo);
	}

	public Categoria readNombre(String nombre) {
		return cDAO.readNombre(nombre);
	}

	public List<Categoria> listaCategorias() {
		return cDAO.listarCategorias();
	}

}
