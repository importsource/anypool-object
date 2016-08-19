package com.importsource.anypool.objectpool;
  
/** 
 * 对象池测试类Test 
 *  
 *  @author Hezf
 */  
public class Test  
{  
    public static void main(String[] args)  
    {  
        // 创建String类型的池化对象管理工厂  
        PoolableObjectFactory factory = StringPoolableObjectFactory  
                .getInstance();  
  
        // 创建栈式对象池  
        ObjectPool pool = StackObjectPoolFactory.getInstance().createPool(  
                factory, 100, String.class);  
  
        // 多线程测试  
        for (int index = 1; index <= 20; ++index)  
        {  
            new MyThread(pool, factory).start();  
        }  
    }  
}  
  
/** 
 * 封装线程用于测试对象池 
 *  
 *  @author Hezf
 *  
 */  
class MyThread extends Thread  
{  
    /** 对象池 */  
    private ObjectPool pool = null;  
  
    /** 池化对象管理工厂 */  
    private PoolableObjectFactory factory = null;  
  
    /** 
     *  
     * @param pool 
     *            对象池 
     * @param factory 
     *            池化对象管理工厂 
     */  
    public MyThread(ObjectPool pool, PoolableObjectFactory factory)  
    {  
        this.pool = pool;  
        this.factory = factory;  
    }  
  
    public void run()  
    {  
        // 获取新对象  
        String buffer = (String) pool.borrowObject();  
  
        // 必要的业务逻辑神马的  
        buffer = "【" + this.currentThread() + ":MONKEY.D.MENG】-->";  
        System.out.println(buffer + "/t活跃对象数：" + pool.getActiveNum()  
                + "/t空闲对象数：" + pool.getIdleNum());  
  
        // 返回对象  
        pool.returnObject(factory.clearObject(buffer));  
    }  
  
    public ObjectPool getPool()  
    {  
        return pool;  
    }  
  
    public void setPool(ObjectPool pool)  
    {  
        this.pool = pool;  
    }  
  
    public PoolableObjectFactory getFactory()  
    {  
        return factory;  
    }  
  
    public void setFactory(PoolableObjectFactory factory)  
    {  
        this.factory = factory;  
    }  
}  