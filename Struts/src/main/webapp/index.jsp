<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Title</title>
</head>
<body>
	<h1>From Struts2</h1>
	<s:form action="useraction" namespace="/" method="post"
		enctype="multipart/form-data">
		Please enter your name
		<s:textfield label="Username" name="username" size="30" type="text" />
		<s:textfield label="Password1" name="pwd1" size="30" type="text" />
		<s:textfield label="Password2" name="pwd2" size="30" type="text" />
		<s:submit label="Submit" name="submit" />
	</s:form>
</body>
</html>