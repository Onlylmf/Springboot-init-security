package com.murphy.constants;

import cn.hutool.core.convert.Convert;
import com.murphy.utils.YmlUtils;

/**
 * 配置常量
 *
 */
public class ConfigConstant {

//	public static  String I18N=YmlUtils.getvalue("config.language");
	//public static  String TOPIC_PREFIX=YmlUtils.getvalue("config.topicPrefix")==null?"":YmlUtils.getvalue("config.topicPrefix");
	public static  String FILE_PATH=YmlUtils.getvalue("config.filePath");


	public static  String LOGIN_URL =YmlUtils.getvalue("config.auth.loginUrl");
	public static  String SALT_KEY =YmlUtils.getvalue("config.auth.salt_key");
	public static  Long EXPIRATION_TIME = Convert.toLong(YmlUtils.getvalue("config.auth.expiration_time"));
	public static  Long EXPIRATION_REMEMBER_TIME = Convert.toLong(YmlUtils.getvalue("config.auth.expiration_remember_time"));
	public static  String TOKEN_TYPE = YmlUtils.getvalue("config.auth.token_type");
	public static  String TOKEN_ROLE_CLAIM = YmlUtils.getvalue("config.auth.token_role_claim");
	public static  String TOKEN_ISSUER = YmlUtils.getvalue("config.auth.token_issuer");
	public static  String TOKEN_AUDIENCE = YmlUtils.getvalue("config.auth.audience");
	public static  String TOKEN_HEADER = YmlUtils.getvalue("config.auth.token_header");
	public static  String TOKEN_PREFIX = YmlUtils.getvalue("config.auth.token_prefix");


}
