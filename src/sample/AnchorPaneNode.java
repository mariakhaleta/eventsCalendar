package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;


public class AnchorPaneNode extends AnchorPane {

    private LocalDate date;

    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> {
          try {
            addEvent(date);
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        });
    }

    public void addEvent(LocalDate date) throws IOException {
      Stage stage = (Stage) getScene().getWindow();
      stage.close();
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addevent.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Создание события");
      stage.setScene(new Scene(root1));
      stage.show();

      AddEventController controller = fxmlLoader.getController();
      controller.initDate(date);
      controller.setDialogStage(stage);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
