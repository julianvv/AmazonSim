import { BoxGeometry, MeshBasicMaterial, Mesh, Texture} from "./three/build/three.module.js";
export function nodegrid(columns, rows, nodesize, originX, originZ, scene) {
    for(let i = 0; i < rows; i++){
        for (let j = 0; j < columns; j ++){
            let geometry = new BoxGeometry(nodesize, nodesize, nodesize);
            let material = generateTextCanvas(i + originX, j + originZ);
            let cube = new Mesh(geometry, material);
            cube.position.x = i + originX;
            cube.position.z = j + originZ;
            scene.add(cube);
        }
    }
}

function generateTextCanvas(i, j){
    let x = document.createElement("canvas");
    let xc = x.getContext("2d");
    x.width = x.height = 128;
    xc.shadowColor = "#000";
    xc.shadowBlur = 7;
    xc.fillStyle = "white";
    xc.font = "30pt arial bold";
    xc.fillText(i + ',' + j, 10, 64);

    let xm = new MeshBasicMaterial({map: new Texture(x)});
    xm.map.needsUpdate = true;
    return xm;
}