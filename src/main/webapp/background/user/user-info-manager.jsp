<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
  <jsp:include page="../proadmin/common/head.jsp"/>
  <meta name="description" content="用户信息管理">
  <title>用户信息管理</title>
  <script type="text/javascript"
          src="${pageContext.request.contextPath}/background/proadmin/assets/js/mytableutil.js"></script>
  <script>
    $(function () {
      var columns = [{filed: 'id', title: '用户编号'},
        {filed: 'userName', title: '用户名'},
        {filed: 'email', title: '邮箱'},
        {filed: 'password', title: '密码'},
        {filed: 'userLevel', title: '用户等级'}];
      var table = $('#bg_user_usertable');
      var pager = $('#bg_user_pager');
      getDataFromServer(table, '${pageContext.request.contextPath}/userManager/userManagerAction!tableData',
        null, columns, pager);
      changeRows = function (rows) {
        $('#bg_user_tablerowsselect').html(rows + '&nbsp;&nbsp;<span class="caret"></span>');
        var params = getParams();
        var currentPage = pager.find('span').last().attr("title");
        params.page = Number(currentPage);
        console.info(params);
        getDataFromServer(table, '${pageContext.request.contextPath}/userManager/userManagerAction!tableData',
          params, columns, pager);
      };

      search = function () {
        var params = getParams();
        params.page = 1;
        console.info(params);
        getDataFromServer(table, '${pageContext.request.contextPath}/userManager/userManagerAction!tableData',
          params, columns, pager);
      }

      previousPage = function (params) {
        var params = getParams();
        var currentPage = pager.find('span').last().attr("title");
        var number = Number(currentPage);
        if (number === 1) {
          params.page = 1;
        } else {
          params.page = Number(currentPage) - 1;
        }
        console.info(params);
        getDataFromServer(table, '${pageContext.request.contextPath}/userManager/userManagerAction!tableData',
          params, columns, pager);
      };
      nextPage = function (params) {
        var params = getParams();
        var currentPage = pager.find('span').last().attr("title");
        var totalPage = pager.find('span').last().attr("tabindex");
        if (currentPage === totalPage) {
          params.page = Number(currentPage);
        } else {
          params.page = Number(currentPage) + 1;
        }
        console.info(params);
        getDataFromServer(table, '${pageContext.request.contextPath}/userManager/userManagerAction!tableData',
          params, columns, pager);
      };
      getParams = function () {
        var rowsNumber = $.trim($('#bg_user_tablerowsselect').text());
        var inputs = $('#bg_user_search input');
        var name = inputs[0].value;
        var eml = inputs[1].value;
        var params = $.parseJSON('{}');
        params.userName = name;
        params.email = eml;
        params.rows = rowsNumber;
        return params;
      };

      userEdit = function (id) {
        var $udig = $('#bg_user_usereditformdialog');
        var $inputs = $('#bg_user_usereditformdialog input');
        var userId = id;
        $.get('${pageContext.request.contextPath}/userManager/userManagerAction!getUser',
          {id: userId},
          function (data) {
            $inputs.eq(1).attr('value', data.email).focus().on('');
            $inputs.eq(0).attr('value', data.userName).focus();
            var $btns = $('#bg_user_usereditformdialog .pmd-modal-action button');
            $btns.eq(0).on('click', function () {
              var userNameTemp = $inputs.eq(0).val();
              var emailTemp = $inputs.eq(1).val();
              $.post('${pageContext.request.contextPath}/userManager/userManagerAction!updateUser', {
                id: userId,
                userName: userNameTemp,
                email: emailTemp
              }, function () {
                getDataFromServer(table, '${pageContext.request.contextPath}/userManager/userManagerAction!tableData',
                  null, columns, pager);
              });
            });
          }
        );
      };
      userDelete = function (id) {
        console.info('delete' + id);
      };
    });
  </script>
</head>

<!-- Styles Ends -->
<body>
<!-- Header Starts -->
<!--Start Nav bar -->
<jsp:include page="../proadmin/common/navbar.jsp"/>
<!-- Header Ends -->

<!-- Sidebar Starts -->
<div class="pmd-sidebar-overlay"></div>

<!-- Left sidebar -->
<jsp:include page="../proadmin/common/aside.jsp"/>
<!-- Sidebar Ends -->

