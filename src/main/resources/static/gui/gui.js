import * as THREE from "../three/build/three.module.js";
import { GUI } from '../three/examples/jsm/libs/dat.gui.module.js';
import { saveCameraLocation, loadCameraLocation } from "../saveLoadCamera.js";
const gui = new GUI();
const cameras = gui.addFolder('Camera\'s');
const options = gui.addFolder('Options');
let currentCamera = "free";

export function Gui(scene, directionalLight, camera, cameraControls){
    const cubeLoader = new THREE.CubeTextureLoader();
    let cameraObject = {

        free: function (){
            cameraControls.enabled = true;
            if (currentCamera !== "free"){
                currentCamera = "free";
                loadCameraLocation(camera);
            }
            console.log("Changed camera to Free Cam.");
        },

        camera1: function (){
            if(currentCamera === "free"){
                saveCameraLocation(camera);
            }
            cameraControls.enabled = false;
            camera.position.x = -89;
            camera.position.y = 20;
            camera.position.z = 130;
            camera.rotation.x = -0.62831853071;
            camera.rotation.y = -0.65539816339;
            camera.rotation.z = -0.41887902047;
            currentCamera = "cam1";
            console.log("Changed camera to Camera 1.");
        },

        camera2: function (){
            if(currentCamera === "free"){
                saveCameraLocation(camera);
            }
            cameraControls.enabled = false;
            camera.position.x = -89;
            camera.position.y = 20;
            camera.position.z = -10;
            camera.rotation.x = 0.62831853071;
            camera.rotation.y = -2.365106;
            camera.rotation.z = 0.51887902047;
            currentCamera = "cam2";
            console.log("Changed camera to Camera 2.");
        },

        camera3: function (){
            if(currentCamera === "free"){
                saveCameraLocation(camera);
            }
            cameraControls.enabled = false;
            camera.position.x = 85;
            camera.position.y = 20;
            camera.position.z = -10;
            camera.rotation.x = 0.62831853071;
            camera.rotation.y = 2.365106;
            camera.rotation.z = -0.46887902047;
            currentCamera = "cam3";
            console.log("Changed camera to Camera 3.");
        },
        camera4: function (){
            if(currentCamera === "free"){
                saveCameraLocation(camera);
            }
            cameraControls.enabled = false;
            camera.position.x = 85;
            camera.position.y = 20;
            camera.position.z = 130;
            camera.rotation.x = -0.62831853071;
            camera.rotation.y = 0.785398;
            camera.rotation.z = 0.51887902047;
            currentCamera = "cam4";
            console.log("Changed camera to Camera 4.");
        }
    }
    cameras.add(cameraObject, 'free').name('Free cam');
    cameras.add(cameraObject, 'camera1').name('Camera 1');
    cameras.add(cameraObject, 'camera2').name('Camera 2');
    cameras.add(cameraObject, 'camera3').name('Camera 3');
    cameras.add(cameraObject, 'camera4').name('Camera 4');

    const sbTextureDay = cubeLoader.load([
        './textures/Skybox/side.png',
        './textures/Skybox/side.png',
        './textures/Skybox/top.png',
        './textures/Skybox/bottom.png',
        './textures/Skybox/side.png',
        './textures/Skybox/side.png'
    ]);
    const sbTextureNight = cubeLoader.load([
        './textures/Skybox/sideN.png',
        './textures/Skybox/sideN.png',
        './textures/Skybox/topN.png',
        './textures/Skybox/bottomN.png',
        './textures/Skybox/sideN.png',
        './textures/Skybox/sideN.png'
    ]);

    let dayObj = {
        name: 'Dag',
        day: true
    }
    options.add(dayObj, 'day').name(dayObj.name).onChange(function (){
        if(dayObj.day === false){
            directionalLight.color.set(0xFFF);
            scene.background = sbTextureNight;
        }else{
            directionalLight.color.set(0xFFFFFF);
            scene.background = sbTextureDay;
        }
    });
}

export function addOption(object, property){
    return options.add(object, property);
}

export function getCurrentCamera(){
    return currentCamera;
}

