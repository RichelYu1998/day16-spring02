package com.tedu.factory;

import java.io.InputStream;
import java.util.Properties;
/**
 * ---------------------------------------------------------
 * Bean: 可重用组件(计算机英语)
 * JavaBean：使用Java语言编写的可重用组件，例如：service层、dao层等
 * JavaBean：通常分为业务Bean和实体Bean
 * 		业务Bean：处理业务逻辑，service层、dao层
 * 		实体Bean：封装数据，例如，为了封装员工信息而编写的Emp实体类.
 * ---------------------------------------------------------
 * 解除耦合:
 * (1)需要提供配置文件，在配置文件中配置service和dao的实现类
 * 		配置内容为：唯一标识=实现类的全限定类名（key=value结构）
 * (2)通过工厂读取配置文件中配置的全限定类名，利用反射创建对象。
 * 		xml配置文件、properties配置文件
 */
public class BeanFactory {
	//声明一个Properties对象，在静态代码块中对其进行初始化
	private static Properties prop;
	static {
		try {
			//为prop进行实例化
			prop = new Properties();
			//获取配置文件的流对象
			InputStream in = BeanFactory.class.getClassLoader()
					.getResourceAsStream("config.properties");
			//将配置文件中的内容读取到Properties对象中
			prop.load( in );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("初始化Properties对象失败!");
		}
	}
	
	/**
	 * 根据config.xml文件中的key获取对应class类的实例
	 * @param key 
	 * @return
	 */
	public static Object getBean(String key) {
		Object bean = null;
		try {
			String className = prop.getProperty( key );
			bean = Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}


