<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        body {
            background-image: url(http://wakpaper.com/large/Theater_wallpapers_88.jpg);
            background-position: center top;
            background-size: 100% auto;
            background-attachment: fixed;
        }
        h1, h2, h3 {
            color: white;
            text-align : center;
            vertical-align: 50%;
        }
    </style>
</head>
<body>
	<h1> Welcome to MAX Cinema</h1>
        <h2> Available commands : </h2>
        <h3> 1) For booking tickets enter <span style="color:greenyellow">/user/{id}/bookTicket </span>, where {id} - userId.</h3>
        <h3> 2) For looking up user tickets <span style="color:greenyellow">/user/{id}/myTickets </span>, where {id} - userId.</h3>
        <h3> Available users : </h3>
    <c:forEach items="${userList}" var="user">
        <h3>${user.id}) ${user.name}  </h3>
    </c:forEach>


</body>
</html>