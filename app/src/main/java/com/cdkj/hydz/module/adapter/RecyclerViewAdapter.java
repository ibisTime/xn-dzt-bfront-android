package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.ManTypeModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<ManTypeModel> list;

    private MyItemClickListener mItemClickListener;


    public RecyclerViewAdapter(Context context, List<ManTypeModel> list) {
        this.list = list;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, null);
        ViewHolder vh = new ViewHolder(view, mItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(list.get(position).isSelect()){
            holder.mTextView.setTextColor(context.getResources().getColor(R.color.black));
        }else{
            holder.mTextView.setTextColor(Color.parseColor("#B3B3B3"));
        }
        holder.mTextView.setText(list.get(position).getName());

        holder.mTextView.setOnClickListener(view -> {
            for (ManTypeModel model : list){
                model.setSelect(false);
            }

            list.get(position).setSelect(true);
            notifyDataSetChanged();

            EventBus.getDefault().post(list.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;

        private MyItemClickListener mListener;

        public ViewHolder(View view, MyItemClickListener mListener) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.txt_type);

            this.mListener = mListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null){
                mListener.OnItemClick(view.findViewById(R.id.txt_type),getPosition());
            }
        }
    }

    public interface MyItemClickListener{
        void OnItemClick(View view, int position);
    }


    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }


}
