package hockeycoach.supportClasses;

import hockeycoach.mainClasses.Stadium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.function.Predicate;

public class SearchBox {
    public void searchStadium(String searchString, List<Stadium> list, TableView<Stadium> tableView){
        ObservableList<Stadium> list1 = FXCollections.observableArrayList(list);
        tableView.getSelectionModel().clearSelection();
        searchString = searchString.trim();
        String[] searchWords = searchString.split("\\s+");

        Predicate<Stadium> filterPredicate =stadium ->{
            if(searchWords.length == 0){
                return true;
            }
            for (String word:searchWords){
                if(word.isEmpty()){
                    continue;
                }
                if(stadium.getStadiumName().toLowerCase().contains(word.toLowerCase()) ||
                stadium.getStadiumAddress().toLowerCase().contains(word.toLowerCase()) ||
                        String.valueOf(stadium.getStadiumZip()).equals(word) ||
                        stadium.getStadiumCity().toLowerCase().contains(word.toLowerCase()) ||
                        stadium.getStadiumCountry().toLowerCase().contains(word.toLowerCase())){
                    return true;
                }

            } return false;

        };
        FilteredList<Stadium> filteredList = new FilteredList<>(list1,filterPredicate);
        tableView.setItems(filteredList);
    }

    public void clearStadium(TextField searchField, List<Stadium> list, TableView<Stadium> tableView){
        searchField.clear();
        tableView.setItems(FXCollections.observableArrayList(list));
    }
}
