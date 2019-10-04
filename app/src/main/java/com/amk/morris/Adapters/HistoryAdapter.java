package com.amk.morris.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amk.morris.Model.HistoryModel;
import com.amk.morris.Model.Person;
import com.amk.morris.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Item> implements Filterable {
    private ArrayList<HistoryModel> historyModels;
    private ArrayList<HistoryModel> filtered_historyModels;
    private Context context;

    public HistoryAdapter(ArrayList<HistoryModel> historyModels, Context context) {
        this.historyModels = historyModels;
        this.context = context;
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_object, parent, false);
        return new Item(view);
    }

    @Override
    public int getItemCount() {
        return filtered_historyModels.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        HistoryModel historyModel = filtered_historyModels.get(position);
        Person self = historyModel.getSelf();
        Person opponent = historyModel.getOpponent();
        holder.self_rating.setText(String.valueOf(self.getScore()));
        holder.self_name.setText(self.getName());
        holder.opponent_rating.setText(String.valueOf(opponent.getScore()));
        holder.opponent_name.setText(opponent.getName());
        holder.status.setText(historyModel.getStatus());
        holder.date.setText(historyModel.getDate());
        if (historyModel.getStatus().equals("برد")) {
            holder.status.setBackground(context.getResources().getDrawable(R.drawable.success_bg));
        } else if (historyModel.getStatus().equals("تساوی")) {
            holder.status.setBackground(context.getResources().getDrawable(R.drawable.draw_bg));
        } else {
            holder.status.setBackground(context.getResources().getDrawable(R.drawable.fail_bg));
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filtered_historyModels = historyModels;
                } else {
                    ArrayList<HistoryModel> list = new ArrayList<>();
                    for (HistoryModel model : historyModels) {
                        if (model.getSelf().getName().toLowerCase().contains(charSequence)
                                || String.valueOf(model.getDate()).contains(charSequence)) {
                            list.add(model);
                        }
                    }
                    filtered_historyModels = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered_historyModels;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtered_historyModels = (ArrayList<HistoryModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class Item extends RecyclerView.ViewHolder {
        Button date, status;
        TextView opponent_name, self_name, self_rating, opponent_rating;

        Item(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status_txt);
            opponent_name = itemView.findViewById(R.id.opponent_name);
            opponent_rating = itemView.findViewById(R.id.opponent_rating);
            self_name = itemView.findViewById(R.id.self_name);
            self_rating = itemView.findViewById(R.id.self_rating);
        }
    }
}