<!--content area start-->
<div id="content" class="pmd-content inner-page">
  <!--tab start-->
  <div class="container-fluid full-width-container value-added-detail-page">
    <!-- Search -->
    <div>
      <div id="bg_user_search" class="pull-right table-title-top-action">
        <div class="row">
          <div class="col-sm-5">
            <div class="form-group pmd-textfield pmd-textfield-floating-label">
              <input class="form-control" id="exampleInputEmail2" type="text" placeholder="用户名"><span
                    class="pmd-textfield-focused"></span>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group pmd-textfield pmd-textfield-floating-label">
              <input class="form-control" id="exampleInputPassword2" type="text" placeholder="邮箱"><span
                    class="pmd-textfield-focused"></span>
            </div>
          </div>
          <div class="col-sm-3">
            <button onclick="search()" type="submit" class="btn btn-primary pmd-ripple-effect">搜索</button>
          </div>
        </div>
      </div>
      <!-- Title -->
      <h1 class="section-title" id="services">
        <span>用户信息管理</span>
      </h1><!-- End Title -->
      <!--breadcrum start-->
      <ol class="breadcrumb text-left">
        <li><a href="index.html">用户模块</a></li>
        <li class="active">用户信息管理</li>
      </ol><!--breadcrum end-->
    </div>
    <!-- Table -->
    <div class="table-responsive pmd-card pmd-z-depth">
      <table id="bg_user_usertable" class="table table-mc-red pmd-table">
      
      </table>
    </div>
    <!-- Card Footer -->
    <div id="bg_user_pager" style="display: none;" class="pmd-card-footer">
      <ul class="pmd-pagination pull-right list-inline">
        <span>每页行数:</span>
        <span class="dropdown pmd-dropdown">
			  <button class="btn pmd-ripple-effect pmd-btn-flat btn-link dropdown-toggle" type="button"
                id="bg_user_tablerowsselect" data-toggle="dropdown" aria-expanded="false">10 <span
                class="caret"></span></button>
			  <ul aria-labelledby="dropdownMenuDivider" role="menu" class="dropdown-menu">
				  <li role="presentation"><a href="javascript:changeRows(10);" tabindex="-1" role="menuitem">10</a></li>
				  <li role="presentation"><a href="javascript:changeRows(20);" tabindex="-1" role="menuitem">20</a></li>
				  <li role="presentation"><a href="javascript:changeRows(30);" tabindex="-1" role="menuitem">30</a></li>
				  <li role="presentation"><a href="javascript:changeRows(40);" tabindex="-1" role="menuitem">40</a></li>
				  <li role="presentation"><a href="javascript:changeRows(50);" tabindex="-1" role="menuitem">50</a></li>
			  </ul>
			  </span>
        <span></span>
        <a href="javascript:previousPage();" aria-label="Previous"><i class="material-icons md-dark pmd-sm">keyboard_arrow_left</i></a>
        <a href="javascript:nextPage();" aria-label="Next"><i
                class="material-icons md-dark pmd-sm">keyboard_arrow_right</i></a>
      </ul>
    </div>
    
    <!-- Modal -->
    <div class="row">
      <div class="col-md-6 col-sm-6">
        <div tabindex="-1" class="modal fade" id="bg_user_usereditformdialog" style="display: none;" aria-hidden="true">
          <div class="modal-dialog" style="margin-top: 0px;">
            <div class="modal-content">
              <div class="modal-header bordered">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h2 class="pmd-card-title-text">用户信息编辑</h2>
              </div>
              <div class="modal-body">
                <form class="form-horizontal">
                  <div class="form-group pmd-textfield pmd-textfield-floating-label">
                    <label for="name">Name</label>
                    <input type="text" class="mat-input form-control" id="name" value=""><span
                          class="pmd-textfield-focused"></span>
                  </div>
                  <div class="form-group pmd-textfield pmd-textfield-floating-label">
                    <label for="email">Email Address</label>
                    <input type="text" class="mat-input form-control" id="email" value=""><span
                          class="pmd-textfield-focused"></span>
                  </div>
                </form>
              </div>
              <div class="pmd-modal-action">
                <button data-dismiss="modal" class="btn pmd-ripple-effect btn-primary" type="button">修改</button>
                <button data-dismiss="modal" class="btn pmd-ripple-effect btn-default" type="button">取消</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--tab start-->

<!--content area end-->

</div>

<!-- Footer Starts -->
<jsp:include page="../proadmin/common/footer.jsp"/>
<!-- Footer Ends -->

<!-- Scripts Starts -->
<script src="${pageContext.request.contextPath}/background/proadmin/assets/js/propeller.min.js"></script>
<script>
  $(document).ready(function () {
    var sPath = window.location.pathname;
    var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
    $(".pmd-sidebar-nav").each(function () {
      $(this).find("a[href='" + sPage + "']").parents(".dropdown").addClass("open");
      $(this).find("a[href='" + sPage + "']").parents(".dropdown").find('.dropdown-menu').css("display", "block");
      $(this).find("a[href='" + sPage + "']").parents(".dropdown").find('a.dropdown-toggle').addClass("active");
      $(this).find("a[href='" + sPage + "']").addClass("active");
    });
  });
</script>

<!-- Scripts Ends -->

<!--detail page table data expand collapse javascript-->
<script type="text/javascript">
  $(document).ready(function () {
    $(".direct-expand").click(function () {
      $(".direct-child-table").slideToggle(300);
      $(this).toggleClass("child-table-collapse");
    });
  });
</script>
<!-- Scripts Ends -->
</body>
</html>