export function saveCameraLocation(camera) {

    localStorage.setItem("camera.position.x", camera.position.x);
    localStorage.setItem("camera.position.y", camera.position.y);
    localStorage.setItem("camera.position.z", camera.position.z);

    localStorage.setItem("camera.rotation.x", camera.rotation.x);
    localStorage.setItem("camera.rotation.y", camera.rotation.y);
    localStorage.setItem("camera.rotation.z", camera.rotation.z);
}

export function loadCameraLocation(camera){

    camera.position.x = parseFloat(localStorage.getItem("camera.position.x"));
    camera.position.y = parseFloat(localStorage.getItem("camera.position.y"));
    camera.position.z = parseFloat(localStorage.getItem("camera.position.z"));

    camera.rotation.x = parseFloat(localStorage.getItem("camera.rotation.x"));
    camera.rotation.y = parseFloat(localStorage.getItem("camera.rotation.y"));
    camera.rotation.z = parseFloat(localStorage.getItem("camera.rotation.z"));

    camera.updateProjectionMatrix();
}