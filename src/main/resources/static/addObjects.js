/*
* Hier wordt de socketcommunicatie geregeld. Er wordt een nieuwe websocket aangemaakt voor het webadres dat we in
* de server geconfigureerd hebben als connectiepunt (/connectToSimulation). Op de socket wordt een .onmessage
* functie geregistreerd waarmee binnenkomende berichten worden afgehandeld.
*/
import * as THREE from "./three/build/three.module.js";
import {GLTFLoader} from "./three/examples/jsm/loaders/GLTFLoader.js";
import {addAnimation, addOption} from "./gui/gui.js";
const gltfLoader = new GLTFLoader();
let socket, warehouseObject;

function parseCommand(input = "") {
    return JSON.parse(input);
}

export function addObjects(worldObjects, scene, lightGroup){
    socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/connectToSimulation");
    socket.onmessage = function (event) {
        //Hier wordt het commando dat vanuit de server wordt gegeven uit elkaar gehaald
        let command = parseCommand(event.data);

        //Wanneer het commando is "object_update", dan wordt deze code uitgevoerd. Bekijk ook de servercode om dit goed te begrijpen.
        if (command.command == "object_update") {
            //Wanneer het object dat moet worden geupdate nog niet bestaat (komt niet voor in de lijst met worldObjects op de client),
            //dan wordt het 3D model eerst aangemaakt in de 3D wereld.
            if (Object.keys(worldObjects).indexOf(command.parameters.uuid) < 0) {

                if (command.parameters.type == "building") {
                    worldObjects[command.parameters.uuid] = null;
                    gltfLoader.load('./gltf/amazonmapv6.glb', function ( gltf ) {
                        let warehouse = gltf.scene;
                        warehouse.scale.set(50,50,50);
                        warehouse.position.set(command.parameters.x, command.parameters.y, command.parameters.z);
                        console.log(gltf);

                        warehouse.traverse( function (node){
                            node.castShadow = true;
                            node.receiveShadow = true;
                        });

                        warehouseObject = {
                            warehouse: warehouse,
                            ceilingStatus: warehouse.children[29].visible
                        }

                        addOption(warehouseObject, 'ceilingStatus').name('Ceiling').onChange(function (){
                            if(warehouseObject.warehouse.children[29].visible){
                                warehouseObject.warehouse.children[29].visible = false;
                                lightGroup.visible = false;
                                console.log('ceiling becomes false');
                            }else{
                                warehouseObject.warehouse.children[29].visible = true;
                                lightGroup.visible = true;
                                console.log('ceiling becomes true');
                            }
                        });

                        scene.add(warehouse);
                        worldObjects[command.parameters.uuid] = warehouse;
                    });
                }
                //Wanneer het object een robot is, wordt de code hieronder uitgevoerd.
                if (command.parameters.type == "robot") {
                    let geometry = new THREE.BoxGeometry(0.9, 0.3, 0.9);
                    let cubeMaterials = [
                        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_side.png"), side: THREE.DoubleSide }), //LEFT
                        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_side.png"), side: THREE.DoubleSide }), //RIGHT
                        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_top.png"), side: THREE.DoubleSide }), //TOP
                        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_bottom.png"), side: THREE.DoubleSide }), //BOTTOM
                        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_front.png"), side: THREE.DoubleSide }), //FRONT
                        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_front.png"), side: THREE.DoubleSide }), //BACK
                    ];

                    let robot = new THREE.Mesh(geometry, cubeMaterials);
                    robot.receiveShadow = true;
                    robot.castShadow = true;
                    robot.position.set(command.parameters.x, command.parameters.y, command.parameters.z);

                    scene.add(robot);
                    worldObjects[command.parameters.uuid] = robot;
                }

                if (command.parameters.type == "truck") {
                    worldObjects[command.parameters.uuid] = null;
                    gltfLoader.load('./gltf/amazontruck.glb', function (gltf){
                        let truck = gltf.scene;
                        truck.scale.set(2.5,2.5,2.5);
                        truck.traverse( function (node){
                            node.castShadow = true;
                            node.receiveShadow = true;
                        });
                        truck.position.set(command.parameters.x, command.parameters.y, command.parameters.z);
                        scene.add(truck);
                        worldObjects[command.parameters.uuid] = truck;
                    });
                }

                if (command.parameters.type == "stellage") {
                    worldObjects[command.parameters.uuid] = null;
                    gltfLoader.load('./gltf/blueStellage.glb', function (gltf){
                        let stellage = gltf.scene;

                        stellage.traverse( function (node){
                            node.castShadow = true;
                            node.receiveShadow = true;
                        });
                        stellage.position.set(command.parameters.x, command.parameters.y, command.parameters.z);
                        scene.add(stellage);
                        worldObjects[command.parameters.uuid] = stellage;
                    });
                }

                if(command.parameters.type == "orderpicker"){
                    worldObjects[command.parameters.uuid] = null;
                    gltfLoader.load('./gltf/tposehuman.glb', function (gltf){
                        let orderPicker = gltf.scene;

                        orderPicker.traverse( function (node){
                            node.castShadow = true;
                            node.receiveShadow = true;
                        });
                        orderPicker.position.set(command.parameters.x, command.parameters.y, command.parameters.z);
                        orderPicker.rotation.y = command.parameters.rotationY;
                        scene.add(orderPicker);
                        worldObjects[command.parameters.uuid] = orderPicker;
                    });
                }
            }

            /*
             * Deze code wordt elke update uitgevoerd. Het update alle positiegegevens van het 3D object.
             */
            let object = worldObjects[command.parameters.uuid];

            if(object != null){
                object.position.x = command.parameters.x;
                object.position.y = command.parameters.y;
                object.position.z = command.parameters.z;

                object.rotation.x = command.parameters.rotationX;
                object.rotation.y = command.parameters.rotationY;
                object.rotation.z = command.parameters.rotationZ;
            }
        }
    }
}
