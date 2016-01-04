<%--
  Created by IntelliJ IDEA.
  User: Emerio
  Date: 12/17/2015
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title>Usher Detail Form</title>
    <meta name="menu" content="UserSettingMenu"/>
</head>

<div class="col-sm-2 main">
    <menu:useMenuDisplayer name="Velocity" config="navlistMenu.vm" permissions="rolesAdapter">
        <menu:displayMenu name="UserSettingMenu"/>
    </menu:useMenuDisplayer>
    <%--<c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose>--%>
</div>

<div class="col-lg-8 col-md-8 main">
    <div class="panel panel-default">
        <div class="panel-body">
            <form:form commandName="usherDetail" method="post" action="usherDetailForm" id="userForm" autocomplete="off">
                <form:hidden path="id"/>
                <form:hidden path="version"/>
                <fieldset>
                    <label><h2>Usher Info</h2></label>

                    <div class="form-group">
                        <label class="control-label">First Name</label>
                        <form:input path="firstName" cssClass="form-control" />
                    </div>

                    <div class="form-group">
                        <label class="control-label">Last Name</label>
                        <form:input path="lastName" cssClass="form-control" />
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 col-lg-6">
                            <label class="control-label">Location</label>
                            <form:input path="location" cssClass="form-control" />
                        </div>
                        <div class="form-group col-md-4 col-lg-4">
                            <label class="control-label">Phone No</label>
                            <form:input path="phoneNo" cssClass="form-control" />
                            <span class="help-block">
                                ex: +6285782660322
                            </span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-lg-6 col-md-6">
                            <label class="control-label">Last Education</label>
                            <form:input path="lastEducation" cssClass="form-control" />
                        </div>

                        <div class="form-group col-lg-6 col-md-6">
                            <label class="control-label">Occupation</label>
                            <form:input path="occupation" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-3 col-lg-3">
                            <label class="control-label">Gender</label>
                            <select id="gender" name="gender" class="form-control">
                                <c:forEach items="${genderList}" var="type">
                                    <option value="${type}" ${fn:contains(usherDetail.gender, type) ? 'selected' : ''}>${type}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-6 col-lg-6">
                            <label class="control-label">DOB</label>
                            <form:input path="dob" cssClass="form-control" />
                        </div>
                        <div class="form-group col-md-3 col-lg-3">
                            <label class="control-label">Age</label>
                            <form:input path="age" cssClass="form-control" />
                            <span class="help-block">
                                Years old
                            </span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-3 col-lg-3">
                            <label class="control-label">Height</label>
                            <form:input path="height" cssClass="form-control" />
                            <span class="help-block">
                                cm
                            </span>
                        </div>
                        <div class="form-group col-md-3 col-lg-3">
                            <label class="control-label">Weight</label>
                            <form:input path="weight" cssClass="form-control" />
                            <span class="help-block">
                                Kg
                            </span>
                        </div>
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
