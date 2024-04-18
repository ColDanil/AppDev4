package ru.mirea.galnykin.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ru.mirea.galnykin.thread.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private	int	counter	= 0;

    @Override
    protected	void	onCreate(Bundle	savedInstanceState)	{
        super.onCreate(savedInstanceState);
        binding	= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());

        // Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 9, НОМЕР ПО СПИСКУ: 05, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Иллюзия обмана");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));

        binding.btn.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void onClick(View v)	{
                new	Thread(new	Runnable()	{
                    public	void run()	{
                        int	numberThread	=	counter++;
                        Log.d("thread",
                                String.format("Запущен	поток	№	%d	студентом	группы	№	%s	номер	по списку	№ %d	",	numberThread,	"БСБО-09-21",	5)
                        );
                        if (binding.days.getText().toString().length() < 1 || binding.lessons.getText().toString().length() < 1)
                            return;
                        int days = Integer.parseInt(binding.days.getText().toString());
                        int lessons = Integer.parseInt(binding.lessons.getText().toString());
                        runOnUiThread(() -> {
                            binding.textView.setText(String.format("Среднее количество пар: %s", ((float) lessons / (float) days)));
                        });
                        Log.d("thread",	"Выполнен поток №	"	+	numberThread);
                    }
                }).start();
            }
        });
    }
}