package com.cyberessence.cyberorangeteam.gamewithoutfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RadioButtonMenu extends AppCompatActivity {
    Button buttonSelectMode;
    int previousSelectedMode =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonSelectMode = (Button) findViewById(R.id.buttonSelectMode);

        buttonSelectMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                openContextMenu(v);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem itemGame = menu.findItem(R.id.itemGame);
        MenuItem itemRandomGame = menu.findItem(R.id.itemRandomGame);

        if (previousSelectedMode==1)
        {
            itemGame.setChecked(true);
        }
        else if (previousSelectedMode==2)
        {
            itemRandomGame.setChecked(true);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.itemGame)
        {
            previousSelectedMode = 1;
            Toast.makeText(getBaseContext(),"Обычный режим",Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId()==R.id.itemRandomGame)
        {
            previousSelectedMode = 2;
            Toast.makeText(getBaseContext(),"Случайные карточки", Toast.LENGTH_LONG).show();
        }

        return super.onContextItemSelected(item);
    }
}
