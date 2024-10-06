package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.R;

import java.util.List;

import Models.ListItems;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


//    private final Context context;
    private final List<ListItems> arraylist;

    public MyAdapter( List<ListItems> arrayList) {
//        this.context = context;
        this.arraylist = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(cardView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int index) {
        ListItems item = arraylist.get(index);
        holder.txtTitle.setText(item.getTitle());
        holder.txtDescription.setText(item.getDescription());
    }


    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView txtTitle;
        private final TextView txtDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription= itemView.findViewById(R.id.txtDescription);
        }
    }

}
