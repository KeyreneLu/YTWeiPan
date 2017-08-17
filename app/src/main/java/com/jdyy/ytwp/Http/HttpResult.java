package com.jdyy.ytwp.Http;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class HttpResult<T> {

    private String error;
    private T results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("error=" + error );
        if (null != results) {
            sb.append(" subjects:" + results.toString());
        }
        return sb.toString();
    }
}
