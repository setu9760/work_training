<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Student

	<p>The time on the server is ${serverTime}.</p>
	<p>The student details are ${student.id}</p>

	<form:form modelAttribute="student">
		<label for="id"> ID: </label>
		<form:input path="id" id="id" />
		<form:errors path="id" />
		<br />
		<label for="name">Name</label>
		<form:input path="name" id="name" />
		<form:errors path="name" />
		<br />
		<label for="age">Age</label>
		<form:input path="age" id="age" />
		<form:errors path="age" />

		<input type="submit" value="submit" />

	</form:form>
</body>
</html>