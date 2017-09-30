package com.cdkj.hydz.module.model;

import java.math.BigDecimal;

/**
 * Created by lei on 2017/8/23.
 */

public class WithdrawModel {

    private String BUSERMONTIMES;
    private String BUSERQXBS;
    private String BUSERQXSX;
    private String QXDBZDJE;
    private Double BUSERQXFL;

    private BigDecimal withdrawAmount;
    private BigDecimal outAmount;
    private BigDecimal inAmount;
    private BigDecimal rechargeAmount;

    public String getBUSERQXSX() {
        return BUSERQXSX;
    }

    public void setBUSERQXSX(String BUSERQXSX) {
        this.BUSERQXSX = BUSERQXSX;
    }

    public BigDecimal getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getBUSERMONTIMES() {
        return BUSERMONTIMES;
    }

    public void setBUSERMONTIMES(String BUSERMONTIMES) {
        this.BUSERMONTIMES = BUSERMONTIMES;
    }

    public String getBUSERQXBS() {
        return BUSERQXBS;
    }

    public void setBUSERQXBS(String BUSERQXBS) {
        this.BUSERQXBS = BUSERQXBS;
    }

    public String getQXDBZDJE() {
        return QXDBZDJE;
    }

    public void setQXDBZDJE(String QXDBZDJE) {
        this.QXDBZDJE = QXDBZDJE;
    }

    public Double getBUSERQXFL() {
        return BUSERQXFL;
    }

    public void setBUSERQXFL(Double BUSERQXFL) {
        this.BUSERQXFL = BUSERQXFL;
    }
}
