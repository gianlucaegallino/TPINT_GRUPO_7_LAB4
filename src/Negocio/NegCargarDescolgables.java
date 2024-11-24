package Negocio;

import java.util.ArrayList;

import dao.CargarDescolgablesDao;
import entidades.Localidad;
import entidades.Nacionalidad;
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
		
		public ArrayList<Localidad> ObtenerLasLocalidades(){
			return desc.obtenerLocalidades();
		}
		
		public ArrayList<Localidad> ObtenerLasLocalidadesPorProvincia(int provinciaId) {
		    ArrayList<Localidad> localidades = desc.obtenerLocalidades();  // Obtener todas las localidades
		    ArrayList<Localidad> localidadesFiltradas = new ArrayList<>();
		    
		    for (Localidad localidad : localidades) {
		        if (localidad.getProvincia().getId() == provinciaId) {
		            localidadesFiltradas.add(localidad);
		        }
		    }
		    
		    return localidadesFiltradas;
		}
}

