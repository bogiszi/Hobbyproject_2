package com.jetbrains.ideatest2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;


public class Main extends Application {

    private Stage stage;


    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        Scene scene = firstScene();
        stage.setTitle("SUDOKU");

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public Scene firstScene() {
        VBox root = new VBox(5);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Label label = new Label("Milyen nehéz SUDOKU-t szeretnél játszani?");
        label.setStyle("-fx-font-size: 20px;");

        Button okButton = new Button("OK");
        okButton.setPrefSize(60, 40);
        RadioButton radio1, radio2, radio3;
        radio1 = new RadioButton("könnyű");
        radio2 = new RadioButton("közepes");
        radio3 = new RadioButton("nehéz");

        okButton.setDisable(true);

        radio1.setOnAction(e -> okButton.setDisable(false));
        radio2.setOnAction(e -> okButton.setDisable(false));
        radio3.setOnAction(e -> okButton.setDisable(false));

        okButton.setOnAction(e ->
                {
                    int inputDifficulty = 0;
                    if (radio1.isSelected()) {
                        inputDifficulty = 1;
                        okButton.setDisable(true);
                    }
                    if (radio2.isSelected()) {
                        inputDifficulty = 2;
                        okButton.setDisable(true);
                    }
                    if (radio3.isSelected()) {
                        inputDifficulty = 3;
                        okButton.setDisable(true);
                    }
                    stage.setScene(playGame(inputDifficulty));
                }
        );

        root.getChildren().addAll(label, radio1, radio2, radio3, okButton);
        root.setAlignment(Pos.CENTER);
        return new Scene(root, 400, 300);
    }


    public Scene playGame(int inputDifficulty) {

        BigBoard sudoku = new BigBoard(inputDifficulty);
        GridPane board = new GridPane();
        TextField[][] textFields = new TextField[9][9];

        for (int blockColumn = 0; blockColumn < 3; blockColumn++) {
            for (int blockRow = 0; blockRow < 3; blockRow++) {

                GridPane box = new GridPane();
                box.setStyle("-fx-background-color: black, -fx-control-inner-background;" +
                        " -fx-background-insets: 0, 2; -fx-padding: 2;");
                for (int column = 0; column < 3; column++) {
                    for (int row = 0; row < 3; row++) {
                        int x = blockRow * 3 + row;
                        int y = blockColumn * 3 + column;
                        String text = sudoku.getTextToTextField(x, y);
                        if (text.equals("0")) {
                            text = "";
                        }
                        TextField textField = new TextField(text);
                        textField.setStyle("-fx-pref-width: 2em;");
                        if (!(textField.getText().equals(""))) {
                            textField.setEditable(false);
                        }
                        textFields[x][y] = textField;

                        textField.setOnKeyPressed(e -> {
                            textFields[x][y].setStyle("-fx-pref-width: 2em;");
                            Number zero = new Number(x, y, 0);
                            sudoku.setBigBoard(zero);
                        });
                        textField.setOnKeyTyped(e -> {
                            if (Character.isDigit(e.getCharacter().charAt(0))) {
                                int userNumber = Integer.parseInt(String.valueOf(e.getCharacter().charAt(0)));
                                Number number = new Number(x, y, userNumber);
                                boolean isGoodNumber = sudoku.checkIfItIsGoodNumber(number);
                                if (isGoodNumber) {
                                    textFields[x][y].setStyle("-fx-pref-width: 2em;-fx-background-color: SpringGreen;");
                                } else {
                                    textFields[x][y].setStyle("-fx-pref-width: 2em;-fx-background-color: LightPink;");
                                }
                            }
                        });
                        GridPane.setConstraints(textField, column, row);
                        box.getChildren().add(textField);
                    }
                }
                addMouseScrolling(board);
                GridPane.setConstraints(box, blockColumn, blockRow);
                board.getChildren().add(box);
                board.setAlignment(Pos.CENTER);
            }
        }
        return new Scene(board, 500, 500);
    }


    public void addMouseScrolling(GridPane board) {

        board.setOnScroll((ScrollEvent event) -> {
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0) {
                zoomFactor = 2.0 - zoomFactor;
            }
            board.setScaleX(board.getScaleX() * zoomFactor);
            board.setScaleY(board.getScaleY() * zoomFactor);
        });
    }

}



