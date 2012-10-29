<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Formulario</title>

</head>

<body>
	<h1>Formulario usuario</h1>
	<br />
	<form:form commandName="userForm" method="POST"
		action="createOrUpdate.html">
		<form:hidden path="id" />
		<table>
			<tr>
				<td>Nombre</td>
				<td><form:input path="name" />
					<form:errors path="name" />
				</td>
			</tr>
			<tr>
				<td>Apellido</td>
				<td><form:input path="surname" />
					<form:errors path="surname" />
				</td>
			</tr>
			<!-- 		<tr> -->
			<!-- 			<td>Fecha alta</td> -->
			<%-- 			<td><form:input path="fechaAlta"/><form:errors path="fechaAlta" /></td> --%>
			<!-- 		</tr> -->

			<!-- 		<tr> -->
			<!-- 			<td>Categoría</td> -->
			<%-- 			<td><form:select path="idCategoriaProducto" items="${categorias}" itemLabel="nombre" itemValue="id" ></form:select></td> --%>
			<!-- 		</tr> -->
			<!-- 		<tr> -->
			<!-- 			<td>Categoría2</td> -->
			<%-- 			<td><form:checkboxes path="categoriasIds" items="${categorias}" itemLabel="nombre" itemValue="id" ></form:checkboxes></td> --%>
			<!-- 		</tr> -->
			<tr>
				<td><input type="submit" value="Guardar" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>