<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
    <title>华宝新能源</title>
</head>
<style>
    * {
        padding: 0;
        margin: 0;
    }

    .main {
        display: flex;
        height: 100vh;
        align-items: center;
        justify-content: center;
        font-size: 18px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #333333;
        line-height: 25px;
    }

    .container {
        flex: 1;
        height: 706px;
        background: #E2E2E2;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .info-box {
        width: 600px;
        height: 550px;
        background: #FFFFFF;
        border-radius: 4px;
    }

    .info-box .info-content {
        padding: 50px 40px;
        margin-top: 100px;
        box-sizing: content-box;
        border-top: 1px solid #DEDEDE;
    }

    .info-box .info-content p:not(:last-child) {
        margin-bottom: 20px;
    }

    .info-box .info-content a,
    .info-box .info-content p span {
        color: #FF5D1E;
    }
</style>

<body>
<div class="main">
    <div class="container">
        <div class="info-box">
            <div class="info-content">
                <p>您好 <span th:text="${email}"></span></p>
                <p>您已经请求了重置密码，可以点击下面的链接来重置密码。</p>
                <p><a th:href="${href}">前往重置密码</a></p>
                <p>如果您没有请求重置密码，请忽略这封邮件。</p>
                <p>在您点击上面链接修改密码之前，您的密码将会保持不变。</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>