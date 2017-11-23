package com.cdkj.hydz.module.order;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.utils.ScrollGridLayoutManager;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.FragmentOrderMeasureBinding;
import com.cdkj.hydz.module.adapter.OrderMeasureAdapter;
import com.cdkj.hydz.module.model.ManModel;
import com.cdkj.hydz.module.model.OrderCommitModel;
import com.cdkj.hydz.module.model.OrderDetailModel;
import com.cdkj.hydz.module.model.OrderMeasureModel;
import com.cdkj.hydz.module.model.SwitchModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import static android.R.attr.key;
import static com.cdkj.baselibrary.appmanager.EventTags.MEASURE;
import static com.cdkj.hydz.module.order.helper.OrderHelper.manModel;
import static com.cdkj.hydz.module.order.helper.OrderHelper.orderDeatilModel;
import static com.cdkj.hydz.module.order.helper.OrderHelper.systemParameterModel;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderMeasureFragment extends BaseLazyFragment {

    private FragmentOrderMeasureBinding mBinding;

    private ScrollGridLayoutManager mGridLayoutManager;

    private OrderMeasureAdapter mAdapter;

    private ArrayList<OrderMeasureModel> data = new ArrayList<>();

    // 禁止输入开关
    private boolean banInputSwitch = false;

    public static OrderMeasureFragment getInstance() {
        OrderMeasureFragment fragment = new OrderMeasureFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(savedInstanceState), R.layout.fragment_order_measure, null, false);

        initEditView();
        initRecyclerView();
        initListener();

        return mBinding.getRoot();
    }

    private void initEditView() {
        if(banInputSwitch){
            mBinding.txtEdit.setVisibility(View.GONE);
        }else {
            mBinding.txtEdit.setVisibility(View.VISIBLE);
        }
    }

    private void initListener() {
        mBinding.txtEdit.setOnClickListener(view -> {
            mBinding.txtEdit.setVisibility(View.GONE);
            mBinding.layoutBtn.setVisibility(View.VISIBLE);
            setEdtStatus(true);

        });

        mBinding.txtCancel.setOnClickListener(view -> {
            mBinding.txtEdit.setVisibility(View.VISIBLE);
            mBinding.layoutBtn.setVisibility(View.GONE);
            setEdtStatus(false);
        });

        mBinding.txtConfirm.setOnClickListener(view -> {
            if (check()){
                mBinding.txtEdit.setVisibility(View.VISIBLE);
                mBinding.layoutBtn.setVisibility(View.GONE);
                setEdtStatus(false);

                packData();
            }


        });
    }

    public void setEdtStatus(boolean canInput){

        for (OrderMeasureModel model : data){

            model.setCanInput(canInput);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {

        mAdapter = new OrderMeasureAdapter(data);
        mGridLayoutManager = new ScrollGridLayoutManager(mActivity, 2);

        // 设置布局管理器
        mBinding.recyclerOrderMeasure.setLayoutManager(mGridLayoutManager);
        // 设置adapter
        mBinding.recyclerOrderMeasure.setAdapter(mAdapter);
        // 设置Item添加和移除的动画
        mBinding.recyclerOrderMeasure.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerOrderMeasure.setNestedScrollingEnabled(false);
//        // 设置Item之间间隔样式
//        mBinding.recyclerOrderMeasure.addItemDecoration(new GridSp);

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Subscribe
    public void setSwitch(SwitchModel model){
        if(model.getEventTag().equals(EventTags.SWITCHALL)){
            banInputSwitch = model.isSwitchs();
            initEditView();
        }

    }

    @Subscribe
    public void setData(String tag){
        if(tag.equals(EventTags.ORDERDATAOK)){
            if(orderDeatilModel != null){
                initViewOrder();
            }
        }else if(tag.equals(EventTags.MANDATAOK)){
            if(manModel != null){
                initViewMan();
            }
        }

    }

    private void initViewOrder(){

        for (OrderDetailModel.SysDictMapBean.MeasureBean bean : orderDeatilModel.getSysDictMap().getMeasure()){
            OrderMeasureModel model = new OrderMeasureModel();
            model.setCanInput(false);
            model.setName(bean.getDvalue());
            model.setKey(bean.getDkey());
            model.setRemark(bean.getRemark());

            data.add(model);
        }

        mAdapter.notifyDataSetChanged();
        setViewOrder();
    }

    private void setViewOrder(){

        if(orderDeatilModel.getSysDictMap() == null || orderDeatilModel.getSysDictMap().getMeasure() == null)
            return;
        for(OrderDetailModel.SysDictMapBean.MeasureBean bean : orderDeatilModel.getSysDictMap().getMeasure()){

            for (OrderMeasureModel model : data){

                if(TextUtils.equals(model.getKey(),bean.getDkey())){

                    if (bean.getOrderSizeData() != null)
                        model.setValue(bean.getOrderSizeData().getDkey());

                }
            }

        }
        packData();


        mAdapter.notifyDataSetChanged();

    }

    private void initViewMan(){

        for (ManModel.SysDictMapBean.MeasureBean bean : manModel.getSysDictMap().getMeasure()){
            OrderMeasureModel model = new OrderMeasureModel();
            model.setCanInput(false);
            model.setName(bean.getDvalue());
            model.setKey(bean.getDkey());
            model.setRemark(bean.getRemark());

            data.add(model);
        }

        mAdapter.notifyDataSetChanged();
        setViewMan();
    }

    private void setViewMan(){

        if(manModel.getSysDictMap() == null || manModel.getSysDictMap().getMeasure() == null)
            return;
        for(ManModel.SysDictMapBean.MeasureBean bean : manModel.getSysDictMap().getMeasure()){

            for (OrderMeasureModel model : data){

                if(TextUtils.equals(model.getKey(),bean.getDkey())){

                    if (bean.getSizeData() != null)
                        model.setValue(bean.getSizeData().getDkey());

                }
            }

        }
        packData();


        mAdapter.notifyDataSetChanged();

    }

    private boolean check(){

        for (OrderMeasureModel model : data){

            if (model.getRemark().equals("1")){
                if(TextUtils.isEmpty(model.getValue())){
                    Toast.makeText(mActivity, "请补充"+model.getName(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

        }

        return true;
    }

    private void packData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(MEASURE);

        for (OrderMeasureModel model : data){
            OrderCommitModel.CommitBean bean = new OrderCommitModel.CommitBean();

            bean.setKey(model.getKey());
            bean.setValue(model.getValue());
            bean.setRemark(model.getRemark());
            commitModel.getCommitContent().add(bean);
        }

        EventBus.getDefault().post(commitModel);
    }



}
