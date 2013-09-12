jive.highcharts = jive.highcharts || {
	chartTypeSelector: null,
	actionBaseData: null,
	actionBaseUrl: null,
	chartsData: {},
	selectedData: null,
	initChartTypeSelector: function(settings) {
		var it = jive.highcharts;
		var highchartsInitEvent = jasperreports.events.registerEvent('jive.highcharts.initChartTypeSelector');

		jQuery.extend(it, settings);
		jive.actionBaseData = settings.actionBaseData;
		jive.actionBaseUrl = settings.actionBaseUrl;
		
        jQuery('#jive_highcharts_components').length == 0 &&  jQuery('body').append('<div id="jive_highcharts_components"></div>');
        jQuery('#jive_highcharts_components').empty();
        it.chartTypeSelector = jQuery('.jive_chartTypeSelector').eq(0);
        it.chartTypeSelector.appendTo('#jive_highcharts_components');
        it.chartTypeSelector.draggable();
        
        it.chartTypeSelector.on('click touchend', '.closeIcon', function() {
        	jive.highcharts.chartTypeSelector.data('chartuuid', null);
        	jive.highcharts.chartTypeSelector.hide();
        });
        
    	jasperreports.events.subscribeToEvents({names: 'jasperreports.reportviewertoolbar.undo, jasperreports.reportviewertoolbar.redo, jasperreports.reportviewertoolbar.undo.all', 
    		callback: function() {
    			var hc = jive.highcharts;
    			
    			hc.selectedData = hc.chartsData[hc.chartTypeSelector.data('chartuuid')];
    			if (hc.selectedData) {
    				hc.renderChartTypeSelector(hc.selectedData.type);
    			}
    		},
    		keep: true
    	});
        
        it.chartTypeSelector.on('click touchstart', 'div.cell', function() {
        	var it = jive.highcharts, chartType;
        	if (jQuery(this).hasClass('disabled')) {
        		return;
        	}
        	
        	chartType = this.getAttribute('data-hcname');
        	
        	if (it.selectedData == null) {
        		it.selectedData = it.chartsData[it.chartTypeSelector.data('chartuuid')];
        	}
        	
        	if (it.selectedData && chartType !== it.selectedData.type) {
	        	it.renderChartTypeSelector(chartType);
	        	var actionData = {
	        			actionName: 'changeChartType',
	        			changeChartTypeData: {
	        				chartComponentUuid: it.selectedData.uuid,
	        				chartType: chartType
	        			}
	        	};
	        	it.selectedData = null;
	        	jive.runAction({
	        		actionData: actionData,
	        		startPoint: it.chartTypeSelector.parent().prev('.mainReportDiv'),
	        		defaultAction: true
        		});
        	} 
        });

        highchartsInitEvent.trigger();
	},
	resetChartData: function() {
		jive.highcharts.chartsData = {};
		jQuery('.jive_chartSettingsIcon').on('mouseenter',function() {
			var self = jQuery(this);
			jive.highcharts.selectedData = jive.highcharts.chartsData[jQuery(this).data('chartuuid')];
			
            self.addClass('over');
            self.next('.jive_chartMenu').show().position({
                of: self,
                at: 'left bottom',
                my: 'left top',
                offset: '0 -1'
            })
        });
        jQuery('.jive_chartMenu').on('mouseleave touchend',function() {
        	var self = jQuery(this);
            self.prev('.jive_chartSettingsIcon').removeClass('over');
            self.hide();
        });
        jQuery('.jive_chartMenu').on('mouseenter touchstart','p.wrap',function(){
            jQuery(this).addClass('over');
        });
        jQuery('.jive_chartMenu').on('mouseleave touchend','p.wrap',function(){
            jQuery(this).removeClass('over');
        });
        jQuery('.jive_chartMenu').on('click touchend','li',function(){
            if(this.innerHTML.indexOf('Types') > 0) {
            	jive.highcharts.chartTypeSelector.data('chartuuid', jive.highcharts.selectedData.uuid);
            	jive.highcharts.chartTypeSelector.show().position({
					of: 'body',
					at: 'center center',
					my: 'center center'
				});
				jive.highcharts.renderChartTypeSelector(jive.highcharts.selectedData.type);
            }
            return false; //cancel event bubbling
        });
	},
	initChart: function(chartType, chartUuid, modelVar) {
		jive.highcharts.chartsData[chartUuid] = {
				type: chartType,
				uuid: chartUuid,
				model: modelVar,
				highchart: new Highcharts.Chart(modelVar)
		};
	},
	renderChartTypeSelector: function(chartType) {
		var it = jive.highcharts;
		it.chartTypeSelector.find('div.cell').removeClass('selected');
		it.chartTypeSelector.find('div.cell[data-hcname="' + chartType + '"]').addClass('selected');
	}
};
