package tsc.gov.api.helper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ManualLoadTest {
    public static void load(String[] args) throws InterruptedException {
        int totalRequests = 10000;
        int concurrentThreads = 100;

        ExecutorService executor = Executors.newFixedThreadPool(concurrentThreads);
        HttpClient client = HttpClient.newHttpClient();

        CountDownLatch latch = new CountDownLatch(totalRequests);
        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger failed = new AtomicInteger(0);

        long start = System.currentTimeMillis();

        for (int i = 0; i < totalRequests; i++) {
            executor.submit(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:3000/api/health"))
                            .GET()
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() == 200) {
                        success.incrementAndGet();
                    } else {
                        failed.incrementAndGet();
                    }
                } catch (Exception e) {
                    failed.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        long duration = System.currentTimeMillis() - start;

        System.out.println("Total Requests: " + totalRequests);
        System.out.println("Success: " + success.get());
        System.out.println("Failed: " + failed.get());
        System.out.println("Time Taken: " + duration + "ms");
        System.out.println("RPS: " + (totalRequests * 1000 / duration));
    }
}
