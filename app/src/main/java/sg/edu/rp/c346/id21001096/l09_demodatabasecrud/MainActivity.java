package sg.edu.rp.c346.id21001096.l09_demodatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add, edit, retrieve;
    TextView tv;
    EditText et;
    ArrayList<Note> al;
    ListView lv;
    ArrayAdapter<Note> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        retrieve = findViewById(R.id.retrieve);
        edit = findViewById(R.id.edit);
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        lv = findViewById(R.id.lv);
        al = new ArrayList<Note>();
        aa = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);


        //When the INSERT button is clicked, the content in et
        //is inserted into the database
        //When RETRIEVE button is clicked,
        //records will be displayed in the listview

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = et.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(data);

                //Refresh code
                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllNotes());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                al.clear();
                al.addAll(dbh.getAllNotes());
                aa.notifyDataSetChanged();
            }

        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note target = al.get(0); //select the first id

                Intent i = new Intent(MainActivity.this,
                        EditActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }

        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Note data = al.get(position);//automatically gets what you have chosen
                Intent i = new Intent(MainActivity.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                al.clear();
                // al.addAll(dbh.getAllNotes());
                String filterText = et.getText().toString().trim();
                if(filterText.length() == 0) {
                    al.addAll(dbh.getAllNotes());
                }
                else{
                    al.addAll(dbh.getAllNotes(filterText));
                }
                aa.notifyDataSetChanged();
            }
        });







    }

    @Override
    protected void onResume() {
        super.onResume();

        retrieve.performClick();
    }

}