import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author leejiawei
 */
public class ConcurrentRequests {

    public static void main(String[] args) throws  Exception{

        List<String >  add= List.of("https://werls.top","https://rpi.werls.top","https://baidu.com");
        HttpClient client = HttpClient.newBuilder().build();
        List<HttpRequest> httpRequestList= add.stream()
                .map(url-> HttpRequest.newBuilder(URI.create(url)))
                .map(HttpRequest.Builder::build)
                .collect(Collectors.toList());
        List<CompletableFuture<HttpResponse<String>>> list= httpRequestList.stream()
                .map(i-> client.sendAsync(i, HttpResponse.BodyHandlers.ofString()))
                .collect(Collectors.toList());
        CompletableFuture.allOf(list.toArray(CompletableFuture<?>[]::new)).join();

        list.forEach(i->{
            try {
                System.out.println(i.get().statusCode());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

    }
}
