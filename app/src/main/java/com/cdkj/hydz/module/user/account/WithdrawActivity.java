package com.cdkj.hydz.module.user.account;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityWithdrawBinding;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.AccountModel;
import com.cdkj.hydz.module.model.CardModel;
import com.cdkj.hydz.module.model.WithdrawModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class WithdrawActivity extends AbsBaseActivity {

    private ActivityWithdrawBinding mBinding;

    private double BUSERQXFL;

    private String bankName;
    private String accountNumber;
    private String bankcardNumber;

    private List<CardModel.ListBean> list = new ArrayList<>();

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, WithdrawActivity.class);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_withdraw, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("提现");
        setSubLeftImgState(true);

        initEditText();
        initListener();

        getUserAccount();
        getList();
        getTip();
    }

    private void initEditText() {
        mBinding.edtPrice.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        //设置字符过滤
        mBinding.edtPrice.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(".") && dest.toString().length() == 0) {
                    return "0.";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int mlength = dest.toString().substring(index).length();
                    if (mlength == 3) {
                        return "";
                    }
                }
                return null;
            }
        }});

        mBinding.edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().trim().equals("")){
                    mBinding.txtTip4.setText("* 本次提现手续费:"+(Double.parseDouble(editable.toString()) * BUSERQXFL));
                }else {
                    mBinding.txtTip4.setText("* 本次提现手续费:0");
                }
            }
        });
    }

    private void initListener() {
        mBinding.layoutBankCard.setOnClickListener(view -> {
            CardListActivity.openForResult(this,true);
        });

        mBinding.txtConfirm.setOnClickListener(view -> {
            if (check())
                withdrawal();
        });
    }

    private boolean check(){
        if (mBinding.edtPrice.getText().toString().equals("")){
            showToast("请输入提现金额");
            return false;
        }
        if (mBinding.txtBankCard.getText().toString().equals("选择银行卡")){
            showToast("请先添加银行卡");
            return false;
        }
        if (mBinding.edtRepassword.getText().toString().length() == 0) {
            showToast("请输入支付密码");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if (!data.getStringExtra("bankName").equals("")) {
                bankName = data.getStringExtra("bankName");
                bankcardNumber = data.getStringExtra("bankcardNumber");

                mBinding.layoutCard.setVisibility(View.VISIBLE);
                mBinding.layoutBankCard.setVisibility(View.GONE);

                mBinding.txtBankName.setText(bankName);
                mBinding.txtBankCard.setText(bankcardNumber.substring(bankcardNumber.length()-4,bankcardNumber.length()));
            }
        }
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
                            mBinding.txtCanUsePrice.setText("可提现金额" + MoneyUtils.showPriceWithUnit(model.getAmount().subtract(model.getFrozenAmount())) + "元");
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

    private void getList() {

        Map<String, String> map = new HashMap<>();
        map.put("limit", "1");
        map.put("start", "1");
        map.put("status", "1");
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("token", SPUtilHelpr.getUserToken());
        map.put("systemCode", MyConfig.SYSTEMCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getCardistData("802015", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CardModel>(this) {

            @Override
            protected void onSuccess(CardModel data, String SucMessage) {
                list = data.getList();
                if (list != null) {
                    if (list.size() > 0) {
                        bankName = list.get(0).getBankName();
                        bankcardNumber = list.get(0).getBankcardNumber();

                        mBinding.layoutCard.setVisibility(View.VISIBLE);
                        mBinding.layoutBankCard.setVisibility(View.GONE);

                        mBinding.txtBankName.setText(bankName);
                        mBinding.txtBankCard.setText(bankcardNumber.substring(bankcardNumber.length()-4,bankcardNumber.length()));
                    }
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }


    private void getTip() {

        List<String> array = new ArrayList<>();
        array.add("QXDBZDJE");
        array.add("BUSERQXBS");
        array.add("BUSERMONTIMES");
        array.add("BUSERQXFL");
        array.add("BUSERQXSX");

        Map<String, Object> map = new HashMap<>();
        map.put("keyList", array);
        map.put("token", SPUtilHelpr.getUserToken());
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("companyCode", MyConfig.COMPANYCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getWithdrawTip("802028", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<WithdrawModel>(this) {

            @Override
            protected void onSuccess(WithdrawModel data, String SucMessage) {

                mBinding.txtTip.setText("1.每月最大取现次数为"+data.getBUSERMONTIMES()+"次");
                mBinding.txtTip2.setText("2.提现金额是" + data.getBUSERQXBS() + "的倍数，单笔最高" + data.getQXDBZDJE());
                mBinding.txtTip3.setText("3.取现手续费:" + (data.getBUSERQXFL()*100)+"%" +","+data.getBUSERQXSX()+"到账");

                BUSERQXFL = data.getBUSERQXFL();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void withdrawal() {

        int amount = (int) Double.parseDouble(mBinding.edtPrice.getText().toString().trim()) * 1000;

        Map<String, String> map = new HashMap<>();

        map.put("token", SPUtilHelpr.getUserToken());
        map.put("applyUser", SPUtilHelpr.getUserId());
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("accountNumber", accountNumber);
        map.put("amount", amount+"");
        map.put("payCardNo", bankcardNumber);
        // 开户行
        map.put("payCardInfo", bankName);
        map.put("applyNote", "");
        map.put("tradePwd", mBinding.edtRepassword.getText().toString());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("802750", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {

            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                showToast("提现申请成功");
                finish();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
