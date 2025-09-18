import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final Map<String, byte[]> staticFiles = new HashMap<>();

    static {
        // Embed frontend files as strings and convert to bytes
        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Factorial Calculator</title>
                    <link rel="stylesheet" href="/styles.css">
                </head>
                <body>
                    <h1>Factorial Calculator: Java Paradigms</h1>
                    <p>Select a paradigm and enter a number to compute its factorial.</p>
                    <label for="paradigm">Paradigm:</label>
                    <select id="paradigm">
                        <option value="procedural">Procedural</option>
                        <option value="oop">OOP</option>
                        <option value="functional">Functional</option>
                    </select>
                    <br><br>
                    <label for="number">Number (0-20):</label>
                    <input type="number" id="number" min="0" max="20" value="5">
                    <br><br>
                    <button onclick="computeFactorial()">Compute</button>
                    <p id="result"></p>
                    <script src="/script.js"></script>
                </body>
                </html>
                """;
        staticFiles.put("/", html.getBytes(StandardCharsets.UTF_8));
        staticFiles.put("/index.html", html.getBytes(StandardCharsets.UTF_8));

        String css = """
                body {
                    font-family: Arial, sans-serif;
                    max-width: 600px;
                    margin: 0 auto;
                    padding: 20px;
                }
                h1 {
                    text-align: center;
                }
                label, select, input, button {
                    font-size: 16px;
                }
                button {
                    padding: 10px 20px;
                    background-color: #4CAF50;
                    color: white;
                    border: none;
                    cursor: pointer;
                }
                button:hover {
                    background-color: #45a049;
                }
                #result {
                    font-weight: bold;
                    margin-top: 20px;
                }
                """;
        staticFiles.put("/styles.css", css.getBytes(StandardCharsets.UTF_8));

        String js = """
                async function computeFactorial() {
                    const paradigm = document.getElementById('paradigm').value;
                    const n = document.getElementById('number').value;
                    if (n < 0 || n > 20) {
                        document.getElementById('result').innerText = 'Please enter a number between 0 and 20.';
                        return;
                    }
                    try {
                        const response = await fetch(`/factorial/${paradigm}/${n}`);
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        const result = await response.text();
                        document.getElementById('result').innerText = `Result: ${result}`;
                    } catch (error) {
                        document.getElementById('result').innerText = `Error: ${error.message}`;
                    }
                }
                """;
        staticFiles.put("/script.js", js.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new StaticHandler());
        server.createContext("/factorial/", new FactorialHandler());
        server.setExecutor(null); // Creates a default executor
        server.start();
        System.out.println("Server started on http://localhost:8000");
    }

    static class StaticHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            byte[] content = staticFiles.getOrDefault(path, "Not Found".getBytes());
            int status = content == "Not Found".getBytes() ? 404 : 200;
            String contentType;
            if (path.endsWith(".css")) {
                contentType = "text/css";
            } else if (path.endsWith(".js")) {
                contentType = "application/javascript";
            } else {
                contentType = "text/html";
            }
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(status, content.length);
            OutputStream os = exchange.getResponseBody();
            os.write(content);
            os.close();
        }
    }

    static class FactorialHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String[] parts = path.split("/");
            if (parts.length != 4 || !parts[1].equals("factorial")) {
                sendResponse(exchange, 400, "Invalid request");
                return;
            }
            String paradigm = parts[2];
            int n;
            try {
                n = Integer.parseInt(parts[3]);
                if (n < 0 || n > 20) {
                    sendResponse(exchange, 400, "Number must be between 0 and 20");
                    return;
                }
            } catch (NumberFormatException e) {
                sendResponse(exchange, 400, "Invalid number");
                return;
            }

            long result;
            switch (paradigm) {
                case "procedural":
                    result = ProceduralFactorial.compute(n);
                    break;
                case "oop":
                    result = new OOPFactorial().compute(n);
                    break;
                case "functional":
                    result = FunctionalFactorial.compute(n);
                    break;
                default:
                    sendResponse(exchange, 400, "Invalid paradigm");
                    return;
            }
            sendResponse(exchange, 200, String.valueOf(result));
        }

        private void sendResponse(HttpExchange exchange, int status, String response) throws IOException {
            exchange.sendResponseHeaders(status, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}