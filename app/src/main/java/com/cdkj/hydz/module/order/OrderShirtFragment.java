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
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.FragmentOrderShirtBinding;
import com.cdkj.hydz.module.adapter.OrderClosureAdapter;
import com.cdkj.hydz.module.adapter.OrderCollarAdapter;
import com.cdkj.hydz.module.adapter.OrderDartedAdapter;
import com.cdkj.hydz.module.adapter.OrderFabricAdapter;
import com.cdkj.hydz.module.adapter.OrderParameterAdapter;
import com.cdkj.hydz.module.adapter.OrderPocketAdapter;
import com.cdkj.hydz.module.adapter.OrderSleeveAdapter;
import com.cdkj.hydz.module.adapter.OrderStyleAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.OrderCommitModel;
import com.cdkj.hydz.module.model.OrderCraftModel;
import com.cdkj.hydz.module.model.OrderMaterialModel;
import com.cdkj.hydz.module.model.OrderProcessModel;
import com.cdkj.hydz.module.model.OrderStyleModel;
import com.cdkj.hydz.module.model.SwitchModel;
import com.cdkj.hydz.module.order.helper.OrderHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.EventTags.CLOSURE;
import static com.cdkj.baselibrary.appmanager.EventTags.COLLAR;
import static com.cdkj.baselibrary.appmanager.EventTags.DARTED;
import static com.cdkj.baselibrary.appmanager.EventTags.FABRIC;
import static com.cdkj.baselibrary.appmanager.EventTags.PARAMETER;
import static com.cdkj.baselibrary.appmanager.EventTags.POCKET;
import static com.cdkj.baselibrary.appmanager.EventTags.PRODUCTDATA;
import static com.cdkj.baselibrary.appmanager.EventTags.SLEEVE;
import static com.cdkj.baselibrary.appmanager.EventTags.STYLE;
import static com.cdkj.hydz.module.order.helper.OrderHelper.hPlusCraftModel;
import static com.cdkj.hydz.module.order.helper.OrderHelper.orderDeatilModel;
import static com.cdkj.hydz.module.order.helper.OrderHelper.orderProcessModel;


/**
 * Created by lei on 20107/8/23.
 */

public class OrderShirtFragment extends BaseLazyFragment {

    private FragmentOrderShirtBinding mBinding;

    // 着装风格
    private GridLayoutManager styleManager;
    private OrderStyleAdapter styleAdapter;
    public ArrayList<OrderStyleModel> styleData = new ArrayList<>();

    // 面料
    private GridLayoutManager fabricManager;
    private OrderFabricAdapter fabricAdapter;
    public ArrayList<OrderCraftModel> fabricData = new ArrayList<>();

    // 规格
    private GridLayoutManager parameterManager;
    private OrderParameterAdapter parameterAdapter;
    public ArrayList<OrderCraftModel> parameterData = new ArrayList<>();

    // 门襟
    private GridLayoutManager closureManager;
    private OrderClosureAdapter closureAdapter;
    public ArrayList<OrderCraftModel> closureData = new ArrayList<>();

    // 领型
    private GridLayoutManager collarManager;
    private OrderCollarAdapter collarAdapter;
    public ArrayList<OrderCraftModel> collarData = new ArrayList<>();

    // 袖型
    private GridLayoutManager sleeveManager;
    private OrderSleeveAdapter sleeveAdapter;
    public ArrayList<OrderCraftModel> sleeveData = new ArrayList<>();

    // 口袋
    private GridLayoutManager pocketManager;
    private OrderPocketAdapter pocketAdapter;
    public ArrayList<OrderCraftModel> pocketData = new ArrayList<>();

    // 门襟
    private GridLayoutManager dartedManager;
    private OrderDartedAdapter dartedAdapter;
    public ArrayList<OrderCraftModel> dartedData = new ArrayList<>();

