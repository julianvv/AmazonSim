package com.nhlstenden.group7.AmazonSim.AstarAlgorithm;

import java.util.List;

public class Test {
    public static void main(String[] args){
        Node initialNode = new Node(0,0);
        Node finalNode = new Node(13,0);
        int rows = 14;
        int cols = 10;
        Astar astar = new Astar(rows, cols, initialNode, finalNode);
        int[][] blocksArray = new int[][]{{1,0},{1,1},{1,2},{1,3},{1,4},{11, 7}, {11, 8}, {12, 7}, {13, 7}};
        astar.setBlocks(blocksArray);
        List<Node> path = astar.findPath();
        for(Node node : path){
            System.out.println(node);
        }
    }
}
