package watodo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import watodo.model.task.ReadOnlyTask;

public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private FlowPane tags;
    @FXML
    private Label status;
    
    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        name.setText(task.getName().fullName);
        id.setText(displayedIndex + ". ");
        startTime.setText(task.getStartTime().value);
        endTime.setText(task.getEndTime().value);
        status.setText(task.getStatus().toString());
        setBackground(task);
        initTags(task);
    }

    //@@author A0130138U
    private void setBackground(ReadOnlyTask task) {

        if(task.getStatus().status == 1) {
            cardPane.setStyle("-fx-background-color: #C4C4B7");
        } else if(task.isTodo()) {
            cardPane.setStyle("-fx-background-color: #6699CC");
        } else if (task.isDeadline()) {
            cardPane.setStyle("-fx-background-color: #E3CA73");
        } else if (task.isEvent()) {
            cardPane.setStyle("-fx-background-color: #7F9F7F");
        }
    }

    //@@author
    private void initTags(ReadOnlyTask task) {
        task.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
