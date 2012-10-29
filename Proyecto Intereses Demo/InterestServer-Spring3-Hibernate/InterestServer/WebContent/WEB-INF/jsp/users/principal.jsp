<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Usuarios REST</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.6.2.min.js" />"></script>
<script type="text/javascript">
	$(function() {
		listUser();
	});

	function saveUser() {
		$.post('createOrUpdate.html', $('#form_alta').serialize(), function(data) {
			alert(data);
		});
	}

	function readUser() {
		var id = $('#input_id_usuario').val();
		$('#td_resultado').empty();
		$.getJSON('view/' + id + '.html', function(data) {
			$('#td_resultadob1').html(data.name);
		});
	}
	
	function searchByName() {
		var nombre = $('#input_nombre_usuario').val() + $('#input_apellido_usuario').val();
		$('#td_resultado').empty();
		$.getJSON('searchByName/' + nombre, function(data) {
			for (i = 0; i < data.length; i++) {
				$('#td_resultadob2').append(
						"<tr><td>" + data[i].id + "</td><td>" + data[i].name
								+ "</td>");
			}
		});
	}

	function listUser() {
		$.getJSON('list.html', function(data) {
			for (i = 0; i < data.length; i++) {
				$('#table_listado').append(
						"<tr><td>" + data[i].id + "</td><td>" + data[i].nombre
								+ "</td>");
			}
		});
	}
</script>
</head>
<body>
	<h1>Usuarios</h1>
	<br /> Listado
	<br>
		<table id="table_listado">
			<tr>
				<td>Id</td>
				<td>Nombre</td>
			</tr>
		</table> <br> Nueva<br>
				<form id="form_alta">
					Nombre:<input name="nombre" /> <input type="button"
						value="Guardar" onclick="saveUser()" />
				</form> <br> Buscador<br>
						<table>
							<tr>
								<td>Buscador de Usuarios por Id</td>
								<td><input type="text" id="input_id_usuario" /><input
									type="button" onclick="readUser()" id="input_buscar"
									value="Buscar" />
								</td>
								<td id="td_resultadob1"></td>
							</tr>
							<tr>
								<td>Buscador de Usuarios por Nombre y Apellido</td>
								<td><input type="text" id="input_nombre_usuario" /><input
									type="text" id="input_apellido_usuario" /> <input type="button"
									onclick="searchByName()" id="input_buscar" value="Buscar" />
								</td>
								<td id="td_resultadob2"></td>
							</tr>
						</table>
</body>
</html>