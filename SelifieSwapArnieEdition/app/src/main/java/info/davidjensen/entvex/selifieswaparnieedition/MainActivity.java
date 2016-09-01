package info.davidjensen.entvex.selifieswaparnieedition;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView drbArnie1;
    ImageView drbArnie2;
    Button btnSwap;
    int swapCurrent = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find all components
        btnSwap = (Button) findViewById(R.id.btnSwap);
        drbArnie1 = (ImageView) findViewById(R.id.ivArnie1);
        drbArnie2 = (ImageView) findViewById(R.id.ivArnie2);

        btnSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (swapCurrent == 1)
                    swapCurrent = 2;
                else
                {
                    swapCurrent = 1;
                }
                drawArnie();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt("swap", swapCurrent);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        swapCurrent = savedInstanceState.getInt("swap");
        drawArnie();

    }

    public void drawArnie()
    {
        if ( swapCurrent == 1 )
        {
            drbArnie1.setImageResource(R.drawable.arnie);
            drbArnie2.setImageResource(R.drawable.arnie2);
        }

        if( swapCurrent == 2 )
        {
            drbArnie1.setImageResource(R.drawable.arnie2);
            drbArnie2.setImageResource(R.drawable.arnie);
        }
    }
}