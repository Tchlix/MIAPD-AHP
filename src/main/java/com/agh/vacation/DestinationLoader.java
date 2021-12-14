package com.agh.vacation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

class DestinationLoader {
    private static final String CITIES_PATH = "Cities\\";

    private DestinationLoader() {

    }

    static List<VacationDestination> loadDestinations(String expertDirectoryName) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<VacationDestination> destinations = new ArrayList<>();
        try (Stream<Path> pathStream = Files.list(Paths.get(CITIES_PATH + expertDirectoryName))) {
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

    static List<List<VacationDestination>> loadMultipleExpertsDestinationRatings() throws IOException {
        List<List<VacationDestination>> expertRatings = new LinkedList<>();
        try (Stream<Path> pathStream = Files.list(Paths.get(CITIES_PATH))) {
            pathStream.filter(Files::isDirectory).forEach(file ->
                    expertRatings.add(loadDestinations(file.getFileName().toString()))
            );
        }
        if (expertRatings.isEmpty())
            throw new IOException("There are no expert files !");
        return expertRatings;
    }
}
