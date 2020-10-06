package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.9);
	private Divisa dolar = new Divisa("Dolar", 1.2);
	private Divisa yen = new Divisa("Yen", 133.6);

	private Divisa[] divisas = { euro, libra, dolar, yen };

	private TextField origenText, destinoText;
	private Button cambiarButton;
	private ComboBox<Divisa> origenCombo, destinoCombo;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		origenText = new TextField();
		origenText.setPrefColumnCount(5);
		origenText.setText("0");

		destinoText = new TextField();
		destinoText.setPrefColumnCount(5);
		destinoText.setText("0");
		destinoText.setEditable(false);

		cambiarButton = new Button();
		cambiarButton.setText("Cambiar");
		cambiarButton.setOnAction(e -> onCambiarButtonAction(e));

		origenCombo = new ComboBox<Divisa>();
		origenCombo.getItems().addAll(divisas);
		origenCombo.getSelectionModel().select(euro);

		destinoCombo = new ComboBox<Divisa>();
		destinoCombo.getItems().addAll(divisas);
		destinoCombo.getSelectionModel().select(libra);

		HBox HB1 = new HBox();
		HB1.setAlignment(Pos.BASELINE_CENTER);
		HB1.getChildren().addAll(origenText, origenCombo);

		HBox HB2 = new HBox();
		HB2.setAlignment(Pos.BASELINE_CENTER);
		HB2.getChildren().addAll(destinoText, destinoCombo);

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(HB1, HB2, cambiarButton);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Cambio Divisa");
		primaryStage.show();
	}

	private void onCambiarButtonAction(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(origenText.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("No has introducido nada");

			alert.showAndWait();
		}
		
		if (origenText.getText().matches("^[a-zA-Z]+$")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Has introducido una letra, pon un número");

			alert.showAndWait();
		}
		
		Double cantidad1 = Double.parseDouble(origenText.getText());
		Divisa divisa1 = origenCombo.getSelectionModel().getSelectedItem();
		Divisa divisa2 = destinoCombo.getSelectionModel().getSelectedItem();

		Double resultado = divisa2.fromEuro(divisa1.toEuro(cantidad1));
		destinoText.setText("" + resultado);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
