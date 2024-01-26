package com.bd.smart_park;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParkingEntryRecyclerAdapter extends RecyclerView.Adapter<ParkingEntryRecyclerAdapter.ViewHolder> {

    List<CarParking> dbList;
    static Context context;
    DatabaseHelper helper;

    public interface deleteListener {
        void onDelete();
    }

    public deleteListener listener;

    ParkingEntryRecyclerAdapter(Context context, List<CarParking> dbList, deleteListener listener) {
        this.dbList = new ArrayList<CarParking>();
        this.context = context;
        this.dbList = dbList;
        helper = new DatabaseHelper(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_entry_item, null);
        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.license.setText(dbList.get(position).getCarLicense());
        holder.driver.setText(dbList.get(position).getDriverName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.deleteCarParking(dbList.get(position).getParkingId());
                if(listener != null) listener.onDelete();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView license, driver;
        public ImageView delete;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            license = (TextView) itemLayoutView.findViewById(R.id.license);
            driver = (TextView) itemLayoutView.findViewById(R.id.driver);
            delete = (ImageView) itemLayoutView.findViewById(R.id.delete);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            /*Intent intent = new Intent(context, DetailsActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("position", getAdapterPosition());
            intent.putExtras(extras);
            context.startActivity(intent);*/
            Toast.makeText(context, "you have clicked Row " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }
}
