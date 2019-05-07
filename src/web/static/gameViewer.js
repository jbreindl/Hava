var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

const tileSize = 30;

var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';

function parseGameState(event) {
    // console.log(event);
    const gameState = JSON.parse(event);

    drawGameBoard(gameState['gridSize']);

    placeSquare(gameState['minnowStart']['x'], gameState['minnowStart']['y'], '#000000');
    placeSquare(gameState['sharkStart']['x'], gameState['sharkStart']['y'], '#000000');



    for (let minnow of gameState['minnows']) {
        placeCircle(minnow['x'], minnow['y'], minnow['id'] === socket.id ? '#00ffff' : '#ff00ff', 2.0);
    }

    for (let shark of gameState['sharks']) {
        placeSquare(shark['x'], shark['y'], shark['id'] === socket.id ? '#0000ff' : '#ff0000', 2.0);
    }

    for (let wall of gameState['sharkWalls']) {
        placeSquare(wall['x'], wall['y'], 'grey');
    }

}

function cleanInt(input) {
    const value = Math.round(input);
    const asString = value.toString(16);
    return value > 15 ? asString : "0" + asString;
}

function rgb(r, g, b) {
    return "#" + cleanInt(r) + cleanInt(g) + cleanInt(b);
}
//fix


function drawGameBoard(gridSize) {

    const gridWidth = gridSize['x'];
    const gridHeight = gridSize['y'];

    context.clearRect(0, 0, gridWidth * tileSize, gridHeight * tileSize);

    canvas.setAttribute("width", gridWidth * tileSize);
    canvas.setAttribute("height", gridHeight * tileSize);

    context.strokeStyle = '#ff5500';
    context.fillStyle = '#0099ff'
    for (let j = 0; j <= gridWidth; j++) {
        context.beginPath();
        context.moveTo(j * tileSize, 0);
        context.lineTo(j * tileSize, gridHeight * tileSize);
        context.stroke();
    }
    for (let k = 0; k <= gridHeight; k++) {
        context.beginPath();
        context.moveTo(0, k * tileSize);
        context.lineTo(gridWidth * tileSize, k * tileSize);
        context.stroke();
    }

}


function placeSquare(x, y, color) {
    context.fillStyle = color;
    context.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    context.strokeStyle = 'black';
    context.strokeRect(x * tileSize, y * tileSize, tileSize, tileSize);
}


function placeCircle(x, y, color, size) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x * tileSize,
        y * tileSize,
        size / 10.0 * tileSize,
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

