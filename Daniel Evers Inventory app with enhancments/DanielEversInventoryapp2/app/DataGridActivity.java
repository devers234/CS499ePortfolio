package com.example.danieleversinventoryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;

public class DataGridActivity extends AppCompatActivity {

    EditText editItem, editQty;
    Button buttonAdd;
    TableLayout dataTable;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_grid);

        db = new DBHelper(this);

        dataTable = findViewById(R.id.dataTable);
        editItem = findViewById(R.id.editItem);
        editQty = findViewById(R.id.editQty);
        buttonAdd = findViewById(R.id.buttonAdd);

        loadTable();

        buttonAdd.setOnClickListener(v -> {
            String itemName = editItem.getText().toString();
            String qtyStr = editQty.getText().toString();

            if (!itemName.isEmpty() && !qtyStr.isEmpty()) {
                int qty = Integer.parseInt(qtyStr);
                boolean inserted = db.addItem(itemName, qty);
                if (inserted) {
                    Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();
                    editItem.setText("");
                    editQty.setText("");
                    loadTable();
                } else {
                    Toast.makeText(this, "Error adding item.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadTable() {
        dataTable.removeViews(1, Math.max(0, dataTable.getChildCount() - 1));
        Cursor cursor = db.getAllItems();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int qty = cursor.getInt(2);

                TableRow row = new TableRow(this);
                TextView nameView = new TextView(this);
                nameView.setText(name);
                nameView.setPadding(8, 8, 8, 8);

                TextView qtyView = new TextView(this);
                qtyView.setText(String.valueOf(qty));
                qtyView.setPadding(8, 8, 8, 8);

                Button deleteBtn = new Button(this);
                deleteBtn.setText("Delete");
                deleteBtn.setOnClickListener(v -> {
                    db.deleteItem(id);
                    loadTable();
                });

                row.addView(nameView);
                row.addView(qtyView);
                row.addView(deleteBtn);
                dataTable.addView(row);
            } while (cursor.moveToNext());
        }
    }
}
