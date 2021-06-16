package com.dataart.streams;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamServiceImpl {

    public static Long wordsCounter(List<String> words, String word) {
        return words.stream()
                .filter(w -> w.replaceAll("[^a-zA-Z]", "").equals(word))
                .count();
    }

    public static Integer getSum(int... i) {
        return IntStream.of(i).sum();
    }

    public static Long getCountWordsLongestThan(List<String> words, int count) {
        return words.stream()
                .filter(word -> word.length() > count)
                .count();
    }

    public static Long getCountWordsStartWith(List<String> words, String startWith) {
        return words.stream()
                .filter(word -> word.startsWith(startWith))
                .count();
    }

    public static String getFirstWordStartWith(List<String> words, String startWith) {
        return words.stream()
                .filter(word -> word.startsWith(startWith))
                .findFirst().orElse(null);
    }

    public static Double getAverageAge(List<Worker> workers) {
        return workers.stream()
                .mapToInt(Worker::getAge)
                .average().orElse(0);
    }

    public static List<String> capitalLetter(List<String> words) {
        return words.stream()
                .map(word -> word.substring(0,1).toUpperCase().concat(word.substring(1).toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<String> concatenationAndToUpperCase(List<String> words1, List<String> words2) {
        List<String> commonList = Stream.concat(words1.stream(), words2.stream()).collect(Collectors.toList());
        return commonList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static List<String> sortWordsByLength(List<String> words) {
        return words.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
    }

    public static List<String> excludeDuplicateAndAlphabeticalSort(List<String> words) {
        return words.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Worker> searchWorkers(List<Company> companies, Profession profession) {
        return companies.stream()
                .map(company -> company.getWorkers().orElse(null))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()).stream()
                .filter(worker -> worker.getProfession().equals(profession))
                .collect(Collectors.toList());
    }

}
