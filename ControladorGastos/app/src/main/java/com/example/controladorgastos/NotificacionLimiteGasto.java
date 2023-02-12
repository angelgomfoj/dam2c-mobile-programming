package com.example.controladorgastos;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class NotificacionLimiteGasto{
    private static int id = 0;
    private Context context;

    public NotificacionLimiteGasto(Context context){
        this.context=context;
        this.id=++id;
    }

    public void NotificationIfExceded() {
        DatabaseHandler db = new DatabaseHandler(context.getApplicationContext());
        if (db.checkLimitExcedido()) {
            createNotificationChannel();
            Intent intent = new Intent(context, NotificacionLimiteGasto.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "3")
                    .setSmallIcon(R.drawable.ic_warning)
                    .setContentTitle("LIMITE DE GASTOS EXCEDIDO")
                    .setContentText("¡Has excedido el límite de gastos!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(1, builder.build());
        }

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal";
            String description = "Canal de notificacion";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("3", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
