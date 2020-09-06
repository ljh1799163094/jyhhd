<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>example</title>
</head>
<body>
<form action="http://localhost:8080/jeecg-boot/technicalInformationupload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>请选择要上传的文件：</td>
            <td><input type="file" name="files"></td>
        </tr>
        <tr>
            <td>请选择要上传的文件：</td>
            <td><input type="file" name="files"></td>
        </tr>
        <tr>
            <td> <input type="text" name="elcId" value="设备ID"></td>
        </tr>
        <tr>
            <td> <input type="text" name="fileType" value="文件类型"></td>
        </tr>
        <tr>
            <td> <input type="text" name="userId" value="用户编号"></td>
        </tr>

        <tr>
            <td align="right"><input type="reset" value="重填"></td>
            <td><input type="submit" value="上传"></td>
        </tr>
    </table>

</form>
</body>
</html>