package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class Controller {

  // Get the pane to put the calendar on
  @FXML
  Pane calendarPane;

  @FXML
  private TableColumn<Event, LocalDate> eventsData;

  @FXML
  private TableColumn<Event, String> eventsTitle;

  @FXML
  private TableColumn<Event, String> eventsTag;

  @FXML
  private TableColumn<Event, String> eventsPlace;

  @FXML
  private TableColumn<Event, String> eventsImportance;

  @FXML
  private TableView<Event> eventsTable;

  @FXML
  private JFXButton deleteEventButton;

  @FXML
  private JFXButton addEventButton;

  @FXML
  private JFXTextField filterEventField;

  @FXML
  static ObservableList<Event> events = FXCollections.observableArrayList();

  public ObservableList<Event> getNotesData() {
    return events;
  }

  FilteredList<Event> filteredData;

  @FXML
  void initialize() {
    eventsData.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("date"));
    eventsTitle.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
    eventsTag.setCellValueFactory(new PropertyValueFactory<Event, String>("tag"));
    eventsPlace.setCellValueFactory(new PropertyValueFactory<Event, String>("place"));
    eventsImportance.setCellValueFactory(new PropertyValueFactory<Event, String>("importance"));
    eventsTable.setItems(events);

    filteredData = new FilteredList<>(events, p -> true);

    filterEventField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredData.setPredicate(event -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (event.getTitle().toLowerCase().contains(lowerCaseFilter)) {
          return true;
        } else if (event.getTag().toLowerCase().contains(lowerCaseFilter)) {
          return true;
        }
        return false;
      });
    });

    SortedList<Event> sortedData = new SortedList<>(filteredData);

    sortedData.comparatorProperty().bind(eventsTable.comparatorProperty());
    eventsTable.setItems(sortedData);
  }

  public void handleNewEvent(ActionEvent actionEvent) throws IOException {
    Stage stage = (Stage) addEventButton.getScene().getWindow();
    stage.close();
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addevent.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Создание события");
    stage.setScene(new Scene(root1));
    stage.show();

    AddEventController controller = fxmlLoader.getController();
    controller.setDialogStage(stage);
  }

  public void handleDeleteEvent(ActionEvent actionEvent) {
    int selectedIndex = eventsTable.getSelectionModel().getSelectedIndex();

    if (selectedIndex >= 0) {
      filteredData.getSource().remove(selectedIndex);
    } else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("No Selection");
      alert.setHeaderText("No event Selected");
      alert.setContentText("Please select an event in the table.");

      alert.showAndWait();
    }
  }
}
