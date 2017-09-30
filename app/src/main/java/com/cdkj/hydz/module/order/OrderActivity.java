package com.cdkj.hydz.module.order;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityOrderBinding;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.HPlusCraftModel;
import com.cdkj.hydz.module.model.OrderCommitModel;
import com.cdkj.hydz.module.model.OrderDetailModel;
import com.cdkj.hydz.module.model.SwitchModel;
import com.cdkj.hydz.module.model.SystemParameterModel;
import com.cdkj.hydz.module.model.UserParameterModel;
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

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;
import static com.cdkj.hydz.module.order.helper.OrderHelper.hPlusCraftModel;

public class OrderActivity extends AbsBaseActivity {

    private ActivityOrderBinding mBinding;

    private String orderCode = "";
    private String orderType = "";
    private String modelCode = "";

    private boolean isProduct = false;

    // 本地数据
    public List<OrderCommitModel.CommitBean> measureData = new ArrayList<>();
    public List<OrderCommitModel.CommitBean> bodyData = new ArrayList<>();

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, String orderCode, String modelCode, String orderType,
                            boolean isProduct, HPlusCraftModel model) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra("orderCode",orderCode);
        intent.putExtra("orderType",orderType);
        intent.putExtra("modelCode",modelCode);
        intent.putExtra("isProduct",isProduct);
        intent.putExtra("model",model);

        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_order, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("订单详情");
        setSubLeftImgState(true);

        if (getIntent()!=null){
            modelCode = getIntent().getStringExtra("modelCode");
            orderCode = getIntent().getStringExtra("orderCode");
            orderType = getIntent().getStringExtra("orderType");
            isProduct = getIntent().getBooleanExtra("isProduct",false);
            if (isProduct){
                hPlusCraftModel = (HPlusCraftModel) getIntent().getSerializableExtra("model");
            }
        }

        mBinding.btnDataConfirm.setOnClickListener(view -> {
            // 录入数据提交
            commitData(false);
        });

        mBinding.btnOrderConfirm.setOnClickListener(view -> {
            // 录入数据提交复核
            commitData(true);
        });

        // 设置fragment加载Flag
        OrderHelper.isOrder = true;

        getSystemParameter();

    }

    public void getSystemParameter() {

        Map<String, String> map = new HashMap<>();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getSystemParameterDetails("620908", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<SystemParameterModel>(this) {

            @Override
            protected void onSuccess(SystemParameterModel data, String SucMessage) {
                OrderHelper.systemParameterModel = data;
                if (OrderHelper.systemParameterModel != null){

                    getUserParameter();
                }else {
                    showToast("页面错误，请关闭重试");
                }


            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    public void getUserParameter() {

        Map<String, String> map = new HashMap<>();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getUserParameterDetails("805908", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<UserParameterModel>(this) {

            @Override
            protected void onSuccess(UserParameterModel data, String SucMessage) {

                OrderHelper.userParameterModel = data;
                if (OrderHelper.userParameterModel != null){
                    getOrder();
                }else {
                    showToast("页面错误，请关闭重试");
                }

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    public void getOrder() {
        Map<String, String> map = new HashMap<>();

        map.put("code", orderCode);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getOrderDetails("620231", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<OrderDetailModel>(this) {

            @Override
            protected void onSuccess(OrderDetailModel data, String SucMessage) {
                OrderHelper.orderDeatilModel = data;
                if(OrderHelper.orderDeatilModel != null){

                    // 使用订单详情model初始化view
                    setView(OrderHelper.orderDeatilModel);
                    // 判断填写内容
                    initView(OrderHelper.orderDeatilModel);

                    SPUtilHelpr.saveModelCode(modelCode);

                    EventBus.getDefault().post(EventTags.ORDERDATAOK);

                }else {
                    showToast("页面错误，请关闭重试");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }


    private void setView(OrderDetailModel data) {

        mBinding.txtOrderId.setText(data.getCode());
        mBinding.txtOrderTime.setText(DateUtil.formatStringData(data.getCreateDatetime(),DEFAULT_DATE_FMT));
        mBinding.txtOrderStatus.setText(OrderHelper.getStatus(data.getStatus()));
        if (data.getProduct() != null){

            mBinding.txtProductName.setText(data.getProduct().getModelName());
        }

        if (data.getAmount() != null) {
            mBinding.txtOrderAmount.setText(MoneyUtils.showPriceWithUnit(data.getAmount()));
            if (data.getOriginalAmount() != null)
                mBinding.txtOrderDiscounts.setText(MoneyUtils.showPriceWithUnit(data.getOriginalAmount().subtract(data.getAmount())));
        }
        if(data.getLogisticsCompany() != null){
            getLogistics(data.getLogisticsCompany());
            mBinding.txtLogisticsId.setText(data.getLogisticsCode());
            mBinding.txtLogisticsTime.setText(DateUtil.formatStringData(data.getDeliveryDatetime(),DEFAULT_DATE_FMT));

        }else {
            mBinding.layoutLogistics.setVisibility(View.GONE);
        }

        mBinding.txtName.setText(data.getApplyName());
        mBinding.txtManName.setText(data.getApplyName());
        mBinding.txtPhone.setText(data.getApplyMobile());
        mBinding.txtManPhone.setText(data.getApplyMobile());
        if (data.getLtProvince() != null){

            mBinding.txtManAddress.setText(data.getLtProvince()+data.getLtCity()+data.getLtArea()+data.getLtAddress());
        }

//        if(data.getStatus().equals("3")){
            if (data.getRemark()!= null && !data.getRemark().equals("")){
                mBinding.txtRemark.setText(data.getRemark());
                mBinding.layoutRemark.setVisibility(View.VISIBLE);
            }

            if (data.getReAddress()!= null && !data.getReAddress().equals("")){
                mBinding.txtAddress.setText(data.getReAddress());
                mBinding.layoutAddress.setVisibility(View.VISIBLE);
            }

//        }

        if (data.getSysDictMap()!=null && data.getSysDictMap().getOther()!=null){

            for(OrderDetailModel.SysDictMapBean.OtherBean bean : data.getSysDictMap().getOther()){

                if (bean.getOrderSizeData() != null){

                    if (bean.getDkey().equals("6-02")){// 身高
                        mBinding.txtManHeight.setText(bean.getOrderSizeData().getDkey()+" cm");
                    }else if(bean.getDkey().equals("6-03")){ // 体重
                        mBinding.txtManWeight.setText(bean.getOrderSizeData().getDkey()+" kg");
                    }
                }
            }
        }

    }

    public void getLogistics(String logistics) {

        if (OrderHelper.userParameterModel.getWl_company() == null){
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(OrderHelper.userParameterModel.getWl_company());
            Iterator it = jsonObject.keys();

            while (it.hasNext()){
                String key = (String) it.next();

                if (key.equals(logistics)){

                    mBinding.txtLogisticsCompany.setText(jsonObject.getString(key));

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView(OrderDetailModel data) {
        if (orderType == null){

            mBinding.btnDataConfirm.setVisibility(View.GONE);
            mBinding.btnOrderConfirm.setVisibility(View.GONE);

            // 打开不可编辑开关
            SwitchModel model = new SwitchModel();
            model.setEventTag(EventTags.SWITCHALL);
            model.setSwitchs(true);// true:不可编辑，false:可编辑
            EventBus.getDefault().post(model);

            return;
        }

        switch (data.getStatus()){
            case "3":  // 已支付（补充资料)

                // 已填写的内容禁止编辑
                SwitchModel modelHPlus = new SwitchModel();
                modelHPlus.setEventTag(EventTags.SWITCHHPLUS);
                modelHPlus.setSwitchs(true);// true:不可编辑，false:可编辑
                EventBus.getDefault().post(modelHPlus);

                break;

            default: // 不可编辑

                mBinding.btnDataConfirm.setVisibility(View.GONE);
                mBinding.btnOrderConfirm.setVisibility(View.GONE);

                // 打开不可编辑开关
                SwitchModel model = new SwitchModel();
                model.setEventTag(EventTags.SWITCHALL);
                model.setSwitchs(true);// true:不可编辑，false:可编辑
                EventBus.getDefault().post(model);

                break;
        }

    }


    @Subscribe
    public void getAllData(OrderCommitModel model){

        switch (model.getEventTag()){

            case EventTags.MEASURE:
                measureData = model.getCommitContent();
                break;

            case EventTags.BODY:
                bodyData = model.getCommitContent();
                break;

        }


    }

    private void commitData(boolean isCommitOrder){

        Map<String,String> codeMap = getMeasureMapData();
        if (codeMap == null) {
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("map",codeMap);
        map.put("orderCode",orderCode);
        map.put("remark","");
        map.put("updater", SPUtilHelpr.getUserId());
        map.put("token", SPUtilHelpr.getUserToken());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("620205", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {

            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()){
                    if (isCommitOrder){ // 是否提交复核
                        commitOrder();
                    }else {
                        showToast("保存数据成功");
                    }

                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void commitOrder(){

        Map<String, Object> map = new HashMap<>();
        map.put("orderCode",orderCode);
        map.put("remark","");
        map.put("updater", SPUtilHelpr.getUserId());
        map.put("token", SPUtilHelpr.getUserToken());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("620206", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {

            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()){
                    showToast("量体成功,请等待复核");
                    finish();
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }


    /**
     * 组装所有量体map数据
     */
    private Map<String,String> getMeasureMapData(){
        Map<String,String> map = new HashMap<>();

        // 量体数据
        if (dataMeasureCheck(measureData)){


            for (OrderCommitModel.CommitBean bean : measureData){
                map.put(bean.getKey(), bean.getValue());
            }

        }else {
            showToast("请完整填写量体信息");
            return null;
        }

        // 特体数据
        if (dataMeasureCheck(bodyData)){

            for (OrderCommitModel.CommitBean bean : bodyData){
                map.put(bean.getKey(), bean.getValue());
            }

        }else {
            showToast("请完整填写特体信息");
            return null;
        }

        return map;
    }

    private boolean dataMeasureCheck(List<OrderCommitModel.CommitBean> data){
        if(data == null || data.size()==0){
            showToast("请确认已填写的信息");
            return false;
        } else {

            for (OrderCommitModel.CommitBean bean : data){

                if (bean.getRemark().equals("1")) { // 是否必填 0选填，1必填
                    if(TextUtils.isEmpty(bean.getValue())){
                        return false;
                    }
                }

            }

            return true;
        }
    }

}
