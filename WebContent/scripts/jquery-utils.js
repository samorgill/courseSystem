/**
 * @author Samuel Orgill 15118305
 * @version 5, 25/3/2016
 */

/*JQuery to display all courses in the database*/

$(document).ready(function(){
	
	$("#allCourses").click(function(){
		
		var format = $("#format").val();
	
	$.ajax({
		url: 'getAllCourses',
		type: 'GET',
		dataType: 'text',		
		data: {format: format},
		
		success: function(data) {
			$('#resultScreen').empty();
			if(format === 'json'){JSONfunction(data)}
			if(format === 'xml'){XMLfunction(data)}
			else if (format === 'text') {TextFunction(data)}
		}});
	});
	
});


/*JQuery to search for a particular course in the database*/
$(document).ready(function(){
	$("#searchCourses").click(function(){
		var course = $("#course").val();
		var format2 = $("#format2").val();
	
		$.ajax({
			url: 'getCourse?coursename=' + course,
			type: 'GET',
			data: {format: format2},
			dataType: 'text',
			success: function(data){
				$('#resultScreen').empty();
				if(format2 === 'json'){JSONfunction(data)}
				if(format2 === 'xml'){XMLfunction(data)}
				else if (format2 === 'text') {TextFunction(data)}
				
			}
		})
	
	})
	
})

/*JQuery for inserting a course into the database*/
$(document).ready(function(){
	$("#insertCourseBtn").click(function(){
		var courseID = $("#courseid").val();
		var courseName = $("#coursename").val();
		var ftOrPt = $("#ftorpt").val();
		var courseCredits = $("#coursecredits").val();
		var courseDuration = $("#courseduration").val();
		var tutorID = $("#tutorid").val();
		var courseTutor = $("#coursetutor").val();
		
		var format3 = $("#format3").val();
	
		$.ajax({
			url: 'insertCourse?courseid=' + courseID + "&coursename=" + courseName + "&ftorpt=" + ftOrPt +
			"&coursecredits=" + courseCredits + "&courseduration=" + courseDuration + "&tutorid=" + 
			tutorID + "&coursetutor=" + courseTutor,
			type: 'POST',
			data: {format: format3},
			dataType: 'text',
			success: function(data){
				
				$('#resultScreen').empty();
				
				if(format3 === 'json'){JSONinsert(data)}
				if(format3 === 'xml'){XMLfunction(data)}
				else if (format3 === 'text') {TextFunction(data)}
				
				alert("Course entered.")
				
			}
		})
	
	})
	
})

/* A function to format JSON data */
function JSONfunction(data){
	var tr;		
	myData = $.parseJSON(data);
	for(var i = 0; i < myData.length; i++){
	tr = $('<tr/>');
	tr.append("<td>" + myData[i].courseID + " " + 
			myData[i].courseName + " " +
			myData[i].fullOrPartTime + " " +
			myData[i].courseCredits + " " +
			" " + myData[i].courseDuration + 
			" " + myData[i].tutorID + " " +
			myData[i].courseTutor + "</td>");
	$('#resultScreen').append(tr);

	}
	
}

/* a function to format JSON data where it is being inserted into the database*/
function JSONinsert(data){
		

	var tr;		
	myData = $.parseJSON(data);
	tr = $('<tr/>');
	tr.append('<td>');
	tr.append(myData.courseID);
	tr.append(" ");
	tr.append(myData.courseName);
	tr.append(" ");
	tr.append(myData.fullOrPartTime);
	tr.append(" ");
	tr.append(myData.courseCredits);
	tr.append(" ");
	tr.append(myData.courseDuration);
	tr.append(" ");
	tr.append(myData.tutorID);
	tr.append(" ");
	tr.append(myData.courseTutor);
	tr.append('</td>');
	
	$('#resultScreen').append(tr);
	
}

/* A function to format the XML data */
function XMLfunction(data){

	
	xml = $.parseXML(data),
	$data = $(xml),
	courseID = $data.find('courseID');
	courseName =$data.find('courseName');
	ftOrPt = $data.find('fullOrPartTime');
	courseCredits = $data.find('courseCredits');
	courseDuration = $data.find('courseDuration');
	tutorID = $data.find('tutorID');
	courseTutor = $data.find('courseTutor');
	
	for(var j = 0; j < courseID.length; j++){
		tr = $('<tr/>');
		tr.append('<td>');
		tr.append(courseID[j]);
		tr.append(" ");
		tr.append(courseName[j]);
		tr.append(" ");
		tr.append(ftOrPt[j]);
		tr.append(" ");
		tr.append(courseCredits[j]);
		tr.append(" ");
		tr.append(courseDuration[j]);
		tr.append(" ");
		tr.append(tutorID[j]);
		tr.append(" ");
		tr.append(courseTutor[j]);
		tr.append('</td>');
		
	$('#resultScreen').append(tr);
	}
	
}

/* A function to format plain text data */
function TextFunction(data){

	var tr;
	var text = data;
	
	var array = new Array();
	
	array = text.split(",");
	
	for(var i = 0; i < array.length; i++){
	
		tr = $('<tr/>');
		tr.append("<td>" + array[i] + "</td>");
		$('#resultScreen').append(tr);
	
	}
	
}

