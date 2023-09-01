package test.cases.trello;


import com.telerikacademy.testframework.Utils;
import org.junit.After;
import org.junit.Before;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;

import org.junit.Test;

public class BoardTest extends BaseTest {
    private BoardsPage boardsPage;
    private BoardPage boardPage;
    private boolean deleted = false;

    @Before
    public void testSetup() {
        login();

        boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();

        boardPage = new BoardPage(actions.getDriver());
    }

    @After
    public void testCleanup() {
        if (!deleted) {
            boardsPage.deleteBoard();
            boardPage.assertBoardIsEmpty();
        }
    }


    @Test
    public void createBoardWhenCreateBoardClicked() {

        //ASSERT
        boardPage.assertAddListExists();
        boardPage.assertBoardIsNotEmpty("trello.boardName");
    }

    @Test
    public void createNewCardInExistingBoardWhenCreateCardClicked() {

        boardPage.addCardToList();

        //ASSERT
        boardPage.assertCardExists("trello.cardName");
        boardPage.assertAddListExists();
    }

    @Test
    public void moveCardBetweenStatesWhenDragAndDropIsUsed() {

        String listName = Utils.getUIMappingByKey("trello.boardPage.firstList");
        String cardName = Utils.getUIMappingByKey("trello.cardName");
        String location = Utils.getUIMappingByKey("trello.boardPage.secondList");
        //ASSERT
        boardPage.assertListExists(listName);

        boardPage.addCardToList();
        //ASSERT
        boardPage.assertCardExists("trello.cardName");

        boardsPage.moveCardToList(cardName, location);
        //ASSERT
        boardPage.assertCardMoved(location,cardName);

    }

    @Test
    public void deleteBoardWhenDeleteButtonIsClicked() {

        boardsPage.deleteBoard();

        //ASSERT
        boardPage.assertBoardIsEmpty();
        deleted = true;
    }
}

