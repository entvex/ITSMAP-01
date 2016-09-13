package info.davidjensen.entvex.oneapptorulethemall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by entvex on 10-09-2016.
 */
public class DemoAdaptor extends BaseAdapter {

    Context context;
    ArrayList<Demo> demos;
    Demo demo;

    public DemoAdaptor(Context c, ArrayList<Demo> demoList){
        this.context = c;
        this.demos = demoList;
    }

    @Override
    public int getCount() {
        if(demos!=null) {
            return demos.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(demos!=null) {
            return demos.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater demoInflator = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = demoInflator.inflate(R.layout.demo_list_item, null);
        }

        demo = demos.get(position);
        if(demo!=null){
            TextView txtTitle = (TextView) convertView.findViewById(R.id.txtDemoTitle);
            txtTitle.setText(demo.getName());

            TextView txtDescription = (TextView) convertView.findViewById(R.id.txtDemoDescription);
            txtDescription.setText(demo.getDescription());
        }
        return convertView;
    }
}

