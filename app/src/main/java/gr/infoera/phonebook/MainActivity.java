package gr.infoera.phonebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addContact(View view){

        //TODO: Den emfanizetai to toast. Na diorthothei. Epeita na mpei kodikas insert sti vasi dedomenon
        Toast.makeText(this,"Yes",Toast.LENGTH_SHORT);
    }

}
