package sg.edu.rp.c346.id22012205.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Spinner spnAD;
Button btnAdd;
Button btndelete;
Button btnclear;
EditText ETTask;
ListView lvTask;
ArrayList<String>TaskArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnAD=findViewById(R.id.spinneraction);
        btnAdd=findViewById(R.id.buttonadd);
        btndelete=findViewById(R.id.buttondelete);
        btnclear=findViewById(R.id.buttonclear);
        ETTask=findViewById(R.id.editTextTask);
        lvTask=findViewById(R.id.tasklist);
        TaskArray=new ArrayList<String>();
        spnAD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ETTask.setHint(getResources().getString(R.string.EThint));
                        btndelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        ETTask.setText("");
                        break;
                    case 1:
                        ETTask.setHint(getResources().getString(R.string.EThint1));
                        btnAdd.setEnabled(false);
                        btndelete.setEnabled(true);
                        ETTask.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter taskadapt=new ArrayAdapter(this, android.R.layout.simple_list_item_1,TaskArray);
        lvTask.setAdapter(taskadapt);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addtask=ETTask.getText().toString();
                TaskArray.add(addtask);
                taskadapt.notifyDataSetChanged();
                ETTask.setText("");
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TaskArray.isEmpty() || TaskArray.size()==0) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    int removetask = Integer.parseInt(ETTask.getText().toString());
                        removetask=removetask-1;
                    if (removetask>=0 && removetask<TaskArray.size()) {
                        TaskArray.remove(removetask);
                        taskadapt.notifyDataSetChanged();
                        ETTask.setText("");
                    }else{
                        Toast.makeText(MainActivity.this, "Wrong Index number", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskArray.clear();
                taskadapt.notifyDataSetChanged();
            }
        });

    }
}