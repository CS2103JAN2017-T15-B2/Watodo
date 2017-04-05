package watodo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView status;
    @FXML
    private ImageView priority;

    private static final String TICK = "/images/tick.png";
    private static final String CROSS = "/images/cross.png";
    private static final String LOW = "/images/low.png";
    private static final String MED = "/images/med.png";
    private static final String HIGH = "/images/high.png";

    Image tick = new Image(TICK);
    Image cross = new Image(CROSS);
    Image low = new Image(LOW);
    Image med = new Image(MED);
    Image high = new Image(HIGH);

    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        name.setText(task.getName().fullName);
        id.setText(displayedIndex + ". ");
        startTime.setText(task.getStartTime().value);
        endTime.setText(task.getEndTime().value);
        setBackground(task);
        setStatusIcon(task);
        setPriorityIcon(task);
        initTags(task);
    }

    private void setPriorityIcon(ReadOnlyTask task) {
        if (task.getPriority().priorityLevel == "high"){
            priority.setImage(high);
        } else if (task.getPriority().priorityLevel == "med"){
            priority.setImage(med);
        } else if (task.getPriority().priorityLevel == "low"){
            priority.setImage(low);
        }
    }

    private void setStatusIcon(ReadOnlyTask task) {
        if (task.getStatus().status == 0){
            status.setImage(cross);
        } else if (task.getStatus().status == 1){
            status.setImage(tick);
        }
    }

    //@@author A0130138U
    private void setBackground(ReadOnlyTask task) {

        if (task.getStatus().status == 1) {
            cardPane.setStyle("-fx-background-color: #C4C4B7");
        } else if (task.isTodo()) {
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
