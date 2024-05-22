package faewulf.squaremap.banner.utils;

import java.util.regex.Pattern;

public class wordCheck {
    static public boolean containsWholeWord(String input, String word) {
        // Define the pattern to match the whole word
        String patternString = "\\b" + Pattern.quote(word.toLowerCase()) + "\\b";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input.toLowerCase()).find();
    }
}
