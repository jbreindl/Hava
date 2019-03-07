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
    this.load.image('background', 'View/images/background.png');
    this.load.image('button', 'View/images/play.png');
    this.load.image('logo', 'View/images/logo.png');

}

function create ()
{
    this.add.image(300, 300, 'background');
    this.add.image(300, 300, 'logo');
    this.add.image(300,450, 'button')
    const button = (this, 300, 450, 'button', hello(), this);
    button.setInteractive()
    this.input.on('pointerdown', hello(), this)

}

function hello (){
    console.log("hello")
}