    // 禁止输入开关
    private boolean banInputSwitch = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(savedInstanceState), R.layout.fragment_order_shirt, null, false);

        initEditView();
        initRecyclerView();
        initListener();

        getProcess();
        return mBinding.getRoot();
    }

    private void initEditView() {
        if(banInputSwitch){
            mBinding.txtStyleEdit.setVisibility(View.GONE);
            mBinding.txtFabricEdit.setVisibility(View.GONE);
            mBinding.txtParameterEdit.setVisibility(View.GONE);
            mBinding.txtClosureEdit.setVisibility(View.GONE);
            mBinding.txtCollarEdit.setVisibility(View.GONE);
            mBinding.txtSleeveEdit.setVisibility(View.GONE);
            mBinding.txtPocketEdit.setVisibility(View.GONE);
            mBinding.txtDartedEdit.setVisibility(View.GONE);
        }else {
            mBinding.txtStyleEdit.setVisibility(View.VISIBLE);
            mBinding.txtFabricEdit.setVisibility(View.VISIBLE);
            mBinding.txtParameterEdit.setVisibility(View.VISIBLE);
            mBinding.txtClosureEdit.setVisibility(View.VISIBLE);
            mBinding.txtCollarEdit.setVisibility(View.VISIBLE);
            mBinding.txtSleeveEdit.setVisibility(View.VISIBLE);
            mBinding.txtPocketEdit.setVisibility(View.VISIBLE);
            mBinding.txtDartedEdit.setVisibility(View.VISIBLE);
        }
    }

    private void initRecyclerView() {
        styleManager = new GridLayoutManager(mActivity, 3);
        styleAdapter = new OrderStyleAdapter(mActivity, styleData);
        setRecyclerView(mBinding.recyclerShirtStyle,styleManager);

        fabricManager = new GridLayoutManager(mActivity, 3);
        fabricAdapter = new OrderFabricAdapter(mActivity, fabricData);
        setRecyclerView(mBinding.recyclerShirtFabric,fabricManager);

        parameterManager = new GridLayoutManager(mActivity, 3);
        parameterAdapter = new OrderParameterAdapter(mActivity, parameterData);
        setRecyclerView(mBinding.recyclerShirtParameter,parameterManager);

        closureManager = new GridLayoutManager(mActivity, 3);
        closureAdapter = new OrderClosureAdapter(mActivity, closureData);
        setRecyclerView(mBinding.recyclerShirtClosure,closureManager);

        collarManager = new GridLayoutManager(mActivity, 3);
        collarAdapter = new OrderCollarAdapter(mActivity, collarData);
        setRecyclerView(mBinding.recyclerShirtCollar,collarManager);

        sleeveManager = new GridLayoutManager(mActivity, 3);
        sleeveAdapter = new OrderSleeveAdapter(mActivity, sleeveData);
        setRecyclerView(mBinding.recyclerShirtSleeve,sleeveManager);

        pocketManager = new GridLayoutManager(mActivity, 3);
        pocketAdapter = new OrderPocketAdapter(mActivity, pocketData);
        setRecyclerView(mBinding.recyclerShirtPocket,pocketManager);

        dartedManager = new GridLayoutManager(mActivity, 3);
        dartedAdapter = new OrderDartedAdapter(mActivity, dartedData);
        setRecyclerView(mBinding.recyclerShirtDarted,dartedManager);

    }

    private void setRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }


    private void initListener() {
        mBinding.txtStyleEdit.setOnClickListener(view -> { // 着装风格
            setEditShow(mBinding.txtStyleEdit, mBinding.layoutStyleBtn, mBinding.recyclerShirtStyle,styleAdapter);
        });
        mBinding.txtStyleConfirm.setOnClickListener(view -> { // 着装风格 确定
            if (check(STYLE)){
                setEditHide(mBinding.txtStyleEdit, mBinding.layoutStyleBtn, mBinding.recyclerShirtStyle);

                packStyleData();
            }
        });
//        mBinding.txtStyleCancel.setOnClickListener(view -> { // 着装风格 取消
//            setEditHide(mBinding.txtStyleEdit, mBinding.layoutStyleBtn, mBinding.recyclerShirtStyle);
//        });

        mBinding.txtFabricEdit.setOnClickListener(view -> { // 面料
            setEditShow(mBinding.txtFabricEdit, mBinding.layoutFabricBtn, mBinding.recyclerShirtFabric,fabricAdapter);
        });
        mBinding.txtFabricConfirm.setOnClickListener(view -> { // 面料 确定
            if (check(FABRIC)){
                setEditHide(mBinding.txtFabricEdit, mBinding.layoutFabricBtn, mBinding.recyclerShirtFabric);

                packFabricData();
            }
        });
//        mBinding.txtFabricCancel.setOnClickListener(view -> { // 面料 取消
//            setEditHide(mBinding.txtFabricEdit, mBinding.layoutFabricBtn, mBinding.recyclerShirtFabric);
//        });

        mBinding.txtParameterEdit.setOnClickListener(view -> { // 规格
            setEditShow(mBinding.txtParameterEdit, mBinding.layoutParameterBtn, mBinding.recyclerShirtParameter,parameterAdapter);
        });
        mBinding.txtParameterConfirm.setOnClickListener(view -> { // 规格 确定
            if (check(PARAMETER)){
                setEditHide(mBinding.txtParameterEdit, mBinding.layoutParameterBtn, mBinding.recyclerShirtParameter);

                packParameterData();
            }
        });
//        mBinding.txtParameterCancel.setOnClickListener(view -> { // 规格 取消
//            setEditHide(mBinding.txtParameterEdit, mBinding.layoutParameterBtn, mBinding.recyclerShirtParameter);
//        });

        mBinding.txtClosureEdit.setOnClickListener(view -> { // 门襟
            setEditShow(mBinding.txtClosureEdit, mBinding.layoutClosureBtn, mBinding.recyclerShirtClosure, closureAdapter);
        });
        mBinding.txtClosureConfirm.setOnClickListener(view -> { // 门襟 确定
            if (check(CLOSURE)){
                setEditHide(mBinding.txtClosureEdit, mBinding.layoutClosureBtn, mBinding.recyclerShirtClosure);

                packClosureData();
            }
        });
//        mBinding.txtClosureCancel.setOnClickListener(view -> { // 门襟 取消
//            setEditHide(mBinding.txtClosureEdit, mBinding.layoutClosureBtn, mBinding.recyclerShirtClosure);
//        });

        mBinding.txtCollarEdit.setOnClickListener(view -> { // 领型
            setEditShow(mBinding.txtCollarEdit, mBinding.layoutCollarBtn, mBinding.recyclerShirtCollar, collarAdapter);
        });
        mBinding.txtCollarConfirm.setOnClickListener(view -> { // 领型 确定
            setEditHide(mBinding.txtCollarEdit, mBinding.layoutCollarBtn, mBinding.recyclerShirtCollar);
            if (check(COLLAR)){

                packCollarData();
            }
        });
//        mBinding.txtCollarCancel.setOnClickListener(view -> { // 领型 取消
//            setEditHide(mBinding.txtCollarEdit, mBinding.layoutCollarBtn, mBinding.recyclerShirtCollar);
//        });

        mBinding.txtSleeveEdit.setOnClickListener(view -> { // 袖型
            setEditShow(mBinding.txtSleeveEdit, mBinding.layoutSleeveBtn, mBinding.recyclerShirtSleeve, sleeveAdapter);
        });
        mBinding.txtSleeveConfirm.setOnClickListener(view -> { // 袖型 确定
            if (check(SLEEVE)){
                setEditHide(mBinding.txtSleeveEdit, mBinding.layoutSleeveBtn, mBinding.recyclerShirtSleeve);

                packSleeveData();
            }
        });
//        mBinding.txtSleeveCancel.setOnClickListener(view -> { // 袖型 取消
//            setEditHide(mBinding.txtSleeveEdit, mBinding.layoutSleeveBtn, mBinding.recyclerShirtSleeve);
//        });

        mBinding.txtPocketEdit.setOnClickListener(view -> { // 口袋
            setEditShow(mBinding.txtPocketEdit, mBinding.layoutPocketBtn, mBinding.recyclerShirtPocket, pocketAdapter);
        });
        mBinding.txtPocketConfirm.setOnClickListener(view -> { // 口袋 确定
            setEditHide(mBinding.txtPocketEdit, mBinding.layoutPocketBtn, mBinding.recyclerShirtPocket);
            if (check(POCKET)){

                packPocketData();
            }
        });
//        mBinding.txtPocketCancel.setOnClickListener(view -> { // 口袋 取消
//            setEditHide(mBinding.txtPocketEdit, mBinding.layoutPocketBtn, mBinding.recyclerShirtPocket);
//        });

        mBinding.txtDartedEdit.setOnClickListener(view -> { // 收省
            setEditShow(mBinding.txtDartedEdit, mBinding.layoutDartedBtn, mBinding.recyclerShirtDarted, dartedAdapter);
        });
        mBinding.txtDartedConfirm.setOnClickListener(view -> { // 袖型 确定
            if (check(DARTED)){
                setEditHide(mBinding.txtDartedEdit, mBinding.layoutDartedBtn, mBinding.recyclerShirtDarted);

                packDartedData();
            }
        });
//        mBinding.txtDartedCancel.setOnClickListener(view -> { // 袖型 取消
//            setEditHide(mBinding.txtDartedEdit, mBinding.layoutDartedBtn, mBinding.recyclerShirtDarted);
//        });
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

    /**
     * 单独获取面料
     */
    public void getProcess() {

        Map<String, String> map = new HashMap<>();
        map.put("status","1");
        map.put("modelCode", SPUtilHelpr.getModelCode());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getMaterialList("620032", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<OrderMaterialModel>(mActivity) {

            @Override
            protected void onSuccess(List<OrderMaterialModel> data, String SucMessage) {
                if (data == null)
                    return;
                for (OrderMaterialModel bean : data){

                    OrderCraftModel model = new OrderCraftModel();
                    model.setSelect(false);
                    model.setKey(bean.getType());
                    model.setName(bean.getModelNum());
                    model.setImg(bean.getPic());
                    model.setCode(bean.getCode());
                    model.setPrice(bean.getPrice());

                    fabricData.add(model);
                }

                fabricAdapter.notifyDataSetChanged();

            }


            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Subscribe
    public void setStyleView(OrderStyleModel model){
        String name = "";
        if (OrderHelper.isShowCraftPrice){
            name = model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")";
        }else {
            name = model.getName();
        }

        mBinding.txtShirtStyle.setText(name);
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
            case EventTags.FABRIC:
                mBinding.txtShirtFabric.setText(name);
                break;

            case EventTags.PARAMETER:
                mBinding.txtShirtParameter.setText(name);
                break;

            case EventTags.CLOSURE:
                mBinding.txtShirtClosure.setText(name);
                break;

            case EventTags.COLLAR:
                mBinding.txtShirtCollar.setText(name);
                break;

            case EventTags.SLEEVE:
                mBinding.txtShirtSleeve.setText(name);
                break;

            case EventTags.POCKET:
                mBinding.txtShirtPocket.setText(name);
                break;

            case EventTags.DARTED:
                mBinding.txtShirtDarted.setText(name);
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
                initStyle();
                initParameter();
                initClosure();
                initCollar();
                initSleeve();
                initPocket();
                initDarted();

//                setView();
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

    private void initStyle(){
        if (orderProcessModel.get_$107() == null)
            return;

        for (OrderProcessModel._$107Bean bean : orderProcessModel.get_$107()){
            OrderStyleModel model = new OrderStyleModel();
            model.setSelect(false);
            model.setKey(bean.getType());
            model.setName(bean.getName());
            model.setKey(bean.getType());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            styleData.add(model);
        }

        styleAdapter.notifyDataSetChanged();
    }

    private void initParameter(){
        if (orderProcessModel.get_$101() == null)
            return;

        for (OrderProcessModel._$101Bean bean : orderProcessModel.get_$101()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setKey(bean.getType());
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            parameterData.add(model);

        }

        parameterAdapter.notifyDataSetChanged();
    }

    private void initClosure(){
        if (orderProcessModel.get_$105() == null)
            return;

        for (OrderProcessModel._$105Bean bean : orderProcessModel.get_$105()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setKey(bean.getType());
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            closureData.add(model);

        }

        closureAdapter.notifyDataSetChanged();
    }

    private void initCollar(){
        if (orderProcessModel.get_$103() == null)
            return;

        for (OrderProcessModel._$103Bean bean : orderProcessModel.get_$103()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setKey(bean.getType());
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            collarData.add(model);

        }

        collarAdapter.notifyDataSetChanged();
    }

    private void initSleeve(){
        if (orderProcessModel.get_$104() == null)
            return;

        for (OrderProcessModel._$104Bean bean : orderProcessModel.get_$104()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setKey(bean.getType());
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            sleeveData.add(model);

        }

        sleeveAdapter.notifyDataSetChanged();
    }

    private void initPocket(){
        if (orderProcessModel.get_$108() == null)
            return;

        for (OrderProcessModel._$108Bean bean : orderProcessModel.get_$108()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setKey(bean.getType());
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            pocketData.add(model);

        }

        pocketAdapter.notifyDataSetChanged();
    }

    private void initDarted(){
        if (orderProcessModel.get_$106() == null)
            return;

        for (OrderProcessModel._$106Bean bean : orderProcessModel.get_$106()){
            OrderCraftModel model = new OrderCraftModel();
            model.setSelect(false);
            model.setKey(bean.getType());
            model.setName(bean.getName());
            model.setImg(bean.getPic());
            model.setCode(bean.getCode());
            model.setPrice(bean.getPrice());

            dartedData.add(model);

        }

        dartedAdapter.notifyDataSetChanged();
    }

//    private void setView(){
//
//        if(TextUtils.isEmpty(orderDeatilModel.getResultMap().getDINGZHI()))
//            return;
//
//        try {
//            JSONObject jsonObject = new JSONObject(orderDeatilModel.getResultMap().getDINGZHI());
//            Iterator it = jsonObject.keys();
//
//            JSONObject object;
//            while (it.hasNext()){
//                String key = (String) it.next();
//                object = new JSONObject(jsonObject.getString(key));
//
//                switch(key){
//
//                    case "1-07":
//
//                        for (OrderStyleModel model : styleData){
//
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtShirtStyle.setText(model.getName());
//                                packStyleData();
//                            }
//                        }
//                        styleAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "1-02":
//
//                        for (OrderCraftModel model : fabricData){
//
//                            if(TextUtils.equals(model.getName(),object.getString("modelNum"))){
//                                model.setSelect(true);
//
//                                mBinding.txtShirtFabric.setText(model.getName());
//                                packFabricData();
//                            }
//
//                        }
//
//                        fabricAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "1-01":
//
//                        for (OrderCraftModel model : parameterData){
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtShirtParameter.setText(model.getName());
//                                packParameterData();
//                            }
//                        }
//                        parameterAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "1-05":
//
//                        for (OrderCraftModel model : closureData){
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtShirtClosure.setText(model.getName());
//                                packClosureData();
//                            }
//                        }
//                        closureAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "1-03":
//
//                        for (OrderCraftModel model : collarData){
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtShirtCollar.setText(model.getName());
//                                packCollarData();
//                            }
//                        }
//                        collarAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "1-04":
//
//                        for (OrderCraftModel model : sleeveData){
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtShirtSleeve.setText(model.getName());
//                                packSleeveData();
//                            }
//                        }
//                        sleeveAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "1-08":
//
//                        for (OrderCraftModel model : pocketData){
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtShirtPocket.setText(model.getName());
//                                packPocketData();
//                            }
//                        }
//                        pocketAdapter.notifyDataSetChanged();
//
//                        break;
//
//                    case "1-06":
//
//                        Log.e("key",key);
//                        Log.e("object.getString(name)",object.getString("name"));
//
//                        for (OrderCraftModel model : dartedData){
//
//                            Log.e("model.getName()",model.getName());
//
//
//                            if(TextUtils.equals(model.getName(),object.getString("name"))){
//                                model.setSelect(true);
//
//                                mBinding.txtShirtDarted.setText(model.getName());
//                                packDartedData();
//                            }
//                        }
//                        dartedAdapter.notifyDataSetChanged();
//
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

                for (OrderStyleModel model : styleData) {

                    if (TextUtils.equals(model.getCode(), value)) {

                        model.setSelect(true);

                        mBinding.txtShirtStyle.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");

                        packStyleData();
                    }
                }
                styleAdapter.notifyDataSetChanged();

                for (OrderCraftModel model : fabricData) {

                    if (TextUtils.equals(model.getCode(), value)) {
                        model.setSelect(true);

                        mBinding.txtShirtFabric.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");

                        packFabricData();
                    }

                }

                fabricAdapter.notifyDataSetChanged();

                for (OrderCraftModel model : parameterData) {
                    if (TextUtils.equals(model.getCode(), value)) {

                        Log.e("value",value);
                        Log.e("model.getCode()",model.getCode());

                        model.setSelect(true);

                        mBinding.txtShirtParameter.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");

                        packParameterData();
                    }
                }
                parameterAdapter.notifyDataSetChanged();

                for (OrderCraftModel model : closureData) {
                    if (TextUtils.equals(model.getCode(), value)) {
                        model.setSelect(true);

                        mBinding.txtShirtClosure.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");

                        packClosureData();
                    }
                }
                closureAdapter.notifyDataSetChanged();

                for (OrderCraftModel model : collarData) {
                    if (TextUtils.equals(model.getCode(), value)) {
                        model.setSelect(true);

                        mBinding.txtShirtCollar.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");

                        packCollarData();
                    }
                }
                collarAdapter.notifyDataSetChanged();

                for (OrderCraftModel model : sleeveData) {
                    if (TextUtils.equals(model.getCode(), value)) {
                        model.setSelect(true);

                        mBinding.txtShirtSleeve.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");

                        packSleeveData();
                    }
                }
                sleeveAdapter.notifyDataSetChanged();

                for (OrderCraftModel model : pocketData) {
                    if (TextUtils.equals(model.getCode(), value)) {
                        model.setSelect(true);

                        mBinding.txtShirtPocket.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");

                        packPocketData();
                    }
                }
                pocketAdapter.notifyDataSetChanged();

                for (OrderCraftModel model : dartedData) {

                    if (TextUtils.equals(model.getCode(), value)) {
                        model.setSelect(true);

                        mBinding.txtShirtDarted.setText(model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")");

                        packDartedData();
                    }
                }
                dartedAdapter.notifyDataSetChanged();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean check(String tag){

        if(TextUtils.equals(tag, STYLE)){
            for(OrderStyleModel model : styleData){
                if(model.isSelect()){
                    return true;
                }
            }

            Toast.makeText(mActivity, "请选择着装风格", Toast.LENGTH_SHORT).show();
            return false;

        } else if(TextUtils.equals(tag, FABRIC)){
            for(OrderCraftModel model : fabricData){
                if(model.isSelect()){
                    return true;
                }
            }

            Toast.makeText(mActivity, "请选择面料", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.equals(tag, PARAMETER)){
            for(OrderCraftModel model : parameterData){
                if(model.isSelect()){
                    return true;
                }
            }

            Toast.makeText(mActivity, "请选择规格", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.equals(tag, CLOSURE)){
            for(OrderCraftModel model : closureData){
                if(model.isSelect()){
                    return true;
                }
            }

            Toast.makeText(mActivity, "请选择门襟", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.equals(tag, COLLAR)){
            for(OrderCraftModel model : collarData){
                if(model.isSelect()){
                    return true;
                }
            }

            Toast.makeText(mActivity, "请选择领型", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.equals(tag, SLEEVE)){
            for(OrderCraftModel model : sleeveData){
                if(model.isSelect()){
                    return true;
                }
            }

            Toast.makeText(mActivity, "请选择袖型", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.equals(tag, POCKET)){
            for(OrderCraftModel model : pocketData){
                if(model.isSelect()){
                    return true;
                }
            }

            Toast.makeText(mActivity, "请选择口袋", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.equals(tag, DARTED)){
            for(OrderCraftModel model : dartedData){
                if(model.isSelect()){
                    return true;
                }
            }

            Toast.makeText(mActivity, "请选择收省", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(mActivity, "模块错误", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void packStyleData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(STYLE);

        for (OrderStyleModel model : styleData){
            if (model.isSelect()){
                OrderCommitModel.CommitBean bean = new OrderCommitModel.CommitBean();
                bean.setKey(model.getKey());
                bean.setValue(model.getCode());
                bean.setPrice(model.getPrice());
                commitModel.getCommitContent().add(bean);

            }
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packFabricData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(FABRIC);

        for (OrderCraftModel model : fabricData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packParameterData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(PARAMETER);

        for (OrderCraftModel model : parameterData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packClosureData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(CLOSURE);

        for (OrderCraftModel model : closureData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packCollarData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(COLLAR);

        for (OrderCraftModel model : collarData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packSleeveData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(SLEEVE);

        for (OrderCraftModel model : sleeveData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packPocketData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(POCKET);

        for (OrderCraftModel model : pocketData){
            addDataTo(model,commitModel);
        }

        EventBus.getDefault().post(commitModel);
    }

    private void packDartedData(){
        OrderCommitModel commitModel = new OrderCommitModel();
        commitModel.setEventTag(DARTED);

        for (OrderCraftModel model : dartedData){
            addDataTo(model,commitModel);
        }

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
