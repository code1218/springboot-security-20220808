
load();

function load() {
	const noticeCode = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/notice/" + noticeCode,
		dataType: "json",
		success: (response) => {
			console.log(JSON.stringify(response.data));
			getNotice(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	})
}

function getNotice(notice) {
	const noticeDetailTitle = document.querySelector(".notice-detail-title");
	const noticeDetailDescriptions = document.querySelectorAll(".notice-detail-description h3");
	const noticeContent = document.querySelector(".notice-content");
	const noticeFile = document.querySelector(".notice-file");
	
	noticeDetailTitle.innerHTML = notice.noticeTitle;
	noticeDetailDescriptions[0].innerHTML = "작성자: " + notice.userId;
	noticeDetailDescriptions[1].innerHTML = "작성일: " + notice.createDate;
	noticeDetailDescriptions[2].innerHTML = "조회수: " + notice.noticeCount;
	noticeContent.innerHTML = notice.noticeContent;
	
	noticeFile.innerHTML = "<h3>첨부파일:</h3>";
	let noticeFileArray = new Array();
	
	notice.downloadFiles.forEach(file => {
		noticeFileArray.push(`<a href="/api/v1/notice/file/${file.fileCode}">${file.fileName}</a>`);
	});
	noticeFile.innerHTML += noticeFileArray.join(" / ");
}





