// function used to implement hit detection
function distance(x1, x2, y1, y2) {
    let x_ = x1 - x2;
    let y_ = y1 - y2;
    return Math.sqrt(x_ * x_ + y_ * y_);
}

// destroy sprite function
function des(spr) {
    spr.destroy();
}



// boundary 236 to 545
const config = {
    type: Phaser.AUTO,
    width: 1200,
    height: 500,
    scene: {
        preload: preload,
        create: create
    }
};

let game = new Phaser.Game(config);

function preload() {
    this.load.image("Sprite0", "shark.png");
    this.load.image("Sprite1", "minnow.png");
    this.load.image('bg', 'back.png');
}

function create() {
    this.add.image(config.width / 2, config.height / 2, 'bg');

    this.shark = this.add.image(450, config.height / 2, 'Sprite0');
    this.minnow = this.add.image(50, config.height / 2, 'Sprite1');

    // first sprite

    this.input.keyboard.on('keydown_A', function event() {
        if (distance(this.minnow.x, this.shark.x, this.minnow.y, this.shark.y) < 50) {
            des(this.minnow);
        }
        if (this.shark.x > 236) {
            this.shark.x -= 8;
        }
    }, this);

    this.input.keyboard.on('keydown_S', function event() {
        if (distance(this.minnow.x, this.shark.x, this.minnow.y, this.shark.y) < 50) {
            des(this.minnow);
        }
        if (this.shark.y < 491) {
            this.shark.y += 8;
        }
    }, this);

    this.input.keyboard.on('keydown_D', function event() {
            if (distance(this.minnow.x, this.shark.x, this.minnow.y, this.shark.y) < 50) {
                des(this.minnow);
            }
            if (this.shark.x < 545) {
                this.shark.x += 8;
            }
        }, this);

    this.input.keyboard.on('keydown_W', function event() {
            if (distance(this.minnow.x, this.shark.x, this.minnow.y, this.shark.y) < 50) {
                des(this.minnow);
            }
            if (this.shark.y > 20) {
                this.shark.y -= 8;
            }
        }, this);

//    second sprite

    this.input.keyboard.on('keydown_LEFT', function event() {
            // hit detection event handler
            if (distance(this.minnow.x, this.shark.x, this.minnow.y, this.shark.y) < 50) {
                des(this.minnow);
            }
            if (this.minnow.x > 20) {
                this.minnow.x -= 10;
            }
        }, this);

    this.input.keyboard.on('keydown_DOWN', function event() {
            // hit detection event handler
            if (distance(this.minnow.x, this.shark.x, this.minnow.y, this.shark.y) < 50) {
                des(this.minnow);
            }
            if (this.minnow.y < 491) {
                this.minnow.y += 10;
            }
        }, this);

    this.input.keyboard.on('keydown_RIGHT', function event() {
            // hit detection event handler
            if (distance(this.minnow.x, this.shark.x, this.minnow.y, this.shark.y) < 50) {
                des(this.minnow);
            }
            if (this.minnow.x < 1199) {
                this.minnow.x += 10;
            }
        }, this);

    this.input.keyboard.on('keydown_UP', function event() {
            // hit detection event handler
            if (distance(this.minnow.x, this.shark.x, this.minnow.y, this.shark.y) < 50) {
                des(this.minnow);
            }
            if (this.minnow.y > 20) {
                this.minnow.y -= 10;
            }
        }, this);
}