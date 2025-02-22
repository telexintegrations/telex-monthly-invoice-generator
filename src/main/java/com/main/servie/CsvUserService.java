package com.main.servie;


import com.main.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CsvUserService {

    private static final String CSV_FILE = "users.csv";

    public List<User> getUsersFromCsv() {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource(CSV_FILE).getInputStream()))) {
            String line;
            boolean firstLine = true; // Skip header row

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }

                String[] data = line.split(",");

                if (data.length == 4) {
                    Long id = Long.parseLong(data[0].trim());
                    String name = data[1].trim();
                    String email = data[2].trim();
                    boolean activeSubscription = Boolean.parseBoolean(data[3].trim());

                    users.add(new User(id, name, email, activeSubscription));
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return users;
    }
}
