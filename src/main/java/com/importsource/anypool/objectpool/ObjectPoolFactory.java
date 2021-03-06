package com.importsource.anypool.objectpool;

/**
 * 对象池工厂接口ObjectPoolFactory：采用工厂模式生产对象池
 * @author Hezf
 */
public interface ObjectPoolFactory
{
	/**
	 * 对象池工厂，用于生成对象池
	 * 
	 * @param factory
	 *            池化对象管理工厂
	 * @param maxIdle
	 *            对象池所申请的最大对象数目
	 * @param clsType
	 *            对象类型
	 * @return
	 */
	public ObjectPool createPool(PoolableObjectFactory factory, int maxNum,
	        Class clsType);
}