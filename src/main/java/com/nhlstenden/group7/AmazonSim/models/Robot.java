package com.nhlstenden.group7.AmazonSim.models;
import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Astar;
import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Node;

import java.util.List;
import java.util.UUID;

public class Robot implements Object3D, Updatable {
    private UUID uuid;
    private String status;
    private Stellage stellage;
    private Stellage stellageTarget;
    private long timer;
    private Node storePosition;
    private Node nextNode;
    private Node target;
    private Astar astar;
    private List<Node> path;

    private double x = -80;
    private double y = -0.68;
    private double z = 60;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Robot(){
        this.uuid = UUID.randomUUID();
        this.status = "start";
    }

    public boolean update(){
        if(this.status.equals("awaitingOrderPicker") && (System.currentTimeMillis() - this.timer) >= 15000){
            this.status = "done";
        }

        if((this.z > 8 && this.z < 21) && ((this.x > -82 && this.x < -76) || (this.x > 77 && this.x < 83))){
            this.rotationX = 0.158259;//(Math.PI * 2)/22;
            if((this.path.get(0).getCol() - 10) - this.z > 0){
                this.y -= 0.063;
            }else{
                this.y += 0.063;
            }
        }else{
            this.rotationX = 0;
        }

        if(getHasStellage()){
            this.stellage.setRobot(this);
        }

        if(nextNode == null){
            if(path == null){
                if(target == null){
                    return true;
                }else{
                    astar = new Astar(174, 140, new Node((int) this.getX() + 89, (int) this.getZ() + 10), target);
                    int[][] blocksArray = new int[][]{
                            //Leftside-Leftramp
                            {0, 19}, {1, 19}, {2, 19}, {3, 19}, {4, 19}, {5, 19}, {6, 19}, {7, 19},
                            {0, 20}, {1, 20}, {2, 20}, {3, 20}, {4, 20}, {5, 20}, {6, 20}, {7, 20},
                            {0, 21}, {1, 21}, {2, 21}, {3, 21}, {4, 21}, {5, 21}, {6, 21}, {7, 21},
                            {0, 22}, {1, 22}, {2, 22}, {3, 22}, {4, 22}, {5, 22}, {6, 22}, {7, 22},
                            {0, 23}, {1, 23}, {2, 23}, {3, 23}, {4, 23}, {5, 23}, {6, 23}, {7, 23},
                            {0, 24}, {1, 24}, {2, 24}, {3, 24}, {4, 24}, {5, 24}, {6, 24}, {7, 24},
                            {0, 25}, {1, 25}, {2, 25}, {3, 25}, {4, 25}, {5, 25}, {6, 25}, {7, 25},
                            {0, 26}, {1, 26}, {2, 26}, {3, 26}, {4, 26}, {5, 26}, {6, 26}, {7, 26},
                            {0, 27}, {1, 27}, {2, 27}, {3, 27}, {4, 27}, {5, 27}, {6, 27}, {7, 27},
                            {0, 28}, {1, 28}, {2, 28}, {3, 28}, {4, 28}, {5, 28}, {6, 28}, {7, 28},
                            {0, 29}, {1, 29}, {2, 29}, {3, 29}, {4, 29}, {5, 29}, {6, 29}, {7, 29},
                            {0, 30}, {1, 30}, {2, 30}, {3, 30}, {4, 30}, {5, 30}, {6, 30}, {7, 30},
                            {0, 31}, {1, 31}, {2, 31}, {3, 31}, {4, 31}, {5, 31}, {6, 31}, {7, 31},
                            {0, 32}, {1, 32}, {2, 32}, {3, 32}, {4, 32}, {5, 32}, {6, 32}, {7, 32},
                            //Rightside-Leftramp
                            {13, 17}, {14, 17}, {15, 17}, {16, 17}, {17, 17}, {18, 17}, {19, 17}, {20, 17},
                            {13, 18}, {14, 18}, {15, 18}, {16, 18}, {17, 18}, {18, 18}, {19, 18}, {20, 18},
                            {13, 19}, {14, 19}, {15, 19}, {16, 19}, {17, 19}, {18, 19}, {19, 19}, {20, 19},
                            {13, 20}, {14, 20}, {15, 20}, {16, 20}, {17, 20}, {18, 20}, {19, 20}, {20, 20},
                            {13, 21}, {14, 21}, {15, 21}, {16, 21}, {17, 21}, {18, 21}, {19, 21}, {20, 21},
                            {13, 22}, {14, 22}, {15, 22}, {16, 22}, {17, 22}, {18, 22}, {19, 22}, {20, 22},
                            {13, 23}, {14, 23}, {15, 23}, {16, 23}, {17, 23}, {18, 23}, {19, 23}, {20, 23},
                            {13, 24}, {14, 24}, {15, 24}, {16, 24}, {17, 24}, {18, 24}, {19, 24}, {20, 24},
                            {13, 25}, {14, 25}, {15, 25}, {16, 25}, {17, 25}, {18, 25}, {19, 25}, {20, 25},
                            {13, 26}, {14, 26}, {15, 26}, {16, 26}, {17, 26}, {18, 26}, {19, 26}, {20, 26},
                            {13, 27}, {14, 27}, {15, 27}, {16, 27}, {17, 27}, {18, 27}, {19, 27}, {20, 27},
                            {13, 28}, {14, 28}, {15, 28}, {16, 28}, {17, 28}, {18, 28}, {19, 28}, {20, 28},
                            {13, 29}, {14, 29}, {15, 29}, {16, 29}, {17, 29}, {18, 29}, {19, 29}, {20, 29},
                            {13, 30}, {14, 30}, {15, 30}, {16, 30}, {17, 30}, {18, 30}, {19, 30}, {20, 30},
                            {13, 31}, {14, 31}, {15, 31}, {16, 31}, {17, 31}, {18, 31}, {19, 31}, {20, 31},
                            {13, 32}, {14, 32}, {15, 32}, {16, 32}, {17, 32}, {18, 32}, {19, 32}, {20, 32},

                            //between-ramps
                            {21, 17}, {22, 17}, {23, 17}, {24, 17}, {25,17}, {26, 17}, {27, 17},
                            {28, 17}, {29, 17}, {30, 17}, {31, 17}, {32, 17}, {33, 17}, {34, 17}, {35, 17}, {36, 17},
                            {37, 17}, {38, 17}, {39, 17}, {40, 17}, {41, 17}, {42, 17}, {43, 17}, {44, 17}, {45, 17},
                            {46, 17}, {47, 17}, {48, 17}, {49, 17}, {50, 17}, {51, 17}, {52, 17}, {53, 17}, {54, 17},
                            {55, 17}, {56, 17}, {57, 17}, {58, 17}, {59, 17}, {60, 17}, {61, 17}, {62, 17}, {63, 17},
                            {64, 17}, {65, 17}, {66, 17}, {67, 17}, {68, 17}, {69, 17}, {70, 17}, {71, 17}, {72, 17},
                            {73, 17}, {74, 17}, {75, 17}, {76, 17}, {77, 17}, {78, 17}, {79, 17}, {80, 17}, {81, 17},
                            {82, 17}, {83, 17}, {84, 17}, {85, 17}, {86, 17}, {87, 17}, {88, 17}, {89, 17}, {90, 17},
                            {91, 17}, {92, 17}, {93, 17}, {94, 17}, {95, 17}, {96, 17}, {97, 17}, {98, 17}, {99, 17},
                            {100, 17}, {101, 17}, {102, 17}, {103, 17}, {104, 17}, {105, 17}, {106, 17}, {107, 17}, {108, 17},
                            {109, 17}, {110, 17}, {111, 17}, {112, 17}, {113, 17}, {114, 17}, {115, 17}, {116, 17}, {117, 17},
                            {118, 17}, {119, 17}, {120, 17}, {121, 17}, {122, 17}, {123, 17}, {124, 17}, {125, 17}, {126, 17},
                            {127, 17}, {128, 17}, {129, 17}, {130, 17}, {131, 17}, {132, 17}, {133, 17}, {134, 17}, {135, 17},
                            {136, 17}, {137, 17}, {138, 17}, {139, 17}, {140, 17}, {141, 17}, {142, 17}, {143, 17}, {144, 17},
                            {145, 17}, {146, 17}, {147, 17}, {148, 17}, {149, 17}, {150, 17}, {151, 17}, {152, 17}, {153, 17},
                            {154, 17}, {155, 17}, {156, 17}, {157, 17}, {158, 17},{20,17},{20,18},{159, 17},{159, 18},
                            {160, 17}, {161,17}, {162, 17}, {163, 17}, {164, 17}, {165, 17}, {166, 17},{166, 18},

                            //leftside-rightramp
                            {159, 19}, {160, 19}, {161, 19}, {162, 19}, {163, 19}, {164, 19}, {165, 19}, {166, 19},
                            {159, 20}, {160, 20}, {161, 20}, {162, 20}, {163, 20}, {164, 20}, {165, 20}, {166, 20},
                            {159, 21}, {160, 21}, {161, 21}, {162, 21}, {163, 21}, {164, 21}, {165, 21}, {166, 21},
                            {159, 22}, {160, 22}, {161, 22}, {162, 22}, {163, 22}, {164, 22}, {165, 22}, {166, 22},
                            {159, 23}, {160, 23}, {161, 23}, {162, 23}, {163, 23}, {164, 23}, {165, 23}, {166, 23},
                            {159, 24}, {160, 24}, {161, 24}, {162, 24}, {163, 24}, {164, 24}, {165, 24}, {166, 24},
                            {159, 25}, {160, 25}, {161, 25}, {162, 25}, {163, 25}, {164, 25}, {165, 25}, {166, 25},
                            {159, 26}, {160, 26}, {161, 26}, {162, 26}, {163, 26}, {164, 26}, {165, 26}, {166, 26},
                            {159, 27}, {160, 27}, {161, 27}, {162, 27}, {163, 27}, {164, 27}, {165, 27}, {166, 27},
                            {159, 28}, {160, 28}, {161, 28}, {162, 28}, {163, 28}, {164, 28}, {165, 28}, {166, 28},
                            {159, 29}, {160, 29}, {161, 29}, {162, 29}, {163, 29}, {164, 29}, {165, 29}, {166, 29},
                            {159, 30}, {160, 30}, {161, 30}, {162, 30}, {163, 30}, {164, 30}, {165, 30}, {166, 30},
                            {159, 31}, {160, 31}, {161, 31}, {162, 31}, {163, 31}, {164, 31}, {165, 31}, {166, 31},
                            {159, 32}, {160, 32}, {161, 32}, {162, 32}, {163, 32}, {164, 32}, {165, 32}, {166, 32},

                            //Rightside-rightramp
                            {172, 19}, {173, 19}, {172, 20}, {173, 20},
                            {172, 21}, {173, 21}, {172, 22}, {173, 22},
                            {172, 23}, {173, 23}, {172, 24}, {173, 24},
                            {172, 25}, {173, 25}, {172, 26}, {173, 26},
                            {172, 27}, {173, 27}, {172, 28}, {173, 28},
                            {172, 29}, {173, 29}, {172, 30}, {173, 30},
                            {172, 31}, {173, 31}, {172, 32}, {173, 32}
                    };
                    astar.setBlocks(blocksArray);
                    path = astar.findPath();
                    nextNode = path.get(0);
                    path.remove(0);

                    //TODO: Transport method toepassen.
                    this.x = nextNode.getRow() - 89;
                    this.z = nextNode.getCol() - 10;

                    nextNode = null;
                }
            }else if(path.size() > 0){
                nextNode = path.get(0);
                path.remove(0);

                this.x = nextNode.getRow() - 89;
                this.z = nextNode.getCol() - 10;
            }else{
                path = null;
            }
        }else{
            //TODO: Transport method toepassen.
            this.x = nextNode.getRow() - 89;
            this.z = nextNode.getCol() - 10;
            nextNode = null;
        }
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Robot.class.getSimpleName().toLowerCase();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setRotationX(double rotationX) {
        this.rotationX = x;
    }

    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

    public void setRotationZ(double rotationZ) {
        this.rotationZ = rotationZ;
    }

    @Override
    public double getRotationX() {
        return this.rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public Stellage getStellage(){
        return this.stellage;
    }

    public void setStellage(Stellage stellage){
        this.stellage = stellage;
    }

    public boolean getHasStellage(){
        if (stellage == null){
            return false;
        }
        return true;
    }

    public void setTarget(Node target){
        this.target = target;
    }
    public Node getTarget(){
        return this.target;
    }

    public void setTargetStellage(Stellage target){
        this.stellageTarget = target;
    }
    public Stellage getTargetStellage(){
        return this.stellageTarget;
    }

    public Node getCurrentNode(){
        return new Node((int) this.x + 89, (int) this.z + 10);
    }

    public boolean getIsAtTarget(){
        return this.target.equals(this.getCurrentNode());
    }

    public Node getStorePosition(){
        return this.storePosition;
    }

    public void setStorePosition(Node node){
        this.storePosition = node;
    }

    public void setTimer(long time){
        this.timer = time;
    }

    public long getTimer(){
        return this.timer;
    }
}
