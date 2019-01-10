package com.hisense.hitv.account.pool;

/**
 *
 * @author Joyce
 * @date 2018/7/23
 */

public class ThreadPoolProxyFactory {

    public static PriorityExecutor mNormalThreadPoolProxy;

    /**
     * 得到普通线程池代理对象mNormalThreadPoolProxy
     */
    public static PriorityExecutor getNormal() {
        if (mNormalThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalThreadPoolProxy == null) {
                    mNormalThreadPoolProxy = new PriorityExecutor(4, false);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }


}
