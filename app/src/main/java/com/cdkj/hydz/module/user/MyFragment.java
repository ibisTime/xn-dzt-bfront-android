package com.cdkj.hydz.module.user;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.PermissionHelper;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.WxUtil;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.FragmentMyBinding;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.AccountModel;
import com.cdkj.hydz.module.model.ServiceDataModel;
import com.cdkj.hydz.module.model.UserInfoModel;
import com.cdkj.hydz.module.model.UserParameterModel;
import com.cdkj.hydz.module.user.account.AccountActivity;
import com.cdkj.hydz.module.user.login.SettingActivity;
import com.cdkj.hydz.module.user.login.SystemMessageActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.hydz.module.man.ManFragment.userParameterModel;

/**
 * Created by lei on 2017/8/21.
 */

public class MyFragment extends BaseLazyFragment {

    private FragmentMyBinding mBinding;
    private UserInfoModel mUserInfoMode;

    private String phone;
    private PermissionHelper permissionHelper;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static MyFragment getInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, null, false);


        initListener();

        return mBinding.getRoot();
    }

    private void initListener() {
        mBinding.layoutAccount.setOnClickListener(v -> {
            AccountActivity.open(mActivity);
        });
        mBinding.layoutNotice.setOnClickListener(v -> {
            SystemMessageActivity.open(mActivity);
        });
        mBinding.layoutQRCode.setOnClickListener(v -> {
            showQRcode(v);
        });

        mBinding.imgPhoto.setOnClickListener(view -> {
            SettingActivity.open(mActivity);
        });
        mBinding.layoutSetting.setOnClickListener(v -> {
            SettingActivity.open(mActivity);
        });

        mBinding.txtServicePhone.setOnClickListener(view -> {
            permissionHelper = new PermissionHelper(this);

            permissionHelper.requestPermissions(new PermissionHelper.PermissionListener() {
                @Override
                public void doAfterGrand(String... permission) {
                    Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone));
                    startActivity(phoneIntent);
                }

                @Override
                public void doAfterDenied(String... permission) {
                    Toast.makeText(mActivity, "已拒绝允许合衣私人定制拨出号码", Toast.LENGTH_SHORT).show();
                }
            }, Manifest.permission.CALL_PHONE);

        });
    }

    //权限处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
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

//        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<UserInfoModel>(mActivity) {
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

        ImgUtils.loadActLogo(mActivity, MyConfig.IMGURL + data.getPhoto(), mBinding.imgPhoto);

        mBinding.txtName.setText(data.getMobile());

        getUserParameter();

//        switch (data.getLevel()){
//            case "1":
//                mBinding.txtLevel.setText("LV"+data.getLevel());
//                mBinding.txtLevelName.setText("银卡会员");
//                break;
//
//            case "2":
//                mBinding.txtLevel.setText("LV"+data.getLevel());
//                mBinding.txtLevelName.setText("金卡会员");
//                break;
//
//            case "3":
//                mBinding.txtLevel.setText("LV"+data.getLevel());
//                mBinding.txtLevelName.setText("铂金会员");
//                break;
//
//            case "4":
//                mBinding.txtLevel.setText("LV"+data.getLevel());
//                mBinding.txtLevelName.setText("钻石会员");
//                break;
//        }

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

//        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<AccountModel>(mActivity) {

            @Override
            protected void onSuccess(List<AccountModel> data, String SucMessage) {
                if (data != null) {
                    for (AccountModel model : data) {
                        if (model.getCurrency().equals("CNY")) {
                            mBinding.txtAccount.setText(MoneyUtils.showPriceWithUnit(model.getAmount()));
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

    @Override
    protected void lazyLoad() {
        if (mBinding != null) {
            getUserAccount();
            getUserInfoRequest();
            getService();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && mBinding != null) {
            getUserAccount();
            getUserInfoRequest();
        }
    }

    @Override
    protected void onInvisible() {

    }

    private void getService() {

        List<String> array = new ArrayList<>();
        array.add("serviceTime");
        array.add("telephone");

        Map<String, Object> map = new HashMap<>();
        map.put("keyList", array);
        map.put("token", SPUtilHelpr.getUserToken());
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("companyCode", MyConfig.COMPANYCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getServiceData("805918", StringUtils.getJsonToString(map));

        addCall(call);

//        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<ServiceDataModel>(mActivity) {

            @Override
            protected void onSuccess(ServiceDataModel data, String SucMessage) {
                phone = data.getTelephone();
                mBinding.txtServicePhone.setText(phone);
                mBinding.txtServiceTime.setText("服务时间: " + data.getServiceTime());
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

        call.enqueue(new BaseResponseModelCallBack<UserParameterModel>(mActivity) {

            @Override
            protected void onSuccess(UserParameterModel data, String SucMessage) {

                if (data == null) return;

                try {
                    JSONObject jsonObject = new JSONObject(userParameterModel.getLt_level());
                    Iterator it = jsonObject.keys();

                    while (it.hasNext()) {
                        String key = (String) it.next();

                        if (key.equals(mUserInfoMode.getLevel())) {
                            mBinding.txtLevel.setText("LV" + mUserInfoMode.getLevel());
                            mBinding.txtLevelName.setText(jsonObject.getString(key));
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void showQRcode(View view) {

        // 一个自定义的布局，作为显示的内容
        View mview = LayoutInflater.from(getActivity()).inflate(R.layout.popup_qrcode, null);

        ImageView qrCode = mview.findViewById(R.id.img_QRCode);
        RelativeLayout qx = mview.findViewById(R.id.layout_cancel);

        final PopupWindow popupWindow = new PopupWindow(mview,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        String textContent = "http://m.he-shirts.com/?#/home?userReferee=" + SPUtilHelpr.getUserId();

        Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
        qrCode.setImageBitmap(mBitmap);

        qx.setOnClickListener(view1 -> {
            popupWindow.dismiss();
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_layout));
        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 50);

    }
}
