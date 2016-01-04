<%--
  Created by IntelliJ IDEA.
  User: Dendy
  Date: 03/01/2016
  Time: 15.19
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <title>Client Form</title>
    <meta name="menu" content="ClientPersonFormMenu"/>
</head>

<div class="col-lg-2 col-md-2 main">
    <h2>Create Person</h2>
</div>>

<div class="col-lg-8 col-md-8 main">
    <div class="panel panel-default">
        <div class="panel-body">
            <form:form commandName="clientPerson" method="post" action="clientPersonForm" id="clientPersonForm"
                       autocomplete="off">
                <form:hidden path="id"/>
                <form:hidden path="version"/>
                <input type="hidden" name="c" value="${param.c}">

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
                        <form:input path="phoneNumber" cssClass="form-control"/>
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