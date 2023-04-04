package com.hope.core;


import com.hope.core.model.ClassInfo;
import com.hope.core.util.TableParseUtil;
import com.hope.model.CodeConfig;

import java.io.IOException;


public class CodeGeneratorTool {

	/**
	 * process Table Into ClassInfo
	 *
	 * @param tableSql
	 * @return
	 */
	public static ClassInfo processTableIntoClassInfo(String tableSql, CodeConfig codeConfig) throws IOException {
		return TableParseUtil.processTableIntoClassInfo(tableSql, codeConfig);
	}

}