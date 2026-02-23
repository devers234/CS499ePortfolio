package com.example.danieleversinventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataGridActivity extends Activity {

    EditText editItem, editQty, editSearch;
    Button buttonAdd, buttonOpenSms;
    TableLayout dataTable;
    DBHelper db;

    Spinner spinnerCondition;
    Switch switchTrade;
    TextView textTotalValue;

    private HashMap<String, Item> itemIndex = new HashMap<>();
    private List<Item> allItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_grid);

        db = new DBHelper(this);

        dataTable = findViewById(R.id.dataTable);
        editItem = findViewById(R.id.editItem);
        editQty = findViewById(R.id.editQty);
        editSearch = findViewById(R.id.editSearch);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonOpenSms = findViewById(R.id.buttonOpenSms);
        spinnerCondition = findViewById(R.id.spinnerCondition);
        switchTrade = findViewById(R.id.switchTrade);
        textTotalValue = findViewById(R.id.textTotalValue);

        loadTable();

        editSearch.setOnEditorActionListener((v, actionId, event) -> {

            String query = editSearch.getText().toString().trim();

            if (query.isEmpty()) {
                Toast.makeText(this, "Enter an item name", Toast.LENGTH_SHORT).show();
                return true;
            }

            Item result = searchItemByName(query);

            if (result != null) {
                Toast.makeText(
                        this,
                        "Found: " + result.getName() + " (Qty: " + result.getQuantity() + ")",
                        Toast.LENGTH_LONG
                ).show();
            } else {
                Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
            }

            return true;
        });

        buttonAdd.setOnClickListener(v -> {

            String itemName = editItem.getText().toString().trim();
            String qtyStr = editQty.getText().toString().trim();
            String condition = spinnerCondition.getSelectedItem().toString();
            boolean willingToTrade = switchTrade.isChecked();

            if (itemName.isEmpty()) {
                editItem.setError("Item name is required");
                return;
            }

            if (qtyStr.isEmpty()) {
                editQty.setError("Quantity is required");
                return;
            }

            if (!qtyStr.matches("\\d+")) {
                editQty.setError("Quantity must be a whole number");
                return;
            }

            int qty = Integer.parseInt(qtyStr);
            if (qty < 0) {
                editQty.setError("Quantity cannot be negative");
                return;
            }

            List<String> validConditions = Arrays.asList("NM", "LP", "MP", "HP", "DMG");
            if (!validConditions.contains(condition)) {
                Toast.makeText(this, "Condition must be NM, LP, MP, HP, or DMG", Toast.LENGTH_SHORT).show();
                return;
            }

            new FetchPriceTask(price -> {

                boolean inserted = db.addItem(
                        itemName,
                        qty,
                        condition,
                        willingToTrade,
                        price
                );

                if (inserted) {
                    Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();
                    editItem.setText("");
                    editQty.setText("");
                    switchTrade.setChecked(false);
                    spinnerCondition.setSelection(0);
                    loadTable();
                } else {
                    Toast.makeText(this, "Error adding item.", Toast.LENGTH_SHORT).show();
                }

            }).execute(itemName);
        });

        buttonOpenSms.setOnClickListener(v -> {
            Intent intent = new Intent(this, SMSActivity.class);
            startActivity(intent);
        });
    }

    private String normalize(String input) {
        return input.toLowerCase().trim();
    }

    private void buildItemIndex(List<Item> items) {
        itemIndex.clear();
        for (Item item : items) {
            itemIndex.put(normalize(item.getName()), item);
        }
    }

    private Item searchItemByName(String name) {
        return itemIndex.get(normalize(name));
    }

    private void loadTable() {
        allItems.clear();
        dataTable.removeViews(1, Math.max(0, dataTable.getChildCount() - 1));

        Cursor cursor = db.getItemsSortedByName();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int qty = cursor.getInt(2);
                String condition = cursor.getString(3);
                boolean willingToTrade = cursor.getInt(4) == 1;
                double price = cursor.getDouble(5);

                allItems.add(new Item(id, name, qty, condition, willingToTrade, price));

                TableRow row = new TableRow(this);

                TextView nameView = new TextView(this);
                nameView.setText(name);
                nameView.setPadding(8, 8, 8, 8);

                TextView qtyView = new TextView(this);
                qtyView.setText(String.valueOf(qty));
                qtyView.setPadding(8, 8, 8, 8);

                TextView conditionView = new TextView(this);
                conditionView.setText(condition);
                conditionView.setPadding(8, 8, 8, 8);

                TextView tradeView = new TextView(this);
                tradeView.setText(willingToTrade ? "Trade: Yes" : "Trade: No");
                tradeView.setPadding(8, 8, 8, 8);

                Button deleteBtn = new Button(this);
                deleteBtn.setText("Delete");
                deleteBtn.setOnClickListener(v -> {
                    db.deleteItem(id);
                    loadTable();
                });

                row.addView(nameView);
                row.addView(qtyView);
                row.addView(conditionView);
                row.addView(tradeView);
                row.addView(deleteBtn);

                dataTable.addView(row);

            } while (cursor.moveToNext());

            buildItemIndex(allItems);
            cursor.close();
        }

        double totalValue = db.getTotalInventoryValue();
        textTotalValue.setText(String.format("Total Value: $%.2f", totalValue));
    }
}