package info.davidjensen.entvex.showmethetasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by entvex on 26-09-2016.
 */
public class TaskAdaptor extends BaseAdapter {

    Context context;
    List<Task> tasks;
    Task task;

    public TaskAdaptor(Context context, List<Task> tasks)
    {
        this.context   = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        if (tasks !=null)
        {
            return tasks.get(i);
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null )
        {
            LayoutInflater InflaterReminder = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = InflaterReminder.inflate(R.layout.task_list_item,null);
        }
        task = tasks.get(position);

        //Filling a item
        if(task != null)
        {
            TextView txtTitle = (TextView) view.findViewById(R.id.txtTaskTitle);
            txtTitle.setText(task.getTask_name());

            TextView txtDescription = (TextView) view.findViewById(R.id.txtTaskDescription);
            txtDescription.setText(task.getPlace_name());
        }
        return view;
    }
}