package com.robotsimulator.task.controller;

import com.robotsimulator.task.model.Robot;
import com.robotsimulator.task.service.RobotGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/robotgame")
public class RobotGameController {

    @Autowired
    RobotGameService robotGameService;

    /**
     * Current implementation adheres to a board of size 5*5, but this can be extended in the future
     * to cater to boards of different sizes
     */
    @GetMapping
    public Robot getNewGame(@RequestParam(defaultValue = "5") int boardSize) {
        return robotGameService.getNewGame(5);
    }

    @PostMapping(path = "/makeMovesAndGetNewPosition", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Robot makeMovesAndGetNewPosition(@RequestBody String moves) {
        return robotGameService.makeMovesAndGetNewPosition(moves);
    }
}
