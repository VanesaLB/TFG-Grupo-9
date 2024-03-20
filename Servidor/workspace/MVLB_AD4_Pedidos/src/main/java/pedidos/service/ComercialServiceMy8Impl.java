package pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pedidos.modelo.entities.Comercial;
import pedidos.repository.ComercialRepository;

@Service
public class ComercialServiceMy8Impl implements ComercialService{
	
	@Autowired
	ComercialRepository corepo;

	@Override
	public List<Comercial> buscarTodosLosComerciales() {
		return corepo.findAll();
	}

	@Override
	public Comercial buscarUno(int idComercial) {
		return corepo.findById(idComercial).orElse(null);
	}

	@Override
	public Comercial insertarComercial(Comercial comercial) {
		return corepo.save(comercial);
	}

	@Override
	public boolean eliminar(int idComercial) {
		try {
			if (buscarUno(idComercial) != null) {
				corepo.deleteById(idComercial);
				return true;
			}else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Comercial modificarComercial(Comercial comercial) {
		try {
			return corepo.save(comercial);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Comercial> buscarComercialesPorPedidosDeCliente(int idCliente) {
		return corepo.comercialAtiendePedidosPorCliente(idCliente);
	}

	@Override
	public List<Comercial> buscarComercialesConPedidos() {
		return corepo.comercialConPedidos();
	}

}
