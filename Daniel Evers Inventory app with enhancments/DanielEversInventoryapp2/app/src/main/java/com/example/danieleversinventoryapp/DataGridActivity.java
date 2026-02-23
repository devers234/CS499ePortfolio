package com.example.danieleversinventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;

import java.util.Arrays;
import java.util.List;

public class DataGridActivity extends Activity {

    EditText editItem, editQty;
    Button buttonAdd, buttonOpenSms;
    TableLayout dataTable;
    DBHelper db;

    // NEW UI fields
    Spinner spinnerCondition;
    Switch switchTrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_grid);

        db = new DBHelper(this);

        dataTable = findViewById(R.id.dataTable);
        editItem = findViewById(R.id.editItem);
        editQty = findViewById(R.id.editQty);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonOpenSms = findViewById(R.id.buttonOpenSms);

        // NEW UI hookups
        spinnerCondition = findViewById(R.id.spinnerCondition);
        switchTrade = findViewById(R.id.switchTrade);

        loadTable();

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


            boolean inserted = db.addItem(itemName, qty, condition, willingToTrade);

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
        });

        buttonOpenSms.setOnClickListener(v -> {
            Intent intent = new Intent(DataGridActivity.this, SMSActivity.class);
            startActivity(intent);
        });
    }

    private void loadTable() {
        dataTable.removeViews(1, Math.max(0, dataTable.getChildCount() - 1)); // Keep header
        Cursor cursor = db.getAllItems();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int qty = cursor.getInt(2);

                // NEW columns (based on updated schema order)
                String condition = cursor.getString(3);
                boolean willingToTrade = cursor.getInt(4) == 1;

                TableRow row = new TableRow(this);

                TextView nameView = new TextView(this);
                nameView.setText(name);
                nameView.setPadding(8, 8, 8, 8);

                TextView qtyView = new TextView(this);
                qtyView.setText(String.valueOf(qty));
                qtyView.setPadding(8, 8, 8, 8);

                // Optional: show condition + trade in the grid
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

            cursor.close();
        }
    }
}
