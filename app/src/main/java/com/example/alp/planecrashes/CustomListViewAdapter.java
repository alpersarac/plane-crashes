package com.example.alp.planecrashes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomListViewAdapter extends ArrayAdapter<Person> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private Activity CustomActivity;
    private final ArrayList<Person> persons;

    public CustomListViewAdapter(Context context, ArrayList<Person> persons) {
        super(context,0, persons);
        this.context = context;
        this.persons = persons;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Person getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return persons.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.list_view_item, null);

            holder = new ViewHolder();
            holder.personImage = (ImageView) convertView.findViewById(R.id.person_image);
            holder.personNameLabel = (TextView) convertView.findViewById(R.id.person_name_label);
            holder.personAddressLabel = (TextView) convertView.findViewById(R.id.person_address_label);
            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();
        }

        Person person = persons.get(position);
        if(person != null){
            if (person.getPhotoURL()==""){
                holder.personImage.setVisibility(View.GONE);
            }else{
                Glide.with(context)
                        .load(person.getPhotoURL())
                        .centerCrop()
                        .into(holder.personImage);
            }


           // holder.personImage.setImageResource(person.getPhotoURL());
            holder.personNameLabel.setText(person.getName());
            holder.personAddressLabel.setText(person.getAddress());

        }
        return convertView;
    }

    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView personNameLabel;
        TextView personAddressLabel;
        ImageView personImage;

    }
}