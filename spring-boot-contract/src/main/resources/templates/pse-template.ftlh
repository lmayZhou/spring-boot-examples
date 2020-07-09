<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>PSE Templates</title>
    <style type="text/css">
        body { font-family: SimSun; }
        @page { size: A4; }

        #product_index li {
            position: relative;
        }

        #product_index th {
            text-align: center;
            width: 160px;
        }

        #product_index {
            padding: 30px;
            box-sizing: border-box;
            color: #009887;
            background: #fff;
        }

        #product_index .top-line-1 {
            border-bottom: #009887 6px solid;
        }

        #product_index .top-line-2 {
            border-bottom: #009887 2px solid;
            margin-top: 5px;
        }

        #product_index input {
            color: #000000 !important;
            font-size: 14px;
            text-align: left;
            outline-style: none;
            border: none;
            max-height: 20px;
            border-bottom: 1px #009887 dashed;
        }

        #product_index .el-row {
            margin-left: 0 !important;
            margin-right: 0 !important;
        }

        #product_index .enrollment-picker input {
            box-shadow: none;
        }

        #product_index .enrollment-picker .el-input__prefix {
            display: none;
        }

        #product_index .date-score {
            width: 105px;
        }

        #product_index .date-score input {
            padding: 0;
            border: none;
        }
    </style>
</head>

