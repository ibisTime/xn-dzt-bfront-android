package com.cdkj.hydz.module.adapter;

import android.support.annotation.Nullable;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.NoticeModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * Created by lei on 2017/8/22.
 */

public class MessageListAdapter extends BaseQuickAdapter<NoticeModel.ListBean, BaseViewHolder> {

    public MessageListAdapter(@Nullable List<NoticeModel.ListBean> data) {
        super(R.layout.item_message_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeModel.ListBean item) {
        if (item == null) return;

        helper.setText(R.id.txt_name,item.getCommentName());
        helper.setText(R.id.txt_phone,item.getCommentMobile());
        helper.setText(R.id.txt_message,"\u3000\u3000"+item.getContent());
        helper.setText(R.id.txt_time, DateUtil.formatStringData(item.getCommentDatetime(),DEFAULT_DATE_FMT));


    }

}
