<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="mobile" class="com.inno.pojo.Mobile" />
    <c:set target="${mobile}" property="id" value="0" />
    <!--jsp:setProperty name="mobile" property="price" value="0" /-->


<h1>Edit mobile</h1>
<form method="post" action="${pageContext.request.contextPath}/editmobile" autocomplete="off">
    <div class="form-group">
        <label for="id">IdModel</label>
        <input name="id" type="text" class="form-control" id="id" value="<jsp:getProperty name="mobile" property="id" />">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

