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
        table.center {
            margin-left:auto;
            margin-right:auto;
        }
        table, td, th
        {
            border:1px solid green;
        }
        th
        {
            background-color:green;
            color:white;
            height:50px;
        }
        table
        {
            width:50%;
            align: center;
        }
        td
        {
            text-align:center;
            background-color:#009966;
            color:white;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${user.name != null}">
        <h2 style="color: white;">Hi, ${user.name} ! This is your tickets. </h2>
            <table>
                <tr>
                    <th>Date</th>
                    <th>Film name</th>
                    <th>Category</th>
                    <th>Place</th>
                </tr>
                <c:forEach items="${ticketList}" var="item">
                    <tr>
                        <td>${item.date.date} / ${item.date.month + 1} </td>
                        <td>${item.title}</td>
                        <td>${item.ticketCategory}</td>
                        <td>${item.place}</td>
                    </tr>
                </c:forEach>
            </table>
        <h2 style="color: white;">Back to <a href="/user/${user.id}/bookTicket"><span style="color:greenyellow;">booking</span></a></h2>
    </c:when>
    <c:otherwise>
        <h1 style="color: white;">Ooops, something going wrong</h1><br/>
        <h2 style="color: white;">${error}</h2>
    </c:otherwise>
</c:choose>

</body>
</html>