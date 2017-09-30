package com.cdkj.hydz.module.adapter;

import android.support.annotation.Nullable;

import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.ManListModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;
import static com.cdkj.hydz.module.man.ManFragment.userParameterModel;

/**
 * Created by lei on 2017/8/22.
 */

public class ManAdapter extends BaseQuickAdapter<ManListModel.ListBean, BaseViewHolder> {

    String province;
    String city;
    String area;
    String address;



    public ManAdapter(@Nullable List<ManListModel.ListBean> data) {
        super(R.layout.item_man, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ManListModel.ListBean item) {
        helper.setText(R.id.txt_ltUserName, SPUtilHelpr.getRealName());
        helper.setText(R.id.txt_time, "最近下单时间:"+ DateUtil.formatStringData(item.getLastOrderDatetime(),DEFAULT_DATE_FMT));
        helper.setText(R.id.txt_name, item.getRealName());
        helper.setText(R.id.txt_phone, item.getMobile());
        helper.setText(R.id.txt_address,checkAddress(item));

        setUserLevel(helper, item);

        setUserFrequent(helper, item);
    }

    private String checkAddress(ManListModel.ListBean item){
        if (item.getProvince() == null){
            province = "";
        }else {
            province = item.getProvince();
        }

        if (item.getCity() == null){
            city = "";
        }else {
            city = item.getCity();
        }

        if (item.getArea() == null){
            area = "";
        }else {
            area = item.getArea();
        }

        return province+city+area;

    }

    private void setUserLevel(BaseViewHolder helper, ManListModel.ListBean item) {
        if (item.getLevel() == null) return;

        if (userParameterModel == null) return;

        try {
            JSONObject jsonObject = new JSONObject(userParameterModel.getUser_level());
            Iterator it = jsonObject.keys();

            while (it.hasNext()){
                String key = (String) it.next();

                if (key.equals(item.getLevel())){
                    helper.setText(R.id.txt_level, jsonObject.getString(key));

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setUserFrequent(BaseViewHolder helper, ManListModel.ListBean item) {
        if (item.getFrequent() == null) return;

        switch (item.getFrequent()){
            case "1":
                helper.setText(R.id.txt_frequent, "新客户");
                break;

            case "2":
                helper.setText(R.id.txt_frequent, "老客户");
                break;

            case "3":
                helper.setText(R.id.txt_frequent, "活跃老客户");
                break;

            case "4":
                helper.setText(R.id.txt_frequent, "非常活跃老客户");
                break;

            case "5":
                helper.setText(R.id.txt_frequent, "预流失客户");
                break;
        }
    }

}
