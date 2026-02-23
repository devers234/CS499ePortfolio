package com.example.danieleversinventoryapp;

import android.os.AsyncTask;

public class FetchPriceTask extends AsyncTask<String, Void, Double> {

    public interface PriceCallback {
        void onPriceFetched(double price);
    }

    private final PriceCallback callback;

    public FetchPriceTask(PriceCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Double doInBackground(String... params) {
        return ScryfallApiHelper.fetchCardPrice(params[0]);
    }

    @Override
    protected void onPostExecute(Double price) {
        callback.onPriceFetched(price);
    }
}