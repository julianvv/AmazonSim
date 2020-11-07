import * as THREE from './three/build/three.module.js';

export function addLights(scene, lightGroup){
    for(let i = 0; i < 4; i ++){
        for(let j = 0; j < 3; j ++){
            let pointLight = new THREE.PointLight(0xFFFFFF, 1, 100, 2);
            pointLight.position.set((-60 + i * 50), 20, (j * 40));
            pointLight.castShadow = true;
            pointLight.shadow.normalBias = 0.25;
            lightGroup.add(pointLight);
        }
    }
    scene.add(lightGroup);
}