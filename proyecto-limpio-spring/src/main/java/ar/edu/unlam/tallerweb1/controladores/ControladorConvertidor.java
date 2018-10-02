package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorConvertidor {
	
	@RequestMapping(path = "/menuConvertidor", method = RequestMethod.GET)
	public ModelAndView irAConvertidor() {
		return new ModelAndView("menuConvertidor");
	}
	
	@RequestMapping("mostrarResultado")
	public ModelAndView irAMostrarResultado(@RequestParam String metodo, @RequestParam String cadena) {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("textoIngresado", cadena);
		
		switch(metodo) {
			case "pasarMayuscula":
				modelo.put("resultado", cadena.toUpperCase());
				break;
			case "pasarMinuscula":
				modelo.put("resultado", cadena.toLowerCase());
				break;
			case "invertirOrden":
				modelo.put("resultado", invertirCadena(cadena));
				break;
			case "cantCaracteres":
				modelo.put("resultado", cadena.length());
				break;
		}
		
		return new ModelAndView("mostrarResultado",modelo);
	}
	
	private String invertirCadena(String cadena) {
		String sCadena=cadena;
		String sCadenaInvertida="";
		for(int x=sCadena.length()-1; x>=0; x--) {
			sCadenaInvertida = sCadenaInvertida + sCadena.charAt(x);
		}
		return sCadenaInvertida;
	}

}
