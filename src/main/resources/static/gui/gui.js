import * as THREE from "../three/build/three.module.js";
import { GUI } from '../three/examples/jsm/libs/dat.gui.module.js';
import { saveCameraLocation, loadCameraLocation } from "../saveLoadCamera.js";
const gui = new GUI();
const animations = gui.addFolder('Animations');
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
            camera.rotation.x = -(Math.PI * 2)/10;
            camera.rotation.y = -((Math.PI*2)/8);
            camera.rotation.z = -(Math.PI*2)/15;
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
            camera.rotation.x = -(Math.PI * 2)/10;
            camera.rotation.y = ((Math.PI*2)/8);
            camera.rotation.z = -(Math.PI*2)/15;
            currentCamera = "cam2";
            console.log("Changed camera to Camera 2.");
        },
        camera3: function (){
            if(currentCamera === "free"){
                saveCameraLocation(camera);
            }
            cameraControls.enabled = false;
            camera.position.x = -89;
            camera.position.y = 20;
            camera.position.z = -10;
            camera.rotation.x = -(Math.PI * 2)/10;
            camera.rotation.y = -((Math.PI*2)/8);
            camera.rotation.z = -(Math.PI*2)/15;
            currentCamera = "cam3";
            console.log("Changed camera to Camera 3.");
        }
    }
    cameras.add(cameraObject, 'free').name('Free cam');
    cameras.add(cameraObject, 'camera1').name('Camera 1');
    cameras.add(cameraObject, 'camera2').name('Camera 2');
    cameras.add(cameraObject, 'camera3').name('Camera 3');

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

export function addOption(object, partOfObject){
    return options.add(object, partOfObject);
}

export function addAnimation(object, partOfObject){
    return animations.add(object, partOfObject);
}

export function getCurrentCamera(){
    return currentCamera;
}

