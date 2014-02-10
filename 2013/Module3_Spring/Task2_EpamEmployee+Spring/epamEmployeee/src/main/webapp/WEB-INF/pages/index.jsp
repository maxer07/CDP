<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
<head>
    <title>CDP Module3 Task2</title>
    <link rel="stylesheet" href="/resources/style/style.css">
    <script type="text/javascript" src="/resources/jquery.js"></script>
    <script type="text/javascript" src="/resources/charts_lib/highcharts-2.3.2.original.src.js"></script>
    <script type="text/javascript" src="/resources/charts_lib/default.service.js"></script>
</head>
</head>

<body>
<a class="button" onclick="$('#one').slideToggle('slow');" href="javascript://">Choose city</a>

<div id="one" style="display: none;">
    <table style="width: 90%; border: 1px solid cadetblue; margin-left: auto; margin-right: auto;" onmouseover="this.style.cursor='pointer';">
        <c:forEach var="i" begin="0" end="${fn:length(cities)-1}" >
            <c:if test="${(i mod 10) eq 0}">
                <tr style="width: 100px;">
            </c:if>
            <c:if test="${ ((i mod 10 )+ 1) eq 0}">
                </tr>
            </c:if>
            <td style="padding-left: 5px; padding-bottom: 3px; font-size:110%;"><a href="getChart?city=<c:out value="${cities[i]}"/>"><c:out value="${cities[i]}"/></a> </td>

        </c:forEach>

    </table>
</div>




<div class="highcharts_parent_container" id="chart" style="margin-left: auto; margin-right: auto;"></div>

<jsp:include page="include/chart_source.jsp" />

</body>
</html>