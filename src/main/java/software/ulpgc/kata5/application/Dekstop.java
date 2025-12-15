package software.ulpgc.kata5.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.kata5.architecture.io.Store;
import software.ulpgc.kata5.architecture.model.Movie;
import software.ulpgc.kata5.architecture.viewmodel.Histogram;
import software.ulpgc.kata5.architecture.viewmodel.HistogramBuilder;

import javax.swing.*;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

public class Dekstop extends JFrame {
    private final Store store;

    public static Dekstop create(Store store){
        return new Dekstop(store);
    }

    private Dekstop(Store store){
        this.store = store;
        this.setTitle("Histograma");
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    public Dekstop display() throws IOException {
        this.getContentPane().add(chartPannelWith(histogram()));
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

    private Histogram histogram() throws IOException {
        Histogram histogram = HistogramBuilder.
                with(movies(store))
                .tittle("Histograma")
                .x("EJEX")
                .y("EJEY")
                .leyend("Movies")
                .build(m -> (m.year() / 10) * 10);
        return histogram;
    }

    static Stream<Movie> movies(Store store) throws IOException {
        return store.LoadAll();
    }
}
