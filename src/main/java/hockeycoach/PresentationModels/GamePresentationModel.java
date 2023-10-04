package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader;
import hockeycoach.mainClasses.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;

import java.util.List;

public class GamePresentationModel extends PresentationModel {
    Button saveButton;
    Button cancelButton;

    TableView<Game> allGames;
    TextField gameTeam;
    TextField gameOpponent;
    TextField gameDate;
    TextField gameTime;
    TextField gameStadium;
    TextField captain;
    TextField assistant1;
    TextField assistant2;
    Button refreshPlayerList;
    TableView<Player> teamPlayers;
    TabPane lineupTabPane;
    AnchorPane lineupAnchorPane;
    ImageView boardImage;
    GridPane lineupGrid;
    TextField gk1;
    TextField dl1;
    TextField dl2;
    TextField dl3;
    TextField dl4;
    TextField dr1;
    TextField dr2;
    TextField dr3;
    TextField dr4;
    TextField c1;
    TextField c2;
    TextField c3;
    TextField c4;
    TextField fl1;
    TextField fl2;
    TextField fl3;
    TextField fl4;
    TextField fr1;
    TextField fr2;
    TextField fr3;
    TextField fr4;
    TextField sgk1;
    TextField sgk2;
    TextField sgk3;
    TextField sd1;
    TextField sd2;
    TextField sd3;
    TextField sf1;
    TextField sf2;
    TextField sf3;
    AnchorPane ppAnchorPane;
    ImageView ppBoardImage;
    GridPane ppLineupGrid;
    TextField ppdl1;
    TextField ppdl2;
    TextField ppdlfiller;
    TextField ppdr1;
    TextField ppdr2;
    TextField ppdrfiller;
    TextField ppc1;
    TextField ppc2;
    TextField ppcfiller;
    TextField ppfl1;
    TextField ppfl2;
    TextField ppflfiller;
    TextField ppfr1;
    TextField ppfr2;
    TextField ppfrfiller;
    AnchorPane bpAnchorPane;
    ImageView bpBoardImage;
    GridPane bpLineupGrid;
    TextField bpdl1;
    TextField bpdl2;
    TextField bpdlfiller;
    TextField bpdr1;
    TextField bpdr2;
    TextField bpdrfiller;
    TextField bpfl1;
    TextField bpfl2;
    TextField bpflfiller;
    TextField bpfr1;
    TextField bpfr2;
    TextField bpfrfiller;
    TextField bpsd1;
    TextField bpsd2;
    TextField bpsf1;
    TextField bpsf2;

    DBLoader dbLoader = new DBLoader();
    List<Game> allGameList;
    Team selectedTeam;


