package com.cdkj.hydz.module.order;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.FragmentOrderEmbroidBinding;
import com.cdkj.hydz.module.adapter.OrderColorAdapter;
import com.cdkj.hydz.module.adapter.OrderFontAdapter;
import com.cdkj.hydz.module.adapter.OrderLocationAdapter;
import com.cdkj.hydz.module.model.OrderCommitModel;
import com.cdkj.hydz.module.model.OrderCraftModel;
import com.cdkj.hydz.module.model.OrderProcessModel;
import com.cdkj.hydz.module.model.SwitchModel;
import com.cdkj.hydz.module.order.helper.OrderHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static com.cdkj.baselibrary.appmanager.EventTags.ADDRESS;
import static com.cdkj.baselibrary.appmanager.EventTags.COLOR;
import static com.cdkj.baselibrary.appmanager.EventTags.CONTENT;
import static com.cdkj.baselibrary.appmanager.EventTags.FONT;
import static com.cdkj.baselibrary.appmanager.EventTags.LOCATION;
import static com.cdkj.baselibrary.appmanager.EventTags.PRODUCTDATA;
import static com.cdkj.baselibrary.appmanager.EventTags.REMARK;
import static com.cdkj.hydz.module.order.helper.OrderHelper.hPlusCraftModel;
import static com.cdkj.hydz.module.order.helper.OrderHelper.orderDeatilModel;
import static com.cdkj.hydz.module.order.helper.OrderHelper.orderProcessModel;

/**
 * Created by lei on 2017/8/24.
 */

public class OrderEmbroidFragment extends BaseLazyFragment {

    private FragmentOrderEmbroidBinding mBinding;

    // 刺绣字体
    private GridLayoutManager fontManager;
    private OrderFontAdapter fontAdapter;
    public ArrayList<OrderCraftModel> fontData = new ArrayList<>();

    // 刺绣字体
    private GridLayoutManager locationManager;
    private OrderLocationAdapter locationAdapter;
    public ArrayList<OrderCraftModel> locationData = new ArrayList<>();

    // 刺绣字体
    private GridLayoutManager colorManager;
    private OrderColorAdapter colorAdapter;
    public ArrayList<OrderCraftModel> colorData = new ArrayList<>();

