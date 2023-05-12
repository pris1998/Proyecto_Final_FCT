package utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.activities.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<MyViewHolder>{

    private Context context;
    private List<DataListPatients> dataListPatients;

    public Adapter(Context context, List<DataListPatients> dataListPatients) {
        this.context = context;
        this.dataListPatients = dataListPatients;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recName.setText(dataListPatients.get(position).getDataName());
        holder.recSurname.setText(dataListPatients.get(position).getDataSurname());
        holder.recCargV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, DetailActivity.class);
                intent.putExtra("Name",dataListPatients.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Surname",dataListPatients.get(holder.getAdapterPosition()).getDataSurname());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataListPatients.size();
    }

    public void searchDataList(ArrayList<DataListPatients> searchList){
        dataListPatients = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView recSurname, recName;
    CardView recCargV;


    public MyViewHolder(@NonNull View itemView){
        super(itemView);

        recSurname = itemView.findViewById(R.id.recSurname);
        recName = itemView.findViewById(R.id.recName);
        recCargV = itemView.findViewById(R.id.recCargV);
    }
}
