package com.cdkj.baselibrary.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.databinding.ActivityModifyNickBinding;
import com.cdkj.baselibrary.model.EventBusModel;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by lei on 2017/8/22.
 */

public class NickModifyActivity extends AbsBaseActivity {

    private ActivityModifyNickBinding mBinding;

    private String nick;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context,String nick) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, NickModifyActivity.class);
        intent.putExtra("nick",nick);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_modify_nick, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("修改昵称");
        setSubLeftImgState(true);

        if (getIntent() != null) {
            nick = getIntent().getStringExtra("nick");
            mBinding.edtNickname.setHint(nick);
        }

        initListener();
    }

    private void initListener() {
        mBinding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mBinding.edtNickname.getText().toString())) {
                    showToast("请输入昵称");
                    return;
                }
                modifyNick();
            }
        });
    }

    /**
     * 修改昵称
     */
    public void modifyNick() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("nickname", mBinding.edtNickname.getText().toString());
        map.put("token", SPUtilHelpr.getUserToken());


        Call call = RetrofitUtils.getBaseAPiService().successRequest("805082", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();
        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {

                    showToast("修改成功");

                    EventBusModel eventBusModel=new EventBusModel();      //刷新上一页数据
                    eventBusModel.setTag(EventTags.CHANGENICK_REFRESH);
                    eventBusModel.setEvInfo(mBinding.edtNickname.getText().toString());
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
