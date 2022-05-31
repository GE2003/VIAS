package com.example.vivs.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.vivs.Base.BaseFragment;
import com.example.vivs.R;

public class FavoriteFragment extends BaseFragment {

    @Override
    protected void initview(View view) {
        TextView viewById = view.findViewById(R.id.text_view);
        Log.d("fav","view-----------"+viewById);
    }

    @Override
    protected void getFragmentContext(Context context) {

    }

    @Override
    protected void onClicklistener() {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void LoadData() {

    }
}
