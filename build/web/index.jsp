<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<c:if var="test" test="${applicationScope.root == null}">
	<jsp:forward page="home"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>Default JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=devce-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    </head>
    <body>
    <a href="user?action=register">Get to register page</a>
    ${USER == null ? '' : USER.username}
    <c:if test="${USER != null}">
     	<br><a href="user?action=logout">log out</a>
    </c:if>
    <br>
    <a href="user">Login page</a>
        <header class="container-fluid">
        </header>
        <main class="container-fluid">
        </main>
        <footer class="container-fluid">
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>