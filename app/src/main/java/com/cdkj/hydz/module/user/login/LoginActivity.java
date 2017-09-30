package com.cdkj.hydz.module.user.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cdkj.baselibrary.activitys.FindPwdActivity;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.interfaces.LoginInterface;
import com.cdkj.baselibrary.interfaces.LoginPresenter;
import com.cdkj.baselibrary.model.UserLoginModel;
import com.cdkj.hydz.MainActivity;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityLoginBinding;

@Route(path = "/user/login")
public class LoginActivity extends AbsBaseActivity implements LoginInterface {
    private LoginPresenter mPresenter;
    private ActivityLoginBinding mBinding;
    private boolean canOpenMain;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, boolean canOpenMain) {
        if (context == null) {
            return;
        }
        Intent intent= new Intent(context, LoginActivity.class);
        intent.putExtra("canOpenMain",canOpenMain);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_login, null, false);
        return mBinding.getRoot();
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mPresenter = new LoginPresenter(this);

        if(getIntent()!=null){
            canOpenMain=getIntent().getBooleanExtra("canOpenMain",false);
        }

        //登录
        mBinding.btnConfirm.setOnClickListener(v -> {
            mPresenter.login(mBinding.edtUsername.getText().toString(), mBinding.edtPassword.getText().toString(), this);
        });

        //找回密码
        mBinding.txtForget.setOnClickListener(v -> {
            FindPwdActivity.open(LoginActivity.this, "");
        });
    }

    @Override
    public void LoginSuccess(UserLoginModel user, String msg) {
        SPUtilHelpr.saveUserId(user.getUserId());
        SPUtilHelpr.saveUserToken(user.getToken());
//        if(canOpenMain){
            MainActivity.open(this);
//        }else{
//            EventBus.getDefault().post(LOGINREFRESH);
//        }
        finish();
    }

    @Override
    public void LoginFailed(String code, String msg) {

    }

    @Override
    public void StartLogin() {
        showLoadingDialog();
    }

    @Override
    public void EndLogin() {
        disMissLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
            mPresenter = null;
        }
    }

    @Override
    protected boolean canFinish() {
        if(canOpenMain){
            MainActivity.open(this);
            finish();
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onBackPressed() {

        if(canOpenMain){
            MainActivity.open(this);
            finish();
        }else{
            super.onBackPressed();
        }

    }
}
