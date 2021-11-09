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
import com.example.theverybest.vo.PlayerVO;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolderPlayer> implements View.OnClickListener {

    //ADAPTADOR PARA EL RECYCLER VIEW!

    List<PlayerVO> playerList;
    View view;
    int positionAvatar;
    private View.OnClickListener listener;

    public PlayerAdapter(List<PlayerVO> playerList) {
        this.playerList = playerList;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_player, parent, false);
        view.setOnClickListener(this);
        return new ViewHolderPlayer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayer holder, int position) {
        holder.imgAvatar.setImageResource(Utilities.avatarList.get(playerList.get(position).getAvatar()-1).getAvatarId());
        holder.tvName.setText(playerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class ViewHolderPlayer extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView tvName, tvBestScore;

        public ViewHolderPlayer(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.idAvatarItemList);
            tvName = itemView.findViewById(R.id.idNick);
            tvBestScore = itemView.findViewById(R.id.idBestScore);
        }
    }
}