    @Override
    public void initializeControls(Pane root) {
        saveButton = (Button) root.lookup("#saveButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        allGames = (TableView) root.lookup("#allGames");
        gameTeam = (TextField) root.lookup("#gameTeam");
        gameOpponent = (TextField) root.lookup("#gameOpponent");
        gameDate = (TextField) root.lookup("#gameDate");
        gameTime = (TextField) root.lookup("#gameTime");
        gameStadium = (TextField) root.lookup("#gameStadium");
        captain = (TextField) root.lookup("#captain");
        assistant1 = (TextField) root.lookup("#assistant1");
        assistant2 = (TextField) root.lookup("#assistant2");
        refreshPlayerList = (Button) root.lookup("#refreshPlayerList");
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        lineupTabPane = (TabPane) root.lookup("#lineupTabPane");
        lineupAnchorPane = (AnchorPane) root.lookup("#lineupAnchorPane");
        boardImage = (ImageView) root.lookup("#boardImage");
        lineupGrid = (GridPane) root.lookup("#lineupGrid");
        gk1 = (TextField) root.lookup("#gk1");
        dl1 = (TextField) root.lookup("#dl1");
        dl2 = (TextField) root.lookup("#dl2");
        dl3 = (TextField) root.lookup("#dl3");
        dl4 = (TextField) root.lookup("#dl4");
        dr1 = (TextField) root.lookup("#dr1");
        dr2 = (TextField) root.lookup("#dr2");
        dr3 = (TextField) root.lookup("#dr3");
        dr4 = (TextField) root.lookup("#dr4");
        c1 = (TextField) root.lookup("#c1");
        c2 = (TextField) root.lookup("#c2");
        c3 = (TextField) root.lookup("#c3");
        c4 = (TextField) root.lookup("#c4");
        fl1 = (TextField) root.lookup("#fl1");
        fl2 = (TextField) root.lookup("#fl2");
        fl3 = (TextField) root.lookup("#fl3");
        fl4 = (TextField) root.lookup("#fl4");
        fr1 = (TextField) root.lookup("#fr1");
        fr2 = (TextField) root.lookup("#fr2");
        fr3 = (TextField) root.lookup("#fr3");
        fr4 = (TextField) root.lookup("#fr4");
        sgk1 = (TextField) root.lookup("#sgk1");
        sgk2 = (TextField) root.lookup("#sgk2");
        sgk3 = (TextField) root.lookup("#sgk3");
        sd1 = (TextField) root.lookup("#sd1");
        sd2 = (TextField) root.lookup("#sd2");
        sd3 = (TextField) root.lookup("#sd3");
        sf1 = (TextField) root.lookup("#sf1");
        sf2 = (TextField) root.lookup("#sf2");
        sf3 = (TextField) root.lookup("#sf3");
        ppAnchorPane = (AnchorPane) root.lookup("#ppAnchorPane");
        ppBoardImage = (ImageView) root.lookup("#ppBoardImage");
        ppLineupGrid = (GridPane) root.lookup("#ppLineupGrid");
        ppdl1 = (TextField) root.lookup("#ppdl1");
        ppdl2 = (TextField) root.lookup("#ppdl2");
        ppdlfiller = (TextField) root.lookup("#ppdlfiller");
        ppdr1 = (TextField) root.lookup("#ppdr1");
        ppdr2 = (TextField) root.lookup("#ppdr2");
        ppdrfiller = (TextField) root.lookup("#ppdrfiller");
        ppc1 = (TextField) root.lookup("#ppc1");
        ppc2 = (TextField) root.lookup("#ppc2");
        ppcfiller = (TextField) root.lookup("#ppcfiller");
        ppfl1 = (TextField) root.lookup("#ppfl1");
        ppfl2 = (TextField) root.lookup("#ppfl2");
        ppflfiller = (TextField) root.lookup("#ppflfiller");
        ppfr1 = (TextField) root.lookup("#ppfr1");
        ppfr2 = (TextField) root.lookup("#ppfr2");
        ppfrfiller = (TextField) root.lookup("#ppfrfiller");
        bpAnchorPane = (AnchorPane) root.lookup("#bpAnchorPane");
        bpBoardImage = (ImageView) root.lookup("#bpBoardImage");
        bpLineupGrid = (GridPane) root.lookup("#bpLineupGrid");
        bpdl1 = (TextField) root.lookup("#bpdl1");
        bpdl2 = (TextField) root.lookup("#bpdl2");
        bpdlfiller = (TextField) root.lookup("#bpdlfiller");
        bpdr1 = (TextField) root.lookup("#bpdr1");
        bpdr2 = (TextField) root.lookup("#bpdr2");
        bpdrfiller = (TextField) root.lookup("#bpdrfiller");
        bpfl1 = (TextField) root.lookup("#bpfl1");
        bpfl2 = (TextField) root.lookup("#bpfl2");
        bpflfiller = (TextField) root.lookup("#bpflfiller");
        bpfr1 = (TextField) root.lookup("#bpfr1");
        bpfr2 = (TextField) root.lookup("#bpfr2");
        bpfrfiller = (TextField) root.lookup("#bpfrfiller");
        bpsd1 = (TextField) root.lookup("#bpsd1");
        bpsd2 = (TextField) root.lookup("#bpsd2");
        bpsf1 = (TextField) root.lookup("#bpsf1");
        bpsf2 = (TextField) root.lookup("#bpsf2");

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();

        allGameList = dbLoader.getGames("SELECT * FROM game WHERE team =" + selectedTeam.getTeamID());
        allGames.getItems().clear();
        allGames.getItems().addAll(allGameList);

        gameTeam.setText(selectedTeam.getName());

        setupEventListeners();
    }

    public void setupEventListeners() {
        allGames.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            gameOpponent.setText(newValue.getOpponent());
            gameDate.setText(String.valueOf(newValue.getGameDate()));
            gameTime.setText(String.valueOf(newValue.getGameTime()));
            gameStadium.setText(newValue.getStadium());
            captain.setText(newValue.getCaptain().getLastName() + " " + newValue.getCaptain().getFirstName());
            assistant1.setText(newValue.getAssistant1().getLastName() + " " + newValue.getAssistant1().getFirstName());
            assistant2.setText(newValue.getAssistant2().getLastName() + " " + newValue.getAssistant2().getFirstName());

            List<Line> lines = dbLoader.getLines("SELECT * FROM line WHERE gameID = " + newValue.getGameID());

            Line firstLine = lines.stream()
                    .filter(line -> line.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstLine != null) {
                gk1.setText(getPlayerName(firstLine.getGoalkeeper()));
                dl1.setText(getPlayerName(firstLine.getDefenderLeft()));
                dr1.setText(getPlayerName(firstLine.getDefenderRight()));
                c1.setText(getPlayerName(firstLine.getCenter()));
                fl1.setText(getPlayerName(firstLine.getForwardLeft()));
                fr1.setText(getPlayerName(firstLine.getForwardRight()));
            }

            Line secondLine = lines.stream()
                    .filter(line -> line.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondLine != null) {
                dr2.setText(getPlayerName(secondLine.getDefenderRight()));
                dl2.setText(getPlayerName(secondLine.getDefenderLeft()));
                c2.setText(getPlayerName(secondLine.getCenter()));
                fr2.setText(getPlayerName(secondLine.getForwardRight()));
                fl2.setText(getPlayerName(secondLine.getForwardLeft()));
            }

            Line thirdLine = lines.stream()
                    .filter(line -> line.getLineNr() == 3)
                    .findAny().orElse(null);
            if (thirdLine != null) {
                dr3.setText(getPlayerName(thirdLine.getDefenderRight()));
                dl3.setText(getPlayerName(thirdLine.getDefenderLeft()));
                c3.setText(getPlayerName(thirdLine.getCenter()));
                fr3.setText(getPlayerName(thirdLine.getForwardRight()));
                fl3.setText(getPlayerName(thirdLine.getForwardLeft()));
            }

            Line fourthLine = lines.stream()
                    .filter(line -> line.getLineNr() == 4)
                    .findAny().orElse(null);
            if (fourthLine != null) {
                dr4.setText(getPlayerName(fourthLine.getDefenderRight()));
                dl4.setText(getPlayerName(fourthLine.getDefenderLeft()));
                c4.setText(getPlayerName(fourthLine.getCenter()));
                fr4.setText(getPlayerName(fourthLine.getForwardRight()));
                fl4.setText(getPlayerName(fourthLine.getForwardLeft()));
            }

            List<PowerplayLine> ppLine = dbLoader.getPPLines("SELECT * FROM powerplayLine WHERE gameID = " + newValue.getGameID());

            PowerplayLine firstPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstPPLine != null) {
                ppdl1.setText(getPlayerName(firstPPLine.getDefenderLeft()));
                ppdr1.setText(getPlayerName(firstPPLine.getDefenderRight()));
                ppc1.setText(getPlayerName(firstPPLine.getCenter()));
                ppfl1.setText(getPlayerName(firstPPLine.getForwardLeft()));
                ppfr1.setText(getPlayerName(firstPPLine.getForwardRight()));
            }

            PowerplayLine secondPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondPPLine != null) {
                ppdl2.setText(getPlayerName(secondPPLine.getDefenderLeft()));
                ppdr2.setText(getPlayerName(secondPPLine.getDefenderRight()));
                ppc2.setText(getPlayerName(secondPPLine.getCenter()));
                ppfl2.setText(getPlayerName(secondPPLine.getForwardLeft()));
                ppfr2.setText(getPlayerName(secondPPLine.getForwardRight()));
            }

