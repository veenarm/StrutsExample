<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html>
<head>
</head>
<body>
<h1><bean:message key="label.common.title" /></h1>

<html:messages id="err_name" property="common.file.err">
<div style="color:red">
	<bean:write name="err_name" />
</div>
</html:messages>
<html:messages id="err_name" property="common.file.err.ext">
<div style="color:red">
	<bean:write name="err_name" />
</div>
</html:messages>
<html:messages id="err_name" property="common.file.err.size">
<div style="color:red">
	<bean:write name="err_name" />
</div>
</html:messages>

<html:form action="/Upload" method="post" enctype="multipart/form-data">
<br />
	<bean:message key="label.common.file.label" /> : <html:file property="file" size="50" />
<br />

	<input type="hidden" name="multipartRequestHandler.servlet.servletContext.attribute(org.apache.struts.action.MODULE)" size="60" value="Field named to trash the default module!"/></br>
	<input type="hidden" name="multipartRequestHandler.servlet.servletContext.attribute(org.apache.struts.globals.MODULE_PREFIXES)" size="60" value="Trash the prefixes to other modules!"/></br>
	<input type="hidden" name="theText" value="ABCDEF"/>

<html:submit>
	<bean:message key="label.common.button.submit" />
</html:submit>

</html:form>


</body>
</html>