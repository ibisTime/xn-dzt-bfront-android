package com.cdkj.hydz.module.order;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.FragmentOrderCraftBinding;
import com.cdkj.hydz.module.adapter.OrderCraftAdapter;
import com.cdkj.hydz.module.adapter.OrderTabAdapter;
import com.cdkj.hydz.module.adapter.RecyclerViewAdapter;
import com.cdkj.hydz.module.model.OrderDetailModel;
import com.cdkj.hydz.module.order.helper.OrderHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.cdkj.hydz.module.order.helper.OrderHelper.orderDeatilModel;

/**
 * Created by lei on 2017/9/16.
 */

public class OrderCraftFragment extends BaseLazyFragment {

    private List<TextView> listTab = new ArrayList<>();

    private OrderCraftAdapter adapter;
    private List<OrderDetailModel.ProductBean.ProductVarListBean> list;

    private OrderTabAdapter tabAdapter;
    private List<OrderDetailModel.ProductBean.ProductVarListBean> tabList;

    private FragmentOrderCraftBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(savedInstanceState), R.layout.fragment_order_craft, null, false);

        initRecyclerView();

        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new OrderCraftAdapter(mActivity, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        //竖直排列、正向排序
        mBinding.recyclerCraft.setLayoutManager(linearLayoutManager);


        mBinding.recyclerCraft.setAdapter(adapter);

        tabList = new ArrayList<>();
        tabAdapter = new OrderTabAdapter(getActivity(), tabList);

        mBinding.recyclerTab.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mBinding.recyclerTab.setAdapter(tabAdapter);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Subscribe
    public void setData(String tag) {
        try {
            if (tag.equals(EventTags.ORDERDATAOK)) {

                if (orderDeatilModel != null) {

                    if (OrderHelper.orderDeatilModel.getProduct() != null) {

                        if (OrderHelper.orderDeatilModel.getProduct().getProductVarList() != null) {

                            if (OrderHelper.orderDeatilModel.getProduct().getProductVarList().size() == 1) {
                                mBinding.layoutTab.setVisibility(View.GONE);
                            } else {
                                tabList.clear();
                                tabList.addAll(OrderHelper.orderDeatilModel.getProduct().getProductVarList());
                                tabList.get(0).setSelect(true);
                                tabAdapter.notifyDataSetChanged();
                            }

                            list.clear();
                            list.add(OrderHelper.orderDeatilModel.getProduct().getProductVarList().get(0));
                            adapter.notifyDataSetChanged();

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void update(OrderDetailModel.ProductBean.ProductVarListBean model) {

        list.clear();
        list.add(OrderHelper.orderDeatilModel.getProduct().getProductVarList().get(model.getPosition()));
        adapter.notifyDataSetChanged();

    }
}