            PowerplayLine fillerPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 3)
                    .findAny().orElse(null);
            if (fillerPPLine != null) {
                ppdlfiller.setText(getPlayerName(fillerPPLine.getDefenderLeft()));
                ppdrfiller.setText(getPlayerName(fillerPPLine.getDefenderRight()));
                ppcfiller.setText(getPlayerName(fillerPPLine.getCenter()));
                ppfrfiller.setText(getPlayerName(fillerPPLine.getForwardLeft()));
                ppfrfiller.setText(getPlayerName(fillerPPLine.getForwardRight()));
            }

            List<BoxplayLine> bpLine = dbLoader.getBPLines("SELECT * FROM boxplayLine WHERE gameID = " + newValue.getGameID());

            BoxplayLine firstBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstBPLine != null) {
                bpdl1.setText(getPlayerName(firstBPLine.getDefenderLeft()));
                bpdr1.setText(getPlayerName(firstBPLine.getDefenderRight()));
                bpfl1.setText(getPlayerName(firstBPLine.getForwardLeft()));
                bpfr1.setText(getPlayerName(firstBPLine.getForwardRight()));
            }

            BoxplayLine secondBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondBPLine != null) {
                bpdl2.setText(getPlayerName(secondBPLine.getDefenderLeft()));
                bpdr2.setText(getPlayerName(secondBPLine.getDefenderRight()));
                bpfl2.setText(getPlayerName(secondBPLine.getForwardLeft()));
                bpfr2.setText(getPlayerName(secondBPLine.getForwardRight()));
            }

            BoxplayLine thirdBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 3)
                    .findAny().orElse(null);
            if (thirdBPLine != null) {
                bpdlfiller.setText(getPlayerName(thirdBPLine.getDefenderLeft()));
                bpdrfiller.setText(getPlayerName(thirdBPLine.getDefenderRight()));
                bpflfiller.setText(getPlayerName(thirdBPLine.getForwardLeft()));
                bpfrfiller.setText(getPlayerName(thirdBPLine.getForwardRight()));
            }

            SubstituteLine substituteLine = dbLoader.getSubLines("SELECT * FROM substituteLine WHERE gameID =" + newValue.getGameID());

            if (substituteLine != null) {
                sgk1.setText(getPlayerName(substituteLine.getGoalkeeper1()));
                sgk2.setText(getPlayerName(substituteLine.getGoalkeeper2()));
                sgk3.setText(getPlayerName(substituteLine.getGoalkeeper3()));
                sd1.setText(getPlayerName(substituteLine.getDefender1()));
                sd2.setText(getPlayerName(substituteLine.getDefender2()));
                sd3.setText(getPlayerName(substituteLine.getDefender3()));
                sf1.setText(getPlayerName(substituteLine.getForward1()));
                sf2.setText(getPlayerName(substituteLine.getForward2()));
                sf3.setText(getPlayerName(substituteLine.getForward3()));
                bpsd1.setText(getPlayerName(substituteLine.getBoxplayDefender1()));
                bpsd2.setText(getPlayerName(substituteLine.getBoxplayDefender2()));
                bpsf1.setText(getPlayerName(substituteLine.getBoxplayForward1()));
                bpsf2.setText(getPlayerName(substituteLine.getBoxplayForward2()));
            }
        });
    }

    public String getPlayerName(Player player) {
        if (player.getPlayerID() > 0) {
            return player.getLastName() + " " + player.getFirstName();
        } else {
            return "";
        }
    }
}
