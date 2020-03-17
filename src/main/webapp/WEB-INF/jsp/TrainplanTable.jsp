<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/tag.jsp" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%--
  Created by IntelliJ IDEA.
  User: Jomn
  Date: 2020/3/14
  Time: 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href=" ${baseurl}assets/images/favicon.png">
    <title>Monster Admin Template - The Most Complete & Trusted Bootstrap 4 Admin Template</title>
    <link href=" ${baseurl}assets/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
<%--    <link href=" ${baseurl}assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href=" ${baseurl}css/style.css" rel="stylesheet">
    <link href=" ${baseurl}css/colors/blue.css" id="theme" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/moment.js/2.22.0/moment-with-locales.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <script>
        $(function () {
            changePage(1);
<%--            $('#datetimepicker1').datetimepicker({--%>
<%--                format: 'YYYY-MM-DD',--%>
<%--                locale: moment.locale('zh-cn')--%>
<%--            });--%>
<%--            $('#datetimepicker2').datetimepicker({--%>
<%--                format: 'YYYY-MM-DD',--%>
<%--                locale: moment.locale('zh-cn')--%>
<%--            });--%>

            $("#modButton").click(function () {

                var peo_id = $("#Peonum").val();
                var name = $("#Peoname").val();
                var content = $("#content").val();
                var send = {};
                send['peo_id'] = peo_id;
                send['name'] = name;
                send['Postname'] = Postname;
                send['content'] = content;
                $.ajax({
                    url:'/active/modTrainplan.action',
                    contentType: 'application/json',
                    type: "post",
                    data:JSON.stringify(send),
                    success:function (data) {
                        $("#myModal").modal('hide');
                        alert('修改成功');
                    }
                });
            });
            $("#checkCancleBtn").click(function () {
                var peo_id = $("#check-Id").val();
                $.ajax({
                    url:'/active/checkTrainplan.action',
                    type:'post',
                    data:{
                        peo_id:peo_id,
                        state:"2"
                    },
                    success:function (data) {
                        console.log(data);
                        location.href='/active/findAllTrainplan.action';
                    }
                })
            });

            $("#checkSureBtn").click(function () {
                var peo_id = $("#check-Id").val();
                $.ajax({
                    url:'/active/checkTrainplan.action',
                    type:'post',
                    data:{
                      peo_id:peo_id,
                        state:"1"
                    },
                    success:function (data) {
                        console.log(data);
                        location.href='/active/findAllTrainplan.action';
                    }
                })
            });

            $("#addButton").click(function () {
                var peo_id = $("#addPeonum").val();
                var content = $("#addcontent").val();
                var send = {};
                send['peo_id'] = peo_id;
                send['content'] = content;
                $.ajax({
                    url:'/active/addTrainplan.action',
                    type:"post",
                    contentType: 'application/json',
                    data:JSON.stringify(send),
                    success:function (data) {
                        console.log(data);
                        $("#addModal").modal('hide');
                        alert('添加成功');
                        $("#addPeonum").val("");
                        $("#addcontent").val("");

                    }
                });
            });


        });

        function delTrainplan(peo_id) {
            console.log(peo_id);
            var send = {};
            send['id'] = peo_id;
            $.ajax({
                url:'/active/deleteTrainplan.action',
                type:"post",
                data:{
                    id:peo_id
                },
                success:function (data) {
                    console.log(data);
                    alert('删除成功');
                    location.href='/active/findAllTrainplan.action';
                }
            });
        }

        function checkTrainplan(peo_id) {
            $("#SURE").modal('show');
            $("#check-Id").val(peo_id);
        }


        function changePage(currentPage) {
            $.get('/active/findAllTrainplanPage.action?pageNo='+currentPage,function (data) {
                console.log(${financialVoPageInfo.list[0].real_money});
                var begin;
                var end;
                var prePage = data.data.prePage;
                var nextPage = data.data.nextPage;
                var totalPage =  data.data.total;
                var firstPag = data.data.navigateFirstPage;
                var lastPag =data.data.navigateLastPage;
                var pageSize = data.data.pageSize;
                console.log(data.data.list);
                var tbody = $("#tbody-data");
                tbody.html("");
                for(var i=0;i<data.data.list.length;i++){
                    var index= pageSize*(currentPage-1) + i;
                    var obj = data.data.list[i];
                    var state;
                    var btnState;
                    if(obj.state == "1"){
                        state = "通过";
                        btnState = "重审";
                    }else if(obj.state == "0"){
                        state = "待审批";
                        btnState = "审批";
                    }else{
                        state = "不通过";
                        btnState = "重审";
                    }

                    console.log(obj.state);
                    // alert(obj.post_name);
                    var tr = ' <tr>\n' +
'                                            <td>'+index+'</td>\n' +
'                                            <td>'+obj.id+'</td>\n' +
'                                            <td>'+obj.name+'</td>\n' +
'                                            <td>'+obj.post_name+'</td>\n' +
'                                            <td>'+obj.departmentName+'</td>\n' +
'                                            <td>'+state+'</td>\n' +
'                                            <td>\n' +
'                                                <c:forEach items="${permissionVo.permissionNameAndUrl}" var="it">\n' +
'                                                    <c:if test="${it.key.indexOf(\'人才培养计划修改\')>-1}">\n' +
'                                                        <button type="button" class="btn btn-info"\n' +
'                                                                onclick="updateTrainplan(\''+obj.id+'\')">查看\n' +
'                                                        </button>\n' +
'                                                    </c:if>\n' +
'                                                    <c:if test="${it.key.indexOf(\'人才培养计划删除\')>-1}">\n' +
'                                                        <button type="button" class="btn btn-danger"\n' +
'                                                                onclick="delTrainplan(\''+obj.id+'\')">删除\n' +
'                                                        </button>\n' +
'                                                    </c:if>\n' +
'                                                </c:forEach>\n' +
'<shiro:hasAnyRoles name="厂长,管理员">\n' +
                        '        <button type="button" class="btn btn-info"\n' +
                        '                onclick="checkTrainplan(\''+obj.id+'\' )">'+btnState+'\n' +
                        '        </button>\n' +
                        '</shiro:hasAnyRoles>'+
'                                            </td>\n' +
'                                        </tr>';
                    tbody.append(tr);
                }


                <%--'<c:choose>\n' +--%>
                <%--'    <c:when test="${obj.state == \'待审批\'}">\n' +--%>
                <%--'        <shiro:hasAnyRoles name="厂长,管理部">\n' +--%>
                <%--'            <button type="button" class="btn btn-info" onclick="checkTrainplan(\'${item.id}\')">审批 </button>\n' +--%>
                <%--'        </shiro:hasAnyRoles>\n' +--%>
                <%--'    </c:when>\n' +--%>
                <%--'    <c:otherwise>\n' +--%>
                <%--'        <shiro:hasAnyRoles name="厂长,管理部">\n' +--%>
                <%--'            <button type="button" class="btn btn-info" onclick="checkTrainplan(\'${item.id}\')">重审 </button>\n' +--%>
                <%--'        </shiro:hasAnyRoles>\n' +--%>
                <%--'    </c:otherwise>\n' +--%>
                <%--'</c:choose>'+--%>
                if(lastPag <10){
                    begin = 1;
                    end = lastPag;
                }else{
                    begin = lastPag-5;
                    end = lastPag + 4;
                    if(begin < 1){
                        begin = 1;
                        end = begin + 9;
                    }
                    if(end > lastPag){
                        end = lastPag;
                        begin = end - 9;
                    }
                }
            // <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">Previous</a></li>
                var pageInfo = $("#pageUl");
                pageInfo.html("");
                var prePage = prePage >1?currentPage-1:1;
                var beforePage = '<li class="page-item " onclick="changePage('+prePage+')"><a class="page-link" href="#!" tabindex="-1">Previous</a></li>';
                pageInfo.append(beforePage);

                for(var i =begin;i<=end;i++){
                    var li;
                    if(i == currentPage){
                        li ='<li class="page-item active" onclick="changePage('+i+')"><a class="page-link" href="#!" tabindex="-1">'+i+'</a></li>';
                    }else{
                        li ='<li class="page-item " onclick="changePage('+i+')"><a class="page-link" href="#!" tabindex="-1">'+i+'</a></li>';
                    }
                    pageInfo.append(li);
                }
                var nextPag1 = currentPage + 1> lastPag?lastPag: currentPage + 1;
                var nextli ='<li class="page-item " onclick="changePage('+nextPag1+')"><a class="page-link" href="#!" tabindex="-1">Next</a></li>';
                pageInfo.append(nextli);

            });
        }

        function addTrainplane() {
            $("#addModal").modal('show');
        }


        function updateTrainplan(peo_id) {
            $.ajax({
                type: 'get',
                data: {
                    peo_id: peo_id
                },
                url: '/active/findTrainplanById.action',
                success: function (data) {
                    console.log(data);
                    $("#myModal").modal('show');
                    $("#Peoname").val(data.data.name);
                    $("#Postname").val(data.data.post_name);
                    $("#content").val(data.data.content);
                    $("#Peonum").val(data.data.id)
                }
            })
        };
    </script>

