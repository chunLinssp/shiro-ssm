<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common_js.jsp"%>
<%@ include file="/WEB-INF/jsp/tag.jsp"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%--
  Created by IntelliJ IDEA.
  User: Jomn
  Date: 2020/3/13
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
<%--    <link rel="icon" type="image/png" sizes="16x16" href=" ${baseurl}assets/images/favicon.png">--%>
    <title>Monster Admin Template - The Most Complete & Trusted Bootstrap 4 Admin Template</title>
    <!-- Bootstrap Core CSS -->
    <link href=" ${baseurl}assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="css/colors/blue.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->
    <script>

        function changeSaleData(data){
            var tbody = $("#sale-body");
            tbody.html("");
            console.log(data);
            for(var i =0;i<data.data.length;i++){
                var item = data.data[i];
                var tr='<tr>\n' +
'                                             <td><span class="round round-success">'+(i+1)+'</span>\n' +
'                                                <input type="hidden" value="'+item.id+'">\n' +
'                                             </td>\n' +
'                                             <td>\n' +
'                                              <h6>'+item.name+'</h6><small class="text-muted">'+item.post_name+'</small></td>\n' +
'                                             <td>'+item.sex+'</td>\n' +
'                                             <td>'+item.sale_num+'</td>\n' +
'                                         </tr>';
                tbody.append(tr);
            }
        }
        function optionSale(value){
            if(value == "month"){
                $.ajax({
                    url:'/sale/month.action',
                    type:'get',
                    success:function (data) {
                        changeSaleData(data);
                    }
                });
            }else if(value == "weekend"){
                $.ajax({
                    url:'/sale/weekend.action',
                    type:'get',
                    success:function (data) {
                        changeSaleData(data);
                    }
                })
            }
            else if(value == "yesterday"){
                console.log('333333');
                $.ajax({
                    url:'/sale/yesterday.action',
                    type:'get',
                    success:function (data) {
                        changeSaleData(data);
                }
                });
            }
            console.log(value);
        }
    </script>

</head>

<body class="fix-header fix-sidebar card-no-border">
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
                    <c:forEach items="${permissions}" var="tab">
                        <c:if test="${tab.name.indexOf('查询')>-1}">
                            <li>
                                <a href="#" class="waves-effect" onclick="optFunction('${tab.url}')">
                                    <div style="text-align: center">
                                        <span >${tab.name}</span>
                                    </div>
                                </a>
                            </li>
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
    <div class="page-wrapper">
        <div class="container-fluid">
            <div class="row page-titles">
                <div class="col-md-8 col-8 align-self-center">
                    <h3 class="text-themecolor m-b-0 m-t-0">Dashboard</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                        <li class="breadcrumb-item active">Dashboard</li>
                    </ol>
                </div>
                <div class="col-md-4 col-4 align-self-center">
                    <a href="https://wrappixel.com/templates/monsteradmin/" class="btn pull-right hidden-sm-down btn-success"> Upgrade to Pro</a>
                </div>
            </div>
            <div class="row">
                <!-- Column -->
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Daily Sales</h4>
                            <div class="text-right">
                                <h2 class="font-light m-b-0"><i class="ti-arrow-up text-success"></i> $120</h2>
                                <span class="text-muted">Todays Income</span>
                            </div>
                            <span class="text-success">80%</span>
                            <div class="progress">
                                <div class="progress-bar bg-success" role="progressbar" style="width: 80%; height: 6px;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Column -->
                <!-- Column -->
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Weekly Sales</h4>
                            <div class="text-right">
                                <h2 class="font-light m-b-0"><i class="ti-arrow-up text-info"></i> $5,000</h2>
                                <span class="text-muted">Todays Income</span>
                            </div>
                            <span class="text-info">30%</span>
                            <div class="progress">
                                <div class="progress-bar bg-info" role="progressbar" style="width: 30%; height: 6px;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Column -->
            </div>
            <!-- Row -->
            <!-- Row -->
<%--            <div class="row">--%>
<%--                <!-- column -->--%>
<%--                <div class="col-sm-12">--%>
<%--                    <div class="card">--%>
<%--                        <div class="card-block">--%>
<%--                            <h4 class="card-title">Revenue Statistics</h4>--%>
<%--                            <div class="flot-chart">--%>
<%--                                <div class="flot-chart-content" id="flot-line-chart"></div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <!-- column -->
            </div>
            <!-- Row -->
            <!-- Row -->
        <shiro:hasRole name="厂长">
            <div class="row">
                <!-- column -->
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">
                                <c:if test="${checkedNumber != 0}">
                                    <a href="/active/findAllTrainplan.action">待审批人才计划 -${checkedNumber}条</a></h4>
                                </c:if>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">待审批人员变更</h4>
                        </div>
                    </div>
                </div>
                <!-- column -->
            </div>
        </shiro:hasRole>

        <shiro:hasRole name="厂长">
            <div class="row"  >
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-block">
                            <select class="custom-select pull-right" onchange="optionSale(this.value)">
                                <option selected value="month">近30天</option>
                                <option value="weekend">近7天</option>
                                <option value="yesterday">昨天</option>
                            </select>
                            <h4 class="card-title">销售Top5</h4>
                            <div class="table-responsive m-t-40">
                                <table class="table stylish-table">
                                    <thead>
                                    <tr>
                                        <th>排名</th>
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>销售额</th>
                                    </tr>
                                    </thead>
                                    <tbody id="sale-body">
                                     <c:forEach items="${top5AsMonth}" var="item" varStatus="state">
                                         <tr>
                                             <td><span class="round round-success">${state.index + 1}</span>
                                                <input type="hidden" value="${item.id}">
                                             </td>
                                             <td>
                                                 <h6>${item.name}</h6><small class="text-muted">${item.post_name}</small></td>
                                             <td>${item.sex}</td>
                                             <td>${item.sale_num}</td>
                                         </tr>
                                     </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </shiro:hasRole>

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

<script src="${baseurl}js/option.js"></script>
<script src=" ${baseurl}assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src=" ${baseurl}assets/plugins/bootstrap/js/tether.min.js"></script>
<script src=" ${baseurl}assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src=" ${baseurl}js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="${baseurl}js/waves.js"></script>
<!--Menu sidebar -->
<script src="${baseurl}js/sidebarmenu.js"></script>
<!--stickey kit -->
<script src=" ${baseurl}assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
<!--Custom JavaScript -->
<script src="${baseurl}js/custom.min.js"></script>
<!-- ============================================================== -->
<!-- This page plugins -->
<!-- ============================================================== -->
<!-- Flot Charts JavaScript -->
<script src=" ${baseurl}assets/plugins/flot/jquery.flot.js"></script>
<script src=" ${baseurl}assets/plugins/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
<script src="${baseurl}js/flot-data.js"></script>
<!-- ============================================================== -->
<!-- Style switcher -->
<!-- ============================================================== -->
<script src=" ${baseurl}assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>

</html>
