package com.robotsimulator.task;

import com.robotsimulator.task.service.RobotGameService;
import com.robotsimulator.task.enums.Direction;
import com.robotsimulator.task.model.Robot;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class RobotGameServiceTest {

    @Test
    public void testMultipleSteps() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "POSITION 2 2 WEST\nFORWARD 2\nRIGHT\nFORWARD 1";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        Assert.assertEquals(Direction.NORTH, robot.getCurrentDirection());
        assertEquals(0, robot.getXPosition());
        assertEquals(1, robot.getYPosition());
    }

    @Test
    public void testPositionValid() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "POSITION 3 3 NORTH";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        Assert.assertEquals(Direction.NORTH, robot.getCurrentDirection());
        assertEquals(3, robot.getXPosition());
        assertEquals(3, robot.getYPosition());
    }

    @Test
    public void testPositionInValid_1() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "POSITION 4 6 WEST";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        Assert.assertEquals(Direction.EAST, robot.getCurrentDirection());
        assertEquals(0, robot.getXPosition());
        assertEquals(0, robot.getYPosition());
    }

    @Test
    public void testPositionInValid_2() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "POSITION 6 4 NORTH";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        Assert.assertEquals(Direction.EAST, robot.getCurrentDirection());
        assertEquals(0, robot.getXPosition());
        assertEquals(0, robot.getYPosition());
    }


    @Test
    public void testNewLocationWithForwardValid() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "FORWARD 2";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        Assert.assertEquals(Direction.EAST, robot.getCurrentDirection());
        assertEquals(2, robot.getXPosition());
        assertEquals(0, robot.getYPosition());
    }

    @Test
    public void testNewLocationWithForwardInvalid() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "FORWARD 5";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        Assert.assertEquals(Direction.EAST, robot.getCurrentDirection());
        assertEquals(0, robot.getXPosition());
        assertEquals(0, robot.getYPosition());
    }

    @Test
    public void testTurnaround() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "TURNAROUND";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        System.out.println(robot);
        Assert.assertEquals(Direction.WEST, robot.getCurrentDirection());
        assertEquals(0, robot.getXPosition());
        assertEquals(0, robot.getYPosition());
    }

    @Test
    public void testLeft() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "POSITION 4 4 NORTH\nLEFT";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        Assert.assertEquals(Direction.WEST, robot.getCurrentDirection());
        assertEquals(4, robot.getXPosition());
        assertEquals(4, robot.getYPosition());
    }

    @Test
    public void testRight() {
        RobotGameService robotGameService = new RobotGameService();
        robotGameService.getNewGame(5);
        String test = "POSITION 1 4 SOUTH\nRIGHT";
        Robot robot = robotGameService.makeMovesAndGetNewPosition(test);
        Assert.assertEquals(Direction.WEST, robot.getCurrentDirection());
        assertEquals(1, robot.getXPosition());
        assertEquals(4, robot.getYPosition());
    }
}
