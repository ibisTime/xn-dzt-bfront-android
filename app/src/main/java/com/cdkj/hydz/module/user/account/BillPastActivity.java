package com.cdkj.hydz.module.user.account;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityBillPastBinding;
import com.cdkj.hydz.module.adapter.BillPastAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;
import static com.cdkj.baselibrary.utils.DateUtil.getMonthToDate;

public class BillPastActivity extends AbsBaseActivity {

    private ActivityBillPastBinding mBinding;

    private List<String> date = new ArrayList<>();

    private BillPastAdapter adapter;

    private String accountNumber;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, String accountNumber) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, BillPastActivity.class);
        intent.putExtra("accountNumber", accountNumber);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_bill_past, null, false);

        mBinding.listBillPast.setOnItemClickListener((adapterView, view, i, l) -> {
            BillListActivity.open(this, accountNumber, date.get(i));
        });

        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("历史账户明细");
        setSubLeftImgState(true);

        if (getIntent() != null){
            accountNumber = getIntent().getStringExtra("accountNumber");
        }

        adapter = new BillPastAdapter(this, date);
        mBinding.listBillPast.setAdapter(adapter);

        setDate();
    }

    private void setDate(){
        for (int i=0; i<4; i++){

            int month = 0-(i+1);
            date.add(DateUtil.formatStringData(getMonthToDate(month).toString(),DEFAULT_DATE_FMT));
        }
        adapter.notifyDataSetChanged();
    }
}
