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
    <div align='center'>
    	<form:form name="new_account" action="${pageContext.request.contextPath}/create_account" method="post">
            <input type='text' name='username' placeholder='Username' />
            <input type='text' name='name' placeholder='Name' />
            <input type='email' name='email' placeholder='email@example.com' />
            <input type='password' name='password' placeholder='Password' />
            <input type='password' name='confirm_password' placeholder='Confirm password' />
            <input type='submit' value='Create account'/>
        </form:form>
    </div>
    <% if (request.getAttribute("create_error") != null){ %>
            <div class="error">
                <small><% out.println(request.getAttribute("create_error")); %></small>
            </div>
            <% } %>
    <script>window.history.replaceState("", "", "/");</script>
</BODY>
</HTML>