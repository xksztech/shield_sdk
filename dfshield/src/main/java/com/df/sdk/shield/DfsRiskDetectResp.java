package com.df.sdk.shield;

import com.turingfd.sdk.pri_mini.RiskDetectResp;

public class DfsRiskDetectResp implements RiskDetectResp {
    private final RiskDetectResp resp;

    public DfsRiskDetectResp(RiskDetectResp resp) {
        this.resp = resp;
    }

    @Override
    public long getErrorCode() {
        return resp.getErrorCode();
    }

    @Override
    public String getDeviceToken() {
        return resp.getDeviceToken();
    }

    @Override
    public long getStageReqTimeMillis() {
        return resp.getStageReqTimeMillis();
    }

    @Override
    public long getStagePackTimeMillis() {
        return resp.getStagePackTimeMillis();
    }

    @Override
    public boolean isDowngrade() {
        return resp.isDowngrade();
    }
}