/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rlearning.view;

import com.rlearning.controller.ColorPaletteController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Andrew
 */
public class SuperPixelEditorView extends Application {


  @Override
  public void start(Stage primaryStage) {
    //new window opens after btnNewWindow is clicked
    Stage newWindowStage = new Stage();
    StackPane newWindowLayout = new StackPane();
    Scene newWindowScene = new Scene(newWindowLayout, 620, 480);
    Button btnNewWindow = new Button("Open a window with this color");
    Button btnColorReset = new Button("Reset Window Color");
    Button btn = new Button("Color Picker");

    newWindowStage.setScene(newWindowScene);
    newWindowStage.setTitle("");
   //Create menu bar
        MenuBar menuBar = new MenuBar();
        //Creates menus
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuHelp = new Menu("Help");
        //File menu items
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        //Edit menu items
        MenuItem undoItem = new MenuItem("Undo");
        MenuItem redoItem = new MenuItem("Redo");
        //Help menu items
        MenuItem controlsItem = new MenuItem("Controls");
        MenuItem aboutItem = new MenuItem("About");
        //Adds menu items to the menus
        menuFile.getItems().addAll(openItem,saveItem,saveAsItem);
        menuEdit.getItems().addAll(undoItem,redoItem);
        menuHelp.getItems().addAll(controlsItem,aboutItem);
        //Adds all the menus to the menu bar
        menuBar.getMenus().addAll(menuFile,menuEdit,menuHelp);
    Stage colorPickerStage = new Stage();
    VBox colorPickerLayout = new VBox();
    Scene colorPickerScene = new Scene(colorPickerLayout, 300, 150);
    ColorPicker colorPicker = new ColorPicker();

    colorPickerLayout.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
    colorPickerLayout.setSpacing(10.0);
    colorPickerLayout.alignmentProperty().setValue(Pos.CENTER);
    colorPickerLayout.getChildren().addAll(colorPicker, btnNewWindow, btnColorReset);

    colorPickerStage.setTitle("Color Picker");
    colorPickerStage.setScene(colorPickerScene);

    //Popup window
    HBox hbButtons = new HBox();
    Stage popupStage = new Stage();
    popupStage.setTitle("Popup Dialog");
    StackPane popupLayout = new StackPane();
    Scene popupScene = new Scene(popupLayout, 300, 50);
    Text popupText = new Text("A window is already open.");

    popupLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
    popupStage.setScene(popupScene);
    popupLayout.getChildren().addAll(popupText);

    //handlers - may be better suited in control
    colorPicker.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        newWindowLayout.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
      }
    });

    btnColorReset.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        newWindowLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        colorPicker.setValue(Color.WHITE);
      }
    });

    btnNewWindow.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (!newWindowStage.isShowing()) {
          newWindowStage.show();
          newWindowLayout.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
        } else popupStage.show();
      }
    });

    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        colorPickerStage.show();
      }
    });

    Button saveBtn = new Button();
    saveBtn.setText("Save Custom Colors");
    saveBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        ColorPaletteController.saveColorPalette(colorPicker);
      }

    });

    Button loadBtn = new Button();
    loadBtn.setText("Load Custom Colors");
    loadBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        ColorPaletteController.loadColorPalette(colorPicker);
      }

    });

    StackPane root = new StackPane();
    root.getChildren().addAll(btn);
    hbButtons.getChildren().add(btn);
    hbButtons.getChildren().add(saveBtn);
    hbButtons.getChildren().add(loadBtn);
    root.getChildren().add(hbButtons);

    Scene scene = new Scene(root, 400, 250);

    primaryStage.setTitle("Super Pixel Art Editor");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
