package lesson3;

import java.util.*;

public class mainAppLes3 {
    public static void main(String[] args) {
        int wordCount = 15;
        String[] literArray = {"A", "B", "C", "D"};
        List<String> wordList = new ArrayList<>(wordCount);
        for (int i = 0; i < wordCount; i++) {
            wordList.add(literArray[(int) (Math.random() * literArray.length)] + literArray[(int) (Math.random() * literArray.length)]);
        }
        Map<String, Integer> wordMap = new HashMap<>();
        Iterator<String> iter = wordList.iterator();
        String word;
        while (iter.hasNext()) {
            word = iter.next();
            if (wordMap.containsKey(word)) {
                wordMap.replace(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
        System.out.println("Массив слов: " + wordList);
        System.out.println("Уникальные элементы: " + wordMap.keySet());
        System.out.println("Количество уникальных элементов: " + wordMap);
        System.out.println("---------------");

        Phonebook phBook = new Phonebook();
        int phoneCount = 10;
        String[] lastNames = {"L", "M", "R", "S", "T"};
        for (int i = 0; i < phoneCount; i++) {
            phBook.add(lastNames[(int) (Math.random() * lastNames.length)], String.valueOf((int) (Math.random() * 10) + i * phoneCount));
        }

        String nameToFindPhone = "R";
        if (phBook.get(nameToFindPhone) != null) {
            System.out.println("Телефон(ы) контакта " + nameToFindPhone + ": " + phBook.get(nameToFindPhone));
        } else {
            System.out.println("Контакт " + nameToFindPhone + " не найден");
        }
    }
}