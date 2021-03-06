<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserSettingMenu"/>
</head>

<body id="generalAccountSettings">
    <c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
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
    <div class="col-md-8 col-lg-8 main">
        <spring:bind path="user.*">
            <c:if test="${not empty status.errorMessages}">
                <div class="alert alert-danger alert-dismissable">
                    <a href="#" data-dismiss="alert" class="close">&times;</a>
                    <c:forEach var="error" items="${status.errorMessages}">
                        <c:out value="${error}" escapeXml="false"/><br/>
                    </c:forEach>
                </div>
            </c:if>
        </spring:bind>

        <form:form commandName="user" method="post" action="userform" id="userForm" autocomplete="off"
                   cssClass="well" onsubmit="return validateUser(this)">
            <form:hidden path="id"/>
            <form:hidden path="version"/>
            <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>
            <fieldset>
                <legend>
                    <h2>General Account Settings</h2>
                </legend>

                <spring:bind path="user.username">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                    <appfuse:label styleClass="control-label" key="user.username"/>
                    <form:input cssClass="form-control" path="username" id="username"/>
                    <form:errors path="username" cssClass="help-block"/>
                    <c:if test="${pageContext.request.remoteUser == user.username}">
                    <span class="help-block">
                        <a href="<c:url value="/updatePassword" />"><fmt:message key='updatePassword.changePasswordLink'/></a>
                    </span>
                    </c:if>
                </div>

                <spring:bind path="user.passwordHint">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                    <appfuse:label styleClass="control-label" key="user.passwordHint"/>
                    <form:input cssClass="form-control" path="passwordHint" id="passwordHint"/>
                    <form:errors path="passwordHint" cssClass="help-block"/>
                </div>

                <div class="row">
                    <spring:bind path="user.email">
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label styleClass="control-label" key="user.email"/>
                        <form:input cssClass="form-control" path="email" id="email"/>
                        <form:errors path="email" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label">Account Type</label>
                    <select id="accountTypes" name="accountType" class="form-control">
                        <c:forEach items="${accountTypeList}" var="type">
                            <option value="${type}" ${fn:contains(user.accountType, type) ? 'selected' : ''}>${type}</option>
                        </c:forEach>
                    </select>
                </div>

                <c:choose>
                    <c:when test="${param.from == 'list' or param.method == 'Add'}">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="userProfile.accountSettings"/></label>
                            <label class="checkbox-inline">
                                <form:checkbox path="enabled" id="enabled"/>
                                <fmt:message key="user.enabled"/>
                            </label>

                            <label class="checkbox-inline">
                                <form:checkbox path="accountExpired" id="accountExpired"/>
                                <fmt:message key="user.accountExpired"/>
                            </label>

                            <label class="checkbox-inline">
                                <form:checkbox path="accountLocked" id="accountLocked"/>
                                <fmt:message key="user.accountLocked"/>
                            </label>

                            <label class="checkbox-inline">
                                <form:checkbox path="credentialsExpired" id="credentialsExpired"/>
                                <fmt:message key="user.credentialsExpired"/>
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="userRoles" class="control-label"><fmt:message key="userProfile.assignRoles"/></label>
                            <select id="userRoles" name="userRoles" multiple="true" class="form-control">
                                <c:forEach items="${availableRoles}" var="role">
                                    <option value="${role.value}" ${fn:contains(user.roles, role.label) ? 'selected' : ''}>${role.label}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:when>
                    <c:when test="${not empty user.username}">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="user.roles"/>:</label>
                            <div class="readonly">
                                <c:forEach var="role" items="${user.roleList}" varStatus="status">
                                    <c:out value="${role.label}"/><c:if test="${!status.last}">,</c:if>
                                    <input type="hidden" name="userRoles" value="<c:out value="${role.label}"/>"/>
                                </c:forEach>
                            </div>
                            <form:hidden path="enabled"/>
                            <form:hidden path="accountExpired"/>
                            <form:hidden path="accountLocked"/>
                            <form:hidden path="credentialsExpired"/>
                        </div>
                    </c:when>
                </c:choose>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                        <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
                    </button>

                    <c:if test="${param.from == 'list' and param.method != 'Add'}">
                        <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                            <i class="icon-trash"></i> <fmt:message key="button.delete"/>
                        </button>
                    </c:if>

                    <button type="submit" class="btn btn-flat btn-default" name="cancel" onclick="bCancel=true">
                        <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
                    </button>
                </div>
            </fieldset>

        </form:form>
    </div>
</body>



<c:set var="scripts" scope="request">
    <script type="text/javascript">
        var msgDelConfirm =
                "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
    // This is here so we can exclude the selectAll call when roles is hidden
    function onFormSubmit(theForm) {
        return validateUser(theForm);
    }
    </script>
</c:set>

<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

