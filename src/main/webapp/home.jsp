<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>

<%
    String username = (String) request.getSession().getAttribute("username");
    if (username == null) {
        request.setAttribute("message", "Login first!!!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
%>



<h2>Welcome dear <%=request.getSession().getAttribute("username")%>
</h2>
<form action="/home" method="post">
    title <input type="text" name="title"></br>
    description <input type="text" name="description "></br>
    <input type="submit" value="home">
</form>

</body>
</html>