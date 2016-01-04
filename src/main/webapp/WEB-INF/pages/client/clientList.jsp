<%--
  Created by IntelliJ IDEA.
  User: Dendy
  Date: 02/01/2016
  Time: 12.46
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title>Client List</title>
    <meta name="menu" content="AdminMenu"/>
</head>


<div class="col-md-10 main col-lg-10">
    <div class="panel panel-default">
        <div class="panel-body">
            <form method="get" action="${ctx}/clients" id="searchForm" class="form-horizontal">
                <fieldset>
                    <legend>
                        <h2>Client List</h2>
                    </legend>
                    <div class="form-group">
                        <label class="control-label col-lg-2 col-md-2">
                            <fmt:message key="button.search"/>
                        </label>
                        <div class="col-lg-8 col-md-8">
                            <input type="text" size="20" name="q" id="query" value="${param.q}"
                                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm">
                        </div>
                        <div class="col-lg-8 col-lg-offset-2">
                            <a class="btn btn-primary" href="<c:url value='/clientForm?method=Add&from=list'/>">
                                <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

                            <a class="btn btn-flat btn-default" href="<c:url value='/home'/>">
                                <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
                        </div>
                    </div>
                </fieldset>

            </form>

            <display:table name="clientList" cellspacing="0" cellpadding="0" requestURI=""
                           defaultsort="1" id="clients" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
                <display:column property="name" escapeXml="true" sortable="true" titleKey="user.username" style="width: 25%"
                                url="/clientForm?from=list" paramId="id" paramProperty="id"/>

                <display:setProperty name="paging.banner.item_name"><fmt:message key="userList.user"/></display:setProperty>
                <display:setProperty name="paging.banner.items_name"><fmt:message key="userList.users"/></display:setProperty>

                <display:setProperty name="export.excel.filename" value="Client List.xls"/>
                <display:setProperty name="export.csv.filename" value="Client List.csv"/>
                <display:setProperty name="export.pdf.filename" value="Client List.pdf"/>
            </display:table>
        </div>
    </div>
</div>

<c:set var="scripts" scope="request">
    <script type="text/javascript">
        $(function() {
            $()
            $("#query").keypress(function(e) {
                if(e.which == 13){
                    var firstChar = $(this).val().charAt(0);
                    if (firstChar == '*' || firstChar == '?') {
                        alert("'*' or '?' not allowed as first character in WildcardQuery")
                    } else {
                        $("#searchForm").submit();
                    }
                }
            });
        });
    </script>
</c:set>


