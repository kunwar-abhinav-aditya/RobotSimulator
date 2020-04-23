package com.robotsimulator.task.helper;

import com.robotsimulator.task.enums.Direction;
import com.robotsimulator.task.enums.Movement;

import java.util.HashMap;
import java.util.Map;

public class DirectionDecider {

    private static DirectionDecider single_instance = null;
    public Map<Movement, Map<Direction, Direction>> directionDeciderMap = new HashMap<>();
    private Map<Direction, Direction> leftApplied = new HashMap<>();
    private Map<Direction, Direction> rightApplied = new HashMap<>();

    private DirectionDecider() {

        this.leftApplied.put(Direction.EAST, Direction.NORTH);
        this.leftApplied.put(Direction.WEST, Direction.SOUTH);
        this.leftApplied.put(Direction.NORTH, Direction.WEST);
        this.leftApplied.put(Direction.SOUTH, Direction.EAST);
        this.directionDeciderMap.put(Movement.LEFT, leftApplied);

        this.rightApplied.put(Direction.EAST, Direction.SOUTH);
        this.rightApplied.put(Direction.WEST, Direction.NORTH);
        this.rightApplied.put(Direction.NORTH, Direction.EAST);
        this.rightApplied.put(Direction.SOUTH, Direction.WEST);

        this.directionDeciderMap.put(Movement.RIGHT, rightApplied);
    }

    public static DirectionDecider getInstance()
    {
        if (single_instance == null)
            single_instance = new DirectionDecider();
        return single_instance;
    }
}
