<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ver</title>
</head>
<body>
	<h1>Ver producto</h1>
	<br />
	<a href="listar.html">Listar</a>
	<table>
		<tr>
			<td>Nombre</td>
			<td>${producto.id}</td>
		</tr>
		<tr>
			<td>Precio</td>
			<td>${producto.nombre}</td>
		</tr>
		<tr>
			<td>Precio</td>
			<td>${producto.precio}</td>
		</tr>
		<tr>
			<td>Categoría</td>
			<td>${producto.categoriaProducto.nombre}</td>
		</tr>
	</table>
</body>
</html>