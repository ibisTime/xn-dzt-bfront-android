package com.cdkj.hydz.module.adapter;

import android.support.annotation.Nullable;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.OrderModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * Created by lei on 2017/8/21.
 */

public class OrderAdapter extends BaseQuickAdapter<OrderModel.ListBean, BaseViewHolder> {


    public OrderAdapter(@Nullable List<OrderModel.ListBean> data) {
        super(R.layout.item_order, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderModel.ListBean item) {
        if (item == null) return;

        helper.setText(R.id.txt_number, item.getCode());
        helper.setText(R.id.txt_name, item.getApplyName());
        helper.setText(R.id.txt_phone, item.getApplyMobile());
        helper.setText(R.id.txt_price, MoneyUtils.showPriceWithUnit(item.getAmount()));
        helper.setText(R.id.txt_date, DateUtil.formatStringData(item.getCreateDatetime(), DEFAULT_DATE_FMT));
        helper.setText(R.id.txt_address, item.getLtProvince() + item.getLtCity() + item.getLtArea() + item.getLtAddress());

        helper.setText(R.id.txt_type, item.getModelName());

        getStatus(helper, item);

    }

    private void getStatus(BaseViewHolder helper, OrderModel.ListBean item) {
        switch (item.getStatus()) {
            case "1": // 待量体
                helper.setText(R.id.txt_status, "待量体");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_yellow);
                break;

            case "2": // 待支付
                helper.setText(R.id.txt_status, "待支付");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "3": // 已支付 待录入
                helper.setText(R.id.txt_status, "待录入");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "4": // 待复核
                helper.setText(R.id.txt_status, "待复核");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "5": // 待生产
                helper.setText(R.id.txt_status, "待生产");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "6": // 生产中
                helper.setText(R.id.txt_status, "生产中");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "7": // 已发货
                helper.setText(R.id.txt_status, "已发货");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "8": // 已收货
                helper.setText(R.id.txt_status, "已收货");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "9": // 已评价
                helper.setText(R.id.txt_status, "已评价");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "10": // 已归档
                helper.setText(R.id.txt_status, "已归档");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            case "11": // 取消订单
                helper.setText(R.id.txt_status, "取消订单");
                helper.setBackgroundRes(R.id.txt_status, R.drawable.corner_order_blue);
                break;

            default:

                break;

        }
    }

}
