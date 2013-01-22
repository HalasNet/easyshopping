package com.ibm.util;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


/**
 * 表结构生成类
 * 
 * @author david
 *
 */
public class TableManager {
	public static void main(String[] args) {
		SchemaExport export = new SchemaExport(
				new AnnotationConfiguration().configure());
		export.create(true, true);
	}

}
