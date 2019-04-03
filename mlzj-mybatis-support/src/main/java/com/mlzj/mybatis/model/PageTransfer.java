package com.mlzj.mybatis.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.function.Function;

/**
 * mybatis分页查询
 * @author yhl
 * @date 2019/2/26
 */
@Data
public class PageTransfer extends Page implements Serializable {

    private static final long serialVersionUID = -7017865256062063623L;
    private Function function;

    public PageTransfer() {
    }

    public PageTransfer(long current, long size) {
        super(current, size);
    }

    public PageTransfer(long current, long size, long total) {
        super(current, size, total);
    }

    public PageTransfer(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

    public PageTransfer(long current, long size, long total, boolean isSearchCount) {
        super(current, size, total, isSearchCount);
    }
}
