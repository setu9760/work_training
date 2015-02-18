<%@page import="javax.jms.TextMessage"%>
<%@page import="javax.jms.MessageProducer"%>
<%@page import="javax.jms.Session"%>
<%@page import="javax.jms.Connection"%>
<%@page import="javax.jms.Destination"%>
<%@page import="javax.jms.ConnectionFactory"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		InitialContext initialContext = null;

		String JNDI_QUEUE = "java:comp/env/jms/ForeignQUEUE_TestMachine";
		String JNDI_CONNECTION_FACTORY = "java:comp/env/jms/ForeignConnectionFactory_TestMachine";
		String message = null;

		try {
			message = request.getParameter("MDBmsg");
			if (message != null) {
				out.println("<p><hr><br> initializing");
				out.println("<br> JNDI ctx=" + initialContext);
				ConnectionFactory connectionFactory = (ConnectionFactory) initialContext
						.lookup(JNDI_CONNECTION_FACTORY);
				out.println("<br> JMS ConnectionFactory=<pre>"
						+ connectionFactory);
				out.println("</pre>");
				Destination destination = (Destination) initialContext
						.lookup(JNDI_QUEUE);
				out.println("<br> JMS Destination=" + destination);
				Connection connection = connectionFactory
						.createConnection();
				out.println("<br> JMS Connection=" + connection);
				String connFact_s = "" + connection;
				connection.start();
				Session jmssession = connection.createSession(true, 0);
				out.println("<br> JMS Session=" + jmssession);
				MessageProducer messageproducer = jmssession
						.createProducer(destination);
				out.println("<br> JMS MessageProducer=" + messageproducer);
				TextMessage textmessage = jmssession.createTextMessage();
				textmessage.setText(message);
				textmessage.setJMSType("TEST_MESSAGE");
				//textmessage.setJMSExpiration(100L);
				textmessage.setJMSPriority(1);
				textmessage.setJMSCorrelationID("CORR_ID:"
						+ System.currentTimeMillis());
				out.println("<br> JMS TextMessage=<pre>" + textmessage);
				out.println("</pre>");
				messageproducer.send(textmessage);
				jmssession.close();
				connection.close();
				out.println("<p><hr><br> DONE.");
			} else {
	%>
	PLEASE ENTER A MESSAGE TO BE SENT:
	<br>
	<form action="manda.jsp" method="POST">
		MDB message: <input type="text" name="MDBmsg" size="64">
		<p>
			<input type="submit" value="Submit" />
	</form>
	<%
		}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<pre>");
			e.printStackTrace(response.getWriter());
			pageContext.getServletContext().log("Errore", e);
			out.print("</pre>");
		} finally {
			try {
				if (initialContext != null)
					initialContext.close();
			} catch (Exception e) {
			}
		}
	%>

</body>
</html>