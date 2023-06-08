<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<HTML>
<BODY>
<link rel="stylesheet" href="/resources/css/style.css" type="text/css" />
	<h1>Welcome</h1>
    <div align='center'>
        <form:form name="login" action="${pageContext.request.contextPath}/login" method="post">
            <input type='text' name='username' placeholder='Username' autofocus />
			<input type='password' name='password' placeholder='Password' />
			<input type='submit' value='Login'/>
        </form:form>
    </div>
    <% if (request.getAttribute("login_error") != null){ %>
        <div class="error">
            <small><% out.println(request.getAttribute("login_error")); %></small>
        </div>
        <% } %>
    <br/><hr><br/>
    <% if (request.getAttribute("query") != null){ %>
           <div><b>The query that ran is : </b><% out.println(request.getAttribute("query")); %></div>
            <% } %>


    <script>window.history.replaceState("", "", "/");</script>
</BODY>
</HTML>