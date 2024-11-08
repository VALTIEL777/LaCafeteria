package com.example.lacafeteria;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Queue;

public class gestionar_ordenActivity extends AppCompatActivity {

    private LinearLayout pendingOrdersLayout;
    private Queue<String> ordenes;
    private Queue<String> ordenesAtendidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestionar_orden);

        pendingOrdersLayout = findViewById(R.id.pendingOrdersLayout);

        // Obtener las colas de MainActivity
        ordenes = MainActivity.getOrdenes();
        ordenesAtendidas = MainActivity.getOrdenesAtendidas();

        mostrarOrdenesPendientes();
    }

    private void mostrarOrdenesPendientes() {
        pendingOrdersLayout.removeAllViews();  // Limpiar la lista antes de cargarla

        for (String orden : ordenes) {
            // Crear una vista para mostrar la orden
            TextView orderTextView = new TextView(this);
            orderTextView.setText(orden);
            orderTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            // Crear botón "Atender" para la orden
            Button attendButton = new Button(this);
            attendButton.setText("Atender");
            attendButton.setOnClickListener(v -> atenderOrden(orden));

            // Añadir TextView y Button a un contenedor de la orden
            LinearLayout orderLayout = new LinearLayout(this);
            orderLayout.setOrientation(LinearLayout.HORIZONTAL);
            orderLayout.addView(orderTextView);
            orderLayout.addView(attendButton);

            // Añadir contenedor al layout principal
            pendingOrdersLayout.addView(orderLayout);
        }
    }

    private void atenderOrden(String orden) {
        ordenes.remove(orden);
        ordenesAtendidas.add(orden);
        Toast.makeText(this, "Orden atendida: " + orden, Toast.LENGTH_SHORT).show();
        mostrarOrdenesPendientes();  // Actualizar la lista de órdenes
    }
}
