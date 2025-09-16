import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.Random;

class newDinoRunnerClone extends JFrame implements KeyListener{
    public static boolean running = true;
    public static String black = "\u001B[47m ";
    public static String white = "\u001B[100m ";
    public static int width = 36;
    public static int height = 20;
    public static String[] pixels = new String[width * height];
    public static boolean upKey;
    public static int y = -16;
    public static int yv = 0;
    public static int walkTimer = 0;
    public static int cactusX = 40;
    public static int cactusTimer = 0;
    public static int cactus2X = 70;
    public static int cactus2Timer = 0;
    public static Random random = new Random();
    public static int score = 0;

    //sprites
    public static int[][] dino1 = {{1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                                   {1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                                   {1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                                   {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                                   {1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1},
                                   {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                                   {0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                                   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                   {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                   {1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1},
                                   {1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1},
                                   {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1}};

    public static int[][] dino2 = {{1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                                   {1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                                   {1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                                   {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                                   {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                                   {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                   {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                   {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                   {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                   {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                   {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                   {1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1}};

    public static int[][] cactus = {{1, 1, 0, 0, 0, 0, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 1, 1},};
    
    //JFrame setup
    public newDinoRunnerClone(){
        this.setTitle("new dino runner clone");
        this.setSize(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        gameLoop();
    }

    static void gameLoop(){
        while (running){
            System.out.print("\033[H\033[2J");
            System.out.flush();

            //player
            yv--;
            y += yv;
            if (y < -16){
                y = -16;
                yv = 0;
                if (upKey){
                    yv = 5;
                }
            }

            //cactus 1
            if (cactusX < -3){
                cactusTimer += random.nextInt(1, 3)+1;
                if (cactusTimer > 15){
                    cactusX = 32;
                    cactusTimer = 0;
                }
            }
            else{
                cactusX -= 2;
                if (cactusX + 3 >= 1 && cactusX <= 7 && y <= -13){
                    running = false;
                }
            }

            //cactus 2
            if (cactus2X < -3){
                cactus2Timer += random.nextInt(1, 3)+1;
                if (cactus2Timer > 15){
                    cactus2X = 32;
                    cactus2Timer = 0;
                }
            }
            else{
                cactus2X -= 2;
                if (cactus2X + 3 >= 1 && cactus2X <= 7 && y <= -13){
                    running = false;
                }
            }

            score++;

            walkTimer++;
            if (walkTimer == 2){
                walkTimer = 0;
            }
            
            //render
            System.out.println("Score: " + score);
            
            for (int i = 0; i < pixels.length; i++){
                pixels[i] = white;
            }

            rect(0, -19, 36, 3);
            if (walkTimer == 0){
                drawSprite(dino1, -2, y);
            }
            else{
                drawSprite(dino2, -2, y);
            }
            drawSprite(cactus, cactusX - 2, -16);
            drawSprite(cactus, cactus2X - 2, -16);

            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    System.out.print(pixels[i * width + j]);
                }
                System.out.println("\u001B[0m");
            }

            try{
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    
    public static void main(String[] args){
        new newDinoRunnerClone();
    }

    static void setPixel(int x, int y){
        if (x >= 0 && x <= width-1 && y <= 0 && y >= (height-1) * -1){
            pixels[Math.abs(y) * width + x] = black;
        }
    }

    static void rect(int x, int y, int width, int height){
        for (int i = x; i < x + width; i++){
            for (int j = y; j < y + height; j++){
                setPixel(i, j);
            }
        }
    }

    static void drawSprite(int[][] sprite, int x, int y){
        for (int i = y; i < y + sprite.length; i++){
            for (int j = x; j < x + sprite[0].length; j++){
                switch (sprite[Math.abs(i-(y+(sprite.length-1)))][j-x]){
                    case 0: setPixel(j, i);
                }
            }
        }
    }
    
    public void keyTyped(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == 38){
            upKey = true;
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == 38){
            upKey = false;
        }
    }
}