<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script src="${pageContext.request.contextPath}/background/jquery-easyui-1.5.1/jquery.min.js"></script>
  <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  
  <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
  
  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
          integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
          crossorigin="anonymous"></script>
  <title>Home</title>
  <script>
    $(function () {
      
      getData = function (columns) {
        //初始化表头
        var table = $('#user_table');
        var theadId = table[0].id + '_' + 'thead';
        var tbodyId = table[0].id + '_' + 'tbody'
        table.append('<thead id="' + theadId + '"><tr></tr></thead><tbody id="' + tbodyId + '"></tbody>');
        var tr = $('#' + theadId + ' > tr');
        for (var i = 0; i < columns.length; i++) {
          tr.append('<td>' + columns[i].title + '</td>');
        }
        $.post("${pageContext.request.contextPath}/userManager/userManagerAction_tableData",
          function (data) {
            var rows = data.rows;
            var tbody = $('#' + tbodyId);
            for (var i = 0; i < rows.length; i++) {
              tbody.append('<tr id="tr_' + i + '"></tr>');
              var newTr = $('#tr_' + i);
              for (var j = 0; j < columns.length; j++) {
                newTr.append('<td>' + rows[i][columns[j].filed] + '</td>');
              }
            }
          }, "json");
      }
    });
  </script>
</head>
<body>
<div class="container">
  <h1>Hello World</h1>
  <button onclick="getData([{filed: 'id', title: '用户编号'},
          {filed: 'userName', title: '用户编号'},
          {filed: 'email', title: '邮箱'},
          {filed: 'userLevel', title: '用户等级'},
        ])">hello</button>
  <div class="table-responsive">
    <table id="user_table" class="table">
    </table>
  </div>
</div>
</body>
</html>
