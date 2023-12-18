package hockeycoach.supportClasses;

public class NodeStatus {
    public enum StatusType {
        EDIT, SELECTED, IDLE, NEW
    }

    private StatusType currentStatus;

    public NodeStatus() {
        this.currentStatus = StatusType.IDLE; // Default status
    }

    public StatusType getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(StatusType statusType) {
        this.currentStatus = statusType;
    }
}
