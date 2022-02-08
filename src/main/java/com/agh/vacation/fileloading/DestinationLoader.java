package com.agh.vacation.fileloading;

import com.agh.vacation.something.VacationDestination;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DestinationLoader {
    private static final String CITIES_PATH = "Cities\\";

    private DestinationLoader() {

    }

    static List<VacationDestination> loadDestinations(String expertDirectoryName) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<VacationDestination> destinations = new ArrayList<>();
        try (Stream<Path> pathStream = Files.list(Paths.get(expertDirectoryName))) {
            pathStream.map(Path::toFile)
                    .forEach(path -> {
                        try {
                            destinations.add(objectMapper.readValue(path, VacationDestination.class));
                        } catch (IOException e) {
                            System.err.println("There was a problem with: " + path);
                        }
                    });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return destinations;
    }

    public static List<ExpertDestinationRatings> loadMultipleExpertsDestinationRatings() throws IOException {
        return loadMultipleExpertsDestinationRatings(Paths.get(CITIES_PATH));
    }

    public static List<ExpertDestinationRatings> loadMultipleExpertsDestinationRatings(Path path) throws IOException {
        List<ExpertDestinationRatings> expertRatings = new LinkedList<>();
        try (Stream<Path> pathStream = Files.list(path)) {
            pathStream.filter(Files::isDirectory).forEach(file ->
                    expertRatings.add(new ExpertDestinationRatings(file.getFileName().toString(),
                            loadDestinations(file.toFile().getAbsolutePath())))
            );
        }
        if (expertRatings.isEmpty())
            throw new IOException("There are no expert files !");
        return expertRatings;
    }
}
