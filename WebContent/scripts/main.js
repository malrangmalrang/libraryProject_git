$(document).ready(function(){
	
/******************** 톱니바퀴 ************************/	
  $("#gear_a").mouseover(function(){
    $("#gear").addClass("fa-spin");
  });
  
  $("#gear_a").mouseout(function() {
	  $("#gear").removeClass("fa-spin");
  });
/******************** 톱니바퀴 ************************/
  
/******************** 메인메뉴 열고 닫기 ************************/
  $(".main-menu").click(function(){
	  if($(this).next().css("display") == "none") {
		  $(this).css("background-color", "rgb(200, 200, 200)");
	  }
	  if($(this).next().css("display") == "block") {
		  $(this).css("background-color", "white");
	  }
	  $(this).next().toggle('slow');
  });
/******************** 메인메뉴 열고 닫기 ************************/
});

/******************** 공지사항 ************************/

/******************** 공지사항 ************************/

/******************** 로그인, 회원가입 팝업창 열고 닫기 **********************/
function loginpopup() {
	document.getElementById('loginpop').style.display='block';
	document.getElementById('joinpop').style.display='none';
} 

function joinpopup() {
	document.getElementById('loginpop').style.display='none';
	document.getElementById('joinpop').style.display='block';
} 
/******************** 로그인, 회원가입 팝업창 열고 닫기 **********************/

/******************** 메뉴 버튼 **********************/
function w3_open() {
  var mySidebar = document.getElementById("mySidebar");
  var overlayBg = document.getElementById("myOverlay");
  
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

function w3_close() {
	  var mySidebar = document.getElementById("mySidebar");
	  var overlayBg = document.getElementById("myOverlay");
	  
	  mySidebar.style.display = "none";
	  overlayBg.style.display = "none";
}
/******************** 메뉴 버튼 **********************/

/******************** 좋아요 버튼 *********************/
function likeFunction(x) {
	if(x.value == "unlike") {
		x.value = "like";
		x.style.fontWeight = "bold";
		x.innerHTML = "✓ Liked";
	} else {
		x.value = "unlike";
		x.style.fontWeight = "normal";
		x.innerHTML = "<b><i class='fa fa-thumbs-up'></i> Like</b>";
	}
}
/******************** 좋아요 버튼 *********************/