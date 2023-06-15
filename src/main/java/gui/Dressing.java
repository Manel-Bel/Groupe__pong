package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class Dressing {
    private ScrollPane root;
    private GridPane rootInterne;
    // children of the game main node
    private Stage sg;
    private Scene sc;// pour créer la scène des paramètres qaund on switch dessus
    private ImageView bg;
    private Color cBall, cRaquette1, cRaquette2, cRaquette3, cRaquette4;
    private ComboBox<Integer> choix;
    Menu menu;

    public Dressing(Stage sg, Menu me, double scale) {
        this.menu = me;
        this.sg = sg;
        root = new ScrollPane();
        root.setMinWidth(1000 * scale + 2 * 50);
        root.setMinHeight(600 * scale);
        root.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        root.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        cBall = Color.WHITE;
        cRaquette1 = Color.WHITE;
        cRaquette2 = Color.WHITE;
        cRaquette3 = Color.WHITE;
        cRaquette4 = Color.WHITE;
        // les titres ;
        Label ballT = new Label("Balle");
        Label bgT = new Label("Background");
        Label raquetT = new Label("Raquette");
        // choix raquette
        choix = new ComboBox<Integer>();
        choix.setId("comboBox");
        choix.setItems(FXCollections.observableArrayList(1, 2, 3, 4));
        choix.getSelectionModel().selectFirst();

        Button op0b = new Button("OPTION 0");
        op0b.setStyle("-fx-background-color: white");
        op0b.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                cBall = Color.WHITE;
            }
        });

        Button op1b = new Button("OPTION 1");
        op1b.setStyle("-fx-background-color: blue");
        op1b.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                cBall = Color.BLUE;
            }
        });

        Button op2b = new Button("OPTION 2");
        op2b.setStyle("-fx-background-color: green");
        op2b.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                cBall = Color.GREEN;
            }
        });
        Button op3b = new Button("OPTION 3");
        op3b.setStyle("-fx-background-color: red");
        op3b.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                cBall = Color.RED;
            }
        });

        // option raquette couleur
        Button op0R = new Button("OPTION 0");
        op0R.setStyle("-fx-background-color: white");
        op0R.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                switch (choix.getValue()) {
                    case 1:
                        cRaquette1 = Color.WHITE;
                        break;
                    case 2:
                        cRaquette2 = Color.WHITE;
                        break;
                    case 3:
                        cRaquette3 = Color.WHITE;
                        break;
                    case 4:
                        cRaquette4 = Color.WHITE;
                        break;
                    default:
                        break;
                }
            }
        });
        Button op1R = new Button("OPTION 1");
        op1R.setStyle("-fx-background-color: blue");
        op1R.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                switch (choix.getValue()) {
                    case 1:
                        cRaquette1 = Color.BLUE;
                        break;
                    case 2:
                        cRaquette2 = Color.BLUE;
                        break;
                    case 3:
                        cRaquette3 = Color.BLUE;
                        break;
                    case 4:
                        cRaquette4 = Color.BLUE;
                        break;
                    default:
                        break;
                }

            }
        });

        Button op2R = new Button("OPTION 2");
        op2R.setStyle("-fx-background-color: green");
        op2R.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                switch (choix.getValue()) {
                    case 1:
                        cRaquette1 = Color.GREEN;
                        break;
                    case 2:
                        cRaquette2 = Color.GREEN;
                        break;
                    case 3:
                        cRaquette3 = Color.GREEN;
                        break;
                    case 4:
                        cRaquette4 = Color.GREEN;
                        break;
                    default:
                        break;
                }
            }
        });

        Button op3R = new Button("OPTION 3");
        op3R.setStyle("-fx-background-color: red");
        op3R.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                switch (choix.getValue()) {
                    case 1:
                        cRaquette1 = Color.RED;
                        break;
                    case 2:
                        cRaquette2 = Color.RED;
                        break;
                    case 3:
                        cRaquette3 = Color.RED;
                        break;
                    case 4:
                        cRaquette4 = Color.RED;
                        break;
                    default:
                        break;
                }
            }
        });

        // option Backgound couleur
        Button op0Bg = new Button();
        op0Bg.setId("buttonImage");
        op0Bg.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                bg = null;
            }
        });

        Button op1Bg = new Button();
        op1Bg.setId("buttonImage");
        Image i1 = new Image(getClass().getResource("/image/bg1.jpg").toExternalForm());
        ImageView view1 = new ImageView(i1);
        view1.setFitWidth(200);
        view1.setFitHeight(155);
        op1Bg.setGraphic(view1);
        op1Bg.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                bg = view1;
            }
        });

        Button op2Bg = new Button();
        op2Bg.setId("buttonImage");
        Image i2 = new Image(getClass().getResource("/image/bg2.png").toExternalForm());
        ImageView view2 = new ImageView(i2);
        view2.setFitWidth(200);
        view2.setFitHeight(155);
        op2Bg.setGraphic(view2);
        op2Bg.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                bg = view2;
            }
        });
        Button op3Bg = new Button();
        op3Bg.setId("buttonImage");
        Image i3 = new Image(getClass().getResource("/image/bg6.jpg").toExternalForm());
        ImageView view3 = new ImageView(i3);
        view3.setFitWidth(200);
        view3.setFitHeight(160);
        op3Bg.setGraphic(view3);
        op3Bg.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                bg = view3;
            }
        });

        Button retour = new Button();
        retour.setId("btnRetour");
        Image i = new Image(getClass().getResource("/image/back.png").toExternalForm());
        ImageView view = new ImageView(i);
        view.setFitHeight(25);
        view.setFitWidth(25);
        retour.setGraphic(view);
        retour.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == retour) {
                    Dressing.this.sg.setScene(menu.getScene());
                }
            }
        });

        HBox h = new HBox(retour);

        // view de model interne
        rootInterne = new GridPane();
        rootInterne.setId("rootI");
        rootInterne.setMinWidth(1000 * scale + 2 * 50);
        rootInterne.setMinHeight(600 * scale);
        rootInterne.setPadding(new Insets(5));
        rootInterne.setVgap(10);
        rootInterne.setHgap(10);

        rootInterne.add(h, 0, 0);
        rootInterne.add(ballT, 0, 1);
        rootInterne.add(op0b, 0, 2);
        rootInterne.add(op1b, 1, 2);
        rootInterne.add(op2b, 2, 2);
        rootInterne.add(op3b, 3, 2);

        rootInterne.add(raquetT, 0, 3);
        rootInterne.add(choix, 0, 4);
        rootInterne.add(op1R, 1, 4);
        rootInterne.add(op2R, 2, 4);
        rootInterne.add(op3R, 3, 4);

        rootInterne.add(bgT, 0, 5);
        rootInterne.add(op0Bg, 0, 6);
        rootInterne.add(op1Bg, 1, 6);
        rootInterne.add(op2Bg, 2, 6);
        rootInterne.add(op3Bg, 3, 6);

        root.setContent(rootInterne);
        this.sc = new Scene(root);
        sc.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
        this.sg.setScene(sc);

    }

    public ImageView getBg() {
        return this.bg;
    }

    public Color getCB() {
        return this.cBall;
    }

    public Color getCR1() {
        return this.cRaquette1;
    }

    public Color getCR2() {
        return this.cRaquette2;
    }

    public Color getCR3() {
        return this.cRaquette3;
    }

    public Color getCR4() {
        return this.cRaquette4;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Scene getScene() {
        return this.sc;
    }

}
