package com.cdkj.hydz.module.man;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseRefreshFragment;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.HeadManBinding;
import com.cdkj.hydz.module.adapter.ManAdapter;
import com.cdkj.hydz.module.adapter.RecyclerViewAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.ManListModel;
import com.cdkj.hydz.module.model.ManTypeModel;
import com.cdkj.hydz.module.model.UserInfoModel;
import com.cdkj.hydz.module.model.UserParameterModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.utils.InputMethodUtils.showInputMethod;

/**
 * Created by lei on 2017/8/21.
 */

public class ManFragment extends BaseRefreshFragment<ManListModel.ListBean> {

    private HeadManBinding mHerderView;
    private String frequent = "";

    private String burry = "";

    private List<ManTypeModel> manTypeModelList;
    private RecyclerViewAdapter recyclerViewAdapter;

    public static UserParameterModel userParameterModel;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static ManFragment getInstance() {
        ManFragment fragment = new ManFragment();
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
        setTopTitle(getString(R.string.man_title));


        mHerderView = DataBindingUtil.inflate(mActivity.getLayoutInflater(), R.layout.head_man, null, false);
        mAdapter.setHeaderAndEmpty(true);
        mAdapter.addHeaderView(mHerderView.getRoot());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            ManListModel.ListBean bean = (ManListModel.ListBean) adapter.getItem(position);
            ManActivity.open(mActivity,bean.getUserId());
        });

        initManType();
        initRecyclerView();

        initHeadListener(pageIndex, limit);

        getUserInfoRequest();

    }

    @Subscribe
    public void getList(ManTypeModel model){
        frequent = model.getFrequent();
        getUserParameter(1, 10);
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfoRequest();
    }

    private void initRecyclerView() {

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHerderView.recyclerMan.setLayoutManager(linearLayoutManager);
        //设置适配器
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), manTypeModelList);
        mHerderView.recyclerMan.setAdapter(recyclerViewAdapter);
    }

    private void initHeadListener(int pageIndex, int limit) {

        mHerderView.txtSearch.setOnClickListener(v -> {
            mHerderView.edtSearch.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标

            getUserParameter(1, limit);

        });

        mHerderView.edtSearch.setOnClickListener(v -> {
            mHerderView.edtSearch.setFocusable(true);//设置输入框可聚集
            mHerderView.edtSearch.setFocusableInTouchMode(true);//设置触摸聚焦
            mHerderView.edtSearch.requestFocus();//请求焦点
            mHerderView.edtSearch.findFocus();//获取焦点
            showInputMethod(mHerderView.edtSearch);
        });

        mHerderView.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                burry = editable.toString();
            }
        });
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

        call.enqueue(new BaseResponseModelCallBack<UserInfoModel>(mActivity) {
            @Override
            protected void onSuccess(UserInfoModel data, String SucMessage) {
                SPUtilHelpr.saveRealName(data.getRealName());

                getUserParameter(1, 10);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected void getListData(int pageIndex, int limit, boolean canShowDialog) {
        Map<String, String> map = new HashMap<>();

        map.put("limit", limit + "");
        map.put("start", pageIndex + "");
        map.put("frequent", frequent);
        map.put("kind","C");
        map.put("ltUser", SPUtilHelpr.getUserId());
        map.put("userName", burry);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("companyCode", MyConfig.COMPANYCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getManListDeta("805120", StringUtils.getJsonToString(map));

        addCall(call);

        if (canShowDialog) showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<ManListModel>(mActivity) {

            @Override
            protected void onSuccess(ManListModel data, String SucMessage) {
                setData(data.getList());
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<ManListModel.ListBean> mDataList) {
        return new ManAdapter(mDataList);
    }


    @Override
    public String getEmptyInfo() {
        return "暂无客户";
    }

    @Override
    public int getEmptyImg() {
        return 0;
    }

    private void initManType(){
        manTypeModelList = new ArrayList<>();
        for (int i=0; i<7; i++){
            ManTypeModel model = new ManTypeModel();
            switch (i){
                case 0:
                    model.setSelect(true);
                    model.setName("全部客户");
                    model.setFrequent("");
                    break;

                case 1:
                    model.setSelect(false);
                    model.setName("新客户");
                    model.setFrequent(i+"");
                    break;

                case 2:
                    model.setSelect(false);
                    model.setName("老客户");
                    model.setFrequent(i+"");
                    break;

                case 3:
                    model.setSelect(false);
                    model.setName("活跃客户");
                    model.setFrequent(i+"");
                    break;

                case 4:
                    model.setSelect(false);
                    model.setName("非常活跃客户");
                    model.setFrequent(i+"");
                    break;

                case 5:
                    model.setSelect(false);
                    model.setName("预流失客户");
                    model.setFrequent(i+"");
                    break;

                case 6:
                    model.setSelect(false);
                    model.setName("流失客户");
                    model.setFrequent(i+"");
                    break;
            }
            manTypeModelList.add(model);

        }
    }

    public void getUserParameter(int pageIndex, int limit) {

        Map<String, String> map = new HashMap<>();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getUserParameterDetails("805908", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<UserParameterModel>(mActivity) {

            @Override
            protected void onSuccess(UserParameterModel data, String SucMessage) {

                if (data.getWl_company() == null){
                    return;
                }
                userParameterModel= data;

                getListData(pageIndex, limit, true);

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
