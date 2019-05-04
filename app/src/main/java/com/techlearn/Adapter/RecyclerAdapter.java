package com.techlearn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techlearn.CustomeClick;
import com.techlearn.Model.Password;
import com.techlearn.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private Context context;
    private List<Password> list;
    private CustomeClick click;

    public RecyclerAdapter(Context context, List<Password> list, CustomeClick click) {
        this.context = context;
        this.list = list;
        this.click = click;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_password,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Password password = this.list.get(position);
        holder.tvTitle.setText(password.getTitle());
        holder.tvCount.setText(String.valueOf(position+1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              click.onClick(password);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list ==  null ? 0 : list.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCount, tvTitle;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCount = itemView.findViewById(R.id.tv_count);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
