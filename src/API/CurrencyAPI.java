package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyAPI {
    private static final String API_URL = "https://api.currencyapi.com/v3/latest?apikey=cur_live_UV0FFfd4WEBGN2L0FK9asP4hT8EqfXgxnhNqb8LQ&currencies=EUR%2CUSD%2CCAD%2CPLN";

    public static String getRates(String baseCurrency, String targetCurrency) throws Exception {
        String apiUrlWithCurrencies = API_URL + "&base_currency=" + baseCurrency + "&currencies=" + targetCurrency;
        URL url = new URL(apiUrlWithCurrencies);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();
        return content.toString();
    }
}
