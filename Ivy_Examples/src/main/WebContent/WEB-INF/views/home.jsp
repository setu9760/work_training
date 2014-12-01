<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	Home

	<p>The time on the server is ${serverTime}.</p>
	<p>The subject details are ${subject.subject_id}</p>


	<%
		String message = request.getParameter("message");
		boolean isSet = message != null;
		if (isSet) {
	%>
	<p>Message:</p><%=message%>
	<%
		}
	%>

	<form:form commandName="subject" modelAttribute="subject" method="post">
		<label for="id"> ID: </label>
		<form:input path="subject_id" id="id" />
		<form:errors path="subject_id" cssClass="error" />
		<br />
		<label for="name"></label>
		<form:input path="subject_name" id="name" />
		<form:errors path="subject_name" cssClass="error" />

		<input type="submit" value="submit" />
	</form:form>

	<a href="<%=request.getContextPath()%>/student">Student</a>
</body>
</html>