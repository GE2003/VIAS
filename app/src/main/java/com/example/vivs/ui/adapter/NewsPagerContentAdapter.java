package com.example.vivs.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vivs.R;
import com.example.vivs.model.domin.Category;
import com.example.vivs.model.domin.news;
import com.skydoves.elasticviews.ElasticCardView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsPagerContentAdapter extends RecyclerView.Adapter<NewsPagerContentAdapter.InnerHolder> {
public    OnListItemClickListener mItemClickListener=null;
    private static final String TAG = "NewsPagerContentAdapter";
    List<news> data  =new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_title, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InnerHolder holder, int position) {
        news news = data.get(position);

        holder.setData(news);
        holder.elasticCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    com.example.vivs.model.domin.news item = data.get(position);
                    mItemClickListener.onItemClick(item);

                }else {
                    Log.d(TAG,"监听器为空");
                }

            }
        });


    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public void setDATA(List<news> records) {
        //这里是接收fragment传进来的数据
        data.clear();
        data.addAll(records);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.News_title_tv)
        public TextView News_title_tv;
         @BindView(R.id.elect_card_view)
         public ElasticCardView elasticCardView;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(news news) {

            News_title_tv.setText(news.getTitle());
        }


    }
    public void  setOnListItemClickListener(OnListItemClickListener listener){
        this.mItemClickListener=listener;
    }
        public interface  OnListItemClickListener{
        void onItemClick(news item);
        }
}