<body>
<div>
    <div id="product_index">
        <div class="top-line-1"></div>
        <div class="top-line-2"></div>
        <ul>
            <li><img src="http://localhost:88/img/pse_logo.png" width="170"/></li>
            <li>
                <span style="font-size:21px;">Online PSE Registration Form</span>
                <span style="font-size:21px;">在线预科班报名表格</span>
            </li>
            <li>
                <span style="line-height:20px;">
                    Course No.
                    <br/>
                    课程编号
                </span>
                <span style="flex:1;padding-left:10px;padding-top:5px;">
                    ${(courseNo)!""}
                </span>
            </li>
        </ul>
        <div style="border-bottom:1px solid #009887;margin-bottom:5px;">
            Please fill in the form in English 请用英文填写此表格
        </div>
        <div style="border:1px solid #009887">
            <table>
                <tr>
                    <td colspan="2"><strong>NAME </strong>姓名<span style="width: 360px;"></span>
                        Surname 姓 ${(surname)!""}
                        Other names 名 ${(otherName)!""}
                    </td>
                </tr>
                <tr>
                    <td><strong>ADDRESS</strong> 地址</td>
                    <td><strong>Date Of Birth </strong></td>
                </tr>
                <tr>
                    <td>
                        <span>Room 室</span> ${(room)!""}
                        <span>Floor 楼</span> ${(floor)!""}
                        <span>Block 座</span> ${(block)!""}
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <span>Building 大厦</span> ${(building)!""}
                    </td>
                    <td>
                        <span>出生日期</span>
                        <span style="margin-left:75px;">日</span> ${(birthDay)!""}
                        <span>月</span> ${(birthMonth)!""}
                        <span>年</span> ${(birthYear)!""}
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>Street 街道</span> ${(street)!""}
                    </td>
                    <td>
                        <span> <strong>Sex </strong>性别:</span>
                        <#if sexType??>
                            <#if sexType = 1>
                                Male 男
                            <#else>
                                Female 女
                            </#if>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>District 区</span> ${(area)!""}
                    </td>
                    <td>
                        <span> <strong>E-mail Address  </strong>邮箱地址:</span>${(email)!""}
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>City 市</span> ${(city)!""}
                    </td>
                    <td>
                        <span> <strong>Mobile No. </strong>手机号码:</span> ${(mobile)!""}
                        <br/>
                        <span style="font-size:12px;">
                            *We may deliver important messages to you by SMS or e-mail.<br/>
                            *我们可能通过短信或电子邮件向您发传重要信息。
                        </span>
                    </td>
                </tr>
            </table>

            <div style="padding:0 10px; border-bottom: 1px solid #009887;">
                <strong>Do you need any special assistance due to disabilities or other reasons?</strong>
                <ul>
                    <li>
                        No否
                    </li>
                    <li>
                        Yes要, please specify 请注明: ${(specialreason)!""}
                    </li>
                </ul>
            </div>
            <div style="background-color:#009887;color:#fff;padding: 0px;" class="text-center">
                <strong>
                    WHY DID YOU CHOOSE STUDY PRE-SESSIONAL COURSE IN CHINA? 您为何选择在中国上预科班?（多重选择）
                </strong>
            </div>
            <ul style="border-bottom: 1px solid #009887;padding-left: 30px;margin-bottom: 5px;">
                <li style="line-height: 17px;padding:5px 0px;">
                    Attending the course in China is less expensive<br/>
                    <strong> 在中国学习费用更低。</strong>
                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    I would prefer to be closer to family and friends.<br/>
                    <strong>我希望与家人和朋友更近。</strong>
                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    I need more time to make visa arrangements before arriving in the UK.<br/>
                    <strong>
                        我需要更多的时间来准备去英国的签证。
                    </strong>
                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    I didn't have time to complete a UK based pre-sessional course.<br/>
                    <strong>
                        我没有时间去英国完成预科课程。
                    </strong>
                </li>
                <li style="line-height: 17px;padding-top:5px;">
                    Other (please give details)<br/>
                    <strong>
                        其他原因（请说明）。${(reason)!""}
                    </strong>
                </li>
            </ul>
            <div style="background-color:#009887;color:#fff;padding: 0px;" class="text-center">
                <strong>
                    DISCLAIMER 声明
                </strong>
            </div>
            <ul style="padding-left: 30px;margin-bottom: 5px;">
                <li style="line-height: 17px;padding:5px 0px;">
                    British Council English Training (Jiangsu) Co. Ltd.will use the information that you are providing
                    in connection with processing your registration. The
                    legal basis for processing your information is in agreement with our terms and conditions of
                    registration.<br/>
                    英协英语培训（江苏）有限公司将会使用您提供的数据来处理您的报名。我们处理您的报名法律依据符合申请列明的条款及细则。

                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    British Council English Training (Jiangsu) Co. Ltd.would like to use the information you provide to
                    send survey and interview invitations, details of
                    activities, services, and events (including social events) as well as joint promotional campaigns
                    with our partners which we think are of interest. To
                    choose your contact preferences, please check any of the boxes below.<br/>
                    英协英语培训（江苏）有限公司将使用您提供的数据，向您发送问卷和访谈邀请、我们认为您感到兴趣的事项、服务、活动（包括社交活动）以及与其他
                    机构合办的推广活动信息。请在下面选择联络方式。
                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    <ul>
                        <li>
                            Email<br/>
                            电邮
                        </li>
                        <li>
                            SMS<br/>
                            短讯
                        </li>
                        <li>
                            Telephone<br/>
                            电话
                        </li>
                    </ul>
                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    You do not have to select any of the options and if you do, you may unsubscribe from any option at
                    any time. We will process your personal
                    information based on this consent. You may unsubscribe by contacting us
                    [enquiries.nj@britishcouncil.org.cn].<br/>
                    您毋须选取以上联络方式，即使您选取了联络方式，您也可以随时取消任何形式的信息订阅。我们将会依据您上述的声明，处理您的个人资料。您可以通
                    过联络我们[enquiries.nj@britishcouncil.org.cn]取消您的信息订阅。
                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    British Council English Training (Jiangsu) Co. Ltd.complies with data protection law in the UK and
                    laws in other countries that meet internationally
                    accepted standards. You have the right to ask for a copy of the information we hold on you, and the
                    right to ask us to correct any inaccuracies in that
                    information. If you have concerns about how we have used your personal information, you also have
                    the right to complain to a privacy regulator. For
                    detailed information, please refer to the privacy section of our website,
                    www.britishcouncil.org/privacy or contact your local British Council English
                    Training (Jiangsu) Co. Ltd.office. We will keep your information within our data retention policies
                    from the time of collection.<br/>
                    英协英语培训（江苏）有限公司遵守英国的数据保障法以及符合国际标准的其他国家的法律。您有权要求索取我们持有的有关您的数据的副本，并有权要
                    求我们更改其中不准确的数据。如果对于我们使用您个人资料的方式有任何疑虑，您也有权向私隐监管机构提出申诉。如果您希望了解更多相关信息，请
                    浏览我们网站内有关私隐的内容(www.britishcouncil.org/privacy) 或联络您当地的英协英语培训（江苏）有限公司办公室。自数据被收集之日起，我们将
                    按照我们的数据持有政策，对您的数据进行规定时限的保存。
                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    I consent to the collection and use of my personal information as set out above.如上所述，我同意收集和使用我的个人信息。
                </li>
                <li style="line-height: 17px;padding:5px 0px;">
                    During the Pre-sessional English course, you will be required to complete a number of written and
                    spoken assessments which will be recorded. The
                    results of these assessments will be shared with the UK institution you have indicated as your
                    target university and you will receive these results via
                    this university.在英语预科班课程中，你将被要求完成一些书面和口头评估，这些评估将被记录下来。这些评估的结果将与您指定为目标大学的英国机构
                    共享，您将通过该大学获得这些结果。
                </li>
                <li style="line-height: 17px;padding:5px 0px 36.37px 0;">
                    The British Council will collect data from the assessments and work you complete in an anonymised
                    manner for the purposes of research and training.
                    英国文化协会将从评估中收集数据，并以匿名方式完成研究和培训。
                </li>
            </ul>
        </div>
        <div class="top-line-1" style="margin-top:10px;"></div>
        <div class="top-line-2"></div>

        <div style="margin-top:60px;margin-bottom:30px;">
            British Council English Centre policies - 2020<br/>
            英国文化教育协会英语中心政策 – 2020年
        </div>

        <div style="background-color:#009887;color:#fff;padding: 0 0px;" class="text-center">
            <strong>
                IELTS SCORE OR EQUIVALENT 雅思或其他英语成绩
            </strong>
        </div>

        <table border="1" width="100%" style="border: #009887 1px solid;">
            <tr>
                <th>Speaking 口语</th>
                <th>Listening 听力</th>
                <th>Reading 阅读</th>
                <th>Writing 写作</th>
                <td rowspan="2" style="padding-left:20px;">
                    <strong>
                        Score 总分：${(total)!""}<br/>
                        Date of Test 考试时间：${(examTime)!""}
                    </strong>
                </td>
            </tr>
            <tr>
                <td>${(speaking)!""}</td>
                <td>${(Listening)!""}</td>
                <td>${(reading)!""}</td>
                <td>${(writing)!""}</td>
            </tr>
        </table>
        <div style="background-color:#009887;color:#fff;padding:0 0px;" class="text-center">
            <strong>
                Photo Right Signature 肖像使用权
            </strong>
        </div>

        <div style="border:#009887 1px solid;padding:10px;">
            Please read this form carefully. It sets out the terms on which you agree to the British Council taking
            photographs and/or making video or audio
            recordings of you and using those photographs or recordings.<br/>
            I hereby:
            <div style="text-indent:50px;">
                agree to the British Council photographing and/or recording me and give permission to the British
                Council to use any material in the
                photographs and/or recordings where the copyright or any other rights are owned by me;
            </div>
            <div style="text-indent:50px;">
                confirm that the British Council shall be entitled to use, free of charge, the photographs and/or
                recordings made of me (without having to
                identify me by name), in their original format or edited, adapted or altered, for the purposes of the
                British Council’s internal and external promotional and
                publicity materials and for any programmes, publications, websites, electronic publications and social
                media services worldwide produced by or on
                behalf of the British Council; and
            </div>
            <div style="text-indent:50px;">
                agree that the British Council shall be entitled to pass the photographs and/or recordings of me, and my
                name, to external press and media
                agencies, publishers and broadcasters, and to partners and other third parties with which the British
                Council works, anywhere in the world, for the
                purposes set out in this Consent Form.
            </div>
            <p>By signing this Consent Form, I agree to these terms set out above.</p>
            <p>
                <strong>
                    请仔细阅读这张表格。它规定了您同意英国文化协会为您拍照和/或录制视频或音频并使用这些照片或录音的条款。
                    本人在此：
                </strong>
            </p>
            <ul style="padding-left: 20px;font-weight:bold">
                <li>
                    同意英国文化协会为我拍照和/或录音，并允许英国文化协会在版权或任何其他权利归我所有的情况下使用照片和/或录音中的任何材料；
                </li>
                <li>
                    确认英国文化协会有权为英国文化协会的内部和外部宣传和宣传材料以及其代表在世界范围内制作的出版物、网站、电子出版物和社交媒体服务及其他使用目的，免费使用我的照片和/或录音
                    （无需指名道姓），并对其原始格式或编辑、改编或更改，以及
                </li>
                <li>
                    同意英国文化协会有权将本人的照片和/或录音以及本人的姓名传递给外部新闻和媒体机构、出版商和广播公司，以及与英国文化协会在全球范围内合作的合作伙伴和其他第三方，以达到本同
                    意书中规定的目的。
                </li>
            </ul>
            <div>
                通过签署本同意书，我同意上述条款。
            </div>
        </div>

        <div style="background-color:rgb(108, 191, 181);color:#fff;padding:0 20px;margin-top:30px;margin-top:20px;">
            <strong>
                1. Refund, withdrawal and deferral 退款、退出及延期政策
            </strong>
        </div>
        <div style="padding:0 20px;margin-bottom:10px;margin-top:10px;">
            1.1. Exceptional circumstances 例外情况
        </div>
        <div style="padding-left:48px;margin-bottom:10px;margin-top:10px;">
            No refund, make-up class or alternative compensation will be given for class cancellations due to
            circumstances unforeseen, inevitable and beyond the control of the British Council. This
            includes but is not limited to outbreak of riot, civil commotion, epidemic, natural disaster, and
            unanticipated class disruption such as necessary building evacuation due to fire.
            因英国文化教育协会无法预料、无法避免和无法控制的情况取消课程的，我们不提供退款、补课或其他补偿。这些情况包括但不限于暴动、内乱、疫情、自然灾害，以及课程的意外中断，如
            火灾导致的建筑物疏散等。
        </div>

        <div style="background-color:rgb(108, 191, 181);color:#fff;padding:0 20px;margin-top:30px;margin-top:20px;">
            <strong>
                2. Scheduling classes 课堂安排政策
            </strong>
        </div>
        <div style="padding:0 20px;margin-bottom:10px;margin-top:10px;">
            2.1. Teachers教师
        </div>
        <div style="padding-left:48px;margin-bottom:10px;margin-top:10px;">
            The British Council reserves the right to assign any teacher to any class.<br/>
            英国文化教育协会保留分配授课老师的权利。
        </div>
        <div style="padding:0 20px;margin-bottom:10px;margin-top:10px;">
            2.2.Inclusive learning 包容性学习政策
        </div>
        <div style="padding-left:48px;margin-bottom:10px;margin-top:10px;">
            We are committed to providing an inclusive learning environment in which students are supported to achieve
            their potential in a spirit of inclusion and acceptance. We value the abilities
            and achievements of all our students. If you need any additional support in class, please notify us by
            filling in the form available at our Customer Services Centre at the time of registration.
            Having this information enables us to help you more effectively. Your information is held in the strictest
            confidence. If a previously identified special educational need is found to have been
            withheld, the British Council reserves the right to reassess the suitability of the learning environment
            provided and, in some cases, this may result in being removed from class.
            我们致力于提供包容性学习环境，本着包容和接纳的精神支持学生发挥他们的潜力。我们珍视所有学生的能力和成绩。如果您在课堂上需要任何额外的支持，请在注册时填写我们客服中心提
            供的表格；我们可以据此为您提供更加有效的协助。您的信息将得到严格保密。若有任何已知的特殊教育需求未得到有效披露，英国文化教育协会保留重新评估所提供学习环境适当性的权
            利，在某些情况下，这可能导致学生退学。
        </div>
        <div>
            The execution, interpretation, performance and dispute settlement of this policy document shall be governed
            by the law of the People’s Republic of China.<br/>
            本政策文件的执行、解释、履行和争议解决受中国法律管辖。
        </div>
        <div>
            Any dispute arising out of or in connection with this policy document shall be solved first through friendly
            consultation between the parties. If the dispute cannot be solved within thirty (30) days
            upon its occurrence, any party shall have the right to submit such dispute to the China International
            Economic and Trade Arbitration Commission for arbitration according to the prevailing
            rules at that time. The location of arbitration is in Beijing.<br/>
            因本政策文件引起的或与本政策文件相关的任何争议应首先通过友好协商解决。若争议无法在发生后的三十（30）日内解决，任何一方有权将该争议提交中国国际经济贸易仲裁委员会，根据当
            时有效的规则仲裁解决。仲裁地为北京。
        </div>

        <div style="margin-top:50px;">
            Student Name: ${(studentName)!""}
            学生姓名
        </div>

        <div style="margin-top:30px;">
            I ${(content)!""}, hereby certify that I have
            read and understood the Registration Terms and Conditions.
            本人已阅读、明白并同意以上报名条款及细则。
        </div>

        <ul style="margin-top:100px;">
            <li>
                ${(studentSign)!""}<br/>
                Signature of student 学生/家长或监护人签署
            </li>
            <li>
                ${(address)!""}<br/>
                Date and Place 日期及地点
            </li>
        </ul>
    </div>
</div>
</body>
</html>