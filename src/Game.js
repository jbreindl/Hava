var config = {
    type: Phaser.AUTO,
    width: 1400,
    height: 600,
    physics: {
        default: 'arcade',
        arcade: { debug: false }
    },
    scene: {
        preload: preload,
        create: create
    }
};

new Phaser.Game(config);

function preload ()
{
    this.load.setBaseURL('http://labs.phaser.io');
    this.load.image('chick', 'assets/sprites/chick.png');// possible fast class
    this.load.image('clown', 'assets/sprites/clown.png');// possible sniper class
    this.load.image('wasp', 'assets/sprites/wasp.png');// possible heavy class
    this.load.image('cursor', 'assets/sprites/drawcursor.png');
    this.load.image('bg', 'assets/skies/fog.png');
}

function create ()
{
    this.add.image(400, 300, 'bg');
    this.add.image(1000, 300, 'bg');// load background twice to cover entire width of map
    var chicks = this.physics.add.group({key: 'chick', frameQuantity: 3, setXY: { x: 500, y: 400, stepX: 50 }});
    var clown = this.physics.add.image(100, 300, 'clown').setVisible(true);
    var wasp = this.physics.add.image(800, 100, 'wasp').setVisible(true);
    var cursor = this.add.image(0, 0, 'cursor').setVisible(false);

    this.input.on('pointermove', function (pointer)
    {
        cursor.setVisible(false).setPosition(pointer.x, pointer.y);

        this.physics.moveToObject(clown, pointer, 40);// last parameter represents speed of class
        this.physics.moveToObject(wasp, pointer, 20);// heavy class is slowest

        Phaser.Utils.Array.Each(          //this moves more than one sprite, possible
                                            // fast class could have multiple elements
            chicks.getChildren(),
            this.physics.moveToObject,
            this.physics,
            pointer, 75);
    }, this);

}