</head>
<body class="fix-header card-no-border">

<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>

<div id="main-wrapper">
    <header class="topbar">
        <nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
            <div class="navbar-header">
                <a class="navbar-brand" href="http://localhost:8080/welcome.action">
                    <b>
                        <span>back to index</span>
                    </b>
                </a>
            </div>
            <div class="navbar-collapse">
                <ul class="navbar-nav mr-auto mt-md-0 ">
                    <li class="nav-item"><a class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark"
                                            href="javascript:void(0)"><i class="ti-menu"></i></a></li>
                    <li class="nav-item hidden-sm-down">
                        <form class="app-search p-l-20">
                            <input type="text" class="form-control" placeholder="Search for..."> <a class="srh-btn"><i
                                class="ti-search"></i></a>
                        </form>
                    </li>
                </ul>
                <ul class="navbar-nav my-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href=""
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img src="${personinf.image}" alt="user" class="profile-pic m-r-5"/>
                            ${personinf.name}</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div class="scroll-sidebar">
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <c:forEach items="${permissionVo.permissionNameAndUrl}" var="tab" varStatus="status">
                        <c:if test="${tab.key.indexOf('查询')>-1}">
                            <c:choose>
                                <c:when test="${permissionVo.active==status.index}">
                                    <li class="active">
                                        <a href="${tab.value}" class="waves-effect"
                                           onclick="optFunction('${tab.value}')">
                                            <div style="text-align: center">
                                                <span>${tab.key}</span>
                                            </div>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="${tab.value}" class="waves-effect"
                                           onclick="optFunction('${tab.value}')">
                                            <div style="text-align: center">
                                                <span>${tab.key}</span>
                                            </div>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                </ul>
                <div class="text-center m-t-30">
                    <a href="https://wrappixel.com/templates/monsteradmin/" class="btn btn-danger"> Upgrade to Pro</a>
                </div>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>

    <div class="modal fade" id="SURE" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" >确定审批通过吗？</h4>
                </div>
                <div class="modal-footer">
                    <input type="hidden" id="check-Id">
                    <button type="button" class="btn btn-default" data-dismiss="modal">close</button>
                    <button type="button" class="btn btn-primary" id="checkSureBtn">通过</button>
                    <button type="button" class="btn btn-warning" id="checkCancleBtn">不通过</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">修改数据</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="Peonum">
                    <div>
                        <span>名称</span>
                        <input id="Peoname" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
                    </div>
                    <div>
                        <span>职位</span>
                        <input id="Postname" disabled type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
                    </div>
                    <div>
                        <span>计划内容</span>
                        <textarea id="content" class="form-control" rows="3"></textarea>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="modButton">提交</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">添加人才计划</h4>
                </div>
                <div class="modal-body">
                    <div>
                        <span>员工编号</span>
                        <input id="addPeonum" type="text" class="form-control" placeholder="id" aria-describedby="basic-addon1">
                    </div>
                    <div>
                        <span>计划内容</span>
                        <textarea id="addcontent" class="form-control" rows="3"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="addButton">提交</button>
                </div>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Page wrapper  -->
    <!-- ============================================================== -->
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->
        <div class="container-fluid">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="row page-titles">
                <div class="col-md-6 col-8 align-self-center">
                    <h3 class="text-themecolor m-b-0 m-t-0">Table</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                        <li class="breadcrumb-item active">Table</li>
                    </ol>
                </div>
                <div class="col-md-6 col-4 align-self-center">
                    <c:forEach items="${permissionVo.permissionNameAndUrl}" var="it">
                        <c:if test="${it.key.indexOf('计划添加')>-1}">
                            <a href="#" class="btn pull-right hidden-sm-down btn-success" onclick="addTrainplane()">${it.key}</a>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <div class="row">
                <!-- column -->
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">人才计划列表</h4>
                            <h5 class="card-subtitle" id="pageTotal">总条数:${trainplanPageInfo.total}</h5>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>员工编号</th>
                                        <th>名称</th>
                                        <th>职位</th>
                                        <th>部门</th>
                                        <th>审批状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbody-data">
                                    <c:forEach items="${trainplanPageInfo.list}" var="item" varStatus="status">
                                        <tr>
                                            <td>${status.index}</td>
                                            <td>${item.id}</td>
                                            <td>${item.name}</td>
                                            <td>${item.post_name}</td>
                                            <td>${item.departmentName}</td>
                                            <td>${item.state}</td>
                                            <td>
                                                <c:forEach items="${permissionVo.permissionNameAndUrl}" var="it">
                                                    <c:if test="${it.key.indexOf('计划修改')>-1}">
                                                        <button type="button" class="btn btn-info"
                                                                onclick="updateTrainplan('${item.id}')">查看
                                                        </button>
                                                    </c:if>

                                                    <c:if test="${it.key.indexOf('删除')>-1}">
                                                        <button type="button" class="btn btn-danger"
                                                                onclick="delFinancial('${item.id}')">删除
                                                        </button>
                                                    </c:if>
                                                </c:forEach>
                                                <shiro:hasAnyRoles name="厂长,管理部">
                                                    <button type="button" class="btn btn-info"
                                                            onclick="checkTrainplan('${item.id}')">审批
                                                    </button>
                                                </shiro:hasAnyRoles>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <nav aria-label="...">
                            <ul id="pageUl" class="pagination" style="float: right;padding-right: 30px">
