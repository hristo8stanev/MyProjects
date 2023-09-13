package pages.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardsPage extends BaseTrelloPage {

    public BoardsPage(WebDriver driver) {
        super(driver, "trello.boardsPage");
    }

    public void createBoard() {

        String boardName = getUIMappingByKey("trello.boardName");

        actions.waitForElementClickable("trello.header.create.menuButton");
        actions.clickElement("trello.header.create.menuButton");

        actions.waitForElementClickable("trello.header.createBoard.dropDownButton");
        actions.clickElement("trello.header.createBoard.dropDownButton");

        actions.waitForElementClickable("trello.createBoard.titleInput");
        actions.typeValueInField(boardName, "trello.createBoard.titleInput");

        actions.waitForElementClickable("trello.create.board.submitButton");
        actions.clickElement("trello.create.board.submitButton");
    }

    public void moveCardToList(String cardName, String listName) {
        String card = String.format(getUIMappingByKey("trello.boardPage.cardByName"), cardName);
        String list = String.format(getUIMappingByKey("trello.boardPage.listByName"), listName);
        actions.clickAndDragElement(card, list);
    }
    public void deleteBoard() {

        actions.waitForElementClickable("trello.showMenu");
        actions.clickElement("trello.showMenu");

        actions.waitForElementClickable("trello.icon.remove");
        actions.clickElement("trello.icon.remove");

        actions.waitForElementClickable("trello.icon.submit.button");
        actions.clickElement("trello.icon.submit.button");
    }

    public void clickOnBoard(String boardName) {
        actions.waitForElementVisible("trello.boardsPage.boardByTeamAndName", boardName);
        actions.clickElement("trello.boardsPage.boardByTeamAndName", boardName);
    }

}
