package com.df.sdk.shield;

import android.content.Context;

import com.turingfd.sdk.pri_mini.RiskDetectResp;
import com.turingfd.sdk.pri_mini.TuringRiskService;

public class DfsRiskService extends TuringRiskService {

    public static DfsRiskDetectResp reqRiskDetectV2(Context context) {
        RiskDetectResp riskDetectResp = TuringRiskService.reqRiskDetectV2(context);
        return new DfsRiskDetectResp(riskDetectResp);
    }

    public static DfsRiskDetectResp reqRiskDetectV2(Context context, boolean cache) {
        RiskDetectResp riskDetectResp = TuringRiskService.reqRiskDetectV2(context, cache);
        return new DfsRiskDetectResp(riskDetectResp);
    }

    public static DfsRiskDetectResp reqRiskDetectWithParam(Context context, java.util.Map<java.lang.Integer, java.lang.String> inputParam) {
        RiskDetectResp riskDetectResp = TuringRiskService.reqRiskDetectWithParam(context, inputParam);
        return new DfsRiskDetectResp(riskDetectResp);
    }

    public static DfsRiskDetectResp reqRiskDetectV3() {
        RiskDetectResp riskDetectResp = TuringRiskService.reqRiskDetectV3();
        return new DfsRiskDetectResp(riskDetectResp);
    }

    public static DfsRiskDetectResp reqRiskDetectV3(boolean cache) {
        RiskDetectResp riskDetectResp = TuringRiskService.reqRiskDetectV3(cache);
        return new DfsRiskDetectResp(riskDetectResp);
    }

    public static DfsRiskDetectResp reqRiskDetectV3(boolean cache, int timeoutMillis, java.util.Map<java.lang.Integer, java.lang.String> inputParam) {
        RiskDetectResp riskDetectResp = TuringRiskService.reqRiskDetectV3(cache, timeoutMillis, inputParam);
        return new DfsRiskDetectResp(riskDetectResp);
    }
}