package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.kafka.KafkaConstants;
import edu.uoc.epcsd.showcatalog.repositories.ShowRepository;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private KafkaTemplate<String, Show> kafkaTemplate;

    //get all shows
    @GetMapping("/allShows")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getAllShows() {
        log.trace("Get All Shows");
        return showRepository.findAll();
    }

    //create show
    @PostMapping("/createShow")
    public Show createShow(@RequestBody Show show) {
        log.trace("Create Show");
        showRepository.save(show);
        kafkaTemplate.send("shows.add", show);
        return show;
    }

    //delete show
    @DeleteMapping("/delete/{showId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShow(@PathVariable Long showId) {

        if (showRepository.existsById(showId)){
            log.trace("Delete Show");
            showRepository.deleteById(showId);
        } else {
            log.trace("Show does not exist");
        }
    }

    // get shows by name
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByName(@PathVariable("name") String name) {
        log.trace("Search show by name");
        return showRepository.findShowsByName(name);
    }

    // get shows by id
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Show getShowsById(@PathVariable("id") Long id) {
        log.trace("Show properties show by id");
        return showRepository.findShowsById(id);
    }

    // get shows by categories
    @GetMapping("/category/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByCategories(@PathVariable("name") String name) {
        log.trace("Search by name");
        return showRepository.findShowsByCategoryName(name);
    }


    // get performance by show name
    @GetMapping("/performance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Performance> getPerformanceByShowId(@PathVariable("id") Long id){
        if (showRepository.existsById(id)){
            log.trace("Get Performances by Id");
            return showRepository.findShowsById(id).getPerformances();
        } else {
            log.trace("Show does not exist, Performance cant be retrieved");
            return null;
        }
    }

    // create performances
    @PostMapping("/performance/createPerformancee")
    public Performance createPerformance(@RequestBody Performance performance, Long id){
        log.trace("Create Performance");
        Show show = showRepository.findShowsById(id);
        List<Performance> performances = show.getPerformances();
        performances.add(performance);
        show.setPerformances(performances);
        showRepository.save(show);
        return performance;
    }

}
