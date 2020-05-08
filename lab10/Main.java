import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * This program finds different ways one can travel by bus (with a bit
 * of walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
    /**
     * The program read a sequence of (id, search string) from standard input.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        List<CompletableFuture<String>> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(BusSg.findBusServicesBetween(new BusStop(sc.next()), sc.next()).thenCompose(x -> x.description()));
        }
        sc.close();
        CompletableFuture.allOf(list.toArray(new CompletableFuture<?>[0])).join();
        list.forEach(x -> x.thenAccept(System.out::println));
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}