<%--                                <li class="page-item disabled">--%>
<%--                                    <a class="page-link" href="#!" tabindex="-1">Previous</a>--%>
<%--                                </li>--%>
<%--                                <li class="page-item"><a class="page-link" href="#!">1</a></li>--%>
<%--                                <li class="page-item active">--%>
<%--                                    <a class="page-link" href="#!">2 </a>--%>
<%--                                </li>--%>
<%--                                <li class="page-item"><a class="page-link" href="#!">3</a></li>--%>
<%--                                <li class="page-item">--%>
<%--                                    <a class="page-link" href="#!">Next</a>--%>
<%--                                </li>--%>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End PAge Content -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Container fluid  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- footer -->
        <!-- ============================================================== -->
        <footer class="footer text-center">
            © 2017 Monster Admin by wrappixel.com
        </footer>
        <!-- ============================================================== -->
        <!-- End footer -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- End Wrapper -->
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- All Jquery -->
<!-- ============================================================== -->
<%--<script src=" ${baseurl}assets/plugins/jquery/jquery.min.js"></script>--%>
<!-- Bootstrap tether Core JavaScript -->
<script src=" ${baseurl}assets/plugins/bootstrap/js/tether.min.js"></script>
<script src=" ${baseurl}assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${baseurl}js/option.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src=" ${baseurl}js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src=" ${baseurl}js/waves.js"></script>
<!--Menu sidebar -->
<script src=" ${baseurl}js/sidebarmenu.js"></script>
<!--stickey kit -->
<script src=" ${baseurl}assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
<!--Custom JavaScript -->
<script src=" ${baseurl}js/custom.min.js"></script>
<!-- ============================================================== -->
<!-- Style switcher -->
<!-- ============================================================== -->
<script src=" ${baseurl}assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>

</html>
