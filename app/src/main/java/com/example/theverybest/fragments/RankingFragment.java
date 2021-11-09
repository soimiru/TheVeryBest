package com.example.theverybest.fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theverybest.ConexionSQLiteHelper;
import com.example.theverybest.R;
import com.example.theverybest.Utilities;
import com.example.theverybest.adapters.RecordsAdapter;
import com.example.theverybest.interfaces.IComunicationFragments;
import com.example.theverybest.vo.PlayerVO;
import com.example.theverybest.vo.RecordsVO;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingFragment extends Fragment {

    View view;
    Activity activity = getActivity();
    IComunicationFragments interfaceComunicationFragments;

    RecyclerView recyclerPlayersRecords;
    PlayerVO playerSelected;
    ArrayList<RecordsVO> recordsList;

    ImageButton backButton;
    RecyclerView recyclerRecords;

    public RankingFragment() {
        // Required empty public constructor
    }
    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunicationFragments =(IComunicationFragments) activity;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ranking, container, false);

        backButton = view.findViewById(R.id.backButton);

        recyclerRecords = view.findViewById(R.id.recyclerRecords);
        recyclerRecords.setLayoutManager(new LinearLayoutManager(this.activity));
        recyclerRecords.setHasFixedSize(true);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceComunicationFragments.showMenu();
            }
        });

        //QUERY PARA MOSTRAR EL RANKING ORDENADO POR SCORE Y TIEMPO
        checkRecords("SELECT * FROM " + Utilities.RANKING_BD + " ORDER BY score DESC, totaltime ASC" );

        fillRecordAdapter();

        return view;
    }

    private void checkRecords(String query) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(activity, Utilities.RANKING_BD, null, 1);
        SQLiteDatabase db = conn.getReadableDatabase(); //Leemos la BD

        Cursor cursor = db.rawQuery(query, null);

        RecordsVO recordsVO = null;
        recordsList = new ArrayList<RecordsVO>();

        while(cursor.moveToNext()){
            recordsVO = new RecordsVO();
            recordsVO.setId(cursor.getInt(1));
            recordsVO.setName(cursor.getString(2));
            recordsVO.setAvatar(cursor.getInt(3));
            recordsVO.setPoints(cursor.getInt(4));
            recordsVO.setCorrect(cursor.getInt(5));
            recordsVO.setIncorrect(cursor.getInt(6));
            recordsVO.setTime(cursor.getString(7));
            recordsVO.setTotaltime(cursor.getInt(8));

            recordsList.add(recordsVO);
        }

        db.close();
    }

    private void fillRecordAdapter() {
        RecordsAdapter recordsAdapter = new RecordsAdapter(recordsList);
        recyclerRecords.setAdapter(recordsAdapter);

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }
}