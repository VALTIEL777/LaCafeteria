package com.example.lacafeteria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.LinkedList;
import java.util.Queue;

public class ver_ordenesActivity extends AppCompatActivity {

    private LinearLayout attendedOrdersLayout;
    private Queue<String> ordenesAtendidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_orden);

        attendedOrdersLayout = findViewById(R.id.attendedOrdersLayout);

        // Obtener las órdenes atendidas desde MainActivity
        ordenesAtendidas = MainActivity.getOrdenesAtendidas();

        mostrarOrdenesAtendidas();
    }

    private void mostrarOrdenesAtendidas() {
        attendedOrdersLayout.removeAllViews(); // Limpiar cualquier vista previa

        if (ordenesAtendidas.isEmpty()) {
            TextView emptyMessage = new TextView(this);
            emptyMessage.setText("No hay órdenes atendidas.");
            attendedOrdersLayout.addView(emptyMessage);
        } else {
            for (String orden : ordenesAtendidas) {
                // Crear un nuevo LinearLayout para cada orden
                LinearLayout orderLayout = new LinearLayout(this);
                orderLayout.setOrientation(LinearLayout.HORIZONTAL);
                orderLayout.setPadding(0, 10, 0, 10);

                TextView orderTextView = new TextView(this);
                orderTextView.setText(orden);
                orderTextView.setTextSize(18);
                orderTextView.setPadding(0, 0, 10, 0);
                orderLayout.addView(orderTextView);

                Button deleteButton = new Button(this);
                deleteButton.setText("Eliminar");
                deleteButton.setOnClickListener(v -> {
                    // Eliminar la orden de la lista de atendidas
                    ordenesAtendidas.remove(orden);
                    mostrarOrdenesAtendidas(); // Actualizar la vista
                });
                orderLayout.addView(deleteButton);

                attendedOrdersLayout.addView(orderLayout); // Agregar el layout de la orden al LinearLayout
            }
        }
    }
}
