package com.mlzj.common.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class Store extends BaseRowModel {

    @ExcelProperty(value = {"门店编码"},index = 1)
    private String storeNo;
    @ExcelProperty(value = {"门店名称"},index = 2)
    private String storeName;
    @ExcelProperty(value = {"地址"},index = 3)
    private String address;

}
