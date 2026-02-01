package utilities;

import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class MailTmClient {
    private static final String BASE_URL = "https://api.mail.tm";
    private String token;
    private String email;
    private String password;
    private static final Gson gson = new Gson();

    public void createAccount() throws Exception {
        String random = UUID.randomUUID().toString().substring(0, 8);
        String domain = fetchValidDomain();
        this.email = random + "@" + domain;
        this.password = "Test123456!";

        JsonObject body = new JsonObject();
        body.addProperty("address", email);
        body.addProperty("password", password);

        HttpURLConnection conn = post("/accounts", body.toString());
        String response = readResponse(conn);
        if (conn.getResponseCode() >= 400) {
            System.err.println("Mail.tm error: " + response);
            throw new RuntimeException("Mail.tm account creation failed.");
        }

        login();
    }
    private String fetchValidDomain() throws Exception {
        HttpURLConnection conn = get("/domains");
        String response = readResponse(conn);
        JsonArray domains = gson.fromJson(response, JsonObject.class).getAsJsonArray("hydra:member");

        if (domains.size() == 0) {
            throw new RuntimeException("No available domains found from Mail.tm");
        }

        // Use the first available domain
        return domains.get(0).getAsJsonObject().get("domain").getAsString();
    }

    private void login() throws Exception {
        JsonObject body = new JsonObject();
        body.addProperty("address", email);
        body.addProperty("password", password);

        HttpURLConnection conn = post("/token", body.toString());
        String response = readResponse(conn);
        JsonObject json = gson.fromJson(response, JsonObject.class);
        this.token = json.get("token").getAsString();
    }

    public String waitForEmailHtml(int timeoutSeconds) throws Exception {
        long endTime = System.currentTimeMillis() + timeoutSeconds * 1000;

        while (System.currentTimeMillis() < endTime) {
            Thread.sleep(3000);

            HttpURLConnection conn = get("/messages");
            String response = readResponse(conn);
            JsonArray messages = gson.fromJson(response, JsonObject.class).getAsJsonArray("hydra:member");

            if (!messages.isEmpty()) {
                String messageId = messages.get(0).getAsJsonObject().get("id").getAsString();
                HttpURLConnection msgConn = get("/messages/" + messageId);
                String msgBody = readResponse(msgConn);
                JsonArray htmlArray = gson.fromJson(msgBody, JsonObject.class).getAsJsonArray("html");

                return htmlArray != null && !htmlArray.isEmpty()
                        ? htmlArray.get(0).getAsString()
                        : "";
            }
        }

        throw new RuntimeException("No email received in Mail.tm inbox.");
    }

    private HttpURLConnection post(String path, String body) throws Exception {
        URL url = new URL(BASE_URL + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        if (token != null) conn.setRequestProperty("Authorization", "Bearer " + token);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }
        return conn;
    }

    private HttpURLConnection get(String path) throws Exception {
        URL url = new URL(BASE_URL + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + token);
        return conn;
    }

    private String readResponse(HttpURLConnection conn) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return br.lines().collect(Collectors.joining());
        }
    }

    public String getEmail() {
        return email;
    }
}
