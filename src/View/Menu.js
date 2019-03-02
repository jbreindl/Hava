var config = {
    type: Phaser.AUTO,
    width: 600,
    height: 600,
    physics: {
        default: 'arcade',
        arcade: {
            gravity: { y: 200 }
        }
    },
    scene: {
        preload: preload,
        create: create
    }
};

var game = new Phaser.Game(config);

function preload ()
{
    this.load.image('background', 'src/View/images/background.png');
    this.load.image('button', 'src/View/images/play.png')
    this.load.image('logo', 'src/View/images/logo.png')
}

function create ()
{
    this.add.image(300, 300, 'background');
    this.add.image(300, 300, 'logo')

}