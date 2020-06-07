package sg.edu.np.mad.mad_recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final Context context;
    ArrayList<String> data = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        CheckBox cbox;
        ViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.todo);
            cbox = itemView.findViewById(R.id.checkbox);
        }

    }


    public Adapter(ArrayList<String> input,Context context) {
        data = input;
        this.context = context;
    }
    public Adapter.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecard,parent,false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final Adapter.ViewHolder viewHolder,int position){

        viewHolder.txt.setText(data.get(position));
        viewHolder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Test","Click");
                LayoutInflater factory = LayoutInflater.from(context);
                final View DialogView = factory.inflate(R.layout.alert, null);
                final AlertDialog Dialog = new AlertDialog.Builder(context).create();
                Dialog.setView(DialogView);
                TextView temp = DialogView.findViewById(R.id.remove);
                temp.setText(viewHolder.txt.getText() + "?");
                DialogView.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        for(int i = data.size()-1;i >= 0; i--)
                        {
                            if(data.get(i) == viewHolder.txt.getText()){
                                data.remove(i);
                                notifyItemRemoved(i);
                                notifyItemRangeChanged(i,data.size());
                                Log.v("Test","Removed" + i);
                                Dialog.dismiss();
                            }
                        }

                    }
                });
                DialogView.findViewById(R.id.No).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog.dismiss();
                    }
                });

                Dialog.show();


            }
        });
    }
    public int getItemCount() {
        return data.size();
    }







}



