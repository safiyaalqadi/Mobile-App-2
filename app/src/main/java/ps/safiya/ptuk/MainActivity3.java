package ps.safiya.ptuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int what=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        result=findViewById(R.id.result);
        Intent i=getIntent();
        String s1;
       // result.setText(i.getStringExtra("firstName")+""+ i.getIntExtra("firstMark",0));
        result.setText(" Name: "+i.getStringExtra("NAME")+"\n Mark: "+ i.getIntExtra("MARK",0));
       // result.setText(i.getStringExtra("lastName")+" "+ i.getIntExtra("lastMark",0));



    }
}