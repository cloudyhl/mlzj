package com.mlzj.common.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class ExcelListener extends AnalysisEventListener<Store> {
    @Override
    public void invoke(Store o, AnalysisContext analysisContext) {
        System.out.println(analysisContext.getCurrentRowNum());
        System.out.println(analysisContext.getCurrentRowAnalysisResult());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
