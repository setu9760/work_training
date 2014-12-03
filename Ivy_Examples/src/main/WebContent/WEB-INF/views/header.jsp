<%@ include file="include.jsp"%>
<%!int pageCount = 0;

	void addCount() {
		pageCount++;
	}%>
<%
	addCount();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<p>The time on the server is ${serverTime}.</p>
	<p>
		You are visitor number:
		<%=pageCount%></p>

</body>
</html>