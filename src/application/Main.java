package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd ");
			StackPane root = new StackPane();
			Scene scene = new Scene(root, 1500, 450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			LinkedList<Flight> ls = new LinkedList<Flight>();

			Image img = new Image("passenger.jpg");

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Button b1 = new Button("Read Data");
			b1.setMinSize(350, 30);
			Button b2 = new Button("Flight's information");
			b2.setMinSize(350, 30);
			Button b3 = new Button("Passengers information for a specific Flight");
			b3.setMinSize(350, 30);
			Button b4 = new Button("Add/Edit Flight");
			b4.setMinSize(350, 30);
			Button b5 = new Button("Reserve a ticket for a specific Flight");
			b5.setMinSize(350, 30);
			Button b6 = new Button("Cancel a reservation");
			b6.setMinSize(350, 30);
			Button b7 = new Button("Check if ticket is reserved");
			b7.setMinSize(350, 30);
			Button b8 = new Button("Information for a spacefic Passsenger");
			b8.setMinSize(350, 30);
			Button b9 = new Button("Exit");
			b9.setMinSize(350, 30);
			b1.setFont(Font.font(15));
			b2.setFont(Font.font(15));
			b3.setFont(Font.font(15));
			b4.setFont(Font.font(15));
			b5.setFont(Font.font(15));
			b6.setFont(Font.font(15));
			b7.setFont(Font.font(15));
			b8.setFont(Font.font(15));
			b9.setFont(Font.font(15));
			VBox v = new VBox();
			v.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9);
			v.setSpacing(10);
			v.setAlignment(Pos.TOP_LEFT);
			v.setPadding(new Insets(10, 10, 10, 10));
			root.getChildren().add(v);

			b1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					GridPane g = new GridPane();
					g.setAlignment(Pos.CENTER);
					g.setStyle("-fx-background-image: url('flight.jpg')");
					Scene scene4 = new Scene(g, 1000, 500);
					scene4.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene4);
					primaryStage.show();

					VBox v = new VBox();
					RadioButton ch2 = new RadioButton("Passengers File");
					ch2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
					RadioButton ch1 = new RadioButton("Flights File        ");
					ch1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
					ToggleGroup t = new ToggleGroup(); // IMPORTANT مشان يتنقل بالخيارات
					v.setSpacing(10);
					v.setAlignment(Pos.CENTER);
					ch1.setToggleGroup(t);
					ch2.setToggleGroup(t);
					v.getChildren().addAll(ch1, ch2);
					g.add(v, 0, 0);
					Button back = new Button("Back");
					back.setFont(Font.font(15));
					back.setAlignment(Pos.BOTTOM_CENTER);
					g.add(back, 0, 20);

					ch2.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub

							FileChooser fc = new FileChooser();
							File f2 = fc.showOpenDialog(primaryStage);
							// File f2 = new File("passengers.txt");
							try {
								if (f2.length() != 0) {
									Scanner input2;
									try {
										input2 = new Scanner(f2);
										while (input2.hasNext()) {
											String Data = input2.nextLine();
											String tokens[] = Data.split(",");
											String[] token = tokens[5].split("/");
											int day = Integer.parseInt(token[0]);
											int month = Integer.parseInt(token[1]);
											int year = Integer.parseInt(token[2]);
											Calendar c = new GregorianCalendar(year, month, day);
											Passenger x = new Passenger(Integer.parseInt(tokens[0]),
													Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], c);

											Node<Flight> cur = ls.head;
											for (; cur != null; cur = cur.getNext()) {
												if (cur.getData().getNumber() == Integer.parseInt(tokens[0]))
													cur.getData().getS().insertion(x);

											}

										}
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

							} catch (NullPointerException e) {
								// TODO Auto-generated catch block

								Label l = new Label("Choose Passengers file!!");
								l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
								l.setTextFill(Color.DARKRED);
								g.add(l, 0, 22);

							}
						}
					});

					ch1.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub

							// File f1 = new File("Flights.txt");

							FileChooser fc = new FileChooser();
							File f1 = fc.showOpenDialog(primaryStage);
							try {
								if (f1.length() != 0) {
									Scanner input;

									try {
										input = new Scanner(f1);
										while (input.hasNext()) {
											String Data = input.nextLine();
											String tokens[] = Data.split(",");
											Flight x = new Flight(Integer.parseInt(tokens[0]), tokens[1], tokens[2],
													tokens[3], Integer.parseInt(tokens[4]));
											ls.insertion(x);
										}

									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}

							} catch (NullPointerException e) {
								// TODO Auto-generated catch block

								Label l = new Label("Choose Flights file!!");
								l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
								l.setTextFill(Color.DARKRED);
								g.add(l, 0, 21);

							}
						}
					});

					back.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							primaryStage.setScene(scene);
						}
					});
				}

			});
			b2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					GridPane gp = new GridPane();

					gp.setAlignment(Pos.CENTER);
					gp.setStyle("-fx-background-image: url('flight.jpg')");
					Scene scene3 = new Scene(gp, 1000, 500);
					scene3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene3);
					primaryStage.show();

					Label t0 = new Label("Flight's Information");
					t0.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					t0.setTextFill(Color.BEIGE);
					t0.setAlignment(Pos.TOP_CENTER);

					TextArea area = new TextArea();
					area.setStyle("-fx-font-size: 14pt;");
					area.setPrefHeight(350);
					area.setPrefWidth(1000);
					VBox h1 = new VBox();
					Button back = new Button("Back");
					back.setFont(Font.font(15));
					h1.getChildren().addAll(t0, area, back);
					h1.setAlignment(Pos.CENTER);
					h1.setPadding(new Insets(50, 50, 50, 50));
					gp.add(h1, 0, 5);
					area.appendText(ls.traverse());

					back.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							primaryStage.setScene(scene);
						}

					});

				}

			});
			b3.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					GridPane gp = new GridPane();
					gp.setPadding(new Insets(50, 0, 0, 0));
					gp.setAlignment(Pos.CENTER);
					gp.setStyle("-fx-background-image: url('flight.jpg')");
					Scene scene2 = new Scene(gp, 1000, 500);
					scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene2);
					primaryStage.show();

					HBox x = new HBox();
					Label t7 = new Label("Enter Flight's Number");
					t7.setFont(Font.font(25));
					t7.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
					TextField l7 = new TextField();
					l7.setMinSize(150, 30);
					Button ok = new Button("Click");
					ok.setFont(Font.font(15));
					x.getChildren().addAll(t7, l7, ok);
					x.setAlignment(Pos.CENTER);
					x.setSpacing(15);
					x.setPadding(new Insets(15, 15, 15, 15));
					gp.add(x, 0, 3);

					TextArea area = new TextArea();
					area.setStyle("-fx-font-size: 14pt;");
					area.setPrefHeight(300);
					area.setPrefWidth(120);
					VBox h1 = new VBox();
					h1.getChildren().add(area);
					h1.setAlignment(Pos.CENTER);
					gp.add(h1, 0, 5);

					ok.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							try {
								if (ls.search(Integer.parseInt(l7.getText())) == true) {
									area.appendText(ls.PrintPassinfo(Integer.parseInt(l7.getText())) + "\n");
								} else
									area.appendText("No Flights with this number" + "\n");

							} catch (NumberFormatException ex) {
								Label l = new Label("Wrong data type were entered!");
								l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
								l.setTextFill(Color.DARKRED);
								gp.add(l, 0, 6);
							}
						}
					});

					Button back = new Button("Back");
					back.setFont(Font.font(15));
					HBox H = new HBox();

					H.getChildren().addAll(back);
					H.setAlignment(Pos.BOTTOM_CENTER);
					gp.add(H, 0, 10);

					back.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							primaryStage.setScene(scene);
						}

					});

				}

			});

			b4.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					GridPane g = new GridPane();
					g.setStyle("-fx-background-image: url('flight.jpg')");
					Scene scene4 = new Scene(g, 1000, 500);
					scene4.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene4);
					primaryStage.show();

					VBox v = new VBox();
					RadioButton ch1 = new RadioButton("Add Flight");
					ch1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

					RadioButton ch2 = new RadioButton("Edit Flight");
					ch2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
					ToggleGroup t = new ToggleGroup(); // IMPORTANT مشان يتنقل بالخيارات
					v.setSpacing(15);
					v.setAlignment(Pos.CENTER);
					ch1.setToggleGroup(t);
					ch2.setToggleGroup(t);
					v.getChildren().addAll(ch1, ch2);
					g.add(v, 0, 0);
					g.setAlignment(Pos.CENTER);
					Button back = new Button("Back");
					back.setAlignment(Pos.BOTTOM_CENTER);
					back.setFont(Font.font(15));
					g.add(back, 0, 1);
					back.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							primaryStage.setScene(scene);
						}

					});

					ch1.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							GridPane gp = new GridPane();
							gp.setStyle("-fx-background-image: url('flight.jpg')");
							Scene scene5 = new Scene(gp, 1000, 500);
							scene5.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(scene5);
							primaryStage.show();

							Label t1 = new Label("Flight Number");
							t1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t1, 0, 0);
							TextField l1 = new TextField();
							l1.setMinSize(70, 30);
							gp.add(l1, 1, 0);

							Label t2 = new Label("Airline Name");
							t2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t2, 0, 1);
							TextField l2 = new TextField();
							gp.add(l2, 1, 1);
							l2.setMinSize(70, 30);

							Label t3 = new Label("Source");
							t3.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t3, 0, 2);
							TextField l3 = new TextField();
							gp.add(l3, 1, 2);

							l3.setMinSize(70, 30);
							Label t4 = new Label("Destination");
							t4.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t4, 0, 3);
							TextField l4 = new TextField();
							gp.add(l4, 1, 3);

							l4.setMinSize(70, 30);
							Label t5 = new Label("Capacity");
							t5.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t5, 0, 4);
							TextField l5 = new TextField();
							gp.add(l5, 1, 4);
							l5.setMinSize(70, 30);
							gp.setAlignment(Pos.CENTER);
							gp.setHgap(5);
							gp.setVgap(10);

							Button back = new Button("Back");
							back.setFont(Font.font(15));
							Button add = new Button("Add");
							add.setFont(Font.font(15));
							HBox H = new HBox();

							H.getChildren().addAll(back, add);
							H.setAlignment(Pos.CENTER);
							H.setSpacing(10);
							gp.add(H, 1, 5);
							add.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									try {
										if (l1.getText() != null && l2.getText() != null && l3.getText() != null
												&& l4.getText() != null && l5.getText() != null) {
											if (ls.search(Integer.parseInt(l1.getText())) == false) {
												ls.insertion(new Flight(Integer.parseInt(l1.getText()), l2.getText(),
														l3.getText(), l4.getText(), Integer.parseInt(l5.getText())));

											} else {
												Label l = new Label("Sorry,this flight number is used");
												l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
												l.setTextFill(Color.DARKRED);
												gp.add(l, 0, 8);
											}
										}
									} catch (NumberFormatException ex) {
										Label l = new Label("Wrong data type were entered!");
										l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										l.setTextFill(Color.DARKRED);
										gp.add(l, 0, 8);
									}
								}

							});
							back.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									primaryStage.setScene(scene4);
								}

							});
						}
					});
					ch2.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub

							GridPane gp = new GridPane();
							gp.setStyle("-fx-background-image: url('flight.jpg')");
							Scene scene6 = new Scene(gp, 1000, 500);
							scene6.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(scene6);
							primaryStage.show();

							Label t0 = new Label("Flight Number");
							t0.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t0, 0, 0);
							TextField l0 = new TextField();
							l0.setMinSize(150, 30);
							gp.add(l0, 1, 0);

							Button b = new Button("Click");
							b.setFont(Font.font(15));
							gp.add(b, 0, 1);

							Label t1 = new Label("New flight Number");
							t1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t1, 0, 2);
							TextField l1 = new TextField();
							l1.setMinSize(150, 30);
							gp.add(l1, 1, 2);
							l1.setDisable(true);

							Label t2 = new Label("Airline Name");
							t2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t2, 0, 3);
							TextField l2 = new TextField();
							gp.add(l2, 1, 3);
							l2.setMinSize(150, 30);
							l2.setDisable(true);

							Label t3 = new Label("Source");
							t3.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t3, 0, 4);
							TextField l3 = new TextField();
							gp.add(l3, 1, 4);
							l3.setDisable(true);
							l3.setMinSize(150, 30);

							Label t4 = new Label("Destination");
							t4.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t4, 0, 5);
							TextField l4 = new TextField();
							gp.add(l4, 1, 5);
							l4.setDisable(true);
							l4.setMinSize(150, 30);

							Label t5 = new Label("Capacity");
							t5.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
							gp.add(t5, 0, 6);
							TextField l5 = new TextField();
							gp.add(l5, 1, 6);
							l5.setMinSize(150, 30);
							l5.setDisable(true);
							gp.setAlignment(Pos.CENTER);
							gp.setHgap(5);
							gp.setVgap(10);

							Button back = new Button("Back");
							back.setFont(Font.font(15));
							Button u = new Button("Update");
							u.setFont(Font.font(15));
							HBox H = new HBox();
							H.getChildren().addAll(back, u);
							H.setAlignment(Pos.CENTER);
							H.setSpacing(10);
							gp.add(H, 1, 7);
							b.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									try {
										if (ls.search(Integer.parseInt(l0.getText())) == true) {

											l1.setDisable(false);
											l2.setDisable(false);
											l3.setDisable(false);
											l4.setDisable(false);
											l5.setDisable(false);
											l1.setText(String.valueOf(Integer.parseInt(l0.getText())));

											l2.setText(ls.returnName(Integer.parseInt(l0.getText())));

											l3.setText(ls.returnSource(Integer.parseInt(l0.getText())));

											l4.setText(ls.returnDes(Integer.parseInt(l0.getText())));

											l5.setText(
													String.valueOf(ls.returnCapacity(Integer.parseInt(l0.getText()))));

										} else {
											Label t = new Label("This flight number is not found!");
											t.setTextFill(Color.DARKRED);
											t.setFont(Font.font(null, FontWeight.BOLD, 20));
											gp.add(t, 0, 8);
										}
									} catch (NumberFormatException ex) {
										Label l = new Label("Wrong data type were entered!");
										l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										l.setTextFill(Color.DARKRED);
										gp.add(l, 0, 8);
									}

								}
							});
							u.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									try {
										if (ls.search(Integer.parseInt(l0.getText())) == true) {
											if (ls.search(Integer.parseInt(l1.getText())) == false
													|| l1.getText().equals(l0.getText())) {

												Node<Flight> cur = ls.head;
												for (; cur != null; cur = cur.getNext()) {
													if (cur.getData().getNumber() == Integer.parseInt(l0.getText())) {
														cur.getData().getS()
																.UpdateFlightNum(Integer.parseInt(l1.getText()));
														cur.getData().setSource(l3.getText());
														cur.getData().setCapacity(Integer.parseInt(l5.getText()));
														cur.getData().setAirlineName(l2.getText());
														cur.getData().setDestination(l4.getText());
														cur.getData().setNumber(Integer.parseInt(l1.getText()));
													}

												}

											}
										}

									} catch (NumberFormatException ex) {
										Label l = new Label("Wrong data type were entered!");
										l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										l.setTextFill(Color.DARKRED);
										gp.add(l, 0, 9);
									}
								}

							});

							back.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									primaryStage.setScene(scene4);
								}
							});
						}
					});

				}
			});
			b5.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					// StackPane s = new StackPane();
					GridPane g = new GridPane();
					g.setStyle("-fx-background-image: url('flight.jpg')");
					Scene scene6 = new Scene(g, 1000, 500);
					scene6.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene6);
					primaryStage.show();

					Button check = new Button("Check");
					check.setFont(Font.font(15));
					HBox h = new HBox();

					Label t1 = new Label("Flight Number");
					t1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

					TextField l1 = new TextField();
					l1.setMinSize(150, 30);

					h.getChildren().addAll(t1, l1, check);
					h.setSpacing(10);
					h.setAlignment(Pos.CENTER);
					g.add(h, 0, 0);
					g.setAlignment(Pos.CENTER);
					TextArea area = new TextArea();
					area.setStyle("-fx-font-size: 14pt;");
					area.setPrefHeight(200);
					area.setPrefWidth(400);

					VBox h1 = new VBox();
					h1.getChildren().add(area);
					h1.setAlignment(Pos.TOP_LEFT);
					h1.setPadding(new Insets(10, 10, 10, 10));
					g.add(h1, 0, 2);
					Button back = new Button("Back");
					back.setFont(Font.font(15));
					HBox H = new HBox();

					H.getChildren().addAll(back);
					H.setAlignment(Pos.BOTTOM_CENTER);
					g.add(H, 0, 10);

					back.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							primaryStage.setScene(scene);
						}
					});

					area.appendText(ls.traverse() + "\n");

					check.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub

							GridPane gp = new GridPane();
							gp.setAlignment(Pos.CENTER);
							gp.setPadding(new Insets(10, 10, 10, 10));
							gp.setHgap(5);
							gp.setVgap(15);
							gp.setPadding(new Insets(50, 0, 0, 0));
							gp.setStyle("-fx-background-image: url('flight.jpg')");
							Scene scene7 = new Scene(gp, 1000, 500);
							scene7.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(scene7);
							primaryStage.show();
							Button back0 = new Button("Back");
							gp.add(back0, 0, 10);
							back0.setFont(Font.font(15));
							back0.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									primaryStage.setScene(scene6);
								}
							});

							try {
								if (ls.search(Integer.parseInt(l1.getText())) == true) {
									if (ls.checkCapacity(Integer.parseInt(l1.getText())) == true) {
										int s = 0;

										Node<Flight> cur = ls.head;
										for (; cur != null; cur = cur.getNext()) {
											if (cur.getData().getNumber() == Integer.parseInt(l1.getText())) {
												s = cur.getData().getS().returnMaximumTicket();
											}
										}
										Button Add = new Button("Add");
										Add.setFont(Font.font(15));
										HBox H = new HBox();
										H.getChildren().addAll(Add);
										H.setAlignment(Pos.BOTTOM_CENTER);
										H.setSpacing(10);
										gp.add(H, 1, 10);

										Label t1 = new Label("Flight Number");
										t1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										gp.add(t1, 0, 0);
										TextField l11 = new TextField(l1.getText());
										l11.setMinSize(150, 30);
										gp.add(l11, 1, 0);
										Label t2 = new Label("Ticket Number");
										t2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										gp.add(t2, 0, 1);
										TextField l2 = new TextField(String.valueOf(s));
										gp.add(l2, 1, 1);

										l2.setMinSize(150, 30);
										Label t3 = new Label("Full Name");
										t3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										gp.add(t3, 0, 2);
										TextField l3 = new TextField();
										gp.add(l3, 1, 2);
										l3.setMinSize(150, 30);
										Label t4 = new Label("Passport Number");
										t4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										gp.add(t4, 0, 3);
										TextField l4 = new TextField();
										gp.add(l4, 1, 3);

										l4.setMinSize(150, 30);
										Label t5 = new Label("Nationality");
										t5.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										gp.add(t5, 0, 4);
										TextField l5 = new TextField();
										gp.add(l5, 1, 4);

										l5.setMinSize(150, 30);
										Label t6 = new Label("Birth Date");
										t6.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
										gp.add(t6, 0, 5);

										DatePicker datePicker = new DatePicker();
										datePicker.setMinSize(150, 30);
										gp.add(datePicker, 1, 5);
										datePicker.setMinSize(150, 30);

										Add.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent arg0) {
												// TODO Auto-generated method stub
												try {
													LocalDate d = datePicker.getValue();
													// System.out.println(d.toString());
													int day = d.getDayOfMonth();
													int month = d.getMonthValue() - 1;
													int year = d.getYear();
													Calendar c = new GregorianCalendar(year, month, day);
													Passenger x = new Passenger(Integer.parseInt(l1.getText()),
															Integer.parseInt(l2.getText()), l3.getText(), l4.getText(),
															l5.getText(), c);

													Node<Flight> cur = ls.head;
													for (; cur != null; cur = cur.getNext()) {
														if (cur.getData().getNumber() == Integer
																.parseInt(l1.getText())) {
															if (ls.CheckPass(l3.getText(),
																	Integer.parseInt(l1.getText())) == false) {
																cur.getData().getS().insertion(x);
															} else {
																Label l = new Label("This Passenger name is added!");
																l.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
																l.setTextFill(Color.DARKRED);
																gp.add(l, 0, 6);
															}

														}
													}

												} catch (NumberFormatException ex) {
													Label l = new Label("Wrong data type were entered!");
													l.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
													l.setTextFill(Color.DARKRED);
													gp.add(l, 0, 6);
												} catch (NullPointerException ex) {
													Label l = new Label("Fill in textFields!");
													l.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
													l.setTextFill(Color.DARKRED);
													gp.add(l, 0, 6);
												}
											}

										});

									} else {
										Label t = new Label("Sorry,There is no enough capacity!");
										t.setTextFill(Color.DARKRED);
										t.setFont(Font.font(null, FontWeight.BOLD, 30));
										gp.add(t, 0, 3);

									}
								} else {
									Label t2 = new Label("This flight number is not found!");
									t2.setTextFill(Color.DARKRED);
									t2.setFont(Font.font(null, FontWeight.BOLD, 30));
									gp.add(t2, 0, 3);
								}

							} catch (NumberFormatException ex) {
								Label l = new Label("Wrong data type were entered!");
								l.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
								l.setTextFill(Color.DARKRED);
								gp.add(l, 0, 5);

							}

						}
					});

				}

			});

			b6.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					GridPane gp = new GridPane();
					gp.setAlignment(Pos.CENTER);
					gp.setPadding(new Insets(5, 0, 10, 10));
					gp.setHgap(5);
					gp.setVgap(15);
					gp.setStyle("-fx-background-image: url('flight.jpg')");
					Scene scene6 = new Scene(gp, 1000, 500);
					scene6.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene6);
					primaryStage.show();
					Button back = new Button("Back");
					back.setFont(Font.font(15));

					HBox x = new HBox();
					Label t7 = new Label("Enter Passenger's Name");
					t7.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField l7 = new TextField();
					l7.setMinSize(150, 30);
					x.getChildren().addAll(t7, l7);
					x.setSpacing(15);
					x.setPadding(new Insets(15, 15, 15, 15));

					Button d = new Button("Delete");
					d.setFont(Font.font(15));
					HBox h3 = new HBox();
					h3.setSpacing(15);
					h3.getChildren().addAll(back, d);
					h3.setAlignment(Pos.CENTER);

					Label xx = new Label("  Flight number");
					xx.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField t = new TextField();
					t.setMinSize(150, 30);
					HBox x2 = new HBox();
					x2.getChildren().addAll(xx, t);
					x2.setSpacing(15);

					VBox vv = new VBox();
					vv.getChildren().addAll(x, x2);
					vv.setAlignment(Pos.CENTER);

					gp.add(vv, 0, 0);
					gp.add(h3, 0, 1);

					d.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							try {

								if (l7.getText() != null && t.getText() != null) {
									if (ls.CheckPass(l7.getText()) == true) {
										Label l = new Label("Deleted! ");
										l.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
										l.setTextFill(Color.DARKRED);
										gp.add(l, 0, 2);
										Node<Flight> cur = ls.head;
										for (; cur != null; cur = cur.getNext()) {
											if (cur.getData().getNumber() == Integer.parseInt(t.getText()))
												cur.getData().getS()
														.delete(cur.getData().getS().returnPassenger(l7.getText()));
										}

									}

									else {
										Label l = new Label("Wrong flight number ");
										l.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
										l.setTextFill(Color.DARKRED);
										gp.add(l, 0, 2);
									}
								} else {
									Label l = new Label("Fill in the textFields!  ");
									l.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
									l.setTextFill(Color.DARKRED);
									gp.add(l, 0, 2);

								}

							} catch (NumberFormatException ex) {
								Label l = new Label("Wrong data type were entered!");
								l.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
								l.setTextFill(Color.DARKRED);
								gp.add(l, 0, 5);
							}
						}
					});

					back.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							primaryStage.setScene(scene);
						}
					});
				}
			});
			b7.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					GridPane gp = new GridPane();
					gp.setAlignment(Pos.CENTER);
					gp.setPadding(new Insets(30, 0, 10, 10));
					gp.setHgap(5);
					gp.setVgap(15);
					gp.setStyle("-fx-background-image: url('flight.jpg')");
					Scene scene7 = new Scene(gp, 1000, 500);
					scene7.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene7);
					primaryStage.show();

					HBox x = new HBox();
					Label t7 = new Label("Enter Passenger's Name");
					t7.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField l7 = new TextField();
					l7.setMinSize(150, 30);
					x.getChildren().addAll(t7, l7);
					x.setAlignment(Pos.CENTER);
					x.setSpacing(15);
					x.setPadding(new Insets(15, 15, 15, 15));

					Label xx = new Label("  Flight number");
					xx.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField t = new TextField();
					t.setMinSize(150, 30);
					HBox x2 = new HBox();
					x2.getChildren().addAll(xx, t);
					x2.setSpacing(15);

					Button click = new Button("Click");
					click.setFont(Font.font(15));
					Button back = new Button("Back");
					back.setFont(Font.font(15));
					VBox v = new VBox();
					v.setSpacing(15);
					HBox x3 = new HBox();
					x3.getChildren().addAll(back, click);
					x3.setAlignment(Pos.BOTTOM_CENTER);
					x3.setSpacing(15);
					v.getChildren().addAll(x, x2, x3);
					gp.add(v, 0, 0);

					click.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub

							try {
								if (ls.search(Integer.parseInt(t.getText())) == true) {
									Node<Flight> cur = ls.head;
									for (; cur != null; cur = cur.getNext()) {
										if (cur.getData().getNumber() == Integer.parseInt(t.getText())) {
											if (cur.getData().getS().searchPass(l7.getText()) == true) {
												Label l = new Label("Yes it's reserved on the flight ");
												l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
												l.setTextFill(Color.DARKRED);
												l.setAlignment(Pos.CENTER);
												gp.add(l, 0, 1);

											} else if (cur.getData().getS().searchPass(l7.getText()) == false) {
												Label l = new Label(
														"Sorry! " + l7.getText() + " is not reserved on the flight ");
												l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
												l.setTextFill(Color.DARKRED);
												l.setAlignment(Pos.BOTTOM_CENTER);
												gp.add(l, 0, 1);
											}
										}
									}

								} else {
									Label l = new Label("Wrong flight number ");
									l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
									l.setTextFill(Color.DARKRED);
									gp.add(l, 0, 2);
								}

							} catch (NumberFormatException ex) {
								Label l = new Label("Wrong data type were entered!");
								l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
								l.setTextFill(Color.DARKRED);
								gp.add(l, 0, 1);
							}

						}

					});

					back.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							primaryStage.setScene(scene);
						}
					});
				}
			});

			b8.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					GridPane gp = new GridPane();
					gp.setAlignment(Pos.CENTER);
					gp.setPadding(new Insets(40, 0, 10, 10));
					gp.setHgap(5);
					gp.setVgap(10);
					gp.setStyle("-fx-background-image: url('flight.jpg')");
					Scene scene6 = new Scene(gp, 1000, 500);
					scene6.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene6);
					primaryStage.show();

					Label t1 = new Label("Flight Number      ");
					t1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

					TextField l1 = new TextField();
					l1.setMinSize(150, 30);
					l1.setDisable(true);
					HBox h1 = new HBox(0);
					h1.setSpacing(15);
					h1.getChildren().addAll(t1, l1);
					gp.add(h1, 0, 2);

					Label t2 = new Label("Ticket Number      ");
					t2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField l2 = new TextField();
					l2.setDisable(true);
					l2.setMinSize(150, 30);
					HBox h2 = new HBox(0);
					h2.getChildren().addAll(t2, l2);
					h2.setSpacing(15);
					gp.add(h2, 0, 3);

					Label t3 = new Label("Full Name             ");
					t3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField l3 = new TextField();
					l3.setDisable(true);
					l3.setMinSize(150, 30);
					HBox h3 = new HBox(0);
					h3.setSpacing(15);
					h3.getChildren().addAll(t3, l3);
					gp.add(h3, 0, 4);

					Label t4 = new Label("Passport Number ");
					t4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField l4 = new TextField();
					l4.setDisable(true);
					l4.setMinSize(150, 30);
					HBox h4 = new HBox();
					h4.setSpacing(15);
					h4.getChildren().addAll(t4, l4);
					gp.add(h4, 0, 5);

					Label t5 = new Label("Nationality            ");
					t5.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField l5 = new TextField();
					l5.setDisable(true);
					l5.setMinSize(150, 30);
					HBox h5 = new HBox(0);
					h5.setSpacing(15);
					h5.getChildren().addAll(t5, l5);
					gp.add(h5, 0, 6);

					Label t6 = new Label("Birth Date             ");
					t6.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					TextField l6 = new TextField();
					l6.setDisable(true);
					l6.setMinSize(150, 30);
					HBox h6 = new HBox(0);
					h6.setSpacing(15);
					h6.getChildren().addAll(t6, l6);
					gp.add(h6, 0, 7);

					HBox x = new HBox();
					Label t7 = new Label("Enter Passenger's Name");
					t7.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					Button click = new Button("Click");
					click.setFont(Font.font(15));
					TextField l7 = new TextField();
					l7.setMinSize(150, 30);
					x.getChildren().addAll(t7, l7);
					x.setSpacing(15);

					gp.add(x, 0, 0);
					gp.add(click, 0, 1);

					Button back = new Button("Back");
					back.setFont(Font.font(15));

					gp.add(back, 0, 8);
					click.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub

							if (l7.getText() != null) {
								if (ls.CheckPass(l7.getText()) == true) {
									l1.setDisable(false);
									l2.setDisable(false);
									l3.setDisable(false);
									l4.setDisable(false);
									l5.setDisable(false);
									l6.setDisable(false);

									l3.setText(l7.getText());
									l2.setText(String.valueOf((ls.returnTicket(l7.getText()))));
									l1.setText(String.valueOf(ls.returnFlightNum(l7.getText())));
									l4.setText(ls.returnPassNum(l7.getText()));
									l5.setText(ls.returnNationaltiy(l7.getText()));
									l6.setText(ls.returnBirth(l7.getText()));

								} else {
									Label l = new Label("Sorry!" + l7.getText() + " is not found ");
									l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
									l.setTextFill(Color.DARKRED);
									l.setAlignment(Pos.BOTTOM_CENTER);
									gp.add(l, 0, 9);
								}
							} else {
								Label l = new Label("Fill in the textField!  ");
								l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
								l.setTextFill(Color.DARKRED);
								l.setAlignment(Pos.BOTTOM_CENTER);
								gp.add(l, 0, 9);

							}

						}
					});

					back.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							primaryStage.setScene(scene);
						}
					});
				}
			});
			b9.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.exit(0);
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
