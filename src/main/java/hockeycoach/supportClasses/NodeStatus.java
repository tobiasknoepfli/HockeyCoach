package hockeycoach.supportClasses;

import org.w3c.dom.Node;

public class NodeStatus {
    boolean editStatus, selectedStatus, initializeStatus, idleStatus;

    public NodeStatus(){}

    public void selectStatus(){

        if(editStatus=true){
            selectedStatus=false;
            initializeStatus=false;
            idleStatus=false;
        }
    }

    public boolean isEditStatus() {
        return editStatus;
    }

    public void setEditStatus(boolean editStatus) {
        this.editStatus = editStatus;
    }

    public boolean isSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(boolean selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public boolean isInitializeStatus() {
        return initializeStatus;
    }

    public void setInitializeStatus(boolean initializeStatus) {
        this.initializeStatus = initializeStatus;
    }

    public boolean isIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(boolean idleStatus) {
        this.idleStatus = idleStatus;
    }
}
