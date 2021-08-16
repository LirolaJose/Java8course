package com.dataart.monads.optional;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl {

    public static List<Worker> getWorkersByCompanyAndProf(MapNpeProtection<String, Company> companyMap, String companyName, Profession profession) {
//        return companyMap
//                .find(companyName)
//                .flatMap(Company::getWorkers)
//                // TODO: please try to avoid using internal streams inside of map/flatMaps
//                .map(workers -> workers.stream()
//                        .filter(worker -> worker.getProfession().equals(profession))
//                        .collect(Collectors.toList()))
//                // TODO: it's not a good idea to return NULL as a collection result
//                // Would you like to receive NULL as a result of method instead of Empty collections on prod codebase?
//                .orElse(null);

        return companyMap
                .find(companyName)
                .flatMap(Company::getWorkers)
                .orElse(Collections.emptyList())
                .stream()
                .filter(worker -> profession.equals(worker.getProfession()))
                .collect(Collectors.toList());
    }

}
