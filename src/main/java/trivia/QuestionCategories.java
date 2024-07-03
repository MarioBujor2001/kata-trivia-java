package trivia;

public enum QuestionCategories {
    POP("Pop"), SCIENCE("Science"), SPORTS("Sports"), ROCK("Rock");

    private final String category;

    QuestionCategories(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.category;
    }
}
