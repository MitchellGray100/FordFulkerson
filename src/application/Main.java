package application;

import java.util.ArrayList;

import controller.ControllerImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application {

	private GridPane root = new GridPane();
	private TextFlow information = new TextFlow();
	private boolean mouseState = false;
	private boolean addState = false;
	private boolean removeState = false;
	private double mouseX = 0;
	private double mouseY = 0;
	private Cursor tempCursor;
	private ArrayList<CircleNode> nodes = new ArrayList<CircleNode>();
	private Button mouseButton = new Button("Mouse");
	private Button addNodeButton = new Button("Add Node");
	private Button removeNodeButton = new Button("Remove Node");
	private Button maxFlowButton = new Button("Max Flow");
	private ControllerImpl controller = new ControllerImpl();

	@Override
	public void start(Stage primaryStage) {
		try {

			ScrollPane scroll = new ScrollPane();

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

			root.setHgap(20);
			root.getStyleClass().add("background");

			Canvas canvas = new Canvas(200, 200);

			scroll.getStyleClass().add("background");
			scroll.fitToWidthProperty().set(true);
			scroll.fitToHeightProperty().set(true);
			scroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
			scroll.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
			scroll.setPannable(false);
			scroll.setContent(root);

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

			scroll.setOnMouseMoved(event -> {
				mouseX = event.getX();
				mouseY = event.getY();
				System.out.println("sMouseX = " + mouseX + " MouseY = " + mouseY);
			});
			scroll.setOnMouseClicked(event -> {
				if (addState) {
					CircleNode circle = new CircleNode(controller.getCurrentNode());
					nodes.add(circle);
					controller.addNode();
					root.getChildren().add(circle);
					System.out.println("ADded: " + controller.getCurrentNode());
				}
			});
			AnimationTimer animationTimer = new AnimationTimer() {
				@Override
				public void handle(long now) {

					update();

				}
			};
			animationTimer.start();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void update() {
		int holder = nodes.size();
		for (int i = 0; i < holder; i++) {
			if (holder <= nodes.size()) {
				collision(addNodeButton);
				collision(removeNodeButton);
				collision(mouseButton);
				collision(maxFlowButton);
				collision(information);
			}
		}
	}

	private void collision(Button button) {
		int holder = nodes.size();
		for (int i = 0; i < holder; i++) {
			if (holder <= nodes.size()) {
				if (nodes.get(i).getBoundsInParent().intersects(button.getBoundsInParent())) {
					root.getChildren().remove(nodes.get(i));
					nodes.get(i).getChildren().removeAll(nodes.get(i).circle, nodes.get(i).numText);
					nodes.get(i).dead = true;
					controller.removeNode(nodes.get(i).numVar + 1);
					nodes.remove(nodes.get(i));
					for (int j = 0; j < nodes.size(); j++) {
						if (nodes.get(i).numVar < nodes.get(j).numVar) {
							System.out.print("num before subtraction" + nodes.get(j).numVar + " ");
							nodes.get(j).numVar--;
							System.out.println("num after subtraction" + nodes.get(j).numVar);

							if (nodes.get(j).numVar == -1) {
								nodes.get(j).numText.setText("s");
							} else if (nodes.get(j).numVar == 0) {
								nodes.get(j).numText.setText("t");
							} else {
								nodes.get(j).numText.setText(((Integer) nodes.get(j).numVar).toString());
							}
						}
					}
//				root.getChildren().remove(this);
////				setTranslateX(10000);
////				setTranslateY(10000);
//				getChildren().removeAll(circle, numText);
//				dead = true;
//				controller.removeNode(numVar + 1);
//				nodes.remove(this);
//				System.out.println("REMOVED: " + numVar);
				}
			}
		}
	}

	private void collision(TextFlow text) {
		int holder = nodes.size();
		for (int i = 0; i < holder; i++) {
			if (holder <= nodes.size()) {
				if (nodes.get(i).getBoundsInParent().intersects(text.getBoundsInParent())) {
					root.getChildren().remove(nodes.get(i));
					nodes.get(i).getChildren().removeAll(nodes.get(i).circle, nodes.get(i).numText);
					nodes.get(i).dead = true;
					controller.removeNode(nodes.get(i).numVar + 1);
					nodes.remove(nodes.get(i));
					for (int j = 0; j < nodes.size(); j++) {
						if (nodes.get(i).numVar < nodes.get(j).numVar) {
							System.out.print("num before subtraction" + nodes.get(j).numVar + " ");
							nodes.get(j).numVar--;
							System.out.println("num after subtraction" + nodes.get(j).numVar);

							if (nodes.get(j).numVar == -1) {
								nodes.get(j).numText.setText("s");
							} else if (nodes.get(j).numVar == 0) {
								nodes.get(j).numText.setText("t");
							} else {
								nodes.get(j).numText.setText(((Integer) nodes.get(j).numVar).toString());
							}
						}
					}
//				root.getChildren().remove(this);
////				setTranslateX(10000);
////				setTranslateY(10000);
//				getChildren().removeAll(circle, numText);
//				dead = true;
//				controller.removeNode(numVar + 1);
//				nodes.remove(this);
//				System.out.println("REMOVED: " + numVar);
				}
			}
		}
	}

	private class CircleNode extends StackPane {
		int numVar;
		boolean dead = false;
		Text numText = new Text();
		Circle circle = new Circle();

		public CircleNode(int num) {
			this.setPrefSize(100, 100);
			this.autosize();
			circle.setRadius(30);
			setTranslateX(mouseX - 152);
			setTranslateY(mouseY - 30);
			numVar = num;
			circle.setFill(Color.ORANGE);
			circle.setStroke(Color.BLACK);
			if (num == -1) {
				numText.setText("s");
			} else if (num == 0) {
				numText.setText("t");
			} else {
				numText.setText(((Integer) num).toString());
			}
			numText.setFont(new Font(24));
			getChildren().addAll(circle, numText);

			setOnMouseReleased(event -> {
				if (!dead) {
					if (removeState) {
						root.getChildren().remove(this);
//						setTranslateX(10000);
//						setTranslateY(10000);
						getChildren().removeAll(circle, numText);
						dead = true;
						controller.removeNode(numVar + 1);
						nodes.remove(this);
						System.out.println("REMOVED: " + numVar);
						for (int i = 0; i < nodes.size(); i++) {
							if (numVar < nodes.get(i).numVar) {
								System.out.print("num before subtraction" + nodes.get(i).numVar + " ");
								nodes.get(i).numVar--;
								System.out.println("num after subtraction" + nodes.get(i).numVar);

								if (nodes.get(i).numVar == -1) {
									nodes.get(i).numText.setText("s");
								} else if (nodes.get(i).numVar == 0) {
									nodes.get(i).numText.setText("t");
								} else {
									nodes.get(i).numText.setText(((Integer) nodes.get(i).numVar).toString());
								}
							}
						}
					}
				}
			});
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
