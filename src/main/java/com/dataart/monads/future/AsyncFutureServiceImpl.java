package com.dataart.monads.future;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class AsyncFutureServiceImpl {


    public static CompletableFuture<Integer> handleString(CompletableFuture<String> future) {
        return future.thenApply(s -> Integer.parseInt(s) * 2);

    }

    public static Integer handleException(CompletableFuture<String> future) throws ExecutionException, InterruptedException {
        return future
                .exceptionally(err -> "-1")
                .thenApply(Integer::parseInt).join();

    }

    public static CompletableFuture<List<Worker>> handleFutures(CompletableFuture<Company> companyCompletableFuture,
                                                                CompletableFuture<Profession> professionCompletableFuture) throws ExecutionException, InterruptedException {

        return CompletableFuture.allOf(professionCompletableFuture, companyCompletableFuture)
                .thenApply(v -> companyCompletableFuture.join()
                        .getWorkers()
                        // TODO: please try to avoid using internal streams inside of map/flatMaps
                        .map(workers -> workers.stream()
                                .filter(worker -> worker.getProfession().equals(professionCompletableFuture.join()))
                                .collect(Collectors.toList()))
                        // TODO: it's not a good idea to return NULL as a collection result
                        .orElse(null));
    }

}
