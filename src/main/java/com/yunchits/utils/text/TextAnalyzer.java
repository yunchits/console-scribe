package com.yunchits.utils.text;


import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public class TextAnalyzer {

    private TextAnalyzer() {
    }

    public static int countUniqueWords(String s) {
        String[] words = words(s);

        int uniqueWordsCount = 0;

        for (int i = 0; i < words.length; i++) {
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (StringUtils.equalsIgnoreCase(words[i], words[j])) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                uniqueWordsCount++;
            }
        }
        return uniqueWordsCount;
    }

    public static int countLetters(String s) {
        return clean(s).length();
    }

    public static boolean hasWord(String s, String word) {
        return StringUtils.containsIgnoreCase(s, word);
    }

    public static int countWordOccurrences(String s, String word) {
        String[] words = words(s);
        int count = 0;

        for (String w : words) {
            if (StringUtils.equalsIgnoreCase(w, word)) {
                count++;
            }
        }
        return count;
    }

    public static String split(String s) {
        String join = StringUtils.join(clean(s).toCharArray(), ' ');
        return StringUtils.upperCase(join);
    }

    private static String clean(String s) {
        return RegExUtils.replaceAll(s, "[^a-zA-Zа-яА-Я]", "");
    }

    private static String[] words(String s) {
        String string = StringUtils.normalizeSpace(s);
        String removeAll = RegExUtils.removeAll(string, "[^a-zA-Zа-яА-Я\\s]");
        return StringUtils.split(removeAll);
    }
}