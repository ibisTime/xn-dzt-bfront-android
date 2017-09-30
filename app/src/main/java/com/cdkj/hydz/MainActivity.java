package com.cdkj.hydz;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.adapters.ViewPagerAdapter;
import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.model.EventBusModel;
import com.cdkj.hydz.databinding.ActivityMainBinding;
import com.cdkj.hydz.module.main.MainFragment;
import com.cdkj.hydz.module.man.ManFragment;
import com.cdkj.hydz.module.order.OrderFragment;
import com.cdkj.hydz.module.user.MyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.cdkj.baselibrary.appmanager.EventTags.MAINCHANGESHOWINDEX;

public class MainActivity extends AbsBaseActivity {

    private ActivityMainBinding mBinding;

    private int mShowIndex = 0;//显示相应页面

    public static final int MAIN = 0;
    public static final int ORDER = 1;
    public static final int MAN = 2;
    public static final int MY = 4;
    private List<Fragment> fragments;

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
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false);

        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initViewPager();

        initListener();

    }

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    /**
     * 初始化事件
     */
    private void initListener() {

        mBinding.layoutMainButtom.layoutMain.setOnClickListener(v -> {
            setShowIndex(0);
        });

        mBinding.layoutMainButtom.layoutOrder.setOnClickListener(v -> {
            setShowIndex(1);
        });

        mBinding.layoutMainButtom.layoutMan.setOnClickListener(v -> {
            setShowIndex(2);
        });

        mBinding.layoutMainButtom.layoutMy.setOnClickListener(v -> {
            if (!SPUtilHelpr.isLogin(this, false)) {
                setTabIndex();
                return;
            }
            setShowIndex(3);
        });

    }


    public void setTabIndex() {
        setTabDark();
        switch (mShowIndex) {
            case 0:
                mBinding.layoutMainButtom.imgMain.setImageResource(R.mipmap.main_light);
                break;
            case 1:
                mBinding.layoutMainButtom.imgOrder.setImageResource(R.mipmap.order_light);
                break;
            case 2:
                mBinding.layoutMainButtom.imgMan.setImageResource(R.mipmap.man_light);
                break;
            case 3:
                mBinding.layoutMainButtom.imgMy.setImageResource(R.mipmap.my_light);
                break;
        }

    }

    private void setTabDark(){
        mBinding.layoutMainButtom.imgMain.setImageResource(R.mipmap.main_dark);
        mBinding.layoutMainButtom.imgOrder.setImageResource(R.mipmap.order_dark);
        mBinding.layoutMainButtom.imgMan.setImageResource(R.mipmap.man_dark);
        mBinding.layoutMainButtom.imgMy.setImageResource(R.mipmap.my_dark);
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mBinding.pagerMain.setPagingEnabled(false);//禁止左右切换

        //设置fragment数据
        fragments = new ArrayList<>();

        fragments.add(MainFragment.getInstance());
        fragments.add(OrderFragment.getInstance());
        fragments.add(ManFragment.getInstance());
        fragments.add(MyFragment.getInstance());

        mBinding.pagerMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        mBinding.pagerMain.setOffscreenPageLimit(fragments.size());
    }


    /**
     * 设置要显示的界面
     *
     * @param index
     */
    private void setShowIndex(int index) {
        if (index < 0 && index >= fragments.size()) {
            return;
        }
        mBinding.pagerMain.setCurrentItem(index, false);
        mShowIndex = index;
        setTabIndex();
    }


    @Subscribe
    public void MainEventBus(EventBusModel eventBusModel) {
        if (eventBusModel == null) {
            return;
        }
        if (TextUtils.equals(eventBusModel.getTag(), MAINCHANGESHOWINDEX)) {
            setShowIndex(eventBusModel.getEvInt());
        }
    }


    @Override
    public void onBackPressed() {
        showDoubleWarnListen("确认退出？",view -> {
            EventBus.getDefault().post(EventTags.AllFINISH);
            finish();
        });
    }
}
