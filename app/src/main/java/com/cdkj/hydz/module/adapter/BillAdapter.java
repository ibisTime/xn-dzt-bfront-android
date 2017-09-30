package com.cdkj.hydz.module.adapter;

import android.support.annotation.Nullable;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.BillModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DATE_md;

/**
 * Created by lei on 2017/8/22.
 */

public class BillAdapter extends BaseQuickAdapter<BillModel.ListBean,BaseViewHolder> {

    public BillAdapter(@Nullable List<BillModel.ListBean> data) {
        super (R.layout.item_bill,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillModel.ListBean item) {

        helper.setText(R.id.txt_date, DateUtil.formatStringData(item.getCreateDatetime(),DATE_md));

        helper.setText(R.id.txt_title, item.getBizNote());

        helper.setText(R.id.txt_amount, MoneyUtils.showPriceWithUnit(item.getTransAmount()));


    }
}
