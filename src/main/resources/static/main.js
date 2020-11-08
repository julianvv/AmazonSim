import * as THREE from "./three/build/three.module.js";
import { OrbitControls } from "./three/examples/jsm/controls/OrbitControls.js";
import {EffectComposer} from "./three/examples/jsm/postprocessing/EffectComposer.js ";
import {BloomPass} from "./three/examples/jsm/postprocessing/BloomPass.js";
import {RenderPass} from "./three/examples/jsm/postprocessing/RenderPass.js";
import {FilmPass} from "./three/examples/jsm/postprocessing/FilmPass.js";
import { Gui, getCurrentCamera } from "./gui/gui.js";
import {addObjects} from "./addObjects.js";
import {addLights} from "./addLights.js";
import { nodegrid } from "./nodeGrid.js";

let cameraControls, camera, currentCamera, gui, lightGroup, mixer, renderer, scene;

window.onload = function () {
    let worldObjects = {};

    function init() {
        camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 1, 10000);
        camera.position.x = 15;
        camera.position.y = 5;
        camera.position.z = 15;

        scene = new THREE.Scene();

        const cubeLoader = new THREE.CubeTextureLoader();
        const sbTextureDay = cubeLoader.load([
            './textures/Skybox/side.png',
            './textures/Skybox/side.png',
            './textures/Skybox/top.png',
            './textures/Skybox/bottom.png',
            './textures/Skybox/side.png',
            './textures/Skybox/side.png'
        ]);
        scene.background = sbTextureDay;

        renderer = new THREE.WebGLRenderer({ antialias: true });
        renderer.setPixelRatio(window.devicePixelRatio);
        renderer.setSize(window.innerWidth, window.innerHeight + 5);
        renderer.outputEncoding = THREE.sRGBEncoding;
        renderer.shadowMapEnabled = true;
        renderer.shadowMapType = THREE.PCFSoftShadowMap;
        document.body.appendChild(renderer.domElement);

        cameraControls = new OrbitControls(camera, renderer.domElement);
        cameraControls.update();

        let directionalLight = new THREE.DirectionalLight(0xFFFFFF, 1.5);
        directionalLight.position.set(-150, 500, -50);
        directionalLight.castShadow = true;
        scene.add(directionalLight);

        gui = new Gui(scene, directionalLight, camera, cameraControls);

        let light = new THREE.AmbientLight(0x404040);
        light.intensity = 2;
        scene.add(light);

        lightGroup = new THREE.Group();
        addLights(scene, lightGroup);
    }

    function resizeRendererToDisplaySize(renderer) {
        const canvas = renderer.domElement;
        const width = canvas.clientWidth;
        const height = canvas.clientHeight;
        const needResize = canvas.width !== width || canvas.height !== height;
        if (needResize) {
            renderer.setSize(width, height, false);
        }
        return needResize;
    }



    let then = 0;
    function render(now) {
        now *= 0.001;
        let deltaTime = now - then;
        then = now;
        camera.updateProjectionMatrix();
        currentCamera = getCurrentCamera();

        if(mixer != null){
            mixer.update(deltaTime);
        }

        if (resizeRendererToDisplaySize(renderer)) {
            const canvas = renderer.domElement;
            camera.aspect = canvas.clientWidth / canvas.clientHeight;
            camera.updateProjectionMatrix();
            composer.setSize(canvas.width, canvas.height);
            renderer.setSize(canvas.width, canvas.height);
        }

        if (currentCamera == "free"){
            renderer.render(scene, camera);
        }
        else{
            composer.render(deltaTime);
        }
        requestAnimationFrame(render);
    }

    init();
    addObjects(worldObjects, scene, lightGroup);

    const composer = new EffectComposer(renderer);
    composer.addPass(new RenderPass(scene,camera));
    const bloomPass = new BloomPass(
        1,    // strength
        10,   // kernel size
        1,    // sigma ?
        1024,  // blur render target resolution
    );
    composer.addPass(bloomPass);

    const filmPass = new FilmPass(
        0.5,   // noise intensity
        0.2,  // scanline intensity
        400,    // scanline count
        true,  // grayscale
    );
    filmPass.renderToScreen = true;
    composer.addPass(filmPass);

    render();

    //Grid-systeem voor het aflezen van coördinaten
    /*
    let columns = 4;
    let rows = 174;
    let nodesize = 0.5;
    let originx = -90;
    let originz = 72;
    nodegrid(columns, rows, nodesize,originx,originz, scene);
        */
}