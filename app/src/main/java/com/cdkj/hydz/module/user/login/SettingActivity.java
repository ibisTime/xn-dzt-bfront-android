package com.cdkj.hydz.module.user.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.activitys.FindPwdActivity;
import com.cdkj.baselibrary.activitys.ImageSelectActivity;
import com.cdkj.baselibrary.activitys.NickModifyActivity;
import com.cdkj.baselibrary.activitys.PayPwdModifyActivity;
import com.cdkj.baselibrary.activitys.UpdatePhoneActivity;
import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.model.EventBusModel;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.QiNiuUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivitySettingBinding;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.UserInfoModel;
import com.cdkj.hydz.module.user.account.CardListActivity;
import com.qiniu.android.http.ResponseInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class SettingActivity extends AbsBaseActivity {

    private ActivitySettingBinding mBinding;

    private UserInfoModel mUserInfoMode;

    public final int PHOTOFLAG = 110;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_setting, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("个人中心");
        setSubLeftImgState(true);

        initListener();

    }

    @Override
    protected void onResume() {
        super.onResume();

        getUserInfoRequest();

    }

    private void initListener() {

        mBinding.layoutPhoto.setOnClickListener(view -> {
            ImageSelectActivity.launch(this, PHOTOFLAG);
        });

        mBinding.layoutNick.setOnClickListener(view -> {
            String nick = "";
            if (mUserInfoMode != null) {
                nick = mUserInfoMode.getNickname();
            }
            NickModifyActivity.open(this, nick);
        });

        mBinding.layoutPhone.setOnClickListener(view -> {
            UpdatePhoneActivity.open(this);
        });

        mBinding.layoutPassword.setOnClickListener(view -> {
            String phone = "";
            if (mUserInfoMode != null) {
                phone = mUserInfoMode.getMobile();
            }
            FindPwdActivity.open(this, phone);
        });

        mBinding.layoutPayPwd.setOnClickListener(view -> {
            //支付密码
            if (mUserInfoMode == null) {
                return;
            }
            PayPwdModifyActivity.open(this, mUserInfoMode.isTradepwdFlag(), mUserInfoMode.getMobile());
        });

        mBinding.layoutCard.setOnClickListener(view -> {
            CardListActivity.open(this, false);
        });

        mBinding.btnLogout.setOnClickListener(v -> {
            showDoubleWarnListen("确认退出登录？", view -> {
                logOut();
            });
        });

    }

    private void logOut() {

        SPUtilHelpr.logOutClear();
        EventBus.getDefault().post(EventTags.AllFINISH);

        LoginActivity.open(this, true);
        finish();

    }

    /**
     * 获取用户信息
     */
    public void getUserInfoRequest() {
        Map<String, String> map = new HashMap<>();

        map.put("userId", SPUtilHelpr.getUserId());
        map.put("token", SPUtilHelpr.getUserToken());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getUserInfoDetails("805121", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<UserInfoModel>(this) {
            @Override
            protected void onSuccess(UserInfoModel data, String SucMessage) {
                mUserInfoMode = data;
                setShowData(mUserInfoMode);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setShowData(UserInfoModel data) {
        if (data == null) return;

        SPUtilHelpr.saveUserName(data.getRealName());
        SPUtilHelpr.saveUserPhoneNum(data.getMobile());
        SPUtilHelpr.saveTradePwdFlag(data.isTradepwdFlag());

        ImgUtils.loadActLogo(this, MyConfig.IMGURL + data.getPhoto(), mBinding.imgPhoto);

        mBinding.txtNick.setText(data.getNickname());
        mBinding.txtPhone.setText(data.getMobile());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (requestCode == PHOTOFLAG) {
            String path = data.getStringExtra(CameraHelper.staticPath);
            showLoadingDialog();
            new QiNiuUtil(this).getQiniuURL(new QiNiuUtil.QiNiuCallBack() {
                @Override
                public void onSuccess(String key, ResponseInfo info, JSONObject res) {
                    updateUserPhoto(key);
                }

                @Override
                public void onFal(String info) {
                    disMissLoading();
                }
            }, path);

        }
    }

    /**
     * 更新哟哦能互头像
     *
     * @param key
     */
    private void updateUserPhoto(final String key) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("photo", key);
        map.put("token", SPUtilHelpr.getUserToken());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("805080", StringUtils.getJsonToString(map));
        addCall(call);
        showLoadingDialog();
        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    ImgUtils.loadActLogo(SettingActivity.this, MyConfig.IMGURL + key, mBinding.imgPhoto);
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Subscribe
    public void updateDataEvent(EventBusModel e) {
        if (e == null) {
            return;
        }
        if (TextUtils.equals(e.getTag(), EventTags.CHANGEPHONENUMBER_REFRESH)) {  //修改电话成功刷新界面
            mUserInfoMode.setMobile(e.getEvInfo());
        } else if (TextUtils.equals(e.getTag(), EventTags.CHANGE_PAY_PWD_REFRESH)) {  //修改支付密码成功刷新界面
            mUserInfoMode.setTradepwdFlag(true);
        }
        getUserInfoRequest();
    }
}
