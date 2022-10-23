package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    DBHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_chat)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        String CHANNEL_ID="1";

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);

            double rand = Math.random()*10;

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_stat_name)
                            .setContentTitle("Мотивация дня");

            switch ((int) rand) {
                case (0):
                    builder.setContentText("Вы не будете расти, если не будете пытаться совершить что-то за пределами того, что вы уже знаете в совершенстве. © Ральф Эмерсон ")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Вы не будете расти, если не будете пытаться совершить что-то за пределами того, что вы уже знаете в совершенстве. © Ральф Эмерсон "));
                    break;

                case (1):
                    builder.setContentText("У вас будут неудачи. У вас будут травмы...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("У вас будут неудачи. У вас будут травмы. Вы будете ошибаться. У вас будут периоды депрессии и отчаяния. Семья, учеба, работа, проблемы быта – все это не раз и не два станет помехой тренингу. Однако стрелка вашего внутреннего компаса должна всегда показывать одно и тоже направление – к цели. © Стюарт МакРоберт"));
                    break;

                case (2):
                    builder.setContentText("А если ты не уверен в себе ничего хорошего никогда не получится. Ведь если ты в себя не веришь, кто же поверит?")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("А если ты не уверен в себе ничего хорошего никогда не получится. Ведь если ты в себя не веришь, кто же поверит?"));
                    break;

                case (3):
                    builder.setContentText("Через двадцать лет вы будете сожалеть...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Через двадцать лет вы будете сожалеть о том, чего не сделали, чем о том, что вы сделали. Поэтому, отбросьте сомнения. Уплывайте прочь от безопасной гавани. Поймайте попутный ветер своими парусами. Исследуйте. Мечтайте. Открывайте. © Марк Твен"));
                    break;

                case (4):
                    builder.setContentText("Еще не все колеса изобретены...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Еще не все колеса изобретены: мир слишком удивителен, чтобы сидеть сложа руки. © Ричард Бренсон"));
                    break;

                case (5):
                    builder.setContentText("Ты никогда не переплывешь океан...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Ты никогда не переплывешь океан, если будешь бояться потерять берег из виду. © Христофор Колумб"));
                    break;

                case (6):
                    builder.setContentText("Говорят, что мотивация длится недолго...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Говорят, что мотивация длится недолго. Что ж, свежесть после ванны – тоже. Поэтому заботиться о них стоит ежедневно. © Зиг Зиглар"));
                    break;

                case (7):
                    builder.setContentText("Почувствуйте попутный ветер...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Почувствуйте попутный ветер в вашем парусе. Двигайтесь…Если нет ветра, беритесь за весла. © Латинская поговорка"));
                    break;

                case (8):
                    builder.setContentText("Если вы цените то, что у вас есть в жизни...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Если вы цените то, что у вас есть в жизни, вы всегда будете получать еще больше. Если думать лишь о том, чего у вас нет – вам никогда не будет достаточно. © Опра Уинфри"));
                    break;

                case (9):
                    builder.setContentText("Свобода ничего не стоит...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Свобода ничего не стоит, если она не включает в себя свободу ошибаться. © Махатма Ганди"));
                    break;

                case (10):
                    builder.setContentText("Настоящее хобби нашего поколения – это нытье...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Настоящее хобби нашего поколения – это нытье и тупая болтовня ни о чем. Неудачные отношения, проблемы с учебой, начальник – мудак. Это все полная фигня. Если у тебя ничего не получается, то есть только один мудак – это ты. И ты сильно удивишься, если узнаешь, как много можно изменить, просто оторвав жопу от дивана. © Джордж Карлин "));
                    break;

                default:
                    builder.setContentText("Здравствуйте");
                    break;
            }

            Notification notification = builder.build();

            notificationManager.notify(1, notification);

            databaseHelper = new DBHelper(getApplicationContext());
            db = databaseHelper.getReadableDatabase();

            cursor = db.rawQuery("select * from " + DBHelper.USER_NAME, null);
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_US));
            db.close();

            if (name.equals("")) {
                Intent intent = new Intent(MainActivity.this, NamedActivity.class);
                startActivity(intent);
            }



        }
    }


}
