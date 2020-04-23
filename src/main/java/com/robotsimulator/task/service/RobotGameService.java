package com.robotsimulator.task.service;

import com.robotsimulator.task.helper.DirectionDecider;
import com.robotsimulator.task.enums.Direction;
import com.robotsimulator.task.enums.Movement;
import com.robotsimulator.task.model.Robot;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
@Data
public class RobotGameService {

    private int boardSize;

    private Robot robot;

    public Robot makeMovesAndGetNewPosition(String moves) {
        Scanner scanner = new Scanner(moves);
        while (scanner.hasNextLine()) {
            String move = scanner.nextLine();
            String[] components = move.split(" ");
            switch (components[0].toUpperCase()) {
                case("POSITION"):
                    this.robot = position(Integer.parseInt(components[1]), Integer.parseInt(components[2]), Direction.valueOf(components[3]), this.robot);
                    break;
                case("FORWARD"):
                    this.robot = forward(Integer.parseInt(components[1]), this.robot);
                    break;
                case("TURNAROUND"):
                    this.robot = turnaround(this.robot);
                    break;
                case("LEFT"):
                    this.robot = left(this.robot);
                    break;
                case("RIGHT"):
                    this.robot = right(this.robot);
                    break;
                case("WAIT"):
                    this.robot = wait(this.robot);
                    break;
            }
        }
        scanner.close();
        return this.robot;
    }

    // Positions the robot at a location on the board and in a particular direction
    private Robot position(int x, int y, Direction direction, Robot robot){
        if (x < this.getBoardSize() && y < this.getBoardSize()) {
            robot.setXPosition(x);
            robot.setYPosition(y);
            robot.setCurrentDirection(direction);
            return updateForwardMovesLeft(robot);
        } else {
            return robot;
        }
    }

    // Moves the robot forward defined number of steps
    private Robot forward(int stepsToTake, Robot robot) {
        System.out.println(stepsToTake);
        System.out.println(robot);
        if (stepsToTake > robot.getForwardMovesLeft()) {
            return robot;
        } else {
            robot.setForwardMovesLeft(robot.getForwardMovesLeft() - stepsToTake);
            switch (robot.getCurrentDirection()) {
                case EAST:
                    robot.setXPosition(robot.getXPosition() + stepsToTake);
                    break;
                case WEST:
                    robot.setXPosition(robot.getXPosition() - stepsToTake);
                    break;
                case NORTH:
                    robot.setYPosition(robot.getYPosition() - stepsToTake);
                    break;
                case SOUTH:
                    robot.setYPosition(robot.getYPosition() + stepsToTake);
                    break;
            }
        }
        return robot;
    }

    // Makes the robot turn around
    private Robot turnaround(Robot robot) {
        switch (robot.getCurrentDirection()) {
            case EAST:
                robot.setCurrentDirection(Direction.WEST);
                break;
            case WEST:
                robot.setCurrentDirection(Direction.EAST);
                break;
            case NORTH:
                robot.setCurrentDirection(Direction.SOUTH);
                break;
            case SOUTH:
                robot.setCurrentDirection(Direction.NORTH);
                break;
        }
        robot.setForwardMovesLeft((this.boardSize - 1) - robot.getForwardMovesLeft());
        return robot;
    }

    // Moves the robot towards left
    private Robot left(Robot robot) {
        Direction leftMovedDir = DirectionDecider.getInstance().directionDeciderMap.get(Movement.LEFT).get(robot.getCurrentDirection());
        robot.setCurrentDirection(leftMovedDir);
        return updateForwardMovesLeft(robot);
    }

    // Moves the robot towards right
    private Robot right(Robot robot) {
        Direction rightMovedDir = DirectionDecider.getInstance().directionDeciderMap.get(Movement.RIGHT).get(robot.getCurrentDirection());
        robot.setCurrentDirection(rightMovedDir);
        return updateForwardMovesLeft(robot);
    }

    // Makes the robot do nothing.
    private Robot wait(Robot robot) {
        return robot;
    }

    // Updates the number of forward moves left for the robot
    private Robot updateForwardMovesLeft(Robot robot) {
        switch (robot.getCurrentDirection()) {
            case EAST:
                robot.setForwardMovesLeft((this.boardSize - 1) - robot.getXPosition());
                break;
            case WEST:
                robot.setForwardMovesLeft(robot.getXPosition());
                break;
            case NORTH:
                robot.setForwardMovesLeft(robot.getYPosition());
                break;
            case SOUTH:
                robot.setForwardMovesLeft((this.boardSize - 1) - robot.getYPosition());
                break;
        }
        return robot;
    }

    public Robot getNewGame(int boardSize) {
        this.setBoardSize(boardSize);
        this.robot = new Robot();
        this.robot.setForwardMovesLeft(boardSize - 1);
        return this.robot;
    }
}
