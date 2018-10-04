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
				modelo.put("m�todo", " Pasar a may�scula");
				break;
			case "pasarMinuscula":
				modelo.put("resultado", cadena.toLowerCase());
				modelo.put("m�todo", " Pasar a minuscula");
				break;
			case "invertirOrden":
				modelo.put("resultado", invertirCadena(cadena));
				modelo.put("m�todo", " Invertir orden");
				break;
			case "cantCaracteres":
				modelo.put("resultado", cadena.length());
				modelo.put("m�todo", " Cantidad de caracteres");
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
