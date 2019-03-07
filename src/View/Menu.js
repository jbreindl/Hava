var config = {
    type: Phaser.AUTO,
    width: 600,
    height: 600,
    scene: {
        preload: preload,
        create: create
    }
};

var game = new Phaser.Game(config);

function preload ()
{
    this.load.image('background', 'images/background.png');
    this.load.image('button', 'images/play.png');
    this.load.image('logo', 'images/logo.png');
}

function create ()
{
    this.add.image(300, 300, 'background');
    this.add.image(300, 300, 'logo');
}