package com.cdkj.hydz.module.order.product;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityFabricBinding;
import com.cdkj.hydz.module.adapter.FabricTypeAdapter;
import com.cdkj.hydz.module.adapter.OrderFabricAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.FabricTypeModel;
import com.cdkj.hydz.module.model.OrderCraftModel;
import com.cdkj.hydz.module.model.OrderMaterialModel;
import com.cdkj.hydz.module.model.ProductFabricCommitModel;
import com.cdkj.hydz.module.model.UserParameterModel;

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


public class FabricActivity extends AbsBaseActivity {

    private ActivityFabricBinding mBinding;

    private String modelSpecsCode;

//    private List<FabricTypeModel> typeList;
//    private FabricTypeAdapter typeAdapter;

    // 面料
    private GridLayoutManager fabricManager;
    private OrderFabricAdapter fabricAdapter;
    public ArrayList<OrderCraftModel> fabricData = new ArrayList<>();

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, String modelSpecsCode) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, FabricActivity.class);
        intent.putExtra("modelSpecsCode", modelSpecsCode);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_fabric, null, false);

        mBinding.btnConfirm.setOnClickListener(view -> {
            if (check()){
                packData();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("选择面料");
        setSubLeftImgState(true);

        if (getIntent() != null){
            modelSpecsCode = getIntent().getStringExtra("modelSpecsCode");
        }

        initListView();
        initRecycleView();

        getProcess("");
//        getSystemParameter();
    }

    private void initListView(){
//        typeList = new ArrayList<>();
//        typeAdapter = new FabricTypeAdapter(this,typeList);
//        mBinding.listType.setAdapter(typeAdapter);

//        mBinding.listType.setOnItemClickListener((adapterView, view, i, l) -> {
//            for (FabricTypeModel model : typeList){
//                model.setSelect(false);
//            }
//
//            getProcess(typeList.get(i).getKey());
//
//            typeList.get(i).setSelect(true);
//            typeAdapter.notifyDataSetChanged();
//
//        });
    }

    private void initRecycleView() {
        fabricManager = new GridLayoutManager(this, 3);
        fabricAdapter = new OrderFabricAdapter(this, fabricData);

        mBinding.recyclerFabric.setLayoutManager(fabricManager);
        mBinding.recyclerFabric.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerFabric.setNestedScrollingEnabled(false);
        mBinding.recyclerFabric.setAdapter(fabricAdapter);
    }

    private boolean check() {
        for (OrderCraftModel model : fabricData) {
            if (model.isSelect()) {
                return true;
            }
        }

        Toast.makeText(this, "请选择面料", Toast.LENGTH_SHORT).show();
        return false;
    }


    private void packData(){
        ProductFabricCommitModel commitModel = new ProductFabricCommitModel();

        for (OrderCraftModel model : fabricData){

            commitModel.setModelSpecsCode(modelSpecsCode);
            commitModel.setCode(model.getCode());
            commitModel.setPrice(model.getPrice());

        }

        EventBus.getDefault().post(commitModel);
        finish();
    }


    @Subscribe
    public void setCraftView(OrderCraftModel model){
        String name = "";
        name = model.getName() + "("+ MoneyUtils.showPriceWithUnit(model.getPrice())+")";

//        mBinding.txtFabric.setText(name);

    }

    /**
     * 获取面料编号
     */
//    public void getSystemParameter() {
//
//        Map<String, String> map = new HashMap<>();
//
//        Call call = RetrofitUtils.createApi(MyApiServer.class).getUserParameterDetails("805908", StringUtils.getJsonToString(map));
//
//        addCall(call);
//
//        showLoadingDialog();
//
//        call.enqueue(new BaseResponseModelCallBack<UserParameterModel>(this) {
//
//            @Override
//            protected void onSuccess(UserParameterModel data, String SucMessage) {
//                if (data != null){
//
//                    if (data.getM_type() != null){
//
//                        Log.e("data.getM_type()",data.getM_type());
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(data.getM_type());
//                            Iterator it = jsonObject.keys();
//
//                            while (it.hasNext()){
//                                String key = (String) it.next();
//
//                                FabricTypeModel model = new FabricTypeModel();
//                                model.setKey(key);
//                                model.setValue(jsonObject.getString(key));
//
//                                typeList.add(model);
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                        typeList.get(0).setSelect(true);
//                        typeAdapter.notifyDataSetChanged();
//
//                        getProcess(typeList.get(0).getKey());
//                    }
//
//                }else {
//                    showToast("页面错误，请关闭重试");
//                }
//
//
//            }
//
//            @Override
//            protected void onFinish() {
//                disMissLoading();
//            }
//        });
//    }


    /**
     * 获取面料
     */
    public void getProcess(String yarn) {

        Map<String, String> map = new HashMap<>();
        map.put("status","1");
//        map.put("yarn",yarn);
        map.put("modelSpecsCode", modelSpecsCode);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getMaterialList("620032", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<OrderMaterialModel>(this) {

            @Override
            protected void onSuccess(List<OrderMaterialModel> data, String SucMessage) {
                if (data == null)
                    return;

                try {
                    fabricData.clear();
                    for (OrderMaterialModel bean : data){

                        OrderCraftModel model = new OrderCraftModel();
                        model.setSelect(false);
                        model.setKey(bean.getType());
                        model.setName(bean.getModelNum());
                        model.setImg(bean.getPic());
                        model.setCode(bean.getCode());
                        model.setPrice(bean.getPrice());
                        model.setModelNum(bean.getModelNum());

                        fabricData.add(model);
                    }

                    Log.e("fabricData.size()",fabricData.size()+"");

                    fabricAdapter.notifyDataSetChanged();
                } catch (Exception e){
                    e.printStackTrace();
                }



            }


            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
