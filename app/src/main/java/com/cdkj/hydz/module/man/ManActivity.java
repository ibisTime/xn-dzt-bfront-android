package com.cdkj.hydz.module.man;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityManBinding;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.ManModel;
import com.cdkj.hydz.module.model.OrderCommitModel;
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

public class ManActivity extends AbsBaseActivity {

    private ActivityManBinding mBinding;

    private String userId;

    // 本地数据
    public List<OrderCommitModel.CommitBean> measureData = new ArrayList<>();
    public List<OrderCommitModel.CommitBean> bodyData = new ArrayList<>();

    public static void open(Context context,String userId) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ManActivity.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_man, null, false);

        mBinding.btnConfirm.setOnClickListener(view -> {
            // 数据打包完成，保存数据
            commit();
        });

        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("客户详情");
        setSubLeftImgState(true);

        if (getIntent() == null)
            return;

        // 设置fragment加载Flag
        OrderHelper.isOrder = false;

        userId = getIntent().getStringExtra("userId");

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
                    getUser();
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

    public void getUser() {
        Map<String, String> map = new HashMap<>();

        map.put("userId", userId);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getManDetails("620221", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<ManModel>(this) {

            @Override
            protected void onSuccess(ManModel data, String SucMessage) {

                OrderHelper.manModel = data;

                if(OrderHelper.manModel != null){

                    // 使用订单详情model初始化view
                    setView(data);

                    EventBus.getDefault().post(EventTags.MANDATAOK);
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


    private void setView(ManModel data) {

        if (data.getRealName() != null){
            mBinding.txtName.setText(data.getRealName());
            mBinding.txtManName.setText(data.getRealName());
        }else {
            mBinding.txtName.setText(data.getRealName());
            mBinding.txtManName.setText(data.getRealName());
        }

        mBinding.txtPhone.setText(data.getMobile());
        mBinding.txtManPhone.setText(data.getMobile());
        mBinding.txtManAddress.setText(data.getAddress());

        mBinding.txtManDays.setText(data.getDays()+"");
        mBinding.txtManBirthday.setText(DateUtil.formatStringData(data.getBirthday(),DateUtil.DATE_FMT_YMD)+"");

        mBinding.txtManExp.setText(MoneyUtils.showPriceWithoutUnit(data.getJyAmount()).split("\\.")[0]);
        mBinding.txtManLevelUp.setText(MoneyUtils.showPriceWithoutUnit(data.getSjAmount()).split("\\.")[0]);
        mBinding.txtManPointTotal.setText(MoneyUtils.showPriceWithoutUnit(data.getJfAmount()).split("\\.")[0]);
        mBinding.txtManPointConsume.setText(MoneyUtils.showPriceWithoutUnit(data.getConAmount()).split("\\.")[0]);
        mBinding.txtManPointSurplus.setText(MoneyUtils.showPriceWithoutUnit(data.getJfAmount().subtract(data.getConAmount())).split("\\.")[0]);

        getUserParameter(data.getLevel());

        if (data.getSysDictMap()!=null && data.getSysDictMap().getOther()!=null){
            for(ManModel.SysDictMapBean.OtherBean bean : data.getSysDictMap().getOther()){
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

    public void getUserParameter(String level) {

        try {
            JSONObject jsonObject = new JSONObject(OrderHelper.userParameterModel.getUser_level());
            Iterator it = jsonObject.keys();

            while (it.hasNext()){
                String key = (String) it.next();

                if (key.equals(level)){

                    mBinding.txtManLevel.setText(jsonObject.getString(key));

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void getAllData(OrderCommitModel model) {

        switch (model.getEventTag()) {

            case EventTags.MEASURE:
                measureData = model.getCommitContent();
                break;

            case EventTags.BODY:
                bodyData = model.getCommitContent();
                break;
        }
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

                if (bean.getRemark().equals("1")){ // 是否必填 0选填，1必填
                    if(TextUtils.isEmpty(bean.getValue())){
                        return false;
                    }
                }

            }

            return true;
        }
    }

    private void commit(){

        Map<String,String> codeMap = getMeasureMapData();
        if (codeMap == null) {
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("map", codeMap);
        map.put("userId", userId);

        Call call = RetrofitUtils.getBaseAPiService().successRequest("620220", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {

            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()){
                    showToast("修改成功");
                    finish();
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
