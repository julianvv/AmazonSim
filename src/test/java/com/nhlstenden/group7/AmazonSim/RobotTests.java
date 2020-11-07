package com.nhlstenden.group7.AmazonSim;

import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Node;
import com.nhlstenden.group7.AmazonSim.models.Robot;
import com.nhlstenden.group7.AmazonSim.models.Stellage;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotTests {

    @Test
    public void RobotTests() {
        Robot robot = new Robot();
        Stellage testStellage = new Stellage();

        assertEquals("getType werkt niet naar behoren...", "robot", robot.getType());

        //Robot positie getter tests
        assertEquals("Robot getX werkt niet naar behoren...", -80, robot.getX(), 0.0);
        assertEquals("Robot getY werkt niet naar behoren...",  -0.68, robot.getY(), 0.0);
        assertEquals("Robot getZ werkt niet naar behoren...",  60, robot.getZ(), 0.0);

        //Robot positie setter tests
        robot.setX(1);
        assertEquals("Robot setX werkt niet", 1, robot.getX(), 0.0);
        robot.setY(2);
        assertEquals("Robot setY werkt niet", 2, robot.getY(), 0.0);
        robot.setZ(3);
        assertEquals("Robot setZ werkt niet", 3, robot.getZ(), 0.0);

        //Rotatie getter tests
        assertEquals("Rotatie begint niet op rotatie 0 t.o.v. de X-as", 0, robot.getRotationX(), 0.0);
        assertEquals("Rotatie begint niet op rotatie 0 t.o.v. de Y-as",  0, robot.getRotationY(), 0.0);
        assertEquals("Rotatie begint niet op rotatie 0 t.o.v. de Z-as",  0, robot.getRotationZ(), 0.0);

        //Rotatie setter tests
        robot.setRotationX(1);
        assertEquals("setRotationX werkt niet naar behoren...", 1, robot.getRotationX(), 0.0);
        robot.setRotationY(2);
        assertEquals("setRotationY werkt niet naar behoren...", 2, robot.getRotationY(), 0.0);
        robot.setRotationZ(3);
        assertEquals("setRotationZ werkt niet naar behoren...", 3, robot.getRotationZ(), 0.0);

        //Status test
        assertEquals("Robot heeft geen start-status van: 'start'", "start", robot.getStatus());
        robot.setStatus("test");
        assertEquals("Status setter werkt niet naar behoren...", "test", robot.getStatus());

        //Stellage tests
        robot.setStellage(testStellage);
        assertTrue("Stellage setter werkt niet naar behoren...", robot.getHasStellage());
        assertEquals("GetStellage werkt niet naar behoren...", testStellage, robot.getStellage());

        //Target tests (Pathfinding)
        assertNull("getTarget werkt niet naar behoren", robot.getTarget());
        robot.setTarget(new Node(1,1));
        assertEquals("setTarget werkt niet naar behoren...", new Node(1,1), robot.getTarget());

        assertNull("getTargetStellage werkt niet naar behoren...", robot.getTargetStellage());
        robot.setTargetStellage(testStellage);
        assertEquals("setTargetStellage werkt niet naar behoren...", testStellage, robot.getTargetStellage());

        assertNull("getStorePosition werkt niet naar behoren...", robot.getStorePosition());
        robot.setStorePosition(new Node(12,12));
        assertEquals("setStorePosition werkt niet naar behoren...", new Node(12,12), robot.getStorePosition());

        assertEquals("getCurrentNode werkt niet naar behoren...", new Node(90, 13), robot.getCurrentNode());
        assertEquals("getIsAtTarget werkt niet naar behoren...", false, robot.getIsAtTarget());
        robot.setTarget(new Node(90,13));
        assertEquals("getIsAtTarget werkt niet naar behoren...", true, robot.getIsAtTarget());

        assertEquals("getTimer werkt niet naar behoren...", 0, robot.getTimer());
        robot.setTimer(21398724);
        assertEquals("setTimer werkt niet naar behoren...", 21398724, robot.getTimer());
    }
}
