package info.davidjensen.entvex.oneapptorulethemall;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class letItSlide extends AppCompatActivity {

    Button btnOKLetItSlide;
    Button btnCancelLetItSlide;

    int Green = 1;
    int Blue  = 1;
    int Red   = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_let_it_slide);

        btnOKLetItSlide             = (Button) findViewById(R.id.btnOKLetItSlide);
        btnCancelLetItSlide         = (Button) findViewById(R.id.btnCancelLetItSlide);
        SeekBar skbBlue_LetItSlide  = (SeekBar) findViewById(R.id.skbBlue_LetItSlide);
        SeekBar skbGreen_LetItSlide = (SeekBar) findViewById(R.id.skbGreen_LetItSlide);
        SeekBar skbRed_LetItSlide   = (SeekBar) findViewById(R.id.skbRed_LetItSlide);

        btnOKLetItSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("letItSlide","The colors is Blue: " + Blue + " Green: " + Green + " Red: " +Red);
                setResult(RESULT_OK,data);
                finish();
            }
        });

       btnCancelLetItSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        skbBlue_LetItSlide.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Blue = i;
                setActivityBackgroundColor(Color.rgb(Red,Green,Blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbGreen_LetItSlide.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Green = i;
                setActivityBackgroundColor(Color.rgb(Red,Green,Blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbRed_LetItSlide.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Red = i;
                setActivityBackgroundColor(Color.rgb(Red,Green,Blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}
