<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
    <script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>

<#list list as a>
<div class="row">
    <div class="col-md-2">
        <div class="row">
            <div class="col-md-6">${a.id}</div>
            <a href="#">Inbox <span class="badge">42</span></a>
        </div>
    </div>
    <div class="col-md-10"> ${a.content}</div>
</div>
</#list>

<form action="/message/send">
    <input type="hidden" name="uid" value="5">
    内容：<input type="text" name="content">
    <input type="submit" value="submit">
</form>
</body>
</html>