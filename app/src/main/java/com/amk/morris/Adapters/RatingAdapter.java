package com.amk.morris.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amk.morris.Model.Person;
import com.amk.morris.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.Item> implements Filterable {
    private ArrayList<Person> personArrayList;
    private ArrayList<Person> filtered_personArrayList;
    private Context context;
    private int MyID;

    public RatingAdapter(ArrayList<Person> personArrayList, Context context) {
        this.personArrayList = personArrayList;
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("pref", MODE_PRIVATE);
        MyID = sharedPreferences.getInt("MyID", -1);
        MyID = 1;
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rating_object, parent, false);
        return new Item(view);
    }

    @Override
    public int getItemCount() {
        return filtered_personArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        Person person = filtered_personArrayList.get(position);
        holder.name.setText(person.getName());
        holder.selfRating.setText(String.valueOf(person.getScore()));
        if (person.getId() == MyID) {
            holder.ratingBack.setBackground(context.getResources().getDrawable(R.drawable.rating_me_object_back));
        } else {
            holder.ratingBack.setBackground(context.getResources().getDrawable(R.drawable.history_object_back));
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filtered_personArrayList = personArrayList;
                } else {
                    ArrayList<Person> list = new ArrayList<>();
                    for (Person person : personArrayList) {
                        if (person.getName().toLowerCase().contains(charSequence)
                                || String.valueOf(person.getRank()).contains(charSequence)) {
                            list.add(person);
                        }
                    }
                    filtered_personArrayList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered_personArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtered_personArrayList = (ArrayList<Person>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class Item extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView name, selfRating;
        LinearLayout ratingBack;

        Item(@NonNull View itemView) {
            super(itemView);
            ratingBack = itemView.findViewById(R.id.rating_object_back);
            profileImage = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.self_name);
            selfRating = itemView.findViewById(R.id.self_rating);
        }
    }
}
