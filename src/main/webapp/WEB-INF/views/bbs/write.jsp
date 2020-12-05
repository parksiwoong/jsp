<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>글 작성</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap">
    <link rel="stylesheet" href="resources/stylesheets/bbs.css">
    <script defer src="https://cdn.ckeditor.com/ckeditor5/23.1.0/classic/ckeditor.js"></script>
    <script defer src="resources/scripts/bbs.js"></script>
</head>
<body>
<form id="write-form" class="body-item form" method="post">
    <table class="form-item table">
        <caption>글 작성</caption>
        <tbody>
        <tr>
            <td>
                <label>
                    <input type="text" name="writer" maxlength="10" placeholder="작성자" autofocus>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <input type="password" name="password" maxlength="100" placeholder="비밀번호">
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <input class="property-full-width" type="text" name="title" maxlength="100" placeholder="제목">
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <textarea class="property-full-width" name="content" maxlength="10000" placeholder="텍스트 에디터를 활용해보세요."></textarea>
                </label>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td>
                <div class="table-item button-container">
                    <input class="button" type="reset" value="다시 작성">
                    <input class="button" type="submit" value="작성">
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>