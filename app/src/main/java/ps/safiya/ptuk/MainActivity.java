package ps.safiya.ptuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   public ArrayList<student> students;
  public MyDatabase myDatabase;
    ListView list_view;
    public MyAdabter myAdabter=new MyAdabter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view = findViewById(R.id.list_view);
       // Intent i=getIntent();
        myDatabase=new MyDatabase(MainActivity.this);
        //SQLiteDatabase dd=null;
        //myDatabase.onCreate(dd);
        students=myDatabase.getAll();
        list_view.setAdapter(myAdabter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.avareg:

                Toast.makeText(MainActivity.this," average : "+myDatabase.getAVG()+"",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent i=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
               // myDatabase.addStudent(new student(i.getIntExtra("mark",0),i.getStringExtra("name")));
                students=myDatabase.getAll();
                myAdabter.notifyDataSetChanged();
                break;
            case R.id.first:
               Intent i2=new Intent(MainActivity.this,MainActivity3.class);

               String s=myDatabase.getFirst().name;
               int s1=myDatabase.getFirst().mark;
                i2.putExtra("NAME",s);
                i2.putExtra("MARK",s1);

               startActivity(i2);
                break;

            case R.id.last:
                Intent i3=new Intent(MainActivity.this,MainActivity3.class);

                String s3=myDatabase.getLast().name;
                int s4=myDatabase.getLast().mark;
                i3.putExtra("NAME",s3);
                i3.putExtra("MARK",s4);

                startActivity(i3);
                break;

            case R.id.sort:

                students=myDatabase.getSorted();
                myAdabter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    class MyAdabter extends BaseAdapter {

        @Override
        public int getCount() {
            return students.size();
        }

        @Override
        public Object getItem(int position) {
            return students.get(position);
        }

        @Override
        public long getItemId(int position) {
            return students.get(position).id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.student_item,parent,false);
            TextView v1=view.findViewById(R.id.name_list);
          TextView v2=view.findViewById(R.id.mark_list);

          v1.setText(students.get(position).name);
          v2.setText(students.get(position).mark+"");




            return view;
        }
    }
}