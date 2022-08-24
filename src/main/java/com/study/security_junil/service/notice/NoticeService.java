package com.study.security_junil.service.notice;

import com.study.security_junil.web.dto.notice.AddNoticeReqDto;
import com.study.security_junil.web.dto.notice.GetNoticeResponseDto;

public interface NoticeService {
	
	public int addNotice(AddNoticeReqDto addNoticeReqDto) throws Exception;
	public GetNoticeResponseDto getNotice(String flag, int noticeCode) throws Exception;
}
