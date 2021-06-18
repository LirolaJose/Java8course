package com.dataart.monads.future;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncFutureServiceImpl {


    public static CompletableFuture<Integer> handleString(CompletableFuture<String> future) {
        return future.thenApply(s -> Integer.parseInt(s) * 2);

    }

    public static Integer handleException(CompletableFuture<String> future) throws ExecutionException, InterruptedException {
        return future
                .exceptionally(err -> String.valueOf(-1))
                .thenApply(Integer::parseInt).join();

    }

    public static CompletableFuture<List<Worker>> handleFutures(CompletableFuture<Company> companyCompletableFuture,
                                                                CompletableFuture<Profession> professionCompletableFuture) throws ExecutionException, InterruptedException {

        return companyCompletableFuture.thenApply(company -> company
                .getWorkers().orElse(null));
    }

}
