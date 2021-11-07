package com.example.theverybest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theverybest.R;
import com.example.theverybest.Utilities;
import com.example.theverybest.vo.AvatarVO;

import java.util.List;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.ViewHolderAvatar> {

    //ADAPTADOR PARA EL RECYCLER VIEW!

    List<AvatarVO> avatarList;
    View view;
    int positionAvatar;

    public AvatarAdapter(List<AvatarVO> avatarList) {
        this.avatarList = avatarList;
    }

    @NonNull
    @Override
    public AvatarAdapter.ViewHolderAvatar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_avatar, parent, false);
        return new ViewHolderAvatar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarAdapter.ViewHolderAvatar holder, int position) {
        //Obtenemos a traves del id de arrayList Avatar de Utilities, el id de la imagen
        holder.imgAvatar.setImageResource(avatarList.get(position).getAvatarId());

        //Esto hace falta porque lo de abajo es una inner class
        final int pos = position;

        //Para la rallita de debajo de las imágenes
        holder.cardAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionAvatar = pos;
                Utilities.selectedAvatar = avatarList.get(pos);
                Utilities.selectedAvatarID = pos+1;
                notifyDataSetChanged();

            }
        });

        if(positionAvatar == position){
            holder.selectionBar.setBackgroundColor(view.getResources().getColor(R.color.black));
        }
        else{
            holder.selectionBar.setBackgroundColor(view.getResources().getColor(R.color.yourColorTitle));
        }
    }

    @Override
    public int getItemCount() {
        return avatarList.size();
    }


    //AQUÍ SE CONSTRUYE LO QUE SE VA A MOSTRAR

    public class ViewHolderAvatar extends RecyclerView.ViewHolder {

        CardView cardAvatar;
        ImageView imgAvatar;
        TextView selectionBar;

        public ViewHolderAvatar(@NonNull View itemView) {
            super(itemView);
            //TODO ESTO ESTÁ EN GRID AVATAR
            cardAvatar = itemView.findViewById(R.id.cardAvatar);
            imgAvatar = itemView.findViewById(R.id.idAvatar);
            selectionBar = itemView.findViewById(R.id.selectionBar);
        }
    }
}
