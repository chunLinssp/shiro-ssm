<%--
  Created by IntelliJ IDEA.
  User: Jomn
  Date: 2020/3/13
  Time: 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Bootstrap -->
<%@ include file="/WEB-INF/jsp/common_js.jsp"%>
<%@ include file="/WEB-INF/jsp/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/tag.jsp"%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet"type="text/css" href="${baseurl}css/login.css">
    <script>
        $(function () {
            $('#formbackground').height($(window).height());
            $('#formbackground').width($(window).width());
        })
        function randomcode_refresh() {
            var i=Math.random();//目的是使页面不一样
            var tmp = "${baseurl}" + "validatecode.jsp?id=" + i;
            $("#randomcode_img").attr("src",tmp );
        }
        function loginsubmit(){
            $("#loginform").submit();
            // var username = $("#usercode").val();
            // var password = $("#pwd").val();
            // var randomcode = $("#randomcode").val();
            // var rememberMe = $("input[name='rememberMe']").prop('checked');
            // console.log(username+":"+password);
            // console.log(randomcode+":"+rememberMe);
            // $.ajax({
            //     type:'post',
            //     url :'login.action',
            //     contentType:'application/x-www-form-urlencoded',
            //     data:{
            //     username:username,
            //     password:password,
            //     randomcode:randomcode,
            //     rememberMe:rememberMe
            //     },
            //     success:function (data) {
            //         alert(data);
            //     }
            // });
        }
    </script>
</head>
<body>
    <div id="formbackground" style="position:absolute; z-index:-1;background-color: #2aabd2">
        <img src="${baseurl}assets/images/login-background.jpg" height="100%" width="100%">
    </div>
        <div class="main">
            <FORM id="loginform" name="loginform" action="${baseurl}login.action" method="post">
                <div class="logincon">
                    <div class="title">
                        <%--   <img alt="" src="${baseurl}images/login/logo.png">--%>
                        <h1 style="font-size: 40px">人力资源管理系统</h1>
                    </div>
                    <div class="cen_con">
                        <img alt="" src="${baseurl}assets/images/bg2.png">
                    </div>
                    <div class="tab_con">
                        <input type="password" style="display:none;" />
                        <table class="tab" border="0" cellSpacing="6" cellPadding="8">
                            <tbody>
                            <tr>
                                <td>用户名：</td>
                                <td colSpan="2"><input type="text" id="usercode"
                                                       name="username" style="WIDTH: 130px" /></td>
                            </tr>
                            <tr>
                                <td>密 码：</td>
                                <td><input type="password" id="pwd" name="password" style="WIDTH: 130px" />
                                </td>
                            </tr>
                            <tr>
                                <td>验证码：</td>
                                <td><input id="randomcode" name="randomcode" size="8" /> <img
                                        id="randomcode_img" src="${baseurl}validatecode.jsp" alt=""
                                        width="56" height="20" align='absMiddle' /> <a
                                        href=javascript:randomcode_refresh()>刷新</a></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="checkbox" name="rememberMe"/>自动登录</td>
                            </tr>
                            <tr>
                                <td colSpan="2" align="center"><input type="button"
                                                                      class="btnalink" onclick="loginsubmit()" value="登&nbsp;&nbsp;录" />
                                    <input type="reset" class="btnalink" value="重&nbsp;&nbsp;置" /></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </FORM>
        </div>
  </div>
</body>
</html>
