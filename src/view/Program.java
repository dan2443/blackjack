package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Program extends Application {
	private Stage primaryStage;
	private BorderPane layout;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("BlackJack");

		initLayout();

	}

	private void initLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
			layout = (BorderPane) loader.load();
			Scene scene = new Scene(layout);
			primaryStage.setScene(scene);
			primaryStage.show();
			gameController controller = loader.getController();
			controller.startGame();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
