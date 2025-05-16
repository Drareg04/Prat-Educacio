public class MastermindGame{
    private static readWords readWords = new readWords();
    public static void main(String[] args) {
        String word = readWords.randomWord();
        System.out.println("Random Word: " + word);
    }
}