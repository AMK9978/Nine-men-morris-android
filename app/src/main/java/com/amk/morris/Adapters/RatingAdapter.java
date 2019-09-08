package com.amk.morris.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amk.morris.Model.Person;
import com.amk.morris.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.Item> {
    private ArrayList<Person> personArrayList;
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
        return personArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        Person person = personArrayList.get(position);
        holder.name.setText(person.getName());
        holder.selfRating.setText(person.getRating());
        if (person.getId() == MyID){
            holder.ratingBack.setBackground(context.getResources().getDrawable(R.drawable.rating_me_object_back));
        }else{
            holder.ratingBack.setBackground(context.getResources().getDrawable(R.drawable.history_object_back));
        }
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
