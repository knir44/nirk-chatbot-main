package com.handson.chatbot.service;

import okhttp3.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImdbService {
    public static final Pattern PRODUCT_PATTERN = Pattern.compile(
            "\"id\":\"([^\"]+)\",.*?\"titleNameText\":\"([^\"]+)\",.*?\"titleReleaseText\":\"([^\"]+)\",.*?\"titlePosterImageModel\":\\{\"url\":\"([^\"]+)\",.*?\"topCredits\":\\[(.*?)\\]"
    );

    public String searchProducts(String keyword) throws IOException {
        return parseProductHtml(getProductHtml(keyword));
    }

    public String parseProductHtml(String html) {
        StringBuilder res = new StringBuilder();  // שימוש ב-StringBuilder
        Matcher matcher = PRODUCT_PATTERN.matcher(html);
        int resultCount = 0;

        while (matcher.find()) {
            String movieName = StringEscapeUtils.unescapeJava(matcher.group(2));
            String movieId = matcher.group(1);
            String movieUrl = "https://www.imdb.com/title/" + movieId;
            String releaseDate = matcher.group(3);
            String credits = matcher.group(5);
            resultCount++;

            res.append(resultCount).append(") ")
                    .append("Movie name: ").append(movieName)
                    .append(", released Date: ").append(releaseDate)
                    .append(", Names: ").append(credits)
                    .append(", Link: ").append(movieUrl)
                    .append(" <br>\n");
        }

        if (resultCount == 0) {
            res.append("No results found for the keyword.");
        } else {
            res.insert(0, "Total results: " + resultCount + "<br>\n");  // הוספת מספר התוצאות בתחילת התשובה
        }

        return res.toString();
    }



    private String getProductHtml(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.imdb.com/find/?q="+keyword+"&s=tt&exact=true&ref_=fn_tt_ex")
                .method("GET", null)
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .addHeader("accept-language", "en-US,en;q=0.9,he-IL;q=0.8,he;q=0.7")
                .addHeader("cookie", "session-id=135-5654895-7271936; session-id-time=2082787201l; ad-oo=0; ci=e30; ubid-main=130-7763678-4987648; session-token=e/L3Y3ZPEVCsrxn9Ri8H+KX9hI8bdSSqVYR8+FiNV3ajrpnA0dX2olpvG1y20P0twtH41xt8mKWlNaYSwtLAYNlx1MB6+5OeXLFFswHa4xHyBqH8vCOxpSrlVeT05+ZkMmXfr1Sppb+aIFYn89QeQUkydybfENldes+NQ5qyWkvcSU1A8HgY3Sg3NIs5SIUix9uL4NJbSjpgkIzCE9Zl2ajs6RXbRcc2DkJ0lVV+46sbM+SrNA6dNP7/zBZ8SR5WaA2jMo60K8dVvxkDvlFA1iscIdypN95Ng4I2PQKh59uHhZJOAl3hvspQExwnZsB8lj6VEZXkueThSCdaVwqf3YILc6GY7SB8; csm-hit=tb:s-3YS0CQ78E7H7WSQF8103|1728482736647&t:1728482736782&adb:adblk_yes; session-id=135-5654895-7271936; session-id-time=2082787201l; session-token=e/L3Y3ZPEVCsrxn9Ri8H+KX9hI8bdSSqVYR8+FiNV3ajrpnA0dX2olpvG1y20P0twtH41xt8mKWlNaYSwtLAYNlx1MB6+5OeXLFFswHa4xHyBqH8vCOxpSrlVeT05+ZkMmXfr1Sppb+aIFYn89QeQUkydybfENldes+NQ5qyWkvcSU1A8HgY3Sg3NIs5SIUix9uL4NJbSjpgkIzCE9Zl2ajs6RXbRcc2DkJ0lVV+46sbM+SrNA6dNP7/zBZ8SR5WaA2jMo60K8dVvxkDvlFA1iscIdypN95Ng4I2PQKh59uHhZJOAl3hvspQExwnZsB8lj6VEZXkueThSCdaVwqf3YILc6GY7SB8")
                .addHeader("priority", "u=0, i")
                .addHeader("sec-ch-ua", "\"Google Chrome\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "document")
                .addHeader("sec-fetch-mode", "navigate")
                .addHeader("sec-fetch-site", "none")
                .addHeader("sec-fetch-user", "?1")
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}