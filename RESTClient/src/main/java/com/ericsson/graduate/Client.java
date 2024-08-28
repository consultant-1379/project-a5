package com.ericsson.graduate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

@Controller
public class Client {
    @Autowired
    private RESTService restService;

    @GetMapping(path = "/")
    public String showIndex() {
        return "index";
    }

    @GetMapping(path = "/postTimeRange")
    public String postTimeRange(Model model, @RequestParam("from") String from, @RequestParam("to") String to) {
        List<Commit> commits = restService.getAllCommits(parseLocalDateTimeWithDefaults(from), parseLocalDateTimeWithDefaults(to));
        model.addAttribute("commits", commits);
        return "commits";
    }

    private static LocalDateTime parseLocalDateTimeWithDefaults(String input) {
        final DateTimeFormatter fmt2 = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();

        return LocalDateTime.parse(input, fmt2);
    }
}
