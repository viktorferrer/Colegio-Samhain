package boundary;

import javafx.scene.layout.Pane;

public interface CommandExecution {
    void execute(Pane top, Pane left);
}