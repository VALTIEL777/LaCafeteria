package com.example.lacafeteria;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class agregar_ordenActivity extends AppCompatActivity {

    private EditText editTextClientName, editTextOrderDescription, editTextQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_orden);

        editTextClientName = findViewById(R.id.editTextClientName);
        editTextOrderDescription = findViewById(R.id.editTextOrderDescription);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        Button buttonSubmitOrder = findViewById(R.id.buttonSubmitOrder);

        buttonSubmitOrder.setOnClickListener(v -> submitOrder());
    }

    private void submitOrder() {
        String clientName = editTextClientName.getText().toString().trim();
        String orderDescription = editTextOrderDescription.getText().toString().trim();
        String quantityText = editTextQuantity.getText().toString().trim();

        if (clientName.isEmpty() || orderDescription.isEmpty() || quantityText.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityText);
        String orderDetails = "Cliente: " + clientName + ", Descripción: " + orderDescription + ", Cantidad: " + quantity;

        // Enviar la orden de vuelta a MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("orderDetails", orderDetails);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
