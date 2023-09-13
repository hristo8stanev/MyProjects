package pages.trello;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }

    public void addCardToList() {

        String cartName = getUIMappingByKey("trello.cardName");

        actions.waitForElementClickable("trello.createCard.titleInput");
        actions.typeValueInField(cartName,"trello.createCard.titleInput");

        actions.waitForElementClickable("trello.create.card.submitButton");
        actions.clickElement("trello.create.card.submitButton");
    }


    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertAddListExists() {
        actions.waitForElementPresent("trello.boardPage.listWrapper");
    }

    public void assertCardExists(String cardName) {
        actions.waitForElementPresent("trello.boardPage.CardByName", cardName);
    }

    public void assertBoardIsNotEmpty(String boardName) {
        actions.waitForElementClickable("trello.boardPage.RecentProject");
        actions.clickElement("trello.boardPage.RecentProject");
        actions.waitForElementPresent("trello.header.create.ProjectView", boardName);
    }
    public void assertBoardIsEmpty() {
        actions.waitForElementVisible("trello.board.icon");
        actions.clickElement("trello.board.icon");
        actions.waitForElementVisible("trello.empty.board");
        actions.clickElement("trello.empty.board");

    }
    public void assertCardMoved(String listName,String cardName ) {
        actions.waitForElementPresent("trello.boardPage.assertCardMovedToNewList", listName, cardName);
    }
}
