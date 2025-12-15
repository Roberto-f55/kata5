package software.ulpgc.kata4.application;

import software.ulpgc.kata4.architecture.model.Movie;
import software.ulpgc.kata4.architecture.viewmodel.Histogram;
import software.ulpgc.kata4.architecture.viewmodel.HistogramBuilder;

import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Dekstop
                .create()
                .display(histogram())
                .setVisible(true);
    }

    private static Histogram histogram() throws IOException {
        Histogram histogram = HistogramBuilder.
                with(movies())
                .tittle("Histograma")
                .x("EJEX")
                .y("EJEY")
                .leyend("Movies")
                .build(m -> (m.year() / 10) * 10);
        return histogram;
    }

    private static Stream<Movie> movies() throws IOException {
        return new RemoteStore(MovieDeserializer::fromTsv)
                .LoadAll()
                .limit(1000);
    }
}