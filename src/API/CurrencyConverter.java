package API;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverter {

    private double exchangeRate;

    public void parseRates(String json, String targetCurrency) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject data = jsonObject.getAsJsonObject("data");
        if (data != null && data.has(targetCurrency)) {
            JsonObject currencyObject = data.getAsJsonObject(targetCurrency);
            exchangeRate = currencyObject.get("value").getAsDouble();
        } else {
            exchangeRate = 0; // Or handle this case differently
        }
    }

    public double convert(double amount) {
        return amount * exchangeRate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
