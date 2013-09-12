// enhance env.rhino to force highcharts correct flow for exporting svg
Document.prototype.createElementNS_backup = Document.prototype.createElementNS;

Document.prototype.createElementNS = function(namespaceURI, qualifiedName) {
    var node = this.createElementNS_backup(namespaceURI, qualifiedName);
	
    if (namespaceURI === 'http://www.w3.org/2000/svg' && qualifiedName.toLowerCase() === 'svg') {
    	node.createSVGRect = function(){};
    }

    return node;
}

// override Window.getComputedStyle to fix getPropertyValue error in Highcharts
window.getComputedStyle_orig = window.getComputedStyle;
window.getComputedStyle = function(element, pseudoElement) {
	return this.getComputedStyle_orig(element, pseudoElement)
		|| new CSS2Properties(element);
}

var svgIndex = 0;
var SVGElements = [];
var SVGTextElements = [];

function renderSVGFromJson (jsonGeneralOptions, jsonChartOptions) {
	var n = Highcharts.createElement('div', null, null, null, true),
		chart,
		svg,
		chartOptions = eval(jsonChartOptions);

	Highcharts.setOptions.call(Highcharts, eval(jsonGeneralOptions));
	new FormatWrapper().visitObject(chartOptions);
	chartOptions.chart.renderTo=n;
    /*
     *  Setup for export
     */
    var plotOptions = ['area','column','bar','line','series','pie','spline'];

    chartOptions.plotOptions = chartOptions.plotOptions || {};
    $.each(plotOptions, function(i,serie) {
        if(chartOptions.plotOptions[serie]) {
            chartOptions.plotOptions[serie].animation = false;
            chartOptions.plotOptions[serie].showCheckbox = false;
        } else {
            chartOptions.plotOptions[serie] = {
                animation: false,
                showCheckbox: false
            }
        }
    });

    chartOptions.exporting = {enabled: false};

    // chart.getSVG() does this as well, resolving image URLs on the server side is problematic
	if (chartOptions.chart) chartOptions.chart.plotBackgroundImage = null;

	chart = new Highcharts.Chart(chartOptions);
    /*
     * Using custom printSVG (added in exporting-2.3.2.src.js file
     * chart.getSVG() re-renders the chart. This way we avoid rendering the chart twice.
     */
	svg = chart.printSVG(n);
	//svg = chart.getSVG();
	chart.destroy();
	Highcharts.discardElement (n);
	return svg;
}