package src.book2.internet;

import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class URLConnectionTest {
    public static void main(String[] args) {
        System.out.println("start");
        String urlName = "https://www.baidu.com";
        try {
            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();
            /*
             * String username = ""; String psw = ""; String input = username + ":" + psw;
             * Base64.Encoder encoder = Base64.getEncoder(); String encoding =
             * encoder.encodeToString(input.getBytes("UTF-8"));
             * connection.setRequestProperty("Authorization", "Basic " + encoding);
             */

            connection.connect();
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (String keyString : headers.keySet()) {
                List<String> value = headers.get(keyString);
                System.out.println(keyString + ": " + value);
            }
            // for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            // String key = entry.getKey();
            // for (String value : entry.getValue()) {
            // System.out.println(key + ": " + value);
            // }
            // }

            System.out.println("----------");
            System.out.println("getContentType: " + connection.getContentType());
            System.out.println("getContentLength: " + connection.getContentLength());
            System.out.println("getContentEncoding: " + connection.getContentEncoding());
            System.out.println("getDate: " + connection.getDate());
            System.out.println("getExpiration: " + connection.getExpiration());
            System.out.println("getLastModified: " + connection.getLastModified());
            System.out.println("----------");

            String encoding = connection.getContentEncoding();
            if (encoding == null) {
                encoding = "UTF-8";
            }

            Scanner in = new Scanner(connection.getInputStream(), encoding);
            StringBuilder builder = new StringBuilder();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                builder.append(line);
            }
            System.out.println(builder.toString());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
