var config = {
    type: Phaser.AUTO,
    width: 1200,
    height: 500,
    scene: {
        preload: preload,
        create: create
    }
};

var game = new Phaser.Game(config);

function preload(){
    this.load.image("Sprite0", "sprite0.png");
    this.load.image("Sprite1", "sprite1.png");
    this.load.image('bg', 'back.png');
}

function create() {
    this.add.image(0, 0, 'bg');
    this.add.image(600, 0, 'bg');
    this.add.image(800, 0, 'bg');

    this.image = this.add.image(100, 100, 'Sprite0');
    this.image1 = this.add.image(200, 200, 'Sprite1');

    this.input.keyboard.on('keydown_A', function event() {
        if(this.image.x > 20) {
            this.image.x -= 5;
        }
    },
        this);

    this.input.keyboard.on('keydown_S', function event() {
        if(this.image.y < 510) {
            this.image.y += 5;
        }
    },
        this);

    this.input.keyboard.on('keydown_D', function event() {
        if(this.image.x < 1210) {
            this.image.x += 5;
        }
    },
        this);

    this.input.keyboard.on('keydown_W', function event() {
        if(this.image.y > 20) {
            this.image.y -= 5;
        }
    },
        this);

//    second sprite

    this.input.keyboard.on('keydown_LEFT', function event() {
            if(this.image1.x > 20) {
                this.image1.x -= 10;
            }
        },
        this);

    this.input.keyboard.on('keydown_DOWN', function event() {
            if(this.image1.y < 510) {
                this.image1.y += 10;
            }
        },
        this);

    this.input.keyboard.on('keydown_RIGHT', function event() {
            if(this.image1.x < 1210) {
                this.image1.x += 10;
            }
        },
        this);

    this.input.keyboard.on('keydown_UP', function event() {
            if(this.image1.y > 20) {
                this.image1.y -= 10;
            }
        },
        this);
}