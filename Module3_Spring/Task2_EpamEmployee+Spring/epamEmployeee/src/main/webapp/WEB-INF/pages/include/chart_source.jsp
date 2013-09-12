<script class="jasperreports" type="text/javascript">
    (function () {
        var chart=${json};
        chart.chart.renderTo = "chart";
        chart.chart.width = 1800;
        chart.chart.height = 780;
        new Highcharts.Chart(chart);
    }());
</script>