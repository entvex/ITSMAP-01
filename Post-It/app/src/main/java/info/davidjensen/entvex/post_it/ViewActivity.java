package info.davidjensen.entvex.post_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    Button btnEdit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        TextView tvText = (TextView) findViewById(R.id.tvText);

        intent = new Intent(this, EditActivity.class);

        if (getIntent().getStringExtra("text") != null)
            tvText.setText(getIntent().getStringExtra("text"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });

    }
}
