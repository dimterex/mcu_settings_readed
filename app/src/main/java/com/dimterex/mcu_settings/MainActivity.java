package com.dimterex.mcu_settings;

import android.microntek.CarManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private CarManager mCarManager;
    private EditText input_text;

    private ListView output_list;
    private ArrayList<String> listItems;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItems = new ArrayList<String>();
        input_text = findViewById(R.id.input_text);
        output_list = findViewById(R.id.output_list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        output_list.setAdapter(adapter);

        mCarManager = new CarManager();

        Button read_button  = findViewById(R.id.read_button);
        read_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_text == null)
                    return;

                if (output_list == null)
                    return;

                String input = input_text.getText().toString();

                String parameter  = mCarManager.getParameters(input);
                if (parameter == null)
                    parameter = input + " failed to read";

                listItems.add(parameter);
                adapter.notifyDataSetChanged();
                output_list.setSelection(listItems.size() - 1);
            }
        });
    }
}