package Negocio;

import java.util.ArrayList;

import dao.CargarDescolgablesDao;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Prestamo;
import entidades.Provincia;
import entidades.Sexo;


public class NegCargarDescolgables {
	private CargarDescolgablesDao desc;
	
	
	// Constructor para inicializar desc
		public NegCargarDescolgables() {
			this.desc = new CargarDescolgablesDao(); // Inicializa desc aquí
		}
		
		public ArrayList<Sexo> obtenerLosSexos() {
			return desc.obtenerSexos();
		}
		
		
		
		public ArrayList<Nacionalidad> ObtenerLasNacionaliadades(){
			return desc.obtenerNacionalidades();
		}
		
		
		public ArrayList<Provincia> ObtenerLasProvincias(){
			return desc.obtenerProvincias();
		}
		
		
		public ArrayList<Localidad> ObtenerLasLocalidadesPorProvincia(int provinciaId) {
		    return desc.obtenerLocalidadesPorProvincia(provinciaId);
		}


		public ArrayList<Cuenta> ObtenerLasCuentasBancarias(int id) {

			return desc.ObtenerLasCuentasBancarias(id);
		}
		
		public ArrayList<Cliente> obtenerIDUsuarioVacio(){
			return desc.obtenerIDUsuarioVacio();
		}
		public ArrayList<Prestamo> ObtenerLosPrestamos(int id) {

			return desc.ObtenerLosPrestamos(id);
		}
}

