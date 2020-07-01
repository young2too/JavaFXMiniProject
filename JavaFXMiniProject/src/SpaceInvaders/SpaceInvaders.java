package SpaceInvaders;

// Icon made by Freepik from www.flaticon.com
// visit: https://www.youtube.com/user/CbX397/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SpaceInvaders extends Application {

   // variables
   private static final Random RAND = new Random();
   private static final int WIDTH = 800;
   private static final int HEIGHT = 600;
   private static final int PLAYER_SIZE = 60;
   static final Image PLAYER_IMG = new Image("/img/player.png");
   static final Image EXPLOSION_IMG = new Image("/img/explosion.png");
   static final int EXPLOSION_W = 128;
   static final int EXPLOSION_ROWS = 3;
   static final int EXPLOSION_COL = 3;
   static final int EXPLOSION_H = 128;
   static final int EXPLOSION_STEPS = 15;

   CommonService comSrv = new CommonServiceImpl();

   static final Image BOMBS_IMG[] = { new Image("/img/1.png"), new Image("/img/2.png"), new Image("/img/3.png"),
         new Image("/img/4.png"), new Image("/img/5.png"), new Image("/img/6.png"), new Image("/img/7.png"),
         new Image("/img/8.png"), new Image("/img/9.png"), new Image("/img/10.png"), };

   final int MAX_BOMBS = 10, MAX_SHOTS = MAX_BOMBS * 2;
   boolean gameOver = false;
   private GraphicsContext gc;

   Rocket player;
   List<Shot> shots;
   List<Universe> univ;
   List<Bomb> Bombs;

   private double mouseX;
   private int score;
   Timeline timeline;
   boolean pauseFlag = false;

   public void pause() {
      if (pauseFlag == false) {
         pauseFlag = true;
         for (int i = Bombs.size() - 1; i >= 0; i--) {
            Bombs.get(i).pauseGame();
         }
         for(int i=0;i<shots.size();i++) {
            shots.get(i).pauseGame();
         }
      } else {
         pauseFlag = false;
         for (int i = Bombs.size() - 1; i >= 0; i--) {
            Bombs.get(i).resumeGame();
         }
         for(int i=0;i<shots.size();i++) {
            shots.get(i).resumeGame();
         }
      }
   }

   // start
   public void start(Stage stage) throws Exception {
      Canvas canvas = new Canvas(WIDTH, HEIGHT);
      canvas.setFocusTraversable(true);
      canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
         @Override
         public void handle(KeyEvent event) {
            System.out.println(event.getCode());
            // TODO Auto-generated method stub
            if (event.getCode() == KeyCode.SPACE) {
               pause();
            }
         }

      });
      gc = canvas.getGraphicsContext2D();

      timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.play();
      canvas.setCursor(Cursor.MOVE);
      canvas.setOnMouseMoved(e -> {
         if(pauseFlag==false)
            mouseX = e.getX();
      });
      canvas.setOnMouseClicked(e -> {
         if (shots.size() < MAX_SHOTS && pauseFlag == false)
            shots.add(player.shoot());
         if (gameOver) {
            gameOver = false;
            setup();
         }
      });
      setup();
      stage.setScene(new Scene(new StackPane(canvas)));
      stage.setTitle("Space Invaders");
      stage.show();

   }

   // setup the game
   private void setup() {
      univ = new ArrayList<>();
      shots = new ArrayList<>();
      Bombs = new ArrayList<>();
      player = new Rocket(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
      score = 0;
      IntStream.range(0, MAX_BOMBS).mapToObj(i -> this.newBomb()).forEach(Bombs::add);
   }

   // run Graphics
   private void run(GraphicsContext gc) {
	   	gc.setFill(Color.grayRgb(20));
	      gc.fillRect(0, 0, WIDTH, HEIGHT);
	      gc.setTextAlign(TextAlignment.CENTER);
	      gc.setFont(Font.font(20));
	      gc.setFill(Color.WHITE);
	      gc.fillText("Score: " + score, 60,  20);
	      gc.fillText("일시정지 : SPACE", 100, 40);

      if(gameOver) {
    	  DataBaseService dbSrv = new DataBaseServiceImpl();
			timeline.stop();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("폭파당했습니다");
			alert.setContentText("나의 점수 : " + score);
			dbSrv.updateScore(score);
			alert.setOnCloseRequest(e -> {
				comSrv.WindowClose(gc.getCanvas().getParent());
			});
			alert.show();
			return;
		}
      univ.forEach(Universe::draw);

      player.update();
      player.draw();
      player.posX = (int) mouseX;

      Bombs.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
         if (player.colide(e) && !player.exploding) {
            player.explode();
         }
      });

      for (int i = shots.size() - 1; i >= 0; i--) {
         Shot shot = shots.get(i);
         if (shot.posY < 0 || shot.toRemove) {
            shots.remove(i);
            continue;
         }
         shot.update();
         shot.draw();
         for (Bomb bomb : Bombs) {
            if (shot.colide(bomb) && !bomb.exploding) {
               score++;
               bomb.explode();
               shot.toRemove = true;
            }
         }
      }

      for (int i = Bombs.size() - 1; i >= 0; i--) {
         if (Bombs.get(i).destroyed && pauseFlag == false) {
            Bombs.set(i, newBomb());
         }
      }

      gameOver = player.destroyed;
      if (RAND.nextInt(10) > 2) {
         univ.add(new Universe());
      }
      for (int i = 0; i < univ.size(); i++) {
         if (univ.get(i).posY > HEIGHT)
            univ.remove(i);
      }
   }

   // player
   public class Rocket {

      int posX, posY, size;
      boolean exploding, destroyed;
      Image img;
      int explosionStep = 0;

      public Rocket(int posX, int posY, int size, Image image) {
         this.posX = posX;
         this.posY = posY;
         this.size = size;
         img = image;
      }

      public Shot shoot() {
         return new Shot(posX + size / 2 - Shot.size / 2, posY - Shot.size);
      }

      public void update() {
         if (exploding)
            explosionStep++;
         destroyed = explosionStep > EXPLOSION_STEPS;
      }

      public void draw() {
         if (exploding) {
            gc.drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W,
                  (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1, EXPLOSION_W, EXPLOSION_H, posX, posY, size,
                  size);
         } else {
            gc.drawImage(img, posX, posY, size, size);
         }
      }

      public boolean colide(Rocket other) {
         int d = distance(this.posX + size / 2, this.posY + size / 2, other.posX + other.size / 2,
               other.posY + other.size / 2);
         return d < other.size / 2 + this.size / 2;
      }

      public void explode() {
         exploding = true;
         explosionStep = -1;
      }

   }

   // computer player
   public class Bomb extends Rocket {

      int SPEED = (score) + 20;
      int savedSpeed = 0;
      
      public Bomb(int posX, int posY, int size, Image image) {
         super(posX, posY, size, image);
      }

      public void update() {
         super.update();
         if (!exploding && !destroyed)
            posY += SPEED;
         if (posY > HEIGHT)
            destroyed = true;
      }

      public void pauseGame() {

         savedSpeed = SPEED;
         SPEED = 0;
      }
      public void resumeGame() {
         SPEED = savedSpeed;
      }
   }

   // bullets
   public class Shot {

      public boolean toRemove;

      int posX, posY, speed = 10;
      static final int size = 6;

      public Shot(int posX, int posY) {
         this.posX = posX;
         this.posY = posY;
      }

      public void pauseGame() {
         // TODO Auto-generated method stub
         speed = 0;
      }

      public void resumeGame() {
         // TODO Auto-generated method stub
         speed = 10;
      }

      public void update() {
         posY -= speed;
      }

      public void draw() {
         gc.setFill(Color.RED);
         if (score >= 50 && score <= 70 || score >= 120) {
            gc.setFill(Color.YELLOWGREEN);
            speed = 50;
            gc.fillRect(posX - 5, posY - 10, size + 10, size + 30);
         } else {
            gc.fillOval(posX, posY, size, size);
         }
      }

      public boolean colide(Rocket Rocket) {
         int distance = distance(this.posX + size / 2, this.posY + size / 2, Rocket.posX + Rocket.size / 2,
               Rocket.posY + Rocket.size / 2);
         return distance < Rocket.size / 2 + size / 2;
      }

   }

   // environment
   public class Universe {
      int posX, posY;
      private int h, w, r, g, b;
      private double opacity;

      public Universe() {
         posX = RAND.nextInt(WIDTH);
         posY = 0;
         w = RAND.nextInt(5) + 1;
         h = RAND.nextInt(5) + 1;
         r = RAND.nextInt(100) + 150;
         g = RAND.nextInt(100) + 150;
         b = RAND.nextInt(100) + 150;
         opacity = RAND.nextFloat();
         if (opacity < 0)
            opacity *= -1;
         if (opacity > 0.5)
            opacity = 0.5;
      }

      public void draw() {
         if (opacity > 0.8)
            opacity -= 0.01;
         if (opacity < 0.1)
            opacity += 0.01;
         gc.setFill(Color.rgb(r, g, b, opacity));
         gc.fillOval(posX, posY, w, h);
         posY += 20;
      }
   }

   Bomb newBomb() {
      return new Bomb(50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)]);
   }

   int distance(int x1, int y1, int x2, int y2) {
      return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
   }
}