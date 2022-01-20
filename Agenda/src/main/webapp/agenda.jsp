<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
/* for (int i = 0; i < lista.size(); i++) {
	out.println(lista.get(i).getIdcon());
	out.println(lista.get(i).getNome());
	out.println(lista.get(i).getFone());
	out.println(lista.get(i).getEmail());
} */
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/telefone_icon.png">
<link rel="stylesheet" href="style.css">

</head>

<body>
	<h1>Agenda de Contatos</h1>
	<div class="menu">
		<a href="novo.html" class="Botao1">Novo contato</a> <a href="report"><img
			id="imgIconPdf" src="imagens/file-pdf-regular.svg"></a>
	</div>
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>

			<tr>
				<td id="tabelaLinha"><%=lista.get(i).getIdcon()%></td>
				<td id="tabelaLinha"><%=lista.get(i).getNome()%></td>
				<td id="tabelaLinha"><%=lista.get(i).getFone()%></td>
				<td id="tabelaLinha"><%=lista.get(i).getEmail()%></td>
				<td id="iconOption"><a
					href="select?idcon=<%=lista.get(i).getIdcon()%>"><img
						id="imgIconEdit" src="imagens/edit-regular.svg"></a> <a
					href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)"><img
						id="imgIconDelete" src="imagens/delete-alt-solid.svg"></a>
			</tr>

			<%
			}
			%>

		</tbody>
	</table>

	<script src="scripts/confirmador.js"></script>

</body>

</html>