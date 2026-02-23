package com.example.danieleversinventoryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private DBHelper db;
    private Context context;

    public ItemAdapter(Context context, ArrayList<Item> items, DBHelper db) {
        this.context = context;
        this.items = items;
        this.db = db;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, qtyView;
        Button deleteBtn, updateBtn;

        public ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.textName);
            qtyView = view.findViewById(R.id.textQuantity);
            deleteBtn = view.findViewById(R.id.buttonDelete);
            updateBtn = view.findViewById(R.id.buttonUpdate);
        }
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);


        holder.nameView.setText(item.getName());
        holder.qtyView.setText("Qty: " + item.getQuantity());


        holder.deleteBtn.setOnClickListener(v -> {
            db.deleteItem(item.getId());
            items.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
        });

        holder.updateBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.update_item, null);

            EditText nameInput = dialogView.findViewById(R.id.editUpdateName);
            EditText qtyInput = dialogView.findViewById(R.id.editUpdateQty);

            // NEW dialog controls
            Spinner spinnerConditionUpdate = dialogView.findViewById(R.id.spinnerConditionUpdate);
            Switch switchTradeUpdate = dialogView.findViewById(R.id.switchTradeUpdate);

            // Prefill dialog values
            nameInput.setText(item.getName());
            qtyInput.setText(String.valueOf(item.getQuantity()));

            // Set spinner to current condition
            String currentCondition = item.getCondition();
            String[] options = {"NM", "LP", "MP", "HP", "DMG"};
            int selectedIndex = 0;
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(currentCondition)) {
                    selectedIndex = i;
                    break;
                }
            }
            spinnerConditionUpdate.setSelection(selectedIndex);

            // Set switch to current trade value
            switchTradeUpdate.setChecked(item.isWillingToTrade());

            builder.setView(dialogView)
                    .setTitle("Update Item")
                    .setPositiveButton("Update", (dialog, which) -> {

                        String newName = nameInput.getText().toString().trim();
                        String qtyStr = qtyInput.getText().toString().trim();

                        String newCondition = spinnerConditionUpdate.getSelectedItem().toString();
                        boolean newWillingToTrade = switchTradeUpdate.isChecked();

                        // ✅ Basic validation to prevent crashes
                        if (newName.isEmpty()) {
                            Toast.makeText(context, "Item name is required", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (!qtyStr.matches("\\d+")) {
                            Toast.makeText(context, "Quantity must be a whole number", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int newQty = Integer.parseInt(qtyStr);

                        List<String> validConditions = Arrays.asList("NM", "LP", "MP", "HP", "DMG");
                        if (!validConditions.contains(newCondition)) {
                            Toast.makeText(context, "Invalid condition", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // ✅ Update database with new fields
                        boolean updated = db.updateItem(item.getId(), newName, newQty, newCondition, newWillingToTrade);

                        if (updated) {
                            // ✅ Update local object so UI refreshes correctly
                            item.setName(newName);
                            item.setQuantity(newQty);
                            item.setCondition(newCondition);
                            item.setWillingToTrade(newWillingToTrade);

                            notifyItemChanged(position);
                            Toast.makeText(context, "Item updated", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
