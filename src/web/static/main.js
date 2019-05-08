const electron = require('electron');
const { app, BrowserWindow } = electron;

let mainWindow;

app.on('ready', () => {
    mainWindow = new BrowserWindow({
        width: 1000,
        height: 700
    });

    mainWindow.setTitle('Hava: Nice Day');
    mainWindow.loadURL('http://localhost:8080');

    mainWindow.on('closed', () => {
        mainWindow = null;
    });
});