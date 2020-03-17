<%--
  Created by IntelliJ IDEA.
  User: Jomn
  Date: 2020/3/16
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/tag.jsp"%>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${baseurl}assets/images/favicon.png">
    <title>Monster Admin Template - The Most Complete & Trusted Bootstrap 4 Admin Template</title>
    <!-- Bootstrap Core CSS -->
    <link href="${baseurl}assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${baseurl}css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="${baseurl}css/colors/blue.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${baseurl}js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#modButton").click(function () {
                var id = $("#Peonum").val();
                var name = $("#Peoname").val();
                var sex = $("#Peosex").val();
                var post_name = $("#Postname").val();
                var departmentName = $("#dep-name option:selected").val();
                var send = {};
                send['id'] = id;
                send['name'] = name;
                send['sex'] = sex;
                send['departmentName'] = departmentName;
                send['post_name'] = post_name;
                $.ajax({
                    url: '/personinf/modOne.action',
                    contentType: 'application/json',
                    type: "post",
                    data: JSON.stringify(send),
                    success: function (data) {
                        alert("修改成功");
                        location.href='/personinf/findAll.action';
                    }
                });
            });
        })
    </script>
</head>

<body class="fix-header card-no-border">
<!-- ============================================================== -->
<!-- Preloader - style you can find in spinners.css -->
<!-- ============================================================== -->
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
</div>
<!-- ============================================================== -->
<!-- Main wrapper - style you can find in pages.scss -->
<!-- ============================================================== -->
<div id="main-wrapper">
    <!-- ============================================================== -->
    <!-- Topbar header - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <header class="topbar">
        <nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
            <div class="navbar-header">
                <a class="navbar-brand" href="http://localhost:8080/welcome.action">
                    <b>
                        <span>back to index</span>
                    </b>
                </a>
            </div>
            <!-- ============================================================== -->
            <!-- Logo -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- End Logo -->
            <!-- ============================================================== -->
            <div class="navbar-collapse">
                <!-- ============================================================== -->
                <!-- toggle and nav items -->
                <!-- ============================================================== -->
                <ul class="navbar-nav mr-auto mt-md-0 ">
                    <!-- This is  -->
                    <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark" href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
                    <li class="nav-item hidden-sm-down">
                        <form class="app-search p-l-20">
                            <input type="text" class="form-control" placeholder="Search for..."> <a class="srh-btn"><i class="ti-search"></i></a>
                        </form>
                    </li>
                </ul>
                <!-- ============================================================== -->
                <!-- User profile and search -->
                <!-- ============================================================== -->
                <ul class="navbar-nav my-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img src="${personinf.image}" alt="user" class="profile-pic m-r-5" />
                            ${personinf.name}</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- ============================================================== -->
    <!-- End Topbar header -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
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
                    <h3 class="text-themecolor m-b-0 m-t-0">Profile</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                        <li class="breadcrumb-item active">Profile</li>
                    </ol>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <!-- Row -->
            <div class="row">
                <!-- Column -->
                <div class="col-lg-4 col-xlg-3 col-md-5">
                    <div class="card">
                        <div class="card-block">
                            <center class="m-t-30"> <img src="${baseurl}assets/images/users/5.jpg" class="img-circle" width="150" />
                                <h4 class="card-title m-t-10">${personinfVo.name}</h4>
                                <h6 class="card-subtitle">${personinfVo.post_name}</h6>
                            </center>
                        </div>
                    </div>
                </div>
                <!-- Column -->
                <!-- Column -->
                <div class="col-lg-8 col-xlg-9 col-md-7">
                    <div class="card">
                        <div class="card-block">
                            <div class="form-group">
                                <div>
                                    <span>员工编号</span>
                                    <input id="Peonum" disabled type="text" class="form-control" placeholder="id" aria-describedby="basic-addon1" value="${personinfVo.id}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div>
                                    <span>名称</span>
                                    <input id="Peoname" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1" value="${personinfVo.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div>
                                    <span>性别</span>
                                    <input id="Peosex" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1" value="${personinfVo.sex}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div>
                                    <span>职位</span>
                                    <input id="Postname" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1" value="${personinfVo.post_name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div>
                                    <span>部门</span>
                                    <select class="form-control" id="dep-name">
                                        <c:forEach items="${departments}" var="item">
                                            <option value="${item.name}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button class="btn btn-success" id="modButton">Update Profile</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Column -->
            </div>
            <!-- Row -->
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
<script src="${baseurl}assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="${baseurl}assets/plugins/bootstrap/js/tether.min.js"></script>
<script src="${baseurl}assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="${baseurl}js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="${baseurl}js/waves.js"></script>
<!--Menu sidebar -->
<script src="${baseurl}js/sidebarmenu.js"></script>
<!--stickey kit -->
<script src="${baseurl}assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
<!--Custom JavaScript -->
<script src="${baseurl}js/custom.min.js"></script>
<!-- ============================================================== -->
<!-- Style switcher -->
<!-- ============================================================== -->
<script src="${baseurl}assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
