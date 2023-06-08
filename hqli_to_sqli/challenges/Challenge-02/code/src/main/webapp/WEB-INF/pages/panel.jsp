<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page trimDirectiveWhitespaces="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<HTML>
<BODY>
    <style>${style}</style>
    <h1>Exodus Panel</h1>
    <% if (request.getAttribute("page") == null){ %>
        <div align='center'>
            <a href="/panel/news">Cyber Restriction News</a>
        </div>
        <br/><hr><br/>
        <div align='center'>
            <a href="/panel/kills">Resistance Leaders Killed</a>
        </div>
        <br/><hr><br/>
        <div align='center'>
            <a href="/panel/wanted">Wanted Traitors</a>
        </div>
    <% } else { %>
        <c:import url="${page}" />
    <% } %>
    <br/><br/>
    <div style="text-align: left;">
        <form:form name="theme" action="${pageContext.request.contextPath}/panel" method="post">
            <select id="theme" name="theme">
                <option value="/resources/css/green.css">Green</option>
                <option value="/resources/css/red.css">Red</option>
                <option value="/resources/css/yellow.css">Yellow</option>
                <option value="/resources/css/pink.css">Pink</option>
            </select>
            <input type='submit' value='Change Theme'/>
        </form:form>
    </div>
    <div style="text-align: left; position: fixed; bottom: 0; width: 100%;">
        <% if (request.getAttribute("update_error") != null){ %>
            <div class="error">
                <small><% out.println(request.getAttribute("update_error")); %></small>
            </div>
        <% } %>
        <form:form name="updateUser" action="${pageContext.request.contextPath}/panel" method="post">
            <input type='text' name='name' placeholder='Name' />
            <input type='email' name='email' placeholder='email@example.com' />
            <input type='submit' value='Update your informations'/>
        </form:form>
        <br/>
        <hr>
        <br/>
        <% if (request.getAttribute("admin_login_error") != null){ %>
            <div class="error">
                <small><% out.println(request.getAttribute("admin_login_error")); %></small>
            </div>
        <% } %>
        <a href="/admin" >Administrate the site if you're an admin</a>
    </div>
</BODY>
</HTML>