package boundary;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;

public abstract class CommandProducer {
	
    private List<CommandExecution> executionList = new ArrayList<>();

    public void addExecution(CommandExecution exe) {
        executionList.add(exe);
    }

    public void removeExecution(CommandExecution exe) {
        executionList.remove(exe);
    }

    public void executeCommand(Pane top, Pane left) {
        for (CommandExecution exe : executionList) {
            exe.execute(top, left);
        }
    }
    
}