    // 禁止输入开关
    private boolean banInputSwitch = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_embroid, null, false);

        initEditView();
        initRecyclerView();
        initListener();

        return mBinding.getRoot();
    }

    private void initEditView() {
        if(banInputSwitch){
            mBinding.txtContentEdit.setVisibility(View.GONE);
            mBinding.txtFontEdit.setVisibility(View.GONE);
            mBinding.txtLocationEdit.setVisibility(View.GONE);
            mBinding.txtColorEdit.setVisibility(View.GONE);
            mBinding.txtRemarkEdit.setVisibility(View.GONE);
            mBinding.txtAddressEdit.setVisibility(View.GONE);
        }else {
            mBinding.txtContentEdit.setVisibility(View.VISIBLE);
            mBinding.txtFontEdit.setVisibility(View.VISIBLE);
            mBinding.txtLocationEdit.setVisibility(View.VISIBLE);
            mBinding.txtColorEdit.setVisibility(View.VISIBLE);
            mBinding.txtRemarkEdit.setVisibility(View.VISIBLE);
            mBinding.txtAddressEdit.setVisibility(View.VISIBLE);
        }
    }

    private void initRecyclerView() {

        fontManager = new GridLayoutManager(mActivity, 3);
        fontAdapter = new OrderFontAdapter(mActivity, fontData);
        setRecyclerView(mBinding.recyclerEmbroidFont,fontManager);

        locationManager = new GridLayoutManager(mActivity, 3);
        locationAdapter = new OrderLocationAdapter(mActivity, locationData);
        setRecyclerView(mBinding.recyclerEmbroidLocation,locationManager);

        colorManager = new GridLayoutManager(mActivity, 3);
        colorAdapter = new OrderColorAdapter(mActivity, colorData);
        setRecyclerView(mBinding.recyclerEmbroidColor,colorManager);
    }

    private void setRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void initListener() {

        mBinding.txtContentEdit.setOnClickListener(view -> {
            mBinding.txtContentEdit.setVisibility(View.GONE);
            mBinding.layoutContentBtn.setVisibility(View.VISIBLE);
            mBinding.layoutEmbroidContent.setVisibility(View.VISIBLE);
        });
        mBinding.txtContentConfirm.setOnClickListener(view -> {

            if (check(CONTENT)) {
                mBinding.txtContentEdit.setVisibility(View.VISIBLE);
                mBinding.layoutContentBtn.setVisibility(View.GONE);
                mBinding.layoutEmbroidContent.setVisibility(View.GONE);

                mBinding.txtEmbroidContent.setText(mBinding.edtEmbroidContent.getText().toString());

                packContentData();
            }

        });

        mBinding.txtFontEdit.setOnClickListener(view -> { // 刺绣字体
            setEditShow(mBinding.txtFontEdit, mBinding.layoutFontBtn, mBinding.recyclerEmbroidFont,fontAdapter);
        });
        mBinding.txtFontConfirm.setOnClickListener(view -> { // 刺绣字体 确定
            if (check(FONT)) {
                setEditHide(mBinding.txtFontEdit, mBinding.layoutFontBtn, mBinding.recyclerEmbroidFont);

                packFontData();
            }
        });
//        mBinding.txtFontCancel.setOnClickListener(view -> { // 刺绣字体 取消
//            setEditHide(mBinding.txtFontEdit, mBinding.layoutFontBtn, mBinding.recyclerEmbroidFont);
//        });

        mBinding.txtLocationEdit.setOnClickListener(view -> { // 刺绣位置
            setEditShow(mBinding.txtLocationEdit, mBinding.layoutLocationBtn, mBinding.recyclerEmbroidLocation,locationAdapter);
        });
        mBinding.txtLocationConfirm.setOnClickListener(view -> { // 刺绣字体 确定
            if (check(LOCATION)) {
                setEditHide(mBinding.txtLocationEdit, mBinding.layoutLocationBtn, mBinding.recyclerEmbroidLocation);

                packLocationData();
            }
        });
//        mBinding.txtLocationCancel.setOnClickListener(view -> { // 刺绣字体 取消
//            setEditHide(mBinding.txtLocationEdit, mBinding.layoutLocationBtn, mBinding.recyclerEmbroidLocation);
//        });

        mBinding.txtColorEdit.setOnClickListener(view -> { // 刺绣字体
            setEditShow(mBinding.txtColorEdit, mBinding.layoutColorBtn, mBinding.recyclerEmbroidColor,colorAdapter);
        });
        mBinding.txtColorConfirm.setOnClickListener(view -> { // 刺绣字体 确定
            if (check(COLOR)) {
                setEditHide(mBinding.txtColorEdit, mBinding.layoutColorBtn, mBinding.recyclerEmbroidColor);

                packColorData();
            }
        });
//        mBinding.txtColorCancel.setOnClickListener(view -> { // 刺绣字体 取消
//            setEditHide(mBinding.txtColorEdit, mBinding.layoutColorBtn, mBinding.recyclerEmbroidColor);
//        });

        mBinding.txtAddressEdit.setOnClickListener(view -> {
            mBinding.txtAddressEdit.setVisibility(View.GONE);
            mBinding.layoutAddressBtn.setVisibility(View.VISIBLE);
            mBinding.layoutEmbroidAddress.setVisibility(View.VISIBLE);
        });
        mBinding.txtAddressConfirm.setOnClickListener(view -> {
            if (check(ADDRESS)) {
                mBinding.txtAddressEdit.setVisibility(View.VISIBLE);
                mBinding.layoutAddressBtn.setVisibility(View.GONE);
                mBinding.layoutEmbroidAddress.setVisibility(View.GONE);

                mBinding.txtEmbroidAddress.setText(mBinding.edtEmbroidAddress.getText().toString());

                packAddressData();
            }

        });


        mBinding.txtRemarkEdit.setOnClickListener(view -> {
            mBinding.txtRemarkEdit.setVisibility(View.GONE);
            mBinding.layoutRemarkBtn.setVisibility(View.VISIBLE);
            mBinding.layoutEmbroidRemark.setVisibility(View.VISIBLE);
        });
        mBinding.txtRemarkConfirm.setOnClickListener(view -> {
            if (check(REMARK)) {
                mBinding.txtRemarkEdit.setVisibility(View.VISIBLE);
                mBinding.layoutRemarkBtn.setVisibility(View.GONE);
                mBinding.layoutEmbroidRemark.setVisibility(View.GONE);

                mBinding.txtEmbroidRemark.setText(mBinding.edtEmbroidRemark.getText().toString());

                packRemarkData();
            }

        });
    }

    private void setEditShow(View vEdt, View vBtn, RecyclerView recyclerView, RecyclerView.Adapter adapter){
        vEdt.setVisibility(View.GONE);
        vBtn.setVisibility(View.VISIBLE);

        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void setEditHide(View vEdt, View vBtn, View vRecycler){
        vEdt.setVisibility(View.VISIBLE);
        vBtn.setVisibility(View.GONE);
        vRecycler.setVisibility(View.GONE);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Subscribe
    public void setCraftView(OrderCraftModel model){
        String name = "";
        if (OrderHelper.isShowCraftPrice){
            name = model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")";
        }else {
            name = model.getName();
        }

        switch (model.getEventBusTag()){
            case EventTags.FONT:
                mBinding.txtEmbroidFont.setText(name);
                break;

            case EventTags.LOCATION:
                mBinding.txtEmbroidLocation.setText(name);
                break;

            case COLOR:
                mBinding.txtEmbroidColor.setText(name);
                break;

        }

    }

    @Subscribe
    public void setSwitch(SwitchModel model){
        if(model.getEventTag().equals(EventTags.SWITCHALL) || model.getEventTag().equals(EventTags.SWITCHHPLUS)){
            banInputSwitch = model.isSwitchs();
            initEditView();
        }

    }

    @Subscribe
    public void setData(String tag){
        if(tag.equals(EventTags.ORDERDATAOK)){
            if(orderProcessModel != null){
                initFont();
                initColor();
                initLocation();

//                setEmbroidView();
//                setOtherView();
            }
        }

    }

    @Subscribe
    public void setProductData(String tag){
        if(tag.equals(PRODUCTDATA)){
            if(hPlusCraftModel != null){
                setProductData();
            }
        }

    }


    private void initFont(){
        if (orderProcessModel.get_$503() == null)
            return;

        for (OrderProcessModel._$503Bean bean : orderProcessModel.get_$503()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            fontData.add(model);

        }

        fontAdapter.notifyDataSetChanged();
    }

    private void initLocation(){
        if (orderProcessModel.get_$502() == null)
            return;

        for (OrderProcessModel._$502Bean bean : orderProcessModel.get_$502()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            locationData.add(model);

        }

        locationAdapter.notifyDataSetChanged();
    }

    private void initColor(){
        if (orderProcessModel.get_$504() == null)
            return;

        for (OrderProcessModel._$504Bean bean : orderProcessModel.get_$504()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            colorData.add(model);

        }

        colorAdapter.notifyDataSetChanged();
    }

//    private void setEmbroidView(){
//
//        if(TextUtils.isEmpty(orderDeatilModel.getResultMap().getCIXIU()))
//            return;
//
//        try {
//            JSONObject jsonObject = new JSONObject(orderDeatilModel.getResultMap().getCIXIU());
//            Iterator it = jsonObject.keys();
//
//            JSONObject object;
//            while (it.hasNext()){
//                String key = (String) it.next();
//                object = new JSONObject(jsonObject.getString(key));
//
//                switch(key){
//
//                    case "5-03":
//
//                        for (OrderCraftModel model : fontData){
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtEmbroidFont.setText(model.getName());
//                                packFontData();
//                            }
//                        }
//                        fontAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "5-02":
//
//                        for (OrderCraftModel model : locationData){
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtEmbroidLocation.setText(model.getName());
//                                packLocationData();
//                            }
//                        }
//                        locationAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "5-04":
//
//                        for (OrderCraftModel model : colorData){
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtEmbroidColor.setText(model.getName());
//                                packColorData();
//                            }
//                        }
//                        colorAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "5-01":
//                        mBinding.txtEmbroidContent.setText(object.getString("code"));
//                        mBinding.edtEmbroidContent.setText(object.getString("code"));
//                        packContentData();
//                        break;
//
//                }
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    private void setProductData(){
        try {
            for (String value : hPlusCraftModel.getCodeList()) {
                Log.e("value",value);

                for (OrderCraftModel model : fontData){
                    if(TextUtils.equals(model.getCode(), value)){
                        model.setSelect(true);

                        mBinding.txtEmbroidFont.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");
                        packFontData();
                    }
                }
                fontAdapter.notifyDataSetChanged();

                for (OrderCraftModel model : locationData){
                    if(TextUtils.equals(model.getCode(), value)){
                        model.setSelect(true);

                        mBinding.txtEmbroidLocation.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");
                        packLocationData();
                    }
                }
                locationAdapter.notifyDataSetChanged();


                for (OrderCraftModel model : colorData){
                    if(TextUtils.equals(model.getCode(), value)){
                        model.setSelect(true);

                        mBinding.txtEmbroidColor.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");
                        packColorData();
                    }
                }
                colorAdapter.notifyDataSetChanged();

            }

            mBinding.txtEmbroidContent.setText(hPlusCraftModel.getCodeMap().get("5-01"));
            mBinding.edtEmbroidContent.setText(hPlusCraftModel.getCodeMap().get("5-01"));
            if (!TextUtils.equals(mBinding.edtEmbroidContent.getText().toString().trim(),"")){
                packContentData();
            }

            mBinding.txtEmbroidRemark.setText(hPlusCraftModel.getCodeMap().get("6-05"));
            mBinding.edtEmbroidRemark.setText(hPlusCraftModel.getCodeMap().get("6-05"));
            if (!TextUtils.equals(mBinding.edtEmbroidRemark.getText().toString().trim(),"")){
                packRemarkData();
            }


            mBinding.txtEmbroidAddress.setText(hPlusCraftModel.getCodeMap().get("6-04"));
            mBinding.edtEmbroidAddress.setText(hPlusCraftModel.getCodeMap().get("6-04"));
            if (!TextUtils.equals(mBinding.edtEmbroidAddress.getText().toString().trim(),"")){
                packAddressData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void setOtherView(){
//
//        if(TextUtils.isEmpty(orderDeatilModel.getResultMap().getQITA()))
//            return;
//
//        try {
//            JSONObject jsonObject = new JSONObject(orderDeatilModel.getResultMap().getQITA());
//            Iterator it = jsonObject.keys();
//
//            JSONObject object;
//            while (it.hasNext()){
//                String key = (String) it.next();
//                object = new JSONObject(jsonObject.getString(key));
//
//                switch(key){
//
//                    case "6-05":
//                        mBinding.txtEmbroidRemark.setText(object.getString("code"));
//                        mBinding.edtEmbroidRemark.setText(object.getString("code"));
//                        packRemarkData();
//                        break;
//
//                    case "6-04":
//                        mBinding.txtEmbroidAddress.setText(object.getString("code"));
//                        mBinding.edtEmbroidAddress.setText(object.getString("code"));
//                        packAddressData();
//                        break;
//
//                }
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

    private boolean check(String tag){

        if(TextUtils.equals(tag, CONTENT)){
            if (TextUtils.equals(mBinding.edtEmbroidContent.getText().toString(), "")){
//                Toast.makeText(mActivity, "请填写刺绣内容", Toast.LENGTH_SHORT).show();
//                return false;
            }

            return true;
        } else if(TextUtils.equals(tag, FONT)){
            for(OrderCraftModel model : fontData){
                if(model.isSelect()){
                    return true;
                }
            }

            if (!TextUtils.equals(mBinding.edtEmbroidContent.getText().toString(), "")){
                Toast.makeText(mActivity, "请选择刺绣字体", Toast.LENGTH_SHORT).show();
                return false;
            }else {
                return true;
            }

        } else if(TextUtils.equals(tag, LOCATION)){
            for(OrderCraftModel model : locationData){
                if(model.isSelect()){
                    return true;
                }
            }

            if (!TextUtils.equals(mBinding.edtEmbroidContent.getText().toString(), "")){
                Toast.makeText(mActivity, "请选择刺绣位置", Toast.LENGTH_SHORT).show();
                return false;
            }else {
                return true;
            }

        } else if(TextUtils.equals(tag, COLOR)){
            for(OrderCraftModel model : colorData){
                if(model.isSelect()){
                    return true;
                }
            }

            if (!TextUtils.equals(mBinding.edtEmbroidContent.getText().toString(), "")){
                Toast.makeText(mActivity, "请选择刺绣颜色", Toast.LENGTH_SHORT).show();
                return false;
            }else {
                return true;
            }

        } else if(TextUtils.equals(tag, ADDRESS)){
            if (TextUtils.equals(mBinding.edtEmbroidAddress.getText().toString(), "")){
                Toast.makeText(mActivity, "请填写邮寄地址", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        } else if(TextUtils.equals(tag, REMARK)){
            if (TextUtils.equals(mBinding.edtEmbroidRemark.getText().toString(), "")){
                Toast.makeText(mActivity, "请填写备注", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        }


        Toast.makeText(mActivity, "模块错误", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void packFontData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(FONT);

        for (OrderCraftModel model : fontData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packLocationData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(LOCATION);

        for (OrderCraftModel model : locationData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packColorData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(COLOR);

        for (OrderCraftModel model : colorData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packContentData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(CONTENT);

        OrderCommitModel.CommitBean bean = new OrderCommitModel.CommitBean();
        bean.setKey("5-01");
        bean.setValue(mBinding.txtEmbroidContent.getText().toString());
        commitModel.getCommitContent().add(bean);

        EventBus.getDefault().post(commitModel);
    }

    private void packAddressData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(ADDRESS);

        OrderCommitModel.CommitBean bean = new OrderCommitModel.CommitBean();
        bean.setKey("6-04");
        bean.setValue(mBinding.txtEmbroidAddress.getText().toString());
        commitModel.getCommitContent().add(bean);

        EventBus.getDefault().post(commitModel);
    }

    private void packRemarkData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(REMARK);

        OrderCommitModel.CommitBean bean = new OrderCommitModel.CommitBean();
        bean.setKey("6-05");
        bean.setValue(mBinding.txtEmbroidRemark.getText().toString());
        commitModel.getCommitContent().add(bean);

        EventBus.getDefault().post(commitModel);
    }

    private void addDataTo(OrderCraftModel model,OrderCommitModel commitModel){
        if (model.isSelect()){
            OrderCommitModel.CommitBean bean = new OrderCommitModel.CommitBean();
            bean.setKey(model.getKey());
            bean.setValue(model.getCode());
            bean.setPrice(model.getPrice());
            commitModel.getCommitContent().add(bean);
        }
    }

}
