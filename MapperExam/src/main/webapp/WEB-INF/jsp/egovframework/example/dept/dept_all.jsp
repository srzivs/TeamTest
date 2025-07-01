<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!--    부트스트랩 css  -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
   <!--    개발자 css -->
   <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<!-- 머리말 -->
<jsp:include page="/common/header.jsp"></jsp:include>

${paginationInfo.totalPageCount} 총페이지수 <br> 
${paginationInfo.currentPageNo} 현재페이지 <br>
${paginationInfo.recordCountPerPage} 보이는 개수<br>

<!-- 본문 -->
<div class="page mt3">
   <form id="listForm" name="listForm" method="get">
   <!-- 수정페이지 열기때문에 필요 -->
   <input type="hidden" id="dno" name="dno">
   <!-- 컨트롤러로 보낼 페이지번호 -->
   <input type="hidden" id="pageIndex" name="pageIndex">
   
<!-- 검색어 컨트롤러 input 태그의 name 속성 이용 -->
      <div class="input-group mb3 mt3">
        <input type="text" 
               class="form-control" 
               id="searchKeyword"
               name="searchKeyword"
               placeholder="부서명입력"
        >
        <button class="btn btn-primary" 
                type="button" 
                onclick="fn_egov_selectList()"
        >
                검색
        </button>
      </div>
       <div id="result"></div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">dno</th>
            <th scope="col">dname</th>
            <th scope="col">loc</th>
          </tr>
        </thead>
        <tbody>
        <!-- 반복문 -->
        <c:forEach var="data" items="${depts}">
        	<tr>
               <td><a href="javascript:fn_select('<c:out value="${data.dno}" />')">
               <c:out value="${data.dno}"/></a></td>
               <td><c:out value="${data.dname}"/></td>
               <td><c:out value="${data.loc}"/></td>
             </tr>
        </c:forEach>
        </tbody>
      </table>
      <!-- 페이지 번호 -->
      <div class="flex-center"><ul class="pagination" id="pagination"></ul></div>
      
   </form>
</div>


<!-- 꼬리말 -->
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- 페이징 라이브러리 -->
<script src="/js/jquery.twbsPagination.js" type="text/javascript"></script>

<script type="text/javascript">
//페이지번호 클릭시 전체조회
	function fn_egov_link_page(pageNo) {
	/* 현재페이지번호저장 */
		$("#pageIndex").val(pageNo);
		$("#listForm").attr("action",'<c:out value="/dept/dept.do" />')
		.submit();
	}
//전체조회
	function fn_egov_selectList() {
		$("#pageIndex").val(1); //최초화면에서는 페이지 번호를 1페이지 지정
		$("#listForm").attr("action",'<c:out value="/dept/dept.do" />')
		.submit();
	}
//수정페이지 열기
	function fn_select(dno) {
		$("#dno").val(dno); //부서번호 기본키
		/* 수정페이지 url */
		$("#listForm").attr("action",'<c:out value="/dept/edition.do" />')
		.submit();
	}
</script>

<script type="text/javascript">
/* 페이징 처리 */
$('#pagination').twbsPagination({
    totalPages: "${paginationInfo.totalPageCount}",
    startPage: parseInt("${paginationInfo.currentPageNo}"),
    visiblePages: "${paginationInfo.recordCountPerPage}",
    initiateStartPageClick: false,
    onPageClick: function (event, page) {
    /* 재조회함수 실행 */
    	fn_egov_link_page(page)
    }
});
</script>

<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>