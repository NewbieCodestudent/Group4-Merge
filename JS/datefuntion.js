// 년도, 월 selectBox source
$(document).ready(function(){
  setYearBox();
  setMonthBox();
  lastday();        
})
function setYearBox() {
  var dt = new Date();
  com_year=dt.getFullYear();
  // 올해 기분으로 -50년부터 +1년까지 출력
  for (var i =(com_year - 50); i <=(com_year); i++){
    $("#year").append("<option value'"+i+"'>"+i+"</option>");
  }  
}
function setMonthBox() {
  var com_month=1;
  // 올해 기분으로 -50년부터 +1년까지 출력
  for (var i =1; i<=12; i++){
    $("#month").append("<option value'"+i+"'>"+i+"</option>");
  }  
}
function lastday(){
  $("#day option").remove();
  var year=document.getElementById('year').value;
  var month=document.getElementById('month').value;
  var day=new Date(new Date(year, month, 1)-86400000).getDate();
  // new Date(new Date(Year, Month, 0)).getDate();
  var dayindex_len=document.getElementById('day').length;
  console.log(day);
  console.log(dayindex_len);
  for(var i=1; i<=day; i++){
    $("#day").append("<option value'"+i+"'>"+i+"</option>");
  }
}