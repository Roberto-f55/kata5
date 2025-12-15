package software.ulpgc.kata5.application.database;

import software.ulpgc.kata5.application.*;
import software.ulpgc.kata5.architecture.model.Movie;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db")){
            connection.setAutoCommit(false);
            importIfNeededInto(connection);
            Dekstop.create(new DatabaseStore(connection))
                    .display()
                    .setVisible(true);
        }
    }

    private static void importIfNeededInto(Connection connection) throws IOException, SQLException {
        if (new File("movies.db").length() > 0) return ;
        Stream<Movie> movies = new RemoteStore(MovieDeserializer::fromTsv).LoadAll();
        new DatabaseRecorder(connection).record(movies);
    }
}
