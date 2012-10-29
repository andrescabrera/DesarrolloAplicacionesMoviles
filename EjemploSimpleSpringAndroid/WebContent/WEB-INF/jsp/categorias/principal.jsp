<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Categoría REST</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<c:url value="/js/jquery-1.6.2.min.js" />"></script> 
<script type="text/javascript">
	$(function() {
		listarCategoria();
	});
	
	
	function guardarCategoria() {
		$.post('guardar.html', $('#form_alta').serialize(), function(data) {
			alert(data);
		});
	}

	function buscarCategoria() {
		var id = $('#input_id_categoria').val();
		$('#td_resultado').empty();
		$.getJSON('ver/' + id + '.html', function(data) {
			$('#td_resultado').html(data.nombre);
		});
	}
	
	
	function listarCategoria() {
		$.getJSON('listar.html', function(data) {
			for (i=0;i<data.length;i++){ 
				$('#table_listado').append("<tr><td>" + data[i].id + "</td><td>" + data[i].nombre + "</td>");
			} 
		});
	}
	
</script>
</head>
<body>
	<h1>Categorias</h1>
	<br />
	Listado<br>
	<table id="table_listado">
		<tr>
			<td>Id</td><td>Nombre</td>
		</tr>
	</table>
	<br>
	Nueva<br>
	<form id="form_alta">
		Nombre:<input name="nombre" />
		<input type="button" value="Guardar" onclick="guardarCategoria()" />
	</form>
	<br>	
	Buscador<br>
	<table>
		<tr>
			<td>Buscador de Categoría</td>
			<td><input type="text" id="input_id_categoria" /><input type="button" onclick="buscarCategoria()" id="input_buscar" value="Buscar" /></td>
			<td id="td_resultado"></td>
		</tr>
	</table>
</body>
</html>