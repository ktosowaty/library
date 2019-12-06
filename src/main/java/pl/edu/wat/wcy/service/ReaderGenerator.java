package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.model.Reader;
import pl.edu.wat.wcy.repository.ReaderRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReaderGenerator {

    private static final int READERS_LIMIT = 50000;

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderGenerator(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public void generate() {
        Path firstNamesPath = Paths.get("src/main/resources/first_names.txt");
        Path lastNamesPath = Paths.get("src/main/resources/last_names.txt");
        List<String> firstNames = getNames(firstNamesPath);
        List<String> lastNames = getNames(lastNamesPath);
        for (int i = 0; i < READERS_LIMIT; i++) {
            Reader reader = new Reader(firstNames.get(i), lastNames.get(READERS_LIMIT - i - 1), generateDate());
            readerRepository.save(reader);
        }
    }

    private List<String> getNames(Path path) {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.limit(READERS_LIMIT).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private LocalDate generateDate() {
        long minDay = LocalDate.of(1940, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2010, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
