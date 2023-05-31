window.addEventListener("DOMContentLoaded", function() {
    CKEDITOR.replace("introduction", {
        height: 350
    });

   /** 신청 최대 인원수 선택 처리 S */
    const selMaxMemberEls = document.getElementsByClassName("selMaxMember");
    const maxMemberEl = document.querySelector(".max_member");
    const maxMember = document.getElementById("maxMember");
    for (const el of selMaxMemberEls) {
        el.addEventListener("click", function() {
            maxMemberEl.classList.remove("dn");
            // 개별 입력인 경우
            if (this.value == 'all') {
                maxMemberEl.classList.add("dn");
                maxMember.value = 0;
            }
        });
    }
   /** 신청 최대 인원수 선택 처리 E */

   /** 스터디 구분 선택에 따른 지역 노출 처리 S */
    const regionTypeEls = document.getElementsByClassName("regionType");
    const addressWrapEl = document.querySelector(".address_wrap");
    for (const el of regionTypeEls) {
        el.addEventListener("click", function() {
            addressWrapEl.classList.remove("dn");
            if (this.value == 'ONLINE') {
                addressWrapEl.classList.add("dn");
                document.getElementById("sido").value = "";
                document.getElementById("sigugun").innerHTML = "<option value=''>- 시구군 선택 -</option>";
            }
        });
    }
   /** 스터디 구분 선택에 따른 지역 노출 처리 E */

   /** 이미지 본문 추가 S */
    const insertEditorEls = document.getElementsByClassName("insert_editor");
    for (const el of insertEditorEls) {
        el.addEventListener("click", insertEditor);
    }

   /** 이미지 본문 추가 E */
});

/**
* 파일 업로드 콜백 처리
*
* @param files
*/
function fileUploadCallback(files) {
    if (!files || files.length == 0) {
        return;
    }
    const imageTpl = document.getElementById("image_file_tpl").innerHTML;
    const fileTpl = document.getElementById("file_tpl").innerHTML;
    const uploadedImagesEl = document.querySelector(".uploadedImages");
    const attachFilesEl = document.querySelector(".attachFiles");
    const domParser = new DOMParser();
    for (const file of files) {
        if (file.location == 'main') { // 메인 이미지
            let html = imageTpl;
            html = html.replace(/<%=fileNo%>/g, file.fileNo)
                        .replace(/<%=imageURL%>/g, file.fileURL);
            const dom = domParser.parseFromString(html, "text/html");
            const el = dom.querySelector("div");
            uploadedImagesEl.appendChild(el);
        } else { // 본문 추가
             let html = fileTpl;
             html = html.replace(/<%=fileNo%>/g, file.fileNo)
                            .replace(/<%=fileURL%>/g, file.fileURL)
                            .replace(/<%=fileName%>/g, file.originalFilename);
             const dom = domParser.parseFromString(html, "text/html");
             const el = dom.querySelector("li");
             attachFilesEl.appendChild(el);
             const img = `<img src="${file.fileURL}">`;
             CKEDITOR.instances.introduction.insertHtml(img);

             const insertEditorEl = el.querySelector(".insert_editor");
             insertEditorEl.addEventListener("click", insertEditor);
        }
    }
}

/**
* 파일 삭제 콜백
*
*/
function fileDeleteCallback(fileNo) {
    const fileEl = document.querySelector(`.file_${fileNo}`);
    if (fileEl) {
        fileEl.parentElement.removeChild(fileEl);
    }
}

function insertEditor(e) {
    const target = e.currentTarget;
    const fileURL = target.dataset.url;

    const img = `<img src="${fileURL}">`;
    CKEDITOR.instances.introduction.insertHtml(img);

}