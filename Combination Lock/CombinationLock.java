public class CombinationLock {
    private String combination;

    public CombinationLock(String combination) {
        this.combination = combination.toLowerCase();
    }

    public String getClue(String guess) {
        return getClue(guess.toLowerCase().toCharArray());
    }

    private String getClue(char[] guess) {
        if(guess.length != combination.length()) {
            throw new IllegalArgumentException(String.format("Guess must be exactly %d characters in length", combination.length()));
        }
        for(int i= 0; i < combination.length(); i++) {
            if(combination.indexOf(guess[i]) == -1) {
                guess[i] = '*';
            } else if(guess[i] != combination.charAt(i)) {
                guess[i] = '+';
            }
        }
        return new String(guess);
    }
}
