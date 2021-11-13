/*********************************
 *  CAMBIER ULYSSE @ENSEA 2021  *
 * ******************************
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application{
    int step = 0;
    public void start(Stage primaryStage){
        primaryStage.setTitle("Demo");
        Group root = new Group();
        Camera MainCamera = new Camera(10,10);
        GameScene scene = new GameScene(root,MainCamera);
        primaryStage.setScene(scene);
        primaryStage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                scene.getHeros().update(time,MainCamera,scene);
                MainCamera.update(time,scene.getHeros());
                scene.update(time,MainCamera);
                for (Enemi enemi : scene.getEnemisArray()) {
                    enemi.update(time, MainCamera,scene);
                }
                scene.enemiSpwaner(time,root,MainCamera);
            }
        };
        scene.setOnMouseClicked( (event)->{
            if(step==1) {
                scene.getHeros().jump();
            } else if(step==0){
                step = 1;
                scene.HideStartScreen();
                timer.start();
            }
        });
        scene.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode().equals(KeyCode.SPACE)){
                scene.getHeros().superspeed();
            }
        });
    }
    public static void main(String[] args) {
        launch(args);

    }
}
