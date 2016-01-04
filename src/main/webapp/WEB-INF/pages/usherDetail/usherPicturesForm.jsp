<%--
  Created by IntelliJ IDEA.
  User: Emerio
  Date: 12/19/2015
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="menu.user.setting.usherPictures"/></title>
    <meta name="menu" content="UserSettingMenu"/>
</head>

<div class="col-sm-2 main">
    <menu:useMenuDisplayer name="Velocity" config="navlistMenu.vm" permissions="rolesAdapter">
        <menu:displayMenu name="UserSettingMenu"/>
    </menu:useMenuDisplayer>
</div>

<div class="col-lg-8 col-md-8 main">
    <div class="panel panel-default">
        <div class="panel-body">
            <form:form commandName="usherDetail" method="post" action="usherPicturesForm" enctype="multipart/form-data"
                       id="form">
                <form:hidden path="id"/>
                <form:hidden path="version"/>
                <fieldset>
                    <legend><h2>Pictures</h2></legend>

                    <div class="form-group">
                        <label class="control-label">Upload Picture</label>
                        <input type="file" name="file" id="file"/>
                        <span class="help-block">
                            accepted type: <i>jpeg, jpg</i><br>
                            max size: 2 MB
                        </span>
                    </div>

                    <div class="form-group">
                        <button type="submit" name="upload" class="btn btn-primary" onclick="bCancel=false">
                            <i class="icon-upload icon-white"></i> <fmt:message key="button.upload"/>
                        </button>
                        <button type="submit" name="cancel" class="btn btn-default" onclick="bCancel=true">
                            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
                        </button>
                    </div>
                </fieldset>
            </form:form>
            <c:forEach items="${usherDetail.usherDetailPictures}" var="pic">
                <div class="col-lg-4 col-md-4">
                    <img class="img-responsive" src="http://localhost:8011/TripMates_pictures/${pic.url}">
                </div>
            </c:forEach>
        </div>
    </div>
</div>


