package application;

import java.util.ArrayList;
import java.util.Random;

import controller.ControllerImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application {

	private Pane root = new Pane();
	private TextFlow information = new TextFlow();
	private boolean mouseState = false;
	private boolean addState = false;
	private boolean removeState = false;
	private boolean addEdgeState = false;
	private boolean removeEdgeState = false;
	private double mouseX = 0;
	private double mouseY = 0;
	private Cursor tempCursor;
	private ArrayList<CircleNode> nodes = new ArrayList<CircleNode>();
	private ArrayList<EdgeLine> edges = new ArrayList<EdgeLine>();
	private CircleNode edgeOne;
	private CircleNode edgeTwo;
	private Button mouseButton = new Button("Mouse");
	private Button addNodeButton = new Button("Add Node");
	private Button removeNodeButton = new Button("Remove Node");
	private Button maxFlowButton = new Button("Max Flow");
	private Button addEdgeButton = new Button("Add Edge");
	private Button removeEdgeButton = new Button("Remove Edge");
	private ControllerImpl controller = new ControllerImpl();
	private Text nodeInfoText = new Text();
	private Scene scene;

	// TODO Curved lines when double
	// TODO Allow Changing of Capacities
	@Override
	public void start(Stage primaryStage) {
		try {

			ScrollPane scroll = new ScrollPane();

			Text introText = new Text("Click a button to get started.");
			Text mouseText = new Text("Click a Node or Edge to view its info.");
			Text addNodeText = new Text("Click anywhere to add a Node.");
			Text removeNodeText = new Text("Click a Node to remove it.");
			Text addEdgeText = new Text("Click two Nodes to add an Edge.");
			Text removeEdgeText = new Text("Click an Edge to remove it or click two Nodes.");
			Text maxFlowText = new Text();
			;
			mouseText.setFont(new Font(32));
			introText.setFont(new Font(32));
			removeNodeText.setFont(new Font(32));
			addNodeText.setFont(new Font(32));
			removeEdgeText.setFont(new Font(32));
			addEdgeText.setFont(new Font(32));
			nodeInfoText.setFont(new Font(32));
			information.getStyleClass().add("textBubble");
			information.getChildren().add(introText);
			information.setPrefSize(300, 1080);

//			root.setHgap(20);
			root.getStyleClass().add("background");

			scroll.getStyleClass().add("background");
//			scroll.fitToWidthProperty().set(true);
			scroll.fitToHeightProperty().set(true);
			scroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
			scroll.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
			scroll.setPannable(false);
			scroll.setContent(information);

			mouseButton.getStyleClass().add("toolButton");
			addNodeButton.getStyleClass().add("toolButton");
			removeNodeButton.getStyleClass().add("toolButton");
			addEdgeButton.getStyleClass().add("toolButton");
			removeEdgeButton.getStyleClass().add("toolButton");
			maxFlowButton.getStyleClass().add("toolButton");
			maxFlowButton.setId("maxFlow");

			root.getChildren().add(scroll);
			root.getChildren().add(mouseButton);
			mouseButton.setTranslateX(313);
			root.getChildren().add(addNodeButton);
			addNodeButton.setTranslateX(460);
			root.getChildren().add(removeNodeButton);
			removeNodeButton.setTranslateX(655);
			root.getChildren().add(addEdgeButton);
			addEdgeButton.setTranslateX(915);
			root.getChildren().add(removeEdgeButton);
			removeEdgeButton.setTranslateX(1111);
			root.getChildren().add(maxFlowButton);
			maxFlowButton.setTranslateX(1368);
			root.getChildren().add(information);

			scene = new Scene(root, 1920, 1080);

			mouseButton.setOnMouseReleased(event -> {
				edgeOne = null;
				edgeTwo = null;
				removeSelectedNodes();
				information.getChildren().remove(nodeInfoText);
				information.getChildren().remove(maxFlowText);
				information.getChildren().remove(addNodeText);
				information.getChildren().remove(introText);
				information.getChildren().remove(removeNodeText);
				information.getChildren().remove(addEdgeText);
				information.getChildren().remove(removeEdgeText);
				if (!information.getChildren().contains(mouseText)) {

					information.getChildren().add(mouseText);
				}
				mouseState = true;
				addState = false;
				removeState = false;
				addEdgeState = false;
				removeEdgeState = false;
				if (!mouseButton.getStyleClass().contains("clickedToolButton")) {
					mouseButton.getStyleClass().add("clickedToolButton");
				}
				addNodeButton.getStyleClass().remove("clickedToolButton");
				removeNodeButton.getStyleClass().remove("clickedToolButton");
				addEdgeButton.getStyleClass().remove("clickedToolButton");
				removeEdgeButton.getStyleClass().remove("clickedToolButton");
				scene.setCursor(Cursor.DEFAULT);
				tempCursor = scene.getCursor();
			});

			addNodeButton.setOnMouseReleased(event -> {
				edgeOne = null;
				edgeTwo = null;
				removeSelectedNodes();
				information.getChildren().remove(nodeInfoText);
				information.getChildren().remove(maxFlowText);
				information.getChildren().remove(mouseText);
				information.getChildren().remove(introText);
				information.getChildren().remove(removeNodeText);
				information.getChildren().remove(addEdgeText);
				information.getChildren().remove(removeEdgeText);
				if (!information.getChildren().contains(addNodeText)) {

					information.getChildren().add(addNodeText);
				}
				mouseState = false;
				addState = true;
				removeState = false;
				addEdgeState = false;
				removeEdgeState = false;
				if (!addNodeButton.getStyleClass().contains("clickedToolButton")) {
					addNodeButton.getStyleClass().add("clickedToolButton");
				}
				scene.setCursor(Cursor.CROSSHAIR);
				tempCursor = scene.getCursor();
				mouseButton.getStyleClass().remove("clickedToolButton");
				removeNodeButton.getStyleClass().remove("clickedToolButton");
				addEdgeButton.getStyleClass().remove("clickedToolButton");
				removeEdgeButton.getStyleClass().remove("clickedToolButton");
			});

			removeNodeButton.setOnMouseReleased(event -> {
				edgeOne = null;
				edgeTwo = null;
				removeSelectedNodes();
				information.getChildren().remove(nodeInfoText);
				information.getChildren().remove(maxFlowText);
				information.getChildren().remove(mouseText);
				information.getChildren().remove(addNodeText);
				information.getChildren().remove(introText);
				information.getChildren().remove(addEdgeText);
				information.getChildren().remove(removeEdgeText);
				if (!information.getChildren().contains(removeNodeText)) {
					information.getChildren().add(removeNodeText);
				}
				mouseState = false;
				addState = false;
				removeState = true;
				addEdgeState = false;
				removeEdgeState = false;
				if (!removeNodeButton.getStyleClass().contains("clickedToolButton")) {
					removeNodeButton.getStyleClass().add("clickedToolButton");
				}
				scene.setCursor(Cursor.CROSSHAIR);
				tempCursor = scene.getCursor();
				mouseButton.getStyleClass().remove("clickedToolButton");
				addNodeButton.getStyleClass().remove("clickedToolButton");
				addEdgeButton.getStyleClass().remove("clickedToolButton");
				removeEdgeButton.getStyleClass().remove("clickedToolButton");
			});

			addEdgeButton.setOnMouseReleased(event -> {
				edgeOne = null;
				edgeTwo = null;
				removeSelectedNodes();
				information.getChildren().remove(nodeInfoText);
				information.getChildren().remove(maxFlowText);
				information.getChildren().remove(mouseText);
				information.getChildren().remove(introText);
				information.getChildren().remove(removeNodeText);
				information.getChildren().remove(addNodeText);
				information.getChildren().remove(removeEdgeText);
				if (!information.getChildren().contains(addEdgeText)) {

					information.getChildren().add(addEdgeText);
				}
				mouseState = false;
				addState = false;
				removeState = false;
				addEdgeState = true;
				removeEdgeState = false;
				if (!addEdgeButton.getStyleClass().contains("clickedToolButton")) {
					addEdgeButton.getStyleClass().add("clickedToolButton");
				}
				scene.setCursor(Cursor.CROSSHAIR);
				tempCursor = scene.getCursor();
				mouseButton.getStyleClass().remove("clickedToolButton");
				removeNodeButton.getStyleClass().remove("clickedToolButton");
				removeEdgeButton.getStyleClass().remove("clickedToolButton");
				addNodeButton.getStyleClass().remove("clickedToolButton");
			});

			removeEdgeButton.setOnMouseReleased(event -> {
				edgeOne = null;
				edgeTwo = null;
				removeSelectedNodes();
				information.getChildren().remove(nodeInfoText);
				information.getChildren().remove(maxFlowText);
				information.getChildren().remove(mouseText);
				information.getChildren().remove(addNodeText);
				information.getChildren().remove(removeNodeText);
				information.getChildren().remove(introText);
				information.getChildren().remove(addEdgeText);
				if (!information.getChildren().contains(removeEdgeText)) {
					information.getChildren().add(removeEdgeText);
				}
				mouseState = false;
				addState = false;
				removeState = false;
				addEdgeState = false;
				removeEdgeState = true;
				if (!removeEdgeButton.getStyleClass().contains("clickedToolButton")) {
					removeEdgeButton.getStyleClass().add("clickedToolButton");
				}
				scene.setCursor(Cursor.CROSSHAIR);
				tempCursor = scene.getCursor();
				mouseButton.getStyleClass().remove("clickedToolButton");
				addNodeButton.getStyleClass().remove("clickedToolButton");
				removeNodeButton.getStyleClass().remove("clickedToolButton");
				addEdgeButton.getStyleClass().remove("clickedToolButton");
			});

			maxFlowButton.setOnMouseReleased(event -> {
				edgeOne = null;
				edgeTwo = null;
				removeSelectedNodes();
				information.getChildren().remove(nodeInfoText);
				information.getChildren().remove(introText);
				information.getChildren().remove(maxFlowText);
				information.getChildren().remove(mouseText);
				information.getChildren().remove(addNodeText);
				information.getChildren().remove(removeNodeText);
				information.getChildren().remove(addEdgeText);
				information.getChildren().remove(removeEdgeText);
//				controller.maxFlow();
				Random rand = new Random();
				if (!information.getChildren().contains(maxFlowText)) {
//					maxFlowText.setText("Max Flow: " + ((Integer) rand.nextInt()).toString());
					maxFlowText.setText("Max Flow: " + ((Integer) controller.maxFlow()));
					maxFlowText.setFont(new Font(32));
					information.getChildren().add(maxFlowText);
				}

				mouseState = false;
				addState = false;
				removeState = false;
				addEdgeState = false;
				removeEdgeState = false;
//				if (!removeNodeButton.getStyleClass().contains("clickedToolButton")) {
//					removeNodeButton.getStyleClass().add("clickedToolButton");
//				}
				scene.setCursor(Cursor.HAND);
				mouseButton.getStyleClass().remove("clickedToolButton");
				addNodeButton.getStyleClass().remove("clickedToolButton");
				removeNodeButton.getStyleClass().remove("clickedToolButton");
				removeEdgeButton.getStyleClass().remove("clickedToolButton");
				addEdgeButton.getStyleClass().remove("clickedToolButton");
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

			removeEdgeButton.setOnMouseEntered(event -> {
				if (!removeEdgeButton.getStyleClass().contains("clickedToolButton")) {
					tempCursor = scene.getCursor();
					scene.setCursor(Cursor.HAND);
				}
			});
			removeEdgeButton.setOnMouseExited(event -> {
				scene.setCursor(tempCursor);
			});

			addEdgeButton.setOnMouseEntered(event -> {
				if (!addEdgeButton.getStyleClass().contains("clickedToolButton")) {
					tempCursor = scene.getCursor();
					scene.setCursor(Cursor.HAND);
				}
			});
			addEdgeButton.setOnMouseExited(event -> {
				scene.setCursor(tempCursor);
			});

			maxFlowButton.setOnMouseEntered(event -> {
				tempCursor = scene.getCursor();
				scene.setCursor(Cursor.HAND);
			});

			maxFlowButton.setOnMouseExited(event -> {
				scene.setCursor(tempCursor);
			});

			root.setOnMouseMoved(event -> {
				mouseX = event.getX();
				mouseY = event.getY();
				System.out.println("MouseX = " + mouseX + " MouseY = " + mouseY);
			});
			root.setOnMouseClicked(event -> {
				if (addState) {
					if (mouseX > 300 && mouseY > 100) {
						CircleNode circle = new CircleNode(controller.getCurrentNode());
						nodes.add(circle);
						circle.setTranslateX(mouseX - 32);
						circle.setTranslateY(mouseY - 32);
						controller.addNode();
						root.getChildren().add(circle);
						System.out.println("ADded: " + controller.getCurrentNode());
					}
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

		collision(addNodeButton);
		collision(removeNodeButton);
		collision(addEdgeButton);
		collision(removeEdgeButton);
		collision(mouseButton);
		collision(maxFlowButton);
		collision(information);

		checkEdges();
	}

	private void removeSelectedNodes() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).getStyleClass().remove("selectedNode");
		}

	}

	private void checkEdges() {
		// TODO Auto-generated method stub
		// Deletes edge line that are old

	}

	private void collision(Button button) {
		int holder = nodes.size();
		for (int i = 0; i < holder; i++) {
			if (holder <= nodes.size()) {
				if (nodes.get(i).getBoundsInParent().intersects(button.getBoundsInParent())) {
					System.out.println("COLLISION ERROR");
					root.getChildren().remove(nodes.get(i));
//					nodes.get(i).getChildren().removeAll(nodes.get(i).circle, nodes.get(i).numText);
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
			this.setPrefSize(30, 30);
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
			circle.setOnMouseEntered(event -> {
				if (!circle.getStyleClass().contains("hoveredNode")) {
					circle.getStyleClass().add("hoveredNode");

				}
				tempCursor = scene.getCursor();
				scene.setCursor(Cursor.HAND);
			});

			circle.setOnMouseExited(event -> {
				circle.getStyleClass().remove("hoveredNode");
				scene.setCursor(tempCursor);
			});
			circle.setOnMouseReleased(event -> {
				if (!dead) {
					if (mouseState) {
						this.getStyleClass().add("selectedNode");
						information.getChildren().clear();
						nodeInfoText = new Text();
						nodeInfoText.setFont(new Font(32));
						String controllerInfo = "Node " + numText.getText() + "\n";
						controllerInfo += "Edges: \n";
//						Hashtable<Node, Edge> edges = controller.getEdgesOFNode(numVar + 1);

						for (int i = 0; i < edges.size(); i++) {
							if (edges.get(i).edge1.numText.equals(numText)) {
								controllerInfo += edges.get(i).edge2.numText.getText() + " ";
								controllerInfo += controller.getCapacityOfEdge(edges.get(i).edge1.numVar + 1,
										edges.get(i).edge2.numVar + 1) + "\n";
							}
						}

						nodeInfoText.setText(controllerInfo);
						information.getChildren().add(nodeInfoText);
						removeSelectedNodes();
						this.getStyleClass().add("selectedNode");
//						nodeInfoText
					}
					if (removeState) {
						root.getChildren().remove(this);
//						setTranslateX(10000);
//						setTranslateY(10000);
						getChildren().removeAll(circle, numText);
						dead = true;
						controller.removeNode(numVar + 1);
						nodes.remove(this);
						System.out.println("REMOVED: " + numVar);
						int holder = edges.size();
						for (int i = 0; i < holder; i++) {
							if (edges.get(i).edge1.numText.equals(numText)
									|| edges.get(i).edge2.numText.equals(numText)) {
								edges.get(i).line.setStrokeWidth(0);
								edges.remove(edges.get(i));
								i--;
								holder--;
							}
						}
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

					if (addEdgeState) {
						if (edgeOne == null) {
							edgeOne = this;
						} else if (edgeTwo == null) {
							edgeTwo = this;
							boolean has = false;
							for (int i = 0; i < edges.size(); i++) {
								if ((edges.get(i).edge1.numVar == edgeOne.numVar
										&& edges.get(i).edge2.numVar == edgeTwo.numVar)) {
									has = true;
								}
							}
							if (!has) {
								EdgeLine temp = new EdgeLine(edgeOne, edgeTwo);
								controller.addEdge(edgeOne.numVar + 1, edgeTwo.numVar + 1, 5);
								// ADD EDGE HERE TODO
								edges.add(temp);
							}

						} else {
							removeSelectedNodes();
							edgeTwo = null;
							edgeOne = this;
						}
						this.getStyleClass().add("selectedNode");

					}
					if (removeEdgeState) {
						if (edgeOne == null) {
							edgeOne = this;
						} else if (edgeTwo == null) {
							edgeTwo = this;
							boolean has = false;
							for (int i = 0; i < edges.size(); i++) {
								if ((edges.get(i).edge1.numVar == edgeOne.numVar
										&& edges.get(i).edge2.numVar == edgeTwo.numVar)) {
									edges.get(i).line.setStrokeWidth(0);
									edges.remove(i);
									controller.removeEdge(edgeOne.numVar + 1, edgeTwo.numVar + 1);
									break;
								}
							}

						} else {
							removeSelectedNodes();
							edgeTwo = null;
							edgeOne = this;
						}
						this.getStyleClass().add("selectedNode");
					}
				}
			});
			numText.setOnMouseEntered(event -> {
				if (!circle.getStyleClass().contains("hoveredNode")) {
					circle.getStyleClass().add("hoveredNode");

				}
				tempCursor = scene.getCursor();
				scene.setCursor(Cursor.HAND);
			});

			numText.setOnMouseExited(event -> {
				circle.getStyleClass().remove("hoveredNode");
				scene.setCursor(tempCursor);
			});
			numText.setOnMouseReleased(event -> {
				if (!dead) {
					if (mouseState) {
						this.getStyleClass().add("selectedNode");
						information.getChildren().clear();
						nodeInfoText = new Text();
						nodeInfoText.setFont(new Font(32));
						String controllerInfo = "Node " + numText.getText() + "\n";
						controllerInfo += "Edges: \n";
//						Hashtable<Node, Edge> edges = controller.getEdgesOFNode(numVar + 1);

						for (int i = 0; i < edges.size(); i++) {
							if (edges.get(i).edge1.numText.equals(numText)) {
								controllerInfo += edges.get(i).edge2.numText.getText() + " ";
								controllerInfo += controller.getCapacityOfEdge(edges.get(i).edge1.numVar + 1,
										edges.get(i).edge2.numVar + 1) + "\n";
							}
						}

						nodeInfoText.setText(controllerInfo);
						information.getChildren().add(nodeInfoText);
						removeSelectedNodes();
						this.getStyleClass().add("selectedNode");
					}
					if (removeState) {
						root.getChildren().remove(this);
//						setTranslateX(10000);
//						setTranslateY(10000);
						getChildren().removeAll(circle, numText);
						dead = true;
						controller.removeNode(numVar + 1);
						nodes.remove(this);
						System.out.println("REMOVED: " + numVar);
						int holder = edges.size();
						for (int i = 0; i < holder; i++) {
							if (edges.get(i).edge1.numText.equals(numText)
									|| edges.get(i).edge2.numText.equals(numText)) {
								edges.get(i).line.setStrokeWidth(0);
								edges.remove(edges.get(i));
								i--;
								holder--;
							}
						}
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

					if (addEdgeState) {
						if (edgeOne == null) {
							edgeOne = this;
						} else if (edgeTwo == null) {
							edgeTwo = this;
							boolean has = false;
							for (int i = 0; i < edges.size(); i++) {
								if ((edges.get(i).edge1.numVar == edgeOne.numVar
										&& edges.get(i).edge2.numVar == edgeTwo.numVar)) {
									has = true;
								}
							}
							if (!has) {
								EdgeLine temp = new EdgeLine(edgeOne, edgeTwo);
								controller.addEdge(edgeOne.numVar + 1, edgeTwo.numVar + 1, 5);
								edges.add(temp);
							}

						} else {
							removeSelectedNodes();
							edgeTwo = null;
							edgeOne = this;
						}
						this.getStyleClass().add("selectedNode");
					}

					if (removeEdgeState) {
						if (edgeOne == null) {
							edgeOne = this;
						} else if (edgeTwo == null) {
							edgeTwo = this;
							boolean has = false;
							for (int i = 0; i < edges.size(); i++) {
								if ((edges.get(i).edge1.numVar == edgeOne.numVar
										&& edges.get(i).edge2.numVar == edgeTwo.numVar)) {
									controller.removeEdge(edgeOne.numVar + 1, edgeTwo.numVar + 1);
									edges.get(i).line.setStrokeWidth(0);
									edges.remove(i);
									break;
								}
							}

						} else {
							removeSelectedNodes();
							edgeTwo = null;
							edgeOne = this;
						}
						this.getStyleClass().add("selectedNode");
					}
				}
			});

//			setOnDragOver(event -> {
//				if (mouseState) {
//					setTranslateX(mouseX);
//					setTranslateY(mouseY);
//				}
//			});
//			setOnDragDetected(new EventHandler<MouseEvent>() {
//				public void handle(MouseEvent event) {
//					if (mouseState) {
//						setTranslateX(mouseX);
//						setTranslateY(mouseY);
//						/* drag was detected, start a drag-and-drop gesture */
//						/* allow any transfer mode */
////						Dragboard db = startDragAndDrop(TransferMode.ANY);
//
//						/* Put a string on a dragboard */
////						ClipboardContent content = new ClipboardContent();
////						content.putIfAbsent(new DataFormat(), this);
////						db.setContent(content);
//
//						event.consume();
//					}
//				}
//			});
		}

		private void removeSelectedNodes() {
			for (int i = 0; i < nodes.size(); i++) {
				nodes.get(i).getStyleClass().remove("selectedNode");
			}

		}

	}

	private class EdgeLine extends StackPane {
		CircleNode edge1;
		CircleNode edge2;
		Line line = new Line();
		Path path = new Path();

		public EdgeLine(CircleNode firstEdge, CircleNode secondEdge) {
//			moveTo.setX(0.0f);
//			moveTo.setY(50.0f);
//			quadTo.setControlX(25.0f);
//			quadTo.setControlY(25f);
//			quadTo.setX(50.0f);
//			quadTo.setY(50.0f);

//			setTranslateX(mouseX - 152);
//			setTranslateY(mouseY - 30);
			edge1 = firstEdge;
			edge2 = secondEdge;
			controller.addEdge(edge1.numVar + 1, edge2.numVar + 1, 5);

//			line.setStartX(edge1.getTranslateX());
//			line.setStartX(edge1.getTranslateY());
//			line.setEndX(edge2.getTranslateX());
//			line.setEndY(edge2.getTranslateY());

			boolean doubleLine = false;
			for (int i = 0; i < edges.size(); i++) {
				if (((edges.get(i).edge1.numVar == edge1.numVar && edges.get(i).edge2.numVar == edge2.numVar))
						|| ((edges.get(i).edge1.numVar == edge2.numVar && edges.get(i).edge2.numVar == edge1.numVar))) {
					doubleLine = true;

					break;
				}
			}
			if (doubleLine) {
				QuadCurveTo quadTo = new QuadCurveTo();
				MoveTo moveTo = new MoveTo();
				path.setStrokeWidth(2);
				path.getElements().add(moveTo);
				path.getElements().add(quadTo);
				moveTo.setX(firstEdge.getTranslateX() + 32);
				moveTo.setY(firstEdge.getTranslateY() + 32);
				quadTo.setControlX((firstEdge.getTranslateX() + 32 + secondEdge.getTranslateX() + 32) / 2);
				quadTo.setControlY((firstEdge.getTranslateY() + 32 + secondEdge.getTranslateY() + 32));
				quadTo.setX(secondEdge.getTranslateX() + 32);
				quadTo.setY(secondEdge.getTranslateY() + 32);
				root.getChildren().add(path);

			} else {
				line.setStartX(firstEdge.getTranslateX() + 32);
				line.setStartY(firstEdge.getTranslateY() + 32);
				line.setEndX(secondEdge.getTranslateX() + 32);
				line.setEndY(secondEdge.getTranslateY() + 32);
				line.setTranslateX(firstEdge.circle.getTranslateX());
				line.setTranslateY(firstEdge.circle.getTranslateY());
				line.setFill(Color.BLACK);
				line.setStrokeWidth(2);
				root.getChildren().add(line);
			}

//			root.getChildren().add(this);

			line.setOnMouseReleased(event -> {
				if (removeEdgeState) {
					root.getChildren().remove(line);
					line.setStrokeWidth(0);

					for (int i = 0; i < edges.size(); i++) {
						if ((edges.get(i).edge1.numVar == edge1.numVar && edges.get(i).edge2.numVar == edge2.numVar)) {
							controller.removeEdge(edge1.numVar + 1, edge2.numVar + 1);
							edges.get(i).line.setStrokeWidth(0);
							edges.remove(i);
							break;
						}
					}
				}
			});
			line.setOnMouseEntered(event -> {
				if (!line.getStyleClass().contains("clickedToolButton")) {

					line.setFill(Color.ORANGE);
					line.setStroke(Color.ORANGE);
					tempCursor = scene.getCursor();
					scene.setCursor(Cursor.HAND);
				}
			});

			line.setOnMouseExited(event -> {
				if (!line.getStyleClass().contains("clickedToolButton")) {

					line.setFill(Color.BLACK);
					line.setStroke(Color.BLACK);
					scene.setCursor(tempCursor);
				}
			});
			path.setOnMouseReleased(event -> {
				if (removeEdgeState) {
					root.getChildren().remove(path);
					path.setStrokeWidth(0);

					for (int i = 0; i < edges.size(); i++) {
						if ((edges.get(i).edge1.numVar == edge1.numVar && edges.get(i).edge2.numVar == edge2.numVar)) {
							controller.removeEdge(edge1.numVar + 1, edge2.numVar + 1);
							edges.get(i).path.setStrokeWidth(0);
							edges.remove(i);
							break;
						}
					}
				}
			});
			path.setOnMouseEntered(event -> {
				if (!path.getStyleClass().contains("clickedToolButton")) {

//					path.setFill(Color.ORANGE);
					path.setStroke(Color.ORANGE);
					tempCursor = scene.getCursor();
					scene.setCursor(Cursor.HAND);
				}
			});

			path.setOnMouseExited(event -> {
				if (!path.getStyleClass().contains("clickedToolButton")) {

//					path.setFill(Color.BLACK);
					path.setStroke(Color.BLACK);
					scene.setCursor(tempCursor);
				}
			});
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
