package com.cdkj.hydz.module.main;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;

import com.cdkj.baselibrary.activitys.WebViewActivity;
import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseRefreshFragment;
import com.cdkj.baselibrary.model.EventBusModel;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.MainActivity;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.HeadMainBinding;
import com.cdkj.hydz.module.adapter.OrderAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.loader.BannerImageLoader;
import com.cdkj.hydz.module.main.message.MessageActivity;
import com.cdkj.hydz.module.main.message.MessageListActivity;
import com.cdkj.hydz.module.model.BannerModel;
import com.cdkj.hydz.module.model.NoticeModel;
import com.cdkj.hydz.module.model.OrderModel;
import com.cdkj.hydz.module.order.OrderActivity;
import com.cdkj.hydz.module.order.product.ProductActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * Created by lei on 2017/8/21.
 */

public class MainFragment extends BaseRefreshFragment<OrderModel.ListBean> {

    private HeadMainBinding mHerderView;
    private NoticeModel.ListBean notice;
    private List<String> banner = new ArrayList<>();

    private List<BannerModel> bannerData = new ArrayList<>();

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static MainFragment getInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return true;
    }

    @Override
    protected void afterCreate(int pageIndex, int limit) {
        setTopTitleLine(false);
        setTopTitleViewBg(R.color.white);
        setTopTitleViewColor(R.color.black);
        setTopTitle(getString(R.string.app_name));

        mHerderView = DataBindingUtil.inflate(mActivity.getLayoutInflater(), R.layout.head_main, null, false);
        mAdapter.setHeaderAndEmpty(true);
        mAdapter.addHeaderView(mHerderView.getRoot());

        initBanner();
        getBanner();
        getNotice();
        getListData(pageIndex, limit, true);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            OrderModel.ListBean bean = (OrderModel.ListBean) adapter.getItem(position);

            if (bean.getStatus().equals("1")) {
                ProductActivity.open(mActivity, bean.getCode());
            } else {
                OrderActivity.open(mActivity, bean.getCode(), "", bean.getType(), false, null);
            }

        });

        mHerderView.txtAllMessage.setOnClickListener(v -> {
            MessageListActivity.open(mActivity);
        });

        mHerderView.layoutMessage.setOnClickListener(view -> {
            if (notice != null) {
                if (notice.getCommenter().equals(SPUtilHelpr.getUserId())) {
                    MessageActivity.open(mActivity, notice.getReceiver());
                } else {
                    MessageActivity.open(mActivity, notice.getCommenter());
                }
            }

        });

        mHerderView.txtAllOrder.setOnClickListener(v -> {
            EventBusModel eventBusModel = new EventBusModel();
            eventBusModel.setEvInt(MainActivity.ORDER); //显示认证界面
            eventBusModel.setTag(EventTags.MAINCHANGESHOWINDEX);
            EventBus.getDefault().post(eventBusModel);

        });


    }

    @Override
    protected void onMRefresh(int pageIndex, int limit) {
        getBanner();
        getNotice();
        getListData(pageIndex, limit, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        getNotice();
        getListData(1, 10, true);
    }

    @Override
    protected void getListData(int pageIndex, int limit, boolean canShowDialog) {

        List<String> status = new ArrayList<>();
        status.add("1");
        status.add("2");
        status.add("3");
        status.add("4");

        Map<String, Object> map = new HashMap<>();
        map.put("limit", limit + "");
        map.put("start", pageIndex + "");
        map.put("uiLocation", "0");
        map.put("statusList", status);
        map.put("ltUser", SPUtilHelpr.getUserId());
        map.put("userId", SPUtilHelpr.getUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getOrderData("620230", StringUtils.getJsonToString(map));

        addCall(call);

        if (canShowDialog) showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<OrderModel>(mActivity) {

            @Override
            protected void onSuccess(OrderModel data, String SucMessage) {
                setData(data.getList());
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<OrderModel.ListBean> mDataList) {
        return new OrderAdapter(mDataList);
    }

    @Override
    public String getEmptyInfo() {
        return null;
    }

    @Override
    public int getEmptyImg() {
        return 0;
    }

    /**
     * 获取banner
     */
    private void getBanner() {
        Map<String, String> map = new HashMap<>();
        map.put("belong", "1");
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("companyCode", MyConfig.COMPANYCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getBannerData("805806", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<BannerModel>(mActivity) {


            @Override
            protected void onSuccess(List<BannerModel> data, String SucMessage) {
                if (data != null) {
                    bannerData = data;
                    banner.clear();
                    for (BannerModel model : data) {
                        banner.add(model.getPic());
                    }
                }

                mHerderView.banner.setImages(banner);
                mHerderView.banner.start();

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void initBanner() {
        //设置图片加载器
        mHerderView.banner.setImageLoader(new BannerImageLoader());
        //设置banner动画效果
        mHerderView.banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        mHerderView.banner.isAutoPlay(true);
        //设置轮播时间
        mHerderView.banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mHerderView.banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner点击事件
        mHerderView.banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                if (bannerData.get(position - 1).getUrl() != null) {

                    if (bannerData.get(position - 1).getUrl().indexOf("http") != -1) {
                        WebViewActivity.openURL(mActivity, bannerData.get(position - 1).getName(), bannerData.get(position - 1).getUrl());
                    }

                }
            }
        });

        // 设置在操作Banner时listView事件不触发
//        mHerderView.banner.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 获取留言
     */
    private void getNotice() {
        Map<String, String> map = new HashMap<>();
        map.put("limit", "1");
        map.put("start", "1");
        map.put("type", "1");
        map.put("receiver", SPUtilHelpr.getUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNoticeListData("620148", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<NoticeModel>(mActivity) {

            @Override
            protected void onSuccess(NoticeModel data, String SucMessage) {
                if (data.getList() != null) {
                    if (data.getList().size() > 0) {
                        notice = data.getList().get(0);
                    }
                }

                setNotice();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void setNotice() {
        if (notice == null) return;

        mHerderView.txtMessage.setText("\u3000\u3000" + notice.getContent());
        mHerderView.txtTime.setText(DateUtil.formatStringData(notice.getCommentDatetime(), DEFAULT_DATE_FMT));

        if (notice.getCommenter().equals(SPUtilHelpr.getUserId())) {
            setTextName(notice.getReceiveName());
            mHerderView.txtPhone.setText(notice.getReceiveMobile());
        } else {
            setTextName(notice.getCommentName());
            mHerderView.txtPhone.setText(notice.getCommentMobile());
        }
    }

    /**
     * 设置姓名显示不能超过5位数
     *
     * @param name
     */
    private void setTextName(String name) {
        if (TextUtils.isEmpty(name)) {
            return;
        }
        if (name.length() > 6) {
            name = name.substring(0, 5) + "...";
        }

        mHerderView.txtName.setText(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHerderView.banner.stopAutoPlay();
    }

    @Override
    protected void lazyLoad() {
        if (mHerderView != null) {
            mHerderView.banner.start();
        }
    }

    @Override
    protected void onInvisible() {
        if (mHerderView != null) {
            mHerderView.banner.stopAutoPlay();
        }
    }

}
