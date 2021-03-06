package com.dataart.predicate;

import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkerPredicates {
    public static final int ADULT_AGE = 20;

    // TIPS: you can use isAgeMoreThan method
    public static Predicate<Worker> isAdult() {
        return isAgeMoreThan(ADULT_AGE);
//        return worker -> worker.getAge() > 20;
    }

    public static Predicate<Worker> isAgeMoreThan(int age) {
        return worker -> worker.getAge() > age;
    }

    // TIPS: you can use isAgeMoreThan method
    public static Predicate<Worker> isAgeMoreThanAndProfession(int age, Profession profession) {
//        return worker -> worker.getAge() > age && worker.getProfession().equals(profession);
        return isAgeMoreThan(age).and(worker -> worker.getProfession().equals(profession));
    }

    // TIPS: you can use isAgeMoreThan method
    public static Predicate<Worker> isAgeMoreThanOrProfession(int age, Profession profession) {
        return isAgeMoreThan(age).or(worker -> worker.getProfession().equals(profession));
    }

    public static List<Worker> filterWorkers(List<Worker> workerList, Predicate<Worker> predicate) {
        return workerList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
