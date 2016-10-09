<%@page contentType="application/xml" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<courseList>
<c:forEach items="${courseList}" var="courseList">

<course>
<courseID>${courseList.courseID}</courseID>
<courseName>${courseList.courseName}</courseName>
<fullOrPartTime>${courseList.fullOrPartTime}</fullOrPartTime>
<courseCredits>${courseList.courseCredits}</courseCredits>
<courseDuration>${courseList.courseDuration}</courseDuration>
<tutorID>${courseList.tutorID}</tutorID>
<courseTutor>${courseList.courseTutor}</courseTutor>
</course>

</c:forEach>
</courseList>



