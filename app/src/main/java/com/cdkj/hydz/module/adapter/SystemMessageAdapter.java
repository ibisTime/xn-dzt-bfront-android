package com.cdkj.hydz.module.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.MessageModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * Created by lei on 2017/8/22.
 */

public class SystemMessageAdapter extends BaseQuickAdapter<MessageModel.ListBean, BaseViewHolder> {

    public SystemMessageAdapter(@Nullable List<MessageModel.ListBean> data) {
        super(R.layout.item_system_message, data);
        Log.e("data.size()", data.size()+"");
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageModel.ListBean item) {
        if (item == null) return;

        helper.setText(R.id.txt_title,item.getSmsTitle());
        helper.setText(R.id.txt_content,item.getSmsContent());
        helper.setText(R.id.txt_time, DateUtil.formatStringData(item.getPushedDatetime(),DEFAULT_DATE_FMT));

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
