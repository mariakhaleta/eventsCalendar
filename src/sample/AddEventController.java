package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;


public class AddEventController {

  @FXML
  private DatePicker meetData;

  @FXML
  private JFXTextField meetTitle;

  @FXML
  private ChoiceBox<String> meetTag;

  @FXML
  private JFXButton backButton;

  @FXML
  private JFXButton addButton;

  @FXML
  private ChoiceBox<String> meetImportantChoice;

  @FXML
  private JFXTextField meetPlace;

  @FXML
  void initialize() {
    meetImportantChoice.getItems().add("Important");
    meetImportantChoice.getItems().add("Medium");
    meetImportantChoice.getItems().add("Less important");

    meetTag.getItems().add("Work");
    meetTag.getItems().add("Finance");
    meetTag.getItems().add("University");
  }

  void setDialogStage(Stage stage) {
  }

  void initDate(LocalDate localDate) {
    meetData.setValue(localDate);
  }

  @FXML
  public void handleAdd(ActionEvent actionEvent) throws IOException {
    if (isInputValid()) {
      Controller.events.add(new Event(meetTitle.getText(), meetData.getValue(), meetPlace.getText(), meetTag.getValue(), meetImportantChoice.getValue()));

      Stage stage = (Stage) backButton.getScene().getWindow();
      stage.close();
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fullCalendar.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(new Scene(root1));
      Controller controller = fxmlLoader.getController();
      controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
      stage.show();
    }
  }

  @FXML
  public void handleCancel(ActionEvent actionEvent) throws IOException {
    Stage stage = (Stage) backButton.getScene().getWindow();
    stage.close();
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fullCalendar.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(new Scene(root1));
    Controller controller = fxmlLoader.getController();
    controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
    stage.show();
  }

  private boolean isInputValid() {
    String errorMessage = "";

    if (meetTitle.getText() == null || meetTitle.getText().length() == 0) {
      errorMessage += "Type title!\n";
    }

    if (meetPlace.getText() == null || meetPlace.getText().length() == 0) {
      errorMessage += "Type place!\n";
    }

    if (errorMessage.length() == 0) {
      return true;
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid Fields");
      alert.setHeaderText("Please correct invalid fields");
      alert.setContentText(errorMessage);

      alert.showAndWait();

      return false;
    }
  }
}
