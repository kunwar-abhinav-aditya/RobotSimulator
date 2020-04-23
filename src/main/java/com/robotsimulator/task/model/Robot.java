package com.robotsimulator.task.model;

import com.robotsimulator.task.enums.Direction;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Robot {

    private Direction currentDirection;
    private int xPosition;
    private int yPosition;
    private int forwardMovesLeft;

    public Robot() {
        this.currentDirection = Direction.EAST;
        this.xPosition = 0;
        this.yPosition = 0;
    }
}
