<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!--    부트스트랩 css  -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
   <!--    개발자 css -->
   <link rel="stylesheet" href="/css/style.css">
    
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<div class="page mt3">
<form id="addForm" name="addForm" method="post">
<input type="hidden" name="csrf" value="${sessionScope.CSRF_TOKEN}">
<input type="hidden" name="dno" value="<c:out value='${deptVO.dno}' />">
        <div class="mb3">
            <label for="dname" class="form-label">dname</label>
            <input 
                   class="form-control"
                   id="dname"
                   name="dname"
                   value="<c:out value='${deptVO.dname}' />"
                   placeholder="dname" />
        </div>
        <div class="mb3">
            <label for="loc" class="form-label">loc</label>
            <input 
                   class="form-control"
                   id="loc"
                   name="loc"
                   value="<c:out value='${deptVO.loc}' />"
                   placeholder="loc" />
        </div>
        <div class="mb3">
            <button class="btn btn-warning" onclick="fn_save()">수정</button>
                        
            <button class="btn btn-danger" onclick="fn_delete()">삭제</button>
        </div>
    </form>
</div>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- 유효성체크 플러그인 -->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.21.0/dist/jquery.validate.min.js"></script>
<script src="/js/dept/dept-validation-config.js"></script>


<script type="text/javascript">
	function fn_save() {
		$("#addForm").attr("action",'<c:out value="/dept/edit.do" />')
		.submit();
	}
	function fn_delete() {
		$("#addForm").attr("action",'<c:out value="/dept/delete.do" />')
		.submit();
	}
	
	
</script>


<jsp:include page="/common/footer.jsp"/>
</body>
</html>
