public class GitKrakenDemo1
{
    private Thread[] threads; // Array to store threads
    private long[] results;   // Array to store results from each thread

    public GitKrakenDemo1()
    {
        threads = new Thread[1000]; // Initialize the array to hold threads
        results = new long[1000];   // Array to store the sum calculated by each thread

        // Create 1000 threads and store them in the array
        for (int i = 0; i < 1000; i++) {
            final int index = i; // Needed to access the specific index in the lambda

            threads[i] = new Thread(() -> {
                long sum = 0;
                for (int j = 1; j <= 1_000_000; j++) {
                    sum += j; // Sum from 1 to 1,000,000
                }
                results[index] = sum; // Store the result in the results array
                System.out.println("Thread " + Thread.currentThread().getId() + " calculated sum: " + sum);
            });

            threads[i].start(); // Start each thread
        }
    }

    public static void main(String[] args)
    {
        GitKrakenDemo1 app = new GitKrakenDemo1();
    }
}
