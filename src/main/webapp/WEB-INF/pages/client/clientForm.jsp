<%--
  Created by IntelliJ IDEA.
  User: Dendy
  Date: 02/01/2016
  Time: 12.42
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <title>Client Form</title>
    <meta name="menu" content="ClientFormMenu"/>
</head>

<div class="col-sm-2 main">
    <ul class="nav nav-pills nav-stacked">
        <li class="nav-header">
            Client Setting
        </li>
        <li class="active">
            <a href="/admin/clientForm" title="General">General</a>
        </li>
        <%-- if on update and has ID --%>
        <c:if test="${not empty param.id}">
            <li>
                <%-- c => client_id --%>
                <a href="/admin/clientPersons?c=${param.id}" title="View Persons">View Persons</a>
            </li>
        </c:if>
    </ul>
</div>

<div class="col-lg-8 col-md-8 main">
    <div class="panel panel-default">
        <div class="panel-body">
            <form:form commandName="client" method="post" action="clientForm" id="clientForm"
                       autocomplete="off">
                <form:hidden path="id"/>
                <form:hidden path="version"/>

                <fieldset>
                    <div class="form-group">
                        <label class="control-label">
                            Name
                        </label>
                        <form:input path="name" cssClass="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="control-label">
                            Phone no
                        </label>
                        <form:input path="phoneNo" cssClass="form-control"/>
                    </div>

                   <div class="form-group">
                       <label class="control-label">
                           Email
                       </label>
                       <form:input path="email" cssClass="form-control"/>
                   </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
                        </button>

                        <button type="submit" class="btn btn-flat btn-default" name="cancel" onclick="bCancel=true">
                            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
                        </button>
                    </div>
                </fieldset>
            </form:form>
        </div>
    </div>
</div>

<c:set var="scripts" scope="request">
    <script type="text/javascript">
        $(function() {


        });
    </script>
</c:set>