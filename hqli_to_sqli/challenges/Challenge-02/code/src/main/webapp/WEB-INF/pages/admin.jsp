<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<HTML>
<BODY>
    <style>
        ${style}
    </style>
    <h1>Exodus Admin Panel</h1>
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
    <% if (request.getAttribute("country_error") != null){ %>
        <br/><br/>
        <div class="error">
            <small><% out.println(request.getAttribute("country_error")); %></small>
        </div>
    <% } %>

    <h2>Admin's secret : <% out.println(request.getAttribute("admin_secret")); %></h2>
    <hr>
    <h2>Targets information</h2>
    <table>
        <thead>
            <th class='first'>Name</th>
            <th class='second'>Address</th>
            <th class='second'>City</th>
            <th class='third'>Country</th>
        </thead>
        <c:choose>
        <c:when test="${rebelLocations != null}">
            <c:forEach items="${rebelLocations}" var="loc">
                <tr>
                <td class='first'>${loc.name}</td>
                <td class='second'>${loc.address}</td>
                <td class='second'>${loc.city}</td>
                <td class='third'>${loc.country}</td>
                </tr>
            </c:forEach>
         </c:when>
         </c:choose>
    	</table>

    <br/><br/>
    <div style="text-align: left;">
        <form:form name="theme" action="${pageContext.request.contextPath}/admin" method="post">
            <select id="theme" name="theme">
                <option value="/resources/css/green.css">Green</option>
                <option value="/resources/css/red.css">Red</option>
                <option value="/resources/css/yellow.css">Yellow</option>
                <option value="/resources/css/pink.css">Pink</option>
            </select>
            <input type='submit' value='Change Theme'/>
        </form:form>
    </div>
</BODY>
</HTML>