<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Listado</title>

</head>

<body>

	<h1>Listado</h1>
	<a href="nuevo.html">Nuevo</a>
	<table>
		<tr>
			<td>ID</td>
			<td>Nombre</td>
			<td>Precio</td>
			<td>Cat</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${productos}" var="p">
			<tr>
				<td>${p.id}</td>
				<td>${p.nombre}</td>
				<td>${p.precio}</td>
				<td>${p.categoriaProducto.nombre}</td>
				<td><a href="editar.html?id=${p.id}">Editar</a>
				</td>
				<td><a href="borrar.html?id=${p.id}">Borrar</a>
				</td>
			</tr>
		</c:forEach>

	</table>



</body>
</html>