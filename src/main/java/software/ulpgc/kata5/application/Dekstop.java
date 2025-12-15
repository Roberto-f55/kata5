package software.ulpgc.kata4.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.kata4.architecture.viewmodel.Histogram;

import javax.swing.*;

public class Dekstop extends JFrame {


    public static Dekstop create(){
        return new Dekstop();
    }

    private Dekstop(){
        this.setTitle("Histograma");
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    public Dekstop display(Histogram histogram){
        this.getContentPane().add(chartPannelWith(histogram));
        return this;
    }

    private ChartPanel chartPannelWith(Histogram histogram) {
        return new ChartPanel(chartWith(histogram));
    }

    private JFreeChart chartWith(Histogram histogram) {
        return ChartFactory.createHistogram(
                histogram.tittle(),
                histogram.x(),
                histogram.y(),
                datasetWith(histogram)
        );
    }

    private XYSeriesCollection datasetWith(Histogram histogram) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesIn(histogram));
        return dataset;
    }

    private XYSeries seriesIn(Histogram histogram) {
        XYSeries serie = new XYSeries(histogram.leyend());
        for (int bin: histogram){
            serie.add(bin, histogram.count(bin));
        }

        return serie;
    }


}
