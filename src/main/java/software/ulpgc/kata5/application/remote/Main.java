package software.ulpgc.kata5.application.remote;

import software.ulpgc.kata5.application.Dekstop;
import software.ulpgc.kata5.application.MovieDeserializer;
import software.ulpgc.kata5.application.RemoteStore;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dekstop
                .create(new RemoteStore(MovieDeserializer::fromTsv))
                .display()
                .setVisible(true);
    }

}