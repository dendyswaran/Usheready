<%--
  Created by IntelliJ IDEA.
  User: Emerio
  Date: 12/19/2015
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="menu.user.setting.usherSkill"/></title>
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
            <form:form commandName="usherDetail" method="post" action="usherSkillForm" enctype="multipart/form-data"
                onsubmit="return validateFileUpload(this)" id="form">
                <form:hidden path="id"/>
                <form:hidden path="version"/>
                <fieldset>
                    <legend><h2>Skill Info</h2>
                        <span>Update your skill</span>
                    </legend>

                    <div class="row">
                        <div class="form-group col-lg-4 col-md-4">
                            <label class="control-label">Upload CV</label>
                            <input type="file" name="file" id="file"/>
                        <span class="help-block">
                            accepted type: <i>pdf, doc, docx</i>
                        </span>
                        </div>

                        <div class="form-group col-lg-6 col-md-6">
                            <label class="control-label">Active CV</label>
                            <form:input path="cvUrl" cssClass="form-control" disabled="true" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label">Job Title</label>
                        <input type="text" class="form-control">
                    </div>

                    <div class="row">
                        <div class="form-group col-lg-5 col-md-5">
                            <label class="control-label">Start Date</label>
                            <input type="text" class="form-control">
                        </div>
                        <div class="form-group col-lg-5 col-md-5">
                            <label class="control-label">End Date</label>
                            <input type="text" class="form-control">
                        </div>
                    </div>



                    <div class="form-group">
                        <button type="submit" name="upload" class="btn btn-primary" onclick="bCancel=false">
                            <i class="icon-upload icon-white"></i> <fmt:message key="button.upload"/>
                        </button>
                        <button type="submit" name="cancel" class="btn btn-flat btn-default" onclick="bCancel=true">
                            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
                        </button>
                    </div>

                </fieldset>
            </form:form>
        </div>
    </div>
</div>
