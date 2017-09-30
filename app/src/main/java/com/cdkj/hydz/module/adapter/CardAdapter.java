package com.cdkj.hydz.module.adapter;

import android.support.annotation.Nullable;

import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.CardModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by lei on 2017/8/22.
 */

public class CardAdapter extends BaseQuickAdapter<CardModel.ListBean, BaseViewHolder> {

    public CardAdapter(@Nullable List<CardModel.ListBean> data) {
        super(R.layout.item_card,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardModel.ListBean item) {

        helper.setText(R.id.txt_name, item.getBankName());

        String card = item.getBankcardNumber();
        helper.setText(R.id.txt_number, card.substring(card.length()-4,card.length()));

//        if(item.getBankCode() != null){
//            String bankCode = item.getBankCode().toLowerCase();
//
//            int logoId = mContext.getResources().getIdentifier("logo_"+bankCode, "mipmap" , mContext.getPackageName());
//            int backId = mContext.getResources().getIdentifier("back_"+bankCode, "mipmap" , mContext.getPackageName());
//
//            if(logoId == 0 && backId == 0){
//                helper.setBackgroundRes(R.id.img_bankCart, R.mipmap.logo_defalut);
//                helper.setBackgroundRes(R.id.layout_bankBg, R.mipmap.back_default);
//            }else {
//                helper.setBackgroundRes(R.id.img_bankCart, logoId);
//                helper.setBackgroundRes(R.id.layout_bankBg, backId);
//            }
//
//        }
    }
}
