package com.cdkj.hydz.module.user.account;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.activitys.PayPwdModifyActivity;
import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityAccountBinding;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.AccountModel;
import com.cdkj.hydz.module.model.WithdrawModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class AccountActivity extends AbsBaseActivity {

    private ActivityAccountBinding mBinding;

    private String accountNumber;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_account, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("账户余额");
        setSubLeftImgState(true);

        initListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserAccount();

    }

    private void initListener() {
        mBinding.txtBill.setOnClickListener(view -> {
            BillActivity.open(this, accountNumber);
        });

        mBinding.btnWithdraw.setOnClickListener(view -> {

            if (SPUtilHelpr.getTradePwdFlag()){
                WithdrawActivity.open(this);
            }else {
                PayPwdModifyActivity.open(this,SPUtilHelpr.getTradePwdFlag(),SPUtilHelpr.getUserPhoneNum());
            }

        });
    }

    /**
     * 获取用户账户
     */
    public void getUserAccount() {
        Map<String, String> map = new HashMap<>();

        map.put("token", SPUtilHelpr.getUserToken());
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("companyCode", MyConfig.COMPANYCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getUserAccountDetails("802503", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<AccountModel>(this) {

            @Override
            protected void onSuccess(List<AccountModel> data, String SucMessage) {
                if (data != null) {
                    for (AccountModel model : data){
                        if(model.getCurrency().equals("CNY")){
                            accountNumber = model.getAccountNumber();
                            mBinding.txtAccount.setText(MoneyUtils.showPriceWithUnit(model.getAmount()));
                            getWithdrawAccount(model.getAccountNumber());

                        }
                    }
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }


    public void getWithdrawAccount(String accountNumber) {
        Map<String, String> map = new HashMap<>();

        map.put("accountNumber", accountNumber);
        map.put("status", "");
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("companyCode", MyConfig.COMPANYCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getWithdrawAccount("802902", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<WithdrawModel>(this) {

            @Override
            protected void onSuccess(WithdrawModel data, String SucMessage) {
                if (data != null) {
                    mBinding.txtWithdraw.setText(MoneyUtils.showPriceWithUnit(data.getWithdrawAmount()));
                    mBinding.txtTotalAccount.setText(MoneyUtils.showPriceWithUnit(data.getInAmount()));
                }

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
