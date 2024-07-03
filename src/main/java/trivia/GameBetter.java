package trivia;

import java.util.*;

public class GameBetter implements IGame {
    List<Player> players = new ArrayList<>();
    Map<QuestionCategories, LinkedList<String>> questions = new HashMap<>();

    int currentPlayerPosition = 0;
    boolean isGettingOutOfPenaltyBox;

    public GameBetter() {
        questions.put(QuestionCategories.POP, new LinkedList<>());
        questions.put(QuestionCategories.SCIENCE, new LinkedList<>());
        questions.put(QuestionCategories.SPORTS, new LinkedList<>());
        questions.put(QuestionCategories.ROCK, new LinkedList<>());
        for (int i = 0; i < 50; i++) {
            createQuestion(QuestionCategories.POP, i);
            createQuestion(QuestionCategories.SCIENCE, i);
            createQuestion(QuestionCategories.SPORTS, i);
            createQuestion(QuestionCategories.ROCK, i);
        }
    }

    private void createQuestion(QuestionCategories category, int index) {
        questions.get(category).addLast(category.toString() + " Question " + index);
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayerPosition);
    }

    public boolean add(String playerName) {
        Player player = new Player();
        player.setName(playerName);
        player.setPlace(0);
        player.setPurse(0);
        player.setInPenaltyBox(false);
        players.add(player);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public void roll(int roll) {
        Player currentPlayer = getCurrentPlayer();
        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer + " is getting out of the penalty box");
                movePlayer(roll);
                askQuestion();
            } else {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            movePlayer(roll);
            askQuestion();
        }

    }

    private void askQuestion() {
        System.out.println(questions.get(currentCategory()).removeFirst());
    }


    private QuestionCategories currentCategory() {
        return questions.keySet().stream().toList().get(getCurrentPlayer().getPlace() % 4);
    }

    public boolean wasCorrectlyAnswered() {
        if (getCurrentPlayer().isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            next();
            return true;
        }
        System.out.println("Answer was correct!!!!");
        incrementPurse();
        next();

        return didPlayerWin();
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayerPosition) + " was sent to the penalty box");
        getCurrentPlayer().setInPenaltyBox(true);

        return true;
    }

    private void movePlayer(int roll) {
        getCurrentPlayer().move(roll);
        System.out.println(getCurrentPlayer()
                + "'s new location is "
                + (getCurrentPlayer().getPlace() + 1));
        System.out.println("The category is " + currentCategory());
    }
    private boolean didPlayerWin() {
        return getCurrentPlayer().getPurse() != 6;
    }

    private void next() {
        currentPlayerPosition++;
        if (currentPlayerPosition == players.size()) {
            currentPlayerPosition = 0;
        }
    }

    private void incrementPurse() {
        getCurrentPlayer().setPurse(getCurrentPlayer().getPurse() + 1);
        System.out.println(players.get(currentPlayerPosition)
                + " now has "
                + getCurrentPlayer().getPurse()
                + " Gold Coins.");
    }
}
