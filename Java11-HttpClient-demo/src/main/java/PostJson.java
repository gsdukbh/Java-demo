import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author leejiawei
 */
public class PostJson {


    public static class Order {
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        Order order = new Order();
        order.setId("1");
        order.setName("test");

        HttpClient client = HttpClient.newBuilder().build();

//        form
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/post"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("name=1&i=2"))
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("response :  " + response.body());


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/post"))
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(order)))
                .build();


        CompletableFuture<String> future = client
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);


        System.out.println("future:  " + future.get());

    }
}
