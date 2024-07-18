package ps.safiya.ptuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
EditText nameToInsert,markToInsert;
Button AddToDataBase;
MyDatabase myDatabase=new MyDatabase(MainActivity2.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nameToInsert=findViewById(R.id.name_insert);
        markToInsert=findViewById(R.id.mark_inser);
        AddToDataBase=findViewById(R.id.add_to_database);

        AddToDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                student std=new student(Integer.parseInt(markToInsert.getText().toString()) ,nameToInsert.getText().toString());
                nameToInsert.setText("");
                markToInsert.setText("");
                myDatabase.addStudent(std);
                Toast.makeText(MainActivity2.this,"added name="+std.id,Toast.LENGTH_SHORT).show();


               //Intent i=new Intent(MainActivity2.this,MainActivity.class);//do it right
              //i.putExtra("name",std.name);
              // i.putExtra("mark",std.mark);

                finish();





            }
        });
    }
}