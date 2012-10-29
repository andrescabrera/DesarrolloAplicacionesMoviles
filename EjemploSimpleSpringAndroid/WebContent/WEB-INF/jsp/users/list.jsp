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
	<a href="create.html">Nuevo</a>
	<table>
		<tr>
			<td>ID</td>
			<td>Nombre</td>
			<td>Apellido</td>
<!-- 			<td>Cat</td> -->
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${users}" var="u">
			<tr>
				<td>${u.id}</td>
				<td>${u.name}</td>
				<td>${u.surname}</td>
<%-- 				<td>${u.categoriaProducto.nombre}</td> --%>
				<td><a href="edit.html?id=${u.id}">Editar</a>
				</td>
				<td><a href="delete.html?id=${u.id}">Borrar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>