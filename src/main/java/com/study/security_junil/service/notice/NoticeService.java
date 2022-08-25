package com.study.security_junil.service.notice;

import java.util.List;

import com.study.security_junil.web.dto.notice.AddNoticeReqDto;
import com.study.security_junil.web.dto.notice.GetNoticeListResponseDto;
import com.study.security_junil.web.dto.notice.GetNoticeResponseDto;

public interface NoticeService {
	
	public List<GetNoticeListResponseDto> getNoticeList(int page, String searchFlag, String searchValue) throws Exception;
	public int addNotice(AddNoticeReqDto addNoticeReqDto) throws Exception;
	public GetNoticeResponseDto getNotice(String flag, int noticeCode) throws Exception;
}
