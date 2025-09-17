package ru.bush.bush_cinema.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bush.bush_cinema.service.ResourcesReader;

@AllArgsConstructor
@RestController
@RequestMapping("/api/temp")
@Tag(name = "temp", description = "Временные апи")
public class TempController {

    @GetMapping("header")
    public String getHeader() {
        return ResourcesReader.getResourceAsString("templates/header.json");
    }

    @GetMapping("news")
    public String getNews() {
        return ResourcesReader.getResourceAsString("templates/news.json");
    }
}
