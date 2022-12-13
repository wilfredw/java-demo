package com.wei.java.excel.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author buhuan.wang
 * @since 2022/12/13
 */
public class SimpleReaderListener<T> implements ReadListener {

    private List<T> result;

    public SimpleReaderListener() {
        result = new ArrayList<>();
    }

    @Override
    public void invoke(Object data, AnalysisContext context) {
        result.add((T) data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("analysed finished.");
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
