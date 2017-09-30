package com.cdkj.hydz.module.order;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.FragmentOrderBodyBinding;
import com.cdkj.hydz.module.adapter.OrderBodyAdapter;
import com.cdkj.hydz.module.model.ManModel;
import com.cdkj.hydz.module.model.OrderBodyModel;
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

import static com.cdkj.baselibrary.appmanager.EventTags.BODY;
import static com.cdkj.hydz.module.order.helper.OrderHelper.manModel;
import static com.cdkj.hydz.module.order.helper.OrderHelper.orderDeatilModel;
import static com.cdkj.hydz.module.order.helper.OrderHelper.systemParameterModel;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderBodyFragment extends BaseLazyFragment {

    private FragmentOrderBodyBinding mBinding;

    private GridLayoutManager mGridLayoutManager;

    private OrderBodyAdapter mAdapter;

    private ArrayList<OrderBodyModel> data = new ArrayList<>();

    // 禁止输入开关
    private boolean banInputSwitch = false;

    public static OrderBodyFragment getInstance() {
        OrderBodyFragment fragment = new OrderBodyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(savedInstanceState), R.layout.fragment_order_body, null, false);

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
            mAdapter.setSelectStatus(true);

        });

        mBinding.txtCancel.setOnClickListener(view -> {
            mBinding.txtEdit.setVisibility(View.VISIBLE);
            mBinding.layoutBtn.setVisibility(View.GONE);
            mAdapter.setSelectStatus(false);
        });

        mBinding.txtConfirm.setOnClickListener(view -> {
            if(check()){
                mBinding.txtEdit.setVisibility(View.VISIBLE);
                mBinding.layoutBtn.setVisibility(View.GONE);
                mAdapter.setSelectStatus(false);

                packData();
            }

        });
    }


    private void initRecyclerView() {

        mAdapter = new OrderBodyAdapter(mActivity, data);
        mGridLayoutManager = new GridLayoutManager(mActivity, 2);

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
        for (OrderDetailModel.SysDictMapBean.FigureBean bean : orderDeatilModel.getSysDictMap().getFigure()){
            OrderBodyModel model = new OrderBodyModel();
            model.setCanSelect(false);
            model.setName(bean.getDvalue());
            model.setKey(bean.getDkey());
            model.setRemark(bean.getRemark());

            data.add(model);
        }

        mAdapter.notifyDataSetChanged();
        setViewOrder();
    }

    private void setViewOrder(){

        if(orderDeatilModel.getSysDictMap() == null || orderDeatilModel.getSysDictMap().getFigure() == null)
            return;

        try {

            for(OrderDetailModel.SysDictMapBean.FigureBean bean : orderDeatilModel.getSysDictMap().getFigure()) {

                for (OrderBodyModel model : data){

                    if(TextUtils.equals(model.getKey(),bean.getDkey())){

                        if (bean.getOrderSizeData() != null){
                            model.setValue(bean.getOrderSizeData().getDvalue());
                            model.setValueKey(bean.getOrderSizeData().getDkey());
                        }
                    }
                }

            }

            packData();
            mAdapter.notifyDataSetChanged();

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private void initViewMan(){
        for (ManModel.SysDictMapBean.FigureBean bean : manModel.getSysDictMap().getFigure()){
            OrderBodyModel model = new OrderBodyModel();
            model.setCanSelect(false);
            model.setName(bean.getDvalue());
            model.setKey(bean.getDkey());
            model.setRemark(bean.getRemark());

            data.add(model);
        }

        mAdapter.notifyDataSetChanged();
        setViewMan();
    }

    private void setViewMan(){

        if(manModel.getSysDictMap() == null || manModel.getSysDictMap().getFigure() == null)
            return;

        try {

            for(ManModel.SysDictMapBean.FigureBean bean : manModel.getSysDictMap().getFigure()) {

                for (OrderBodyModel model : data){

                    if(TextUtils.equals(model.getKey(),bean.getDkey())){

                        if (bean.getSizeData() != null){
                            model.setValue(bean.getSizeData().getDvalue());
                            model.setValueKey(bean.getSizeData().getDkey());
                        }
                    }
                }

            }

            packData();
            mAdapter.notifyDataSetChanged();

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private boolean check(){

        for (OrderBodyModel model : data){

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
        commitModel.setEventTag(BODY);

        for (OrderBodyModel model : data){

            OrderCommitModel.CommitBean bean = new OrderCommitModel.CommitBean();
            bean.setKey(model.getKey());
            bean.setValue(model.getValueKey());
            bean.setRemark(model.getRemark());
            commitModel.getCommitContent().add(bean);
        }

        EventBus.getDefault().post(commitModel);
    }
}
