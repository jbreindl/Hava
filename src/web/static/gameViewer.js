let socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);
let canvas = document.getElementById("PlayGround");
let context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';
const sizeOfGridSquares = 30;

function parseGameState(event) {
    const gameState = JSON.parse(event);

    drawGameBoard(gameState['gridSize']);
    addSpawn(gameState['minnowStart']['x'], gameState['minnowStart']['y'], '#000000');
    addSpawn(gameState['sharkStart']['x'], gameState['sharkStart']['y'], '#000000');



    for (let minnow of gameState['minnows']) {
        //this makes the minnow image
        // placeMinnow(minnow['x'], minnow['y'], minnow['id'] === socket.id ? '#00ffff' : '#ff00ff')
        addMinnow(minnow['x'], minnow['y'], minnow['id'] === socket.id ? '#00ffff' : '#ff00ff', 2.0);
    }

    for (let shark of gameState['sharks']) {
        addShark(shark['x'], shark['y'], shark['id'] === socket.id ? '#0000ff' : '#ff0000', 2.0);
    }

    for (let wall of gameState['sharkWalls']) {
        addShark(wall['x'], wall['y'], 'grey');
    }

}

function drawGameBoard(gridSize) {

    const gridWidth = gridSize['x'];
    const gridHeight = gridSize['y'];

    context.clearRect(0, 0, gridWidth * sizeOfGridSquares, gridHeight * sizeOfGridSquares);

    canvas.setAttribute("width", gridWidth * sizeOfGridSquares);
    canvas.setAttribute("height", gridHeight * sizeOfGridSquares);

    context.fillStyle = '#66ffff';
    context.fillRect(0, 0, gridWidth * sizeOfGridSquares, gridHeight * sizeOfGridSquares);

    context.fillStyle = '#00ff7f';
    context.fillRect(gridWidth * sizeOfGridSquares - sizeOfGridSquares, 0, gridWidth * sizeOfGridSquares, gridHeight * sizeOfGridSquares);

    context.strokeStyle = '#000000';
    context.beginPath();
    context.moveTo(4 * sizeOfGridSquares, 0);
    context.lineTo(4 * sizeOfGridSquares, gridHeight * sizeOfGridSquares);
    context.stroke();
}


function addShark(x, y, color) {
    context.fillStyle = color;
    context.fillRect(x * sizeOfGridSquares, y * sizeOfGridSquares, sizeOfGridSquares, sizeOfGridSquares);
    context.strokeStyle = 'black';
    context.strokeRect(x * sizeOfGridSquares, y * sizeOfGridSquares, sizeOfGridSquares, sizeOfGridSquares);
}

function addSpawn(x, y) {
    context.fillStyle = '#000000';
    context.fillRect(x * sizeOfGridSquares, y * sizeOfGridSquares, sizeOfGridSquares, sizeOfGridSquares);
    context.strokeStyle = 'black';
    context.strokeRect(x * sizeOfGridSquares, y * sizeOfGridSquares, sizeOfGridSquares, sizeOfGridSquares);
}


function addMinnow(x, y, color, size) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x * sizeOfGridSquares,
        y * sizeOfGridSquares,
        size / 10.0 * sizeOfGridSquares,
        0,
        2 * Math.PI);
    context.fill();
    context.strokeStyle = 'black';
    context.stroke();
}

// function placeMinnow(x, y, color){
//     var img = new Image()
//     img.src = "logo.png"
//     context.drawImage(x, y)
// }


