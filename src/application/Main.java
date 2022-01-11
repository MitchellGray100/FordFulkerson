package application;

import controller.ControllerImpl;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application {
	private TextFlow information = new TextFlow();
	private boolean mouseState = false;
	private boolean addState = false;
	private boolean removeState = false;
	private Cursor tempCursor;

	@Override
	public void start(Stage primaryStage) {
		try {

			ControllerImpl controller = new ControllerImpl();

			Text introText = new Text("Click a button or node to get started");
			Text mouseText = new Text("Click a Node to view its info");
			Text addNodeText = new Text("Click anywhere to add a Node");
			Text removeNodeText = new Text("Click a Node to remove it");

			mouseText.setFont(new Font(32));
			introText.setFont(new Font(32));
			removeNodeText.setFont(new Font(32));
			addNodeText.setFont(new Font(32));
			information.getStyleClass().add("textBubble");
			information.getChildren().add(introText);
			information.setPrefWidth(300);

			GridPane root = new GridPane();
			root.setHgap(20);
			root.getStyleClass().add("background");

			Canvas canvas = new Canvas(200, 200);

			ScrollPane scroll = new ScrollPane();
			scroll.getStyleClass().add("background");
			scroll.fitToWidthProperty().set(true);
			scroll.fitToHeightProperty().set(true);
			scroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
			scroll.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
			scroll.setPannable(false);
			scroll.setContent(root);

			Button mouseButton = new Button("Mouse");
			Button addNodeButton = new Button("Add Node");
			Button removeNodeButton = new Button("Remove Node");
			Button maxFlowButton = new Button("Max Flow");

			mouseButton.getStyleClass().add("toolButton");
			addNodeButton.getStyleClass().add("toolButton");
			removeNodeButton.getStyleClass().add("toolButton");
			maxFlowButton.getStyleClass().add("toolButton");
			maxFlowButton.setId("maxFlow");

			root.add(mouseButton, 1, 0, 5, 3);
			root.add(addNodeButton, 6, 0, 5, 3);
			root.add(removeNodeButton, 11, 0, 5, 3);
			root.add(maxFlowButton, 16, 0, 5, 3);
			root.add(information, 0, 2, 1, 1);
			root.add(canvas, 0, 10, 10, 10);

			Scene scene = new Scene(scroll, 1440, 720);

			mouseButton.setOnMouseClicked(event -> {
				information.getChildren().remove(addNodeText);
				information.getChildren().remove(introText);
				information.getChildren().remove(removeNodeText);
				if (!information.getChildren().contains(mouseText)) {

					information.getChildren().add(mouseText);
				}
				mouseState = true;
				addState = false;
				removeState = false;
				if (!mouseButton.getStyleClass().contains("clickedToolButton")) {
					mouseButton.getStyleClass().add("clickedToolButton");
				}
				addNodeButton.getStyleClass().remove("clickedToolButton");
				removeNodeButton.getStyleClass().remove("clickedToolButton");
				scene.setCursor(Cursor.DEFAULT);
				tempCursor = scene.getCursor();
			});

			addNodeButton.setOnMouseClicked(event -> {
				information.getChildren().remove(mouseText);
				information.getChildren().remove(introText);
				information.getChildren().remove(removeNodeText);
				if (!information.getChildren().contains(addNodeText)) {

					information.getChildren().add(addNodeText);
				}
				mouseState = false;
				addState = true;
				removeState = false;
				if (!addNodeButton.getStyleClass().contains("clickedToolButton")) {
					addNodeButton.getStyleClass().add("clickedToolButton");
				}
				scene.setCursor(Cursor.CROSSHAIR);
				tempCursor = scene.getCursor();
				mouseButton.getStyleClass().remove("clickedToolButton");
				removeNodeButton.getStyleClass().remove("clickedToolButton");
			});

			removeNodeButton.setOnMouseClicked(event -> {
				information.getChildren().remove(mouseText);
				information.getChildren().remove(addNodeText);
				information.getChildren().remove(introText);
				if (!information.getChildren().contains(removeNodeText)) {
					information.getChildren().add(removeNodeText);
				}
				mouseState = false;
				addState = false;
				removeState = true;
				if (!removeNodeButton.getStyleClass().contains("clickedToolButton")) {
					removeNodeButton.getStyleClass().add("clickedToolButton");
				}
				scene.setCursor(Cursor.CROSSHAIR);
				tempCursor = scene.getCursor();
				mouseButton.getStyleClass().remove("clickedToolButton");
				addNodeButton.getStyleClass().remove("clickedToolButton");
			});

			maxFlowButton.setOnMouseClicked(event -> {
				information.getChildren().remove(mouseText);
				information.getChildren().remove(addNodeText);
				information.getChildren().remove(removeNodeText);
				if (!information.getChildren().contains(introText)) {
					information.getChildren().add(introText);
				}

				mouseState = false;
				addState = false;
				removeState = false;
//				if (!removeNodeButton.getStyleClass().contains("clickedToolButton")) {
//					removeNodeButton.getStyleClass().add("clickedToolButton");
//				}
				scene.setCursor(Cursor.HAND);
				mouseButton.getStyleClass().remove("clickedToolButton");
				addNodeButton.getStyleClass().remove("clickedToolButton");
				removeNodeButton.getStyleClass().remove("clickedToolButton");
			});

			mouseButton.setOnMouseEntered(event -> {
				if (!mouseButton.getStyleClass().contains("clickedToolButton")) {
					tempCursor = scene.getCursor();
					scene.setCursor(Cursor.HAND);
				}
			});
			mouseButton.setOnMouseExited(event -> {
				scene.setCursor(tempCursor);
			});
			removeNodeButton.setOnMouseEntered(event -> {
				if (!removeNodeButton.getStyleClass().contains("clickedToolButton")) {
					tempCursor = scene.getCursor();
					scene.setCursor(Cursor.HAND);
				}
			});
			removeNodeButton.setOnMouseExited(event -> {
				scene.setCursor(tempCursor);
			});

			addNodeButton.setOnMouseEntered(event -> {
				if (!addNodeButton.getStyleClass().contains("clickedToolButton")) {
					tempCursor = scene.getCursor();
					scene.setCursor(Cursor.HAND);
				}
			});
			addNodeButton.setOnMouseExited(event -> {
				scene.setCursor(tempCursor);
			});

			maxFlowButton.setOnMouseEntered(event -> {
				tempCursor = scene.getCursor();
				scene.setCursor(Cursor.HAND);
			});

			maxFlowButton.setOnMouseExited(event -> {
				scene.setCursor(tempCursor);
			});
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
