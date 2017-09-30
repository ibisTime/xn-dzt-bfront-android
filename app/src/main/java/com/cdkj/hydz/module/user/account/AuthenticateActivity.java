package com.cdkj.hydz.module.user.account;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.MainActivity;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityAuthenticateBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class AuthenticateActivity extends AbsBaseActivity {

    private ActivityAuthenticateBinding mBinding;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_authenticate, null, false);

        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("实名认证");
        setSubLeftImgState(true);

        mBinding.btnConfirm.setOnClickListener(view -> {
            if (check()){
                authenticate();
            }
        });

    }

    private boolean check(){
        if (mBinding.edtName.getText().toString().equals("")) {
            showToast("请填写真实姓名");
            return false;
        }
        if (mBinding.edtIdentity.getText().toString().length() == 15 || mBinding.edtIdentity.getText().toString().length() == 18) {

        } else {
            showToast("请填写正确格式的身份证号码");
            return false;
        }

        return true;
    }

    private void authenticate(){
        Map<String, String> map = new HashMap<>();
        map.put("idKind", "1");
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("realName", mBinding.edtName.getText().toString());
        map.put("idNo", mBinding.edtIdentity.getText().toString());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("805190", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {

            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()){
                    SPUtilHelpr.saveRealName(mBinding.edtName.getText().toString());
                    showToast("认证成功");
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
