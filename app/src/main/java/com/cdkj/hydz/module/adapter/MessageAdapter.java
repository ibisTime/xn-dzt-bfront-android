package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.NoticeModel;

import java.util.List;

/**
 * Created by lei on 2017/8/26.
 */

public class MessageAdapter extends BaseAdapter {

    private Context context;
    private List<NoticeModel.ListBean> list;

    private ViewHolder holder;

    public MessageAdapter(Context context, List<NoticeModel.ListBean> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_message, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (list.get(i).getCommenter().equals(SPUtilHelpr.getUserId())){ // 此消息是自己发的
            holder.layoutOther.setVisibility(View.GONE);
            holder.txtContentMy.setText(list.get(i).getContent());
            ImgUtils.loadActLogo(context,list.get(i).getCommentPhoto(),holder.imgPhotoMy);

        }else { // 此消息是对方发的
            holder.layoutMy.setVisibility(View.GONE);
            holder.txtContentOther.setText(list.get(i).getContent());
            ImgUtils.loadActLogo(context,list.get(i).getCommentPhoto(),holder.imgPhotoOther);
        }

        return view;
    }

    static class ViewHolder {
        TextView txtContentMy;
        TextView txtContentOther;
        LinearLayout layoutMy;

        ImageView imgPhotoMy;
        ImageView imgPhotoOther;
        LinearLayout layoutOther;

        ViewHolder(View view) {
            layoutMy = view.findViewById(R.id.layout_my);
            imgPhotoMy = view.findViewById(R.id.img_photo_my);
            txtContentMy = view.findViewById(R.id.txt_content_my);

            layoutOther = view.findViewById(R.id.layout_other);
            imgPhotoOther = view.findViewById(R.id.img_photo_other);
            txtContentOther = view.findViewById(R.id.txt_content_other);
        }
    }
}
