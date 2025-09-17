package ru.bush.bush_cinema.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ResourcesReader {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T readJson(String path, Class<T> clazz) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            return objectMapper.readValue(resource.getInputStream(), clazz);
        } catch (Exception e) {
            log.error("Can not load data from {}", path, e);
        }

        return null;
    }

    public static String getResourceAsString(String path) {
        try {
            byte[] bytes = getResource(path).readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("Can not load data from {}", path, e);
        }
        return null;
    }

    private static InputStream getResource(String path) throws IOException {
        return new ClassPathResource(path).getInputStream();
    }
}
