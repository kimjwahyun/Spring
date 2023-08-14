<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	#board table {
	    width:800px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	}
	
	#board table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#board table th {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#board table td {
	    text-align:left;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	.odd {background:silver}
	.note-btn-group{width: auto;}
   	.note-toolbar{width: auto;}
</style>
<script type="text/javascript">
	function sendData(f) {
		if(f.title.value.trim().length <=0){
			alert("제목을 입력하세요");
			f.title.focus();
			return;
		}
		if(f.writer.value.trim().length <=0){
			alert("작성자를 입력하세요");
			f.writer.focus();
			return;
		}
		if(f.content.value.trim().length <=0){
			alert("내용을 입력하세요");
			f.content.focus();
			return;
		}
		if(f.pwd.value.trim().length <=0){
			alert("비밀번호를 입력하세요");
			f.pwd.focus();
			return;
		}
	
		f.action="/board_ans_insert.do";
		f.submit();
	}
	function list_go(f) {
		f.action="/board_list.do";
		f.submit();
	}
</script>
</head>
<body>
	<div id="board">
	<form method="post" encType="multipart/form-data">
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td><input type="text" name="title" size="45"/></td>
				</tr>
				<tr>
					<th>이름:</th>
					<td><input type="text" name="writer" size="12"/></td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><textarea name="content" cols="50" 
							rows="8" id="content"></textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<td><input type="file" name="file"/></td>
				</tr>
				<tr>
					<th>비밀번호:</th>
					<td><input type="password" name="pwd" size="12"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="button" value="답글입력" onclick="sendData(this.form)"/>
						<input type="reset" value="다시"/>
						<input type="button" value="목록" onclick="list_go(this.form)"/>
						<input type="hidden" name="cPage" value="${cPage}">
						<input type="hidden" name="idx" value="${idx}">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
       <script src="resources/js/summernote-lite.js"></script>
       <script src="resources/js/lang/summernote-ko-KR.js"></script>
       <script type="text/javascript">
       $(function(){
          $('#content').summernote({
             lang : 'ko-KR',
             height : 300,
             focus : true,
             callbacks : {
                onImageUpload :  function(files, editor){
                   for (var i = 0; i < files.length; i++) {
                     sendImage(files[i], editor);
                  }
                }
             }
         });
          $("#content").summernote("lineHeight",.7);
       });
       function sendImage(file, editor) {
         var frm = new FormData();
         frm.append("s_file",file);
         $.ajax({
            url : "/saveImage.do",
            data : frm,
            type : "post",
            contentType : false,
            processData : false,
            dataType : "json",
         }).done(function(data) {
            var path = data.path;
            var fname = data.fname;
            $("#content").summernote("editor.insertImage",path+"/"+fname);
         });
      }
  	</script>
</body>
</html>

