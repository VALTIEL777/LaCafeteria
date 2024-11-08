package com.example.lacafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private static Queue<String> ordenes = new LinkedList<>();
    private static Queue<String> ordenesAtendidas = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonAddOrder = findViewById(R.id.buttonAddOrder);
        Button buttonManageOrders = findViewById(R.id.buttonManageOrders);
        Button buttonViewAttendedOrders = findViewById(R.id.buttonViewAttendedOrders);

        // Abrir la actividad para agregar una orden
        buttonAddOrder.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, agregar_ordenActivity.class);
            startActivityForResult(intent, 1);
        });

        // Abrir la actividad para gestionar órdenes
        buttonManageOrders.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, gestionar_ordenActivity.class);
            startActivity(intent);
        });

        // Abrir la actividad para ver órdenes atendidas
        buttonViewAttendedOrders.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ver_ordenesActivity.class);
            startActivity(intent);
        });
    }

    // Método para recibir la orden agregada en AgregarOrdenActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String orderDetails = data.getStringExtra("orderDetails");
            ordenes.add(orderDetails); // Agregar a la cola de órdenes
            Toast.makeText(this, "Orden agregada: " + orderDetails, Toast.LENGTH_SHORT).show();
        }
    }

    // Método para gestionar la orden, moverla a órdenes atendidas y eliminar de la cola
    public static void atenderOrden() {
        if (!ordenes.isEmpty()) {
            String ordenAtendida = ordenes.poll(); // Obtener y quitar la primera orden
            ordenesAtendidas.add(ordenAtendida); // Agregar a la cola de órdenes atendidas
        }
    }

    public static Queue<String> getOrdenes() {
        return ordenes;
    }

    public static Queue<String> getOrdenesAtendidas() {
        return ordenesAtendidas;
    }
}
