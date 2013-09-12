package com.epam.cdp.oleshchuk.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChartModel {

    private Chart chart;
    private Title title;
    private Subtitle subtitle;
	private xAxis xAxis;
    private yAxis yAxis;
	private List<Series> series;
    private Legend legend;
    private PlotOptions plotOptions;

    public PlotOptions getPlotOptions() {
        return plotOptions;
    }

    public void setPlotOptions(PlotOptions plotOptions) {
        this.plotOptions = plotOptions;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public yAxis getyAxis() {
        return yAxis;
    }

    public void setyAxis(yAxis yAxis) {
        this.yAxis = yAxis;
    }

    public ChartModel() {
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Subtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public xAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(xAxis xAxis) {
		this.xAxis = xAxis;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}
}
