var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);
var canvas = document.getElementById("PlayGround");
var context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';
const sizeOfGridSquares = 30;

function parseGameState(event) {
    // console.log(event);
    const gameState = JSON.parse(event);

    drawGameBoard(gameState['gridSize']);

    placeSquare(gameState['minnowStart']['x'], gameState['minnowStart']['y'], '#000000');
    placeSquare(gameState['sharkStart']['x'], gameState['sharkStart']['y'], '#000000');



    for (let minnow of gameState['minnows']) {
        //this makes the minnow image
        placeMinnow(minnow['x'], minnow['y'], minnow['id'] === socket.id ? '#00ffff' : '#ff00ff')
        placeCircle(minnow['x'], minnow['y'], minnow['id'] === socket.id ? '#00ffff' : '#ff00ff', 2.0);
    }

    for (let shark of gameState['sharks']) {
        placeSquare(shark['x'], shark['y'], shark['id'] === socket.id ? '#0000ff' : '#ff0000', 2.0);
    }

    for (let wall of gameState['sharkWalls']) {
        placeSquare(wall['x'], wall['y'], 'grey');
    }

}

function drawGameBoard(gridSize) {

    const gridWidth = gridSize['x'];
    const gridHeight = gridSize['y'];

    context.clearRect(0, 0, gridWidth * sizeOfGridSquares, gridHeight * sizeOfGridSquares);

    canvas.setAttribute("width", gridWidth * sizeOfGridSquares);
    canvas.setAttribute("height", gridHeight * sizeOfGridSquares);

    context.strokeStyle = '#ff5500';
    context.fillStyle = '#0099ff'
    for (let j = 0; j <= gridWidth; j++) {
        context.beginPath();
        context.moveTo(j * sizeOfGridSquares, 0);
        context.lineTo(j * sizeOfGridSquares, gridHeight * sizeOfGridSquares);
        context.stroke();
    }
    for (let k = 0; k <= gridHeight; k++) {
        context.beginPath();
        context.moveTo(0, k * sizeOfGridSquares);
        context.lineTo(gridWidth * sizeOfGridSquares, k * sizeOfGridSquares);
        context.stroke();
    }

}


function placeSquare(x, y, color) {
    context.fillStyle = color;
    context.fillRect(x * sizeOfGridSquares, y * sizeOfGridSquares, sizeOfGridSquares, sizeOfGridSquares);
    context.strokeStyle = 'black';
    context.strokeRect(x * sizeOfGridSquares, y * sizeOfGridSquares, sizeOfGridSquares, sizeOfGridSquares);
}


function placeCircle(x, y, color, size) {
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

function placeMinnow(x, y, color){
    var img = new Image()
    img.src = "logo.png"
    context.drawImage(x, y)
}

function xComp(degrees){
    return Math.cos(Math.PI*degrees/180.0)
}

function yComp(degrees){
    return Math.sin(Math.PI*degrees/180.0)
}

