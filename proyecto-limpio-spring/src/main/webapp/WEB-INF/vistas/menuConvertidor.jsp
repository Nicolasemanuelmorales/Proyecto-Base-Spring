<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Convertir Cadena</title>
</head>
<body>
	
	<form action="mostrarResultado" method="GET">
		<label>Ingrese la frase:</label><br>
		<input type = "text" name="cadena" id="cadena"></input>
		<br>
		<br>
		<label>Seleccione un método:</label><br>
		<input type="radio" id="1" name="nombreOperacion" value="pasarMayuscula" checked> Pasar a Mayuscula<br>
  		<input type="radio" id="2" name="nombreOperacion" value="pasarMinuscula"> Pasar a Minuscula<br>
  		<input type="radio" id="3" name="nombreOperacion" value="invertirOrden"> Invertir Orden <br>
		<input type="radio" id="4" name="nombreOperacion" value="cantCaracteres"> Cantidad de Caracteres<br><br>
		
		<input type="button" onclick="convertirCadena();" value="Convertir">
	</form>
	
	<script>
	
		function convertirCadena(){
			var cadena = document.getElementById("cadena").value;
			var operacion = document.querySelector('input[name="nombreOperacion"]:checked').value;

			location.href='./mostrarResultado/' + operacion + '/' + cadena;
		}
	
	</script>
</body>
</html>