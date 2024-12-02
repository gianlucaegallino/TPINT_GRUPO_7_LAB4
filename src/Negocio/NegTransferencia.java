package Negocio;

import dao.CuentaDao;

public class NegTransferencia {
	private CuentaDao dao = new CuentaDao();
	
	public Boolean realizarTransferencia(float monto, String cbuInicio, String cbuDestinatario) {
		
		try {
			dao.RemoverMonto(cbuInicio, monto);
			dao.AgregarMonto(cbuDestinatario, monto);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
