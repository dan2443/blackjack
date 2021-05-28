package view;

import gameEngine.GameManager;
import gameEngine.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class gameController {

	@FXML
	private Button hit;
	@FXML
	private Button stand;
	@FXML
	private Button tryAgain;
	@FXML
	private ImageView pcard0;
	@FXML
	private ImageView pcard1;
	@FXML
	private ImageView pcard2;
	@FXML
	private ImageView pcard3;
	@FXML
	private ImageView pcard4;
	@FXML
	private ImageView pcard5;
	@FXML
	private ImageView dcard0;
	@FXML
	private ImageView dcard1;
	@FXML
	private ImageView dcard2;
	@FXML
	private ImageView dcard3;
	@FXML
	private ImageView dcard4;
	@FXML
	private ImageView dcard5;
	@FXML
	private Text status;
	@FXML
	private Text playerScore;
	@FXML
	private Text dealerScore;

	private ImageView[] pcards;
	private ImageView[] dcards;
	private final Image hiddenCard = new Image("/images/cardBack.png");

	private GameManager game;

	public gameController() {
	}

	public void init() {
		pcards = new ImageView[6];
		dcards = new ImageView[6];
		pcards[0] = pcard0;
		pcards[1] = pcard1;
		pcards[2] = pcard2;
		pcards[3] = pcard3;
		pcards[4] = pcard4;
		pcards[5] = pcard5;
		dcards[0] = dcard0;
		dcards[1] = dcard1;
		dcards[2] = dcard2;
		dcards[3] = dcard3;
		dcards[4] = dcard4;
		dcards[5] = dcard5;
	}

	public void startGame() {
		init();
		game = new GameManager();
		game.init();
		game.getPlayer().hit();
		lastCardImageLoader(game.getPlayer(), pcards);
		game.getDealer().hit();
		lastCardImageLoader(game.getDealer(), dcards);
		game.getPlayer().hit();
		lastCardImageLoader(game.getPlayer(), pcards);
		game.getDealer().hit();
		dcards[1].setImage(hiddenCard);
		playerScore.setText("Score: " + Integer.toString(game.getPlayer().getTotalValue()));

	}

	public void lastCardImageLoader(Player player, ImageView[] pcards) {
		int i = player.getAmountOfCards() - 1; // the index of the recent card
		pcards[i].setImage(player.getDeck()[i].getCardImage());
	}

	public void handleHit() {
		game.getPlayer().hit();
		lastCardImageLoader(game.getPlayer(), pcards);
		playerScore.setText("Score: " + Integer.toString(game.getPlayer().getTotalValue()));
		if (game.isLost(game.getPlayer()))
			gameOver();
	}

	public void handleStand() {
		dealerDraw();
		gameOver();

	}

	public void gameOver() {
		if (game.isLost(game.getPlayer()))
			gameOverPopup("Player lost!\nScored over 21");
		else if (game.isLost(game.getDealer()))
			gameOverPopup("Player won!\nDealer Scored over 21");
		else if (game.isWon(game.getPlayer(), game.getDealer()))
			gameOverPopup("Player won!\nScored more then dealer");
		else if (game.isWon(game.getDealer(), game.getPlayer()))
			gameOverPopup("Player lost!\nScored less then dealer");
		else
			gameOverPopup("Tie!");

	}

	public void gameOverPopup(String result) {
		Stage newStage = new Stage();
		VBox comp = new VBox();
		comp.setAlignment(Pos.CENTER);
		comp.setSpacing(10);
		Text resultText = new Text(result);
		resultText.setStyle("-fx-font: 22 family;");
		Button playAgainB = new Button("Play again");
		playAgainB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				restartGame();
				newStage.close();

			}
		});
		Button exitB = new Button("Exit");
		exitB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		comp.getChildren().add(resultText);
		comp.getChildren().add(playAgainB);
		comp.getChildren().add(exitB);
		Scene stageScene = new Scene(comp, 250, 150);
		newStage.setScene(stageScene);
		newStage.show();
	}

	public void restartGame() {
		for (int i = 0; i < pcards.length; i++)
			pcards[i].setImage(null);
		for (int i = 0; i < dcards.length; i++)
			dcards[i].setImage(null);
		playerScore.setText("Score:");
		dealerScore.setText("Score: ?");
		startGame();
	}

	public void dealerDraw() {
		lastCardImageLoader(game.getDealer(), dcards);
		while (game.getDealer().getTotalValue() < 17) {
			game.getDealer().hit();
			lastCardImageLoader(game.getDealer(), dcards);
		}
		dealerScore.setText("Score: " + Integer.toString(game.getDealer().getTotalValue()));
	}

}
