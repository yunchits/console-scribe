package com.yunchits.utils.text;


import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public class TextAnalyzer {

    private TextAnalyzer() {}

    public static int countUniqueLetters(String s) {
        String letters = split(s);
        int count = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            int occurrences = StringUtils.countMatches(letters, c);
            if (occurrences > 0) {
                count++;
            }
        }
        return count;
    }

    public static int countLetters(String s) {
        return clean(s).length();
    }

    public static String split(String s) {
        String join = StringUtils.join(clean(s).toCharArray(), ' ');
        return StringUtils.upperCase(join);
    }

    public static String clean(String s) {
        return RegExUtils.replaceAll(s, "[^a-zA-Z]", "");
    }

    public static boolean hasWord(String s, String word) {
        return StringUtils.containsIgnoreCase(s, word);
    }

    public static int countWordOccurrences(String s, String word) {
        int count = 0;
        int index = StringUtils.indexOfIgnoreCase(s, word);
        while (index != -1) {
            count++;
            index = StringUtils.indexOfIgnoreCase(s, word, index + 1);
        }
        return count;
    }
}