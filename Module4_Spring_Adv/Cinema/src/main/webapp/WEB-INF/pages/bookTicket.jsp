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

<script src="http://code.jquery.com/jquery-latest.js"></script>


<script type="text/javascript">
    $(function() {
        $('#book_ticket').on('submit', function (e) {
            var data = $('form').serializeArray(),
            obj = {};
            for(var i = 0; i < data.length; i++){
                obj[data[i].name] = obj[data[i].name] || [];
                obj[data[i].name].push(parseInt(data[i].value));
            }
            var result = JSON.stringify(obj);
            $.ajax({
                    type: 'post',
                    url: '/jsonBookTicket?userId='+${user.id},
                    dataType: 'json',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    data: result,
                    success: function (obj) {
                        alert(obj.message);
                        var arr = obj.bookedIds;
                        var length = arr.length,
                        element = null;
                        for (var i = 0; i < length; i++) {
                            element = arr[i];
                            $('.ticketId' + element  +'tr').children('td, th').css('background-color','red');
                            $('#ticketId' + element).attr("disabled", "disabled");
                        }
                    } ,
                    error: function(obj){
                        alert(obj.message);
                    }
                });
                e.preventDefault();
            });
    });
</script>

<c:choose>
    <c:when test="${user.name != null}">
        <h2 style="color: white;">Hi, ${user.name} ! Please, book tickets, if you want or  <a href="/user/${user.id}/myTickets"><span style="color:greenyellow;">look yours.</span></a></h2>
        <form id="book_ticket">
            <table>
                <tr>
                    <th>Date</th>
                    <th>Film name</th>
                    <th>Category</th>
                    <th>Place</th>
                    <th>Book it</th>
                </tr>
            <c:forEach items="${ticketList}" var="item">
                <tr class="ticketId${item.id}tr">
                    <td class="ticketId${item.id}td">${item.date.date} / ${item.date.month + 1} </td>
                    <td class="ticketId${item.id}td">${item.title}</td>
                    <td class="ticketId${item.id}td">${item.ticketCategory}</td>
                    <td class="ticketId${item.id}td">${item.place}</td>
                    <td class="ticketId${item.id}td"><input type="checkbox" name="ticketId" value="${item.id}" id="ticketId${item.id}"></td>
                </tr>
            </c:forEach>
            </table>
            <input type="submit">
        </form>


    </c:when>
    <c:otherwise>
        <h1 style="color: white;">Ooops, something going wrong</h1><br/>
        <h2 style="color: white;">${error}</h2>
    </c:otherwise>
</c:choose>

</body>
</html>