<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AD_srouce_page</title>
    <style>
        body {
            width: 1070px;
            height: 180px;
            background-color: powderblue;
            margin: 0px;
            padding: 0px;
        }
        img {
            width: 1070px;
            height: 200px;
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
    <a id="AD_link" href="" target="_parent">
        <img src="" alt="AD_view" id="AD_view">
    </a>
    <a id="event_0" href="event_selectOne.do?img_link=event_0.jpg&event_name=Golf%20Party">
	    <img src="CSS/AD/event_0.jpg" alt="AD">
    </a>
    <a id="event_1" href="event_selectOne.do?img_link=event_1.jpg&event_name=DRIVE%20FOR%20DIAGNOSIS">
	    <img src="CSS/AD/event_1.jpg" alt="AD">
    </a>
    <a id="event_2" href="event_selectOne.do?img_link=event_2.jpg&event_name=Golf_Event">
	    <img  src="CSS/AD/event_2.jpg" alt="AD">
    </a>
    <script>
        const ad_number = Math.floor(Math.random() * 3);
        console.log(ad_number);
        var img_view = "CSS/AD/event_"+ad_number+".jpg";
        console.log(img_view);
        document.getElementById("AD_view").setAttribute('src',img_view);
        document.getElementById("AD_link").setAttribute('href',document.getElementById("event_"+ad_number).href);
        console.log(document.getElementById("event_"+ad_number).href);
    </script>
</body>
</html>