let writeForm = window.document.body.querySelector('#write-form');
ClassicEditor.create(writeForm.querySelector('textarea'),{
    ckfinder : {
        uploadUrl : '/bbs/upload_image'
    }
});

writeForm.onsubmit = function () {
    if (writeForm.elements['writer'].value === '') {
        alert('작성자를 입력해주세요.');
        writeForm.elements['writer'].focus();
        return false;
    } else if (writeForm.elements['password'].value === '') {
        alert('비밀번호를 입력해주세요.');
        writeForm.elements['password'].focus();
        return false;
    } else if (writeForm.elements['title'].value === '') {
        alert('제목을 입력해주세요.');
        writeForm.elements['title'].focus();
        return false;
    } else if (writeForm.elements['content'].value === '') {
        alert('내용을 입력해주세요.');
        writeForm.elements['content'].focus();
        return false;
    } else {
        return true;
    }
};