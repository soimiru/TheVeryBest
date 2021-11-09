package com.example.theverybest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theverybest.R;
import com.example.theverybest.Utilities;
import com.example.theverybest.vo.RecordsVO;

import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolderRecords>implements View.OnClickListener{

    List<RecordsVO> recordsList;
    View view;

    public RecordsAdapter(List<RecordsVO> recordsList) {
        this.recordsList = recordsList;
    }

    @NonNull
    @Override
    public ViewHolderRecords onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_records, parent, false);
        view.setOnClickListener(this);
        return new ViewHolderRecords(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecords holder, int position) {
        if(recordsList.get(position).getAvatar() != 0){
            holder.imgAvatar.setImageResource(Utilities.avatarList.get(recordsList.get(position).getAvatar()-1).getAvatarId());
            holder.nickname.setText(recordsList.get(position).getName());
            holder.points.setText(recordsList.get(position).getPoints()+"");
            holder.time.setText(recordsList.get(position).getTime());
            holder.right.setText(recordsList.get(position).getCorrect()+"");
            holder.wrong.setText(recordsList.get(position).getIncorrect()+"");
        }
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    public class ViewHolderRecords extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView nickname, time, right, wrong, points;

        public ViewHolderRecords(@NonNull View itemView) {
            super(itemView);

            imgAvatar = view.findViewById(R.id.avatarRECORDS);
            nickname = view.findViewById(R.id.nickRECORDS);
            time = view.findViewById(R.id.timeRECORDS);
            right = view.findViewById(R.id.correctRECORDS);
            wrong = view.findViewById(R.id.wrongRECORDS);
            points = view.findViewById(R.id.pointsRECORDS);
        }
    }

    @Override
    public void onClick(View view) {

    }

}
