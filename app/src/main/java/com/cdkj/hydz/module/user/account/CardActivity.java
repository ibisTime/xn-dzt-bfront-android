package com.cdkj.hydz.module.user.account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.dialog.InputDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityCardBinding;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.BankModel;
import com.cdkj.hydz.module.model.CardModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class CardActivity extends AbsBaseActivity {

    private ActivityCardBinding mBinding;

    private String code;

    private boolean isModify;



    private String[] bankNameStr;
    private String[] bankCodeStr;
    private String bankCode;
    private InputDialog inputDialog;

    public static void open(Context context, String code, boolean isModify) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CardActivity.class);
        intent.putExtra("code", code);
        intent.putExtra("isModify", isModify);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_card, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {


        if (getIntent() != null) {
            code = getIntent().getStringExtra("code");
            isModify = getIntent().getBooleanExtra("isModify", false);
        }

        if (isModify) {
            setTopTitle("修改银行卡");
            setSubRightTitleAndClick("删除", v -> {
                tip();
            });
            getCardData();
        } else {
            setTopTitle("添加银行卡");
            mBinding.txtName.setText(SPUtilHelpr.getUserName());
        }

        initListener();

        getBank();

    }

    private void initListener() {
        mBinding.txtBankName.setOnClickListener(v -> {
            chooseBankCard();
        });

        mBinding.txtConfirm.setOnClickListener(v -> {
            if (check()) {
                if (isModify) {
                    showInputDialog();
                } else {
                    addCard();
                }
            }
        });
    }

    private void chooseBankCard() {
        new AlertDialog.Builder(this).setTitle("请选择银行").setSingleChoiceItems(
                bankNameStr, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        txtBankCard.setText(list.get(which).getBankName());
                        mBinding.txtBankName.setText(bankNameStr[which]);

                        bankCode = bankCodeStr[which];

                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", null).show();
    }

    /**
     * 获取用户账户
     */
    public void getBank() {
        Map<String, String> map = new HashMap<>();

        map.put("token", SPUtilHelpr.getUserToken());
        map.put("payType", "WAP");

        Call call = RetrofitUtils.createApi(MyApiServer.class).getBankListData("802116", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<BankModel>(this) {

            @Override
            protected void onSuccess(List<BankModel> data, String SucMessage) {
                if (data != null) {
                    if (bankNameStr == null) {
                        bankNameStr = new String[data.size()];
                    }
                    if (bankCodeStr == null) {
                        bankCodeStr = new String[data.size()];
                    }

                    for (int i = 0; i < data.size(); i++) {
                        bankNameStr[i] = data.get(i).getBankName();
                    }
                    for (int i = 0; i < data.size(); i++) {
                        bankCodeStr[i] = data.get(i).getBankCode();
                    }
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void getCardData() {
        Map<String, String> map = new HashMap<>();

        map.put("token", SPUtilHelpr.getUserToken());
        map.put("code", code);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getCardDetails("802017", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CardModel.ListBean>(this) {

            @Override
            protected void onSuccess(CardModel.ListBean data, String SucMessage) {
                mBinding.txtName.setText(data.getRealName());
                mBinding.edtCardId.setText(data.getBankcardNumber());
                mBinding.txtBankName.setText(data.getBankName());
                mBinding.edtSubbranch.setText(data.getSubbranch());
                bankCode = data.getBankCode();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private boolean check() {
        if (mBinding.txtName.getText().toString().equals("")) {
            Toast.makeText(this, "请填写您的姓名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mBinding.txtBankName.getText().toString().equals("")) {
            Toast.makeText(this, "请填写银行名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mBinding.edtSubbranch.getText().toString().equals("")) {
            Toast.makeText(this, "请填写支行名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mBinding.edtCardId.getText().toString().length() < 16 || mBinding.edtCardId.getText().toString().length() > 19) {
            Toast.makeText(this, "请填写正确的银行卡号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void addCard() {
        Map<String, String> map = new HashMap<>();

        map.put("code", code);
        map.put("subbranch", mBinding.edtSubbranch.getText().toString().trim());
        map.put("realName", mBinding.txtName.getText().toString().trim());
        map.put("bankcardNumber", mBinding.edtCardId.getText().toString().trim());
        map.put("bankName", mBinding.txtBankName.getText().toString().trim());
        map.put("bankCode", bankCode);
        map.put("currency", "CNY");
        map.put("type", "B");
        map.put("token", SPUtilHelpr.getUserToken());
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("systemCode", MyConfig.SYSTEMCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getCardSuccess("802010", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<String>(this) {

            @Override
            protected void onSuccess(String data, String SucMessage) {
                finish();
                showToast("添加成功");
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void modifiCard(String tradePwd) {
        Map<String, String> map = new HashMap<>();
        map.put("subbranch", mBinding.edtSubbranch.getText().toString().trim());
        map.put("realName", mBinding.txtName.getText().toString().trim());
        map.put("bankcardNumber", mBinding.edtCardId.getText().toString().trim());
        map.put("bankName", mBinding.txtBankName.getText().toString().trim());
        map.put("bankCode", bankCode);
        map.put("code", code);
        map.put("status", "1");
        map.put("tradePwd", tradePwd);
        map.put("token", SPUtilHelpr.getUserToken());
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("systemCode", MyConfig.SYSTEMCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getCardSuccess("802013", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<String>(this) {

            @Override
            protected void onSuccess(String data, String SucMessage) {
                finish();
                showToast("修改成功");

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void showInputDialog() {
        if(inputDialog ==null){
            inputDialog = new InputDialog(this).builder().setTitle("请输入支付密码")
                    .setPositiveBtn("确定", (view, inputMsg) -> {

                        if (inputDialog.getContentView().getText().toString().trim().equals("")) {
                            showToast("请输入支付密码");
                        } else {
                            modifiCard(inputDialog.getContentView().getText().toString());
                            inputDialog.dismiss();
                        }

                    })
                    .setNegativeBtn("取消", null)
                    .setContentMsg("");
            inputDialog.getContentView().setText("");
            inputDialog.getContentView().setHint("请输入支付密码");
        }
        inputDialog.getContentView().setText("");
        inputDialog.show();
    }

    private void tip() {
        new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("您确定要删除该银行卡吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete();
                    }
                }).setNegativeButton("取消", null).show();
    }

    private void delete() {
        Map<String, String> map = new HashMap<>();

        map.put("code", code);
        map.put("token", SPUtilHelpr.getUserToken());
        map.put("systemCode", MyConfig.SYSTEMCODE);

        Call call = RetrofitUtils.getBaseAPiService().successRequest("802011", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {

            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()){
                    finish();
                    showToast("删除成功");
                }

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

}
