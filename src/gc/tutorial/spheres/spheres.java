package gc.tutorial.spheres;

import com.sun.javafx.scene.NodeHelper;
import com.sun.javafx.scene.SceneUtils;
import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class spheres extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage)
    {
        Sphere origin = new Sphere(10);
        Cylinder cylinderx = new Cylinder(10,100);
        Cylinder cylindery = new Cylinder(10,100);
        Cylinder cylinderz = new Cylinder(10,100);

        SmartGroup group = new SmartGroup();
        group.getChildren().add(origin);
        group.getChildren().add(cylinderx);
        group.getChildren().add(cylindery);
        group.getChildren().add(cylinderz);


        cylinderx.getTransforms().addAll(new Rotate(90, Rotate.Z_AXIS));
        cylindery.getTransforms().addAll(new Rotate(90, Rotate.Y_AXIS));
        cylinderz.getTransforms().addAll(new Rotate(90, Rotate.X_AXIS));

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.YELLOW);
        scene.setCamera(camera);

        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);
        group.translateZProperty().set(-300);

        cylinderx.translateXProperty().set(cylinderx.translateXProperty().get() + 50);
        cylindery.translateYProperty().set(cylindery.translateYProperty().get() + 50);
        cylinderz.translateZProperty().set(cylinderz.translateZProperty().get() + 50);



        PhongMaterial materiaRed = new PhongMaterial();
        materiaRed.setSpecularColor(Color.RED);
        materiaRed.setDiffuseColor(Color.RED);

        PhongMaterial materiaBlue = new PhongMaterial();
        materiaBlue.setSpecularColor(Color.BLUE);
        materiaBlue.setDiffuseColor(Color.BLUE);

        PhongMaterial materiaGreen = new PhongMaterial();
        materiaGreen.setSpecularColor(Color.GREEN);
        materiaGreen.setDiffuseColor(Color.GREEN);

        cylinderx.setMaterial(materiaRed);
        cylindery.setMaterial(materiaGreen);
        cylinderz.setMaterial(materiaBlue);

        Translate pivot = new Translate();
        Rotate rotateZMinus= new Rotate(-10,Rotate.X_AXIS);
        Rotate rotateZPlus= new Rotate(+10,Rotate.X_AXIS);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case S:
                    camera.translateZProperty().set(camera.getTranslateZ() - 100);
                    break;
                case Z:
                    camera.translateZProperty().set(camera.getTranslateZ() + 100);
                    break;
                case Q:
                    camera.translateXProperty().set(camera.getTranslateX() - 100);
                    break;
                case D:
                    camera.translateXProperty().set(camera.getTranslateX() + 100);
                    break;
                case NUMPAD8:
                    camera.translateYProperty().set(camera.getTranslateY() - 100);
                    break;
                case NUMPAD2:
                    camera.translateYProperty().set(camera.getTranslateY() + 100);
                    break;
                case NUMPAD9:
                    camera.getTransforms().addAll(
                            pivot,
                            rotateZPlus,
                            new Rotate(+50, Rotate.X_AXIS),
                            new Translate(0, 0, +50)
                    );
                    break;
                case NUMPAD1:
                    camera.getTransforms().addAll(
                            pivot,
                            rotateZMinus,
                            new Rotate(-50, Rotate.X_AXIS),
                            new Translate(0, 0, -50)
                    );
                    break;
            }
        });

        primaryStage.setTitle("Michael window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    class SmartGroup extends Group
    {
        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang)
        {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotateByY(int ang)
        {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
    }
}

class toSave
{
    public double[] create_model(int nb_inputs)
    {
        double[] tab= new double[nb_inputs+1];
        for (Object o : tab)
        {

        }
        return tab;
    }
}

