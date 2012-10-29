<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ver</title>
</head>
<body>
	<h1>Ver usuario</h1>
	<br />
	<a href="list.html">Listar</a>
	<table>
		<tr>
			<td>Id</td>
			<td>${user.id}</td>
		</tr>
		<tr>
			<td>Nombre</td>
			<td>${user.name}</td>
		</tr>
		<tr>
			<td>Apellido</td>
			<td>${user.surname}</td>
		</tr>
		<tr>
			<td>Fecha de Nacimiento</td>
			<td>${user.dateOfBirth}</td>
		</tr>
	</table>
</body>
</html>