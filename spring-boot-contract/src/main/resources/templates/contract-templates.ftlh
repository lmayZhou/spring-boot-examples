<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Contract Templates</title>
        <style mce_bogus="1" type="text/css">
            .template {
                font-family: "SimSun";
                color: black;
                padding: 10px 40px 10px 40px;
            }

            .template div {
                line-height: 1.5;
            }

            .header1 {
                font-size: 20px;
                font-weight: 800;
                text-align: center
            }

            .header2 {
                font-family: "SimSun";
                font-size: 15px;
                font-weight: 800;
                text-align: center
            }

            .table1 table{
                line-height: 1;
                margin-top: 5px;
                width: 100%;
                border : 0.5px solid black;
                table-layout:fixed;
                border-collapse: collapse;
                overflow:hidden;
            }

            .table1 td{
                text-align:center;
                border: 0.5px solid black;
                word-break:break-all;
                border-collapse: collapse;
                font-size: 15px;
            }
        </style>
    </head>
    <body>
        <div id="templateNumFiv" class="template">
            <p class="header1">${(companyFullName)?default("")}</p>
            <p class="header2">国产棉提货（出库）单</p><br/><br/><br/>
            <table width="100%">
                <tr>
                    <td width="70%" style="text-align: left;font-size: 15px;">${(subinvName)?default("")}：</td>
                    <td width="30%" style="text-align: left;font-size: 15px;">编号：${(outNum)?default("")}</td>
                </tr>
            </table>
            <div style="font-size: 15px;">&nbsp;&nbsp;请将我司存放在贵仓库的${wareOutbatches?default("")}批棉花（重量共计${wareOutWeight?default("")}吨）的货权转移至${(exeCustName)?default("")} 名下，请贵仓库给予${(exeCustName)?default("")}办理提货手续,具体批次如下：</div><br/>
            <table class="table1" style="width: 100%;table-layout:fixed;word-break:break-all;padding-bottom: 0px;margin-bottom: 0px;border-bottom: 0px;border-collapse:collapse;" >
                <tr>
                    <td style="width: 10%;text-align: center;" >买方</td>
                    <td style="width: 45%;text-align: center;" >${(exeCustName)?default("")}</td>
                    <td style="width: 15%;text-align: center;">合同号</td>
                    <td style="width: 30%;text-align: center;" >${(conNum)?default("")}</td>
                </tr>
            </table>
            <table class="table1" style="width: 100%;table-layout:fixed;word-break:break-all;padding-bottom: 0px;margin-bottom: 0px;border-bottom: 0px;border-collapse:collapse;" >
                <tr>
                    <td style="width: 10%;text-align: center;">序号</td>
                    <td style="width: 10%;text-align: center;">产地</td>
                    <td style="width: 25%;text-align: center;">批次</td>
                    <td style="width: 10%;text-align: center;">件数</td>
                    <td style="width: 15%;text-align: center;">重量</td>
                    <td style="width: 20%;text-align: center;">重量标准</td>
                    <td style="width: 10%;text-align: center;">货位</td>
                </tr>
                <tr>
                    <td style="width: 10%;text-align: center;">汇总</td>
                    <td style="width: 10%;text-align: center;"></td>
                    <td style="width: 25%;text-align: center;"></td>
                    <td style="width: 10%;text-align: center;">${(wareOutConut)?default("")}</td>
                    <td style="width: 15%;text-align: center;">${wareOutWeight?default("")}</td>
                    <td style="width: 20%;text-align: center;"></td>
                    <td style="width: 10%;text-align: center;"></td>
                </tr>
            </table>
            <br/>
            <div style="font-size: 15px">费用承担：<label style="display: inline;" id="fivPay">${(fee)?default("")}</label><br/>
                本提货（出库）单传真件、扫描件与原件具有同等法律效力。
            </div>
            <br/>
            <table width="100%" style="">

                <tr>
                    <td style="width:60%;font-size: 15px;"></td>
                    <td style="width:40%;text-align: center;font-size: 15px;">${(companyFullName)?default("")}</td>
                </tr>
                <tr>
                    <td style="width:60%;font-size: 15px;"></td>
                    <td style="width:40%;text-align: center;font-size: 15px;">${(orderDate005)?default("")}</td>
                </tr>
            </table><br/><br/><br/><br/><br/><br/>

            <table width="100%">
                <tr>
                    <td style="width:33%;text-align: left;font-size: 15px;">部门经理：</td>
                    <td style="width:33%;text-align: left;font-size: 15px;">经办人：${(peopleName)?default("")}</td>
                    <td style="width:33%;text-align: left;font-size: 15px;"><#if '${isWf?default("")}'=='Y'>审核人：${employeeName?default("")}<#else >审核人：${(actName)?default("")}</#if></td>
                </tr>
            </table>
            <br/>
            <table width="100%">
                <tr>
                    <td style="width:100%;text-align: left;font-size: 15px;">收款信息：${(remark)?default("")}</td>
                </tr>
            </table>
        </div>
    </body>
</html>