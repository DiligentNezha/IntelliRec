/**
 * Created by Kenny on 2017/3/18.
 */

$(function () {
  //初始化表结构
  var initTableStructure = function (table, columns) {
    var theadId = table[0].id + '_' + 'thead';
    var tbodyId = table[0].id + '_' + 'tbody'
    table.append('<thead id="' + theadId + '"><tr></tr></thead><tbody id="' + tbodyId + '"></tbody>');
    var tr = $('#' + theadId + ' > tr');
    for (var i = 0; i < columns.length; i++) {
      tr.append('<th data-title="' + columns[i].title + '">' + columns[i].title + '</th>');
    }
    tr.append('<th>操作</th>');
    return $('#' + tbodyId);
  };
  //获取数据
  getDataFromServer = function (table, url, params, columns, pager) {
    table.children().remove();
    $.post(url, params, function (data) {
      fillTable(table, data, columns, pager);
    });
  };

  //填充表格
  var fillTable = function (table, data, columns, pager) {
    var tbody = initTableStructure(table, columns);
    var rows = data.rows;
    for (var i = 0; i < rows.length; i++) {
      tbody.append('<tr id="tr_' + i + '"></tr>');
      var newTr = $('#tr_' + i);
      for (var j = 0; j < columns.length; j++) {
        newTr.append('<td data-title="' + columns[j].title + '">' + rows[i][columns[j].filed] + '</td>');
      }
      newTr.append('<td id="td"' + j + ' class="pmd-table-row-action"></td>');
      var last = $('#tr_' + i + ' > td').last();
      last.append('<a href="javascript:userEdit(' + rows[i][columns[0].filed] + ');" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"><i class="material-icons md-dark pmd-sm">edit</i></a>');
      last.append('<a href="javascript:userDelete(' + rows[i][columns[0].filed] + ');" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"><i class="material-icons md-dark pmd-sm">delete</i></a>');
    }
    updatePageComponInfo(data, pager);
  };

  //更新翻页组件信息
  var updatePageComponInfo = function (data, pager) {
    pager.css({'display': 'table'});
    var currentPage = data.rows[0].page;
    var perRows = data.rows[0].rows;
    var recordsTotal = data.recordsTotal;
    var totalPage;
    var temp = recordsTotal % perRows;
    if (temp > 0) {
      totalPage = Math.floor(recordsTotal / perRows) + 1;
    } else {
      totalPage = Math.floor(recordsTotal / perRows);
    }
    var end;
    if (currentPage !== totalPage) {
      end = currentPage * perRows;
    } else {
      end = recordsTotal;
    }
    var pageStr = (currentPage - 1) * perRows + 1 + ' - ' + end + ' of ' + recordsTotal;
    var span = pager.find('span').last();
    span.attr("title", currentPage);
    span.attr("tabindex", totalPage);
    span.text(pageStr);
  };

  var getData = function (table, pager, columns, params, url) {
    //初始化表头
    var theadId = table[0].id + '_' + 'thead';
    var tbodyId = table[0].id + '_' + 'tbody'
    table.append('<thead id="' + theadId + '"><tr></tr></thead><tbody id="' + tbodyId + '"></tbody>');
    var tr = $('#' + theadId + ' > tr');
    for (var i = 0; i < columns.length; i++) {
      tr.append('<th data-title="' + columns[i].title + '">' + columns[i].title + '</th>');
    }
    tr.append('<th>操作</th>');
    $.post(url, params, function (data) {
      var currentPage = data.rows[0].page;
      var perRows = data.rows[0].rows;
      var pageStr = (currentPage - 1) * perRows + 1 + ' - ' + perRows * currentPage + ' of ' + data.recordsTotal;
      var rows = data.rows;
      var tbody = $('#' + tbodyId);
      for (var i = 0; i < rows.length; i++) {
        tbody.append('<tr id="tr_' + i + '"></tr>');
        var newTr = $('#tr_' + i);
        for (var j = 0; j < columns.length; j++) {
          newTr.append('<td data-title="' + columns[j].title + '">' + rows[i][columns[j].filed] + '</td>');
        }
        newTr.append('<td id="td"' + j + ' class="pmd-table-row-action"></td>');
        var last = $('#tr_' + i + ' > td').last();
        last.append('<a href="javascript:userEdit(' + rows[i][columns[0].filed] + ');" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"><i class="material-icons md-dark pmd-sm">edit</i></a>');
        last.append('<a href="javascript:userDelete(' + rows[i][columns[0].filed] + ');" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"><i class="material-icons md-dark pmd-sm">delete</i></a>');
      }
      //初始化翻页组件信息
      pager.text(pageStr);
      $(x).style.display=($(x).style.display=="none")?"":"none";
    }, 'json');
  };

});
