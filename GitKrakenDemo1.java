import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;

public class GitKrakenDemo1
{
    public static void main(String[] args)
    {
        int numThreads = 1000; // Number of threads
        int totalSum = 0;      // Variable to store the final total sum

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads); // Create a thread pool
        List<Future<Integer>> futures = new ArrayList<>();

        // Create 1000 threads and assign each one a task to compute the sum of numbers from 1 to 1 million
        for (int i = 0; i < numThreads; i++) {
            Future<Integer> future = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int sum = 0;
                    // Sum numbers from 1 to 1,000,000
                    for (int j = 1; j <= 1000000; j++) {
                        sum += j;
                    }
                    return sum; // Return the sum for this thread
                }
            });
            futures.add(future); // Store the Future for each thread
        }

        try {
            // Wait for all threads to finish and calculate the total sum
            for (Future<Integer> future : futures) {
                totalSum += future.get(); // Wait for each thread to complete and get the result
                // The get() method implicitly ensures the main thread "joins" each worker thread
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown(); // Shut down the executor service

        // Output the total sum of all thread results
        System.out.println("Total Sum from all threads: " + totalSum);
    }
}
