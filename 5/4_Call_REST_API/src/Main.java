import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Hello, World!");
        try (HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://backbybe.fly.dev/bestiary/alignments")) //link for API here.
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        }

    }
}