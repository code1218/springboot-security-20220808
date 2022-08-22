package com.study.security_junil.service.notice;

import com.study.security_junil.web.dto.notice.AddNoticeReqDto;

public interface NoticeService {
	
	public int addNotice(AddNoticeReqDto addNoticeReqDto) throws Exception;